package nutrifit;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.IOException;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Date;
   
public class ReadWriteController extends Database {  
     
	/*
	 * These three functions below implement the singleton design pattern.
	 */
	private static ReadWriteController instance = null;

    private ReadWriteController(){

    	}

    public static ReadWriteController getInstance() {
        if (instance == null) {
            instance = new ReadWriteController();
        }
        return instance;
    }
	

	/**
	 * This method creates a new healthInfoLog table if it doesn't already exist
	 */
    @Override
    public void createNewTable() {

        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS healthInfoLog (\n"
                + "	date date,\n"
                + "	meal char,\n"
                + "	foodID int,\n"
                + "	amountRatio double,\n"
                + " caloriesConsumed int,\n"
                + " fat double,\n"
                + " protein double,\n"
                + " carbohydrates double,\n"
                + " sugar double,\n"
                + " caloriesBurned int\n"
                + ");";
        
        try (Connection conn = super.connect();
                Statement stmt = conn.createStatement()) {
        	System.out.println("inside try");
            // create a new table
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    /**
     * This function is used to turn a java Date object into a String that can be used in an SQL query.
     * @param date, date that you would like to convert
     * @return String of the form YYYY-MM-DD
     */
    private String dateToString(Date date) {
    	Calendar calStart = Calendar.getInstance();
    	calStart.setTime(date);
    	
    	String monthStart = ""+(calStart.get(Calendar.MONTH)+1);
    	String dayStart=""+calStart.get(Calendar.DAY_OF_MONTH);
    	if ((calStart.get(Calendar.MONTH)+1)<10) {
    		monthStart = "0"+monthStart;
    	}
    	if(calStart.get(Calendar.DAY_OF_MONTH)<10){
    		dayStart = "0"+dayStart;
    	}
    	String output = calStart.get(Calendar.YEAR)+"-"+monthStart+"-"+dayStart;
    	
    	return output;
    }
    
    /**
     * This is the most of the data retrieval methods. Returns the total calories consumed, calories burned, protein consumed, fat consumed,
     * carbs consumed, and sugar consumed in the specified time period between start Date and end date.
     * @param startDate
     * @param endDate
     * @return HealthInfo object that contains the result
     * @throws Exception if startDate is later than endDate
     */
    public HealthInfo retrieveDataBetweenDates(Date startDate, Date endDate) throws Exception{
    	

    	long startDateInMS = startDate.getTime();
    	long endDateInMS = endDate.getTime();
    	
    	if (endDateInMS < startDateInMS) {
    		throw new Exception();
    	}

    	String dateStartForSql = dateToString(startDate);
    	
    	String dateEndForSql = dateToString(endDate);


    	String sql="SELECT SUM(caloriesConsumed), SUM(protein), SUM(fat), SUM(carbohydrates), SUM(sugar), SUM(caloriesBurned) FROM healthInfoLog WHERE date>='"+dateStartForSql+"' AND date<='"+dateEndForSql+"'";
    	
    	HealthInfo output = new HealthInfo();
    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
               // loop through the result set
    		 while(rs.next()) {
        		output.setCaloriesConsumed(rs.getInt("SUM(caloriesConsumed)"));
               	output.setProtein(rs.getDouble("SUM(protein)"));
            	output.setFat(rs.getDouble("SUM(fat)"));
            	output.setCarbohydrates(rs.getDouble("SUM(carbohydrates)"));
            	output.setSugar(rs.getDouble("SUM(sugar)"));
            	output.setCaloriesBurned(rs.getInt("SUM(caloriesBurned)"));
    		 } 
               
               
               conn.close();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
    	
    	return output;
    	
    }
    
    /**
     * This method returns a list of the top x nutrients consumed between the two dates and the amount of each. 
     * x, the amount of nutrients listed is specified by the user 
     * @param startDate
     * @param endDate
     * @param amountListed, the amount of different nutrients and their amounts listed.
     * @return an ArrayList of NutrientNameAndAmount objects that corresponds with the top x nutrients and their amounts
     * @throws Exception if startDate is later than endDate
     */
	public ArrayList<NutrientNameAndAmount> retrieveAdvancedDataBetweenDates(Date startDate, Date endDate, int amountListed) throws Exception{
	    	
	
	    	long startDateInMS = startDate.getTime();
	    	long endDateInMS = endDate.getTime();
	    	
	    	ArrayList<NutrientNameAndAmount> resultArrayList = new ArrayList<NutrientNameAndAmount>();
	    	
	    	if (endDateInMS < startDateInMS) {
	    		throw new Exception();
	    	}
	    	
	    	String dateStartForSql = dateToString(startDate);
	    	
	    	String dateEndForSql = dateToString(endDate);
	    	
	    	
	    	String sql="SELECT `nutrient name`.NutrientName AS NutrientName, (V.NutrientAmountSum / IF(STRCMP(`nutrient name`.NutrientUnit,\"mg\") = 0, 1000, 1) /  IF(STRCMP(`nutrient name`.NutrientUnit,\"µg\") = 0, 1000000, 1) / IF(STRCMP(`nutrient name`.NutrientUnit,\"IU\") = 0, 3333000, 1) /  IF(STRCMP(`nutrient name`.NutrientUnit,\"NE\") = 0, 1000, 1)) "
	    			+ "AS NutrientAmountSum FROM (SELECT nutrientID, SUM(NutrientAmount) AS NutrientAmountSum FROM (SELECT `nutrient amount`.foodID, `nutrient amount`.nutrientID, (`nutrient amount`.NutrientValue * healthinfolog.amountRatio) AS NutrientAmount FROM `nutrient amount` INNER JOIN healthinfolog ON `nutrient amount`.foodID = healthinfolog.foodID AND healthinfolog.date<='"+dateEndForSql+"' AND healthinfolog.date>='"+dateStartForSql+"')"
	    					+ " AS nutrients GROUP BY nutrientID) AS V, `nutrient name` WHERE `nutrient name`.NutrientID = V.NutrientID AND `nutrient name`.NutrientCode != 208 AND `nutrient name`.NutrientCode != 268 AND `nutrient name`.NutrientCode != 207 AND `nutrient name`.NutrientCode != 255 AND `nutrient name`.NutrientCode != 287 AND (`nutrient name`.NutrientCode < 210 OR `nutrient name`.NutrientCode > 214) AND "
	    					+ "(`nutrient name`.NutrientCode < 501 OR `nutrient name`.NutrientCode > 521) AND `nutrient name`.NutrientCode < 605 ORDER BY NutrientAmountSum DESC LIMIT "+amountListed;
	    	
	    	
	    	try (Connection conn = super.connect();
	                Statement stmt  = conn.createStatement();
	                ResultSet rs    = stmt.executeQuery(sql)){
	               
	               // loop through the result set
	    		 while(rs.next()) {
	    			 
	    			resultArrayList.add(new NutrientNameAndAmount(rs.getString("NutrientName"),rs.getDouble("NutrientAmountSum")));
	    		 } 
	               
	               
	               conn.close();
	           } catch (SQLException e) {
	               System.out.println(e.getMessage());
	           }
	    	
	    	for (int i =0; i<resultArrayList.size(); i++) {
	    		System.out.println(resultArrayList.get(i));
	    	}
	    	
	    	return resultArrayList;
	    	
	    }
	
	
	/**
	 * This method returns the amount of servings eaten in each food group (meat, dairy, fruits + veggies and grain) between the two dates
	 * @param startDate
	 * @param endDate
	 * @return an Arraylist of FoodAmountAndFoodGroup objects with the relevant data
	 * @throws Exception if startDate is later than endDate
	 */
	public ArrayList<FoodAmountAndFoodGroup> retrieveFoodGroupDataBetweenDates(Date startDate, Date endDate) throws Exception{
		long startDateInMS = startDate.getTime();
    	long endDateInMS = endDate.getTime();
    	
    	ArrayList<FoodAmountAndFoodGroup> resultArrayList = new ArrayList<FoodAmountAndFoodGroup>();
    	
    	if (endDateInMS < startDateInMS) {
    		throw new Exception();
    	}
    	String dateStartForSql = dateToString(startDate);
    	
    	String dateEndForSql = dateToString(endDate);
    	
    	String sql="SELECT SUM((healthinfolog.amountRatio * 100)) As Amount, `food group`.FoodGroupID FROM `food group`, `food name`, healthinfolog WHERE `food group`.FoodGroupID = `food name`.FoodGroupID AND `food name`.FoodID = healthinfolog.FoodID AND healthinfolog.date >= '"+dateStartForSql+"' AND healthinfolog.date <= '"+dateEndForSql+"' GROUP BY `food group`.FoodGroupID";
    	
    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
               // loop through the result set
    		 while(rs.next()) {
    			 
    			resultArrayList.add(new FoodAmountAndFoodGroup(rs.getDouble("Amount"),rs.getInt("FoodGroupID")));
    		 } 
               
               
               conn.close();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }

    	
    	for (int i =0; i<resultArrayList.size(); i++) {
    		System.out.println(resultArrayList.get(i));
    	}
    	
    	return resultArrayList;
    	
    }
   
	/**
	 * Retrieves daily caloric data (burned and consumed)
	 * @param startDate
	 * @param endDate
	 * @return ArrayList of DateAndCalories objects with relevant data.
	 * @throws Exception if startDate is later than endDate
	 */
	public ArrayList<DateAndCalories> retrieveCalorieDataBetweenDates(Date startDate, Date endDate) throws Exception{
		long startDateInMS = startDate.getTime();
    	long endDateInMS = endDate.getTime();
    	
    	ArrayList<DateAndCalories> resultArrayList = new ArrayList<DateAndCalories>();
    	
    	if (endDateInMS < startDateInMS) {
    		throw new Exception();
    	}
    	String dateStartForSql = dateToString(startDate);
    	
    	String dateEndForSql = dateToString(endDate);
    	
    	String sql="SELECT healthinfolog.date, SUM(healthinfolog.caloriesConsumed), SUM(healthinfolog.caloriesBurned) FROM healthinfolog WHERE date >= '"+dateStartForSql+"' AND date <= '"+dateEndForSql+"' GROUP BY healthinfolog.date ORDER BY date";
	
    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
               // loop through the result set
    		 while(rs.next()) {

    			 
    			resultArrayList.add(new DateAndCalories(rs.getDate("date"),rs.getInt("SUM(healthinfolog.caloriesConsumed)"), rs.getInt("SUM(healthinfolog.caloriesBurned)")));
    		 } 
               
               
               conn.close();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }

    	

    	
    	return resultArrayList;
    	
    }
	
	/**
	 * used to display a portion of the database to make sure it is working
	 * @return last entry in the healthInfoLog database
	 */
    public String debugDumpDatabase(){
        String sql = "SELECT * FROM healthInfoLog";
        String output = "";
        try (Connection conn = super.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	output =rs.getDate("date") +  "\t" + 
                        rs.getString("meal") + "\t" +
                        rs.getInt("foodID") + "\t" +
                        rs.getDouble("amountRatio") + "\t" +
                        rs.getInt("caloriesConsumed")+ "\t" +
                        rs.getDouble("protein")+ "\t" +
                        rs.getDouble("fat")+ "\t" +
                        rs.getDouble("carbohydrates")+ "\t" +
                        rs.getDouble("sugar")+ "\t" +
                        rs.getInt("caloriesBurned")+"\n";
            }
            
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return output;
    }
    
    
    /**
     * This function checks database if a meal for a given day and given meal type has alrady been entered. For example
     * if a user has already entered a breakfast for 2020-01-01 then doesMealExist('b','2020-01-01) will return true.
     * @param mealType, is the meal type (breakfast, lunch, dinner) of the meal that is to be checked
     * @param date, the date when the meal took place.
     * @return true if meal has already been entered and false if not.
     */
    public boolean doesMealExist(char mealType, Date date) {
    	if (mealType == 's' || mealType == 'n') {
    		return false;
    	}
    	System.out.println(date.toString());
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	System.out.println("cal"+cal.get(Calendar.YEAR)+","+cal.get(Calendar.MONTH)+","+cal.get(Calendar.DAY_OF_MONTH));
    	
    	String month = ""+(cal.get(Calendar.MONTH)+1);
    	String day=""+cal.get(Calendar.DAY_OF_MONTH);
    	if ((cal.get(Calendar.MONTH)+1)<10) {
    		month = "0"+month;
    	}
    	if(cal.get(Calendar.DAY_OF_MONTH)<10){
    		day = "0"+day;
    	}
    	String dateForSql = cal.get(Calendar.YEAR)+"-"+month+"-"+day;
    	String sql = "SELECT COUNT(*) FROM healthinfolog WHERE date = '"+dateForSql+"' AND meal ='"+mealType+"'"; 
    	 try (Connection conn = super.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){
                
 		 		rs.next();
    		 	System.out.println(rs.getInt(1)+ " "+mealType+" "+dateForSql);

            	if (rs.getInt(1)!=0) {
            		
            		return true;
                }

                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    	 
    	 	return false;
    }
    

    /**
     * This method searches the food names database for the string the user entered into the search bar.
     * @param search that user entered
     * @return list of food names that contain user entered string
     */
    public String[] searchFoodNames(String search) {
    	ArrayList<String> list = new ArrayList<String>();
    	
    	String sql = "SELECT `food name`.FoodDescription FROM `food name` WHERE FoodDescription LIKE '%"+search+"%';"; 
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
    		while (rs.next()) {
    			list.add(rs.getString("FoodDescription"));
            	
    		}

    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	String[] output = new String[list.size()];
    	for(int i=0; i<list.size();i++) {
    		output[i] = list.get(i);
    	}
    	return output;
    }
    
    /**
     * the FoodID of a food with a given name
     * @param foodName the name of the food
     * @return its foodID
     */
    public int IDOfAGivenFood(String foodName) {
    	String output = "";
    	String sql = "SELECT FoodID FROM `food name` WHERE FoodDescription='"+foodName+"'";

    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
              
    		rs.next();

    		output = rs.getString("FoodID");
    		
    		System.out.println("in try conn for IDoffood()");
    		conn.close();
    		
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	
    	return Integer.parseInt(output);
    	
    }
    
    /**
     * stores exercise into healthInfoLog table
     * @param caloriesBurned
     * @param date
     * @throws Exception
     */
    public void storeExercise(int caloriesBurned, Date date) throws Exception {
    	
        
        HealthInfo parameter = new HealthInfo(date,'n',0,0,0,0.0,0.0,0.0,0.0, caloriesBurned);
        storeIntoHealthLogTable(parameter);
        
    }
    
    /**
     * stores meal into healthInfoLog table
     * @param mealChar
     * @param foodID
     * @param amount
     * @param date
     * @throws Exception
     */
    public void storeMeal(char mealChar, int foodID, int amount, Date date) throws Exception {
        
        
        long dateInMS = date.getTime();
        System.out.println(dateInMS);
        
        
        
    	
    	String sql = "SELECT NutrientValue FROM `nutrient amount` WHERE FoodID="+foodID+" AND (NutrientID=203 OR NutrientID=208 OR NutrientID=204 OR NutrientID = 205 OR NutrientID= 269);";
        
    	int caloriesConsumed=0;
    	double protein=0;
    	double fat=0;
    	double carbohydrates=0;
    	double sugar=0;
    	
    	double ratioToHundredGrams = amount/100.0;
    	
    	int i =0;
    	
        try (Connection conn = super.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {

            	if (i==0) {
            		protein = rs.getDouble("NutrientValue") * ratioToHundredGrams;
            	}
            	else if (i==1) {
            		fat = rs.getDouble("NutrientValue") * ratioToHundredGrams;
            	}
            	else if (i == 2) {
            		carbohydrates = rs.getDouble("NutrientValue") * ratioToHundredGrams;
            	}
            	else if (i==3) {
            		caloriesConsumed = (int) (Math.round(rs.getInt("NutrientValue") * ratioToHundredGrams));
            	}
            	else{
            		sugar = rs.getDouble("NutrientValue") * ratioToHundredGrams;
            	}
            	i++;
            	
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        HealthInfo parameter = new HealthInfo(date,mealChar,foodID, ratioToHundredGrams,caloriesConsumed,protein,fat,carbohydrates, sugar, 0);
        /*Date date, char meal, int foodID, double amountRatio, int caloriesConsumed, double protein, double fat,
		double carbohydrates, double sugar, int caloriesBurned*/
        storeIntoHealthLogTable(parameter);
        

        
    }
    
    
    /**
     * does the sql query needed to input into table.
     * @param healthInfo
     */
    private void storeIntoHealthLogTable(HealthInfo healthInfo) {
        String sql = "INSERT INTO healthInfoLog(date, meal, foodID, amountRatio, caloriesConsumed, fat, protein, carbohydrates, sugar, caloriesBurned) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, healthInfo.getDate());
            pstmt.setString(2, String.valueOf(healthInfo.getMeal()));
            pstmt.setInt(3,healthInfo.getFoodID());
            pstmt.setDouble(4,healthInfo.getAmountRatio());
            pstmt.setInt(5,healthInfo.getCaloriesConsumed());
            pstmt.setDouble(6, healthInfo.getFat());
            pstmt.setDouble(7, healthInfo.getProtein());
            pstmt.setDouble(8, healthInfo.getCarbohydrates());
            pstmt.setDouble(9, healthInfo.getSugar());
            pstmt.setInt(10, healthInfo.getCaloriesBurned());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
    /**
     * removes the healthInfoLog table
     */
	@Override
	public void removeTable() {
		try(Connection conn = super.connect();
		         Statement stmt = conn.createStatement();
		      ) {		      
		         String sql = "DROP TABLE healthInfoLog";
		         stmt.executeUpdate(sql);
		         System.out.println("Table deleted in given database..."); 
		         stmt.close();
		         conn.close();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		   }
		
	}
   
 
