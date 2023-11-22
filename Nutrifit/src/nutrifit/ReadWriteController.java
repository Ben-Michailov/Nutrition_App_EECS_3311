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
     
	//Connection conn = null;
	
	private static ReadWriteController instance = null;

    private ReadWriteController(){
    	/* try {  
             // db parameters  
         	String url = "jdbc:sqlite:test_database";
             // create a connection to the database  
             conn = DriverManager.getConnection(url);  
               
             System.out.println("Connection to SQLite has been established.");  
               
    	 	} catch (SQLException e) {  
             System.out.println(e.getMessage());  
    	 	} /*finally {  
             try {  
                 if (conn != null) {  
                     conn.close();  
                 }  
             } catch (SQLException ex) {  
                 System.out.println(ex.getMessage());  
             }  
         }*/
    	}

    public static ReadWriteController getInstance() {
        if (instance == null) {
            instance = new ReadWriteController();
        }
        return instance;
    }
	

	
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
    
    public HealthInfo retrieveDataBetweenDates(Date startDate, Date endDate) throws Exception{
    	

    	long startDateInMS = startDate.getTime();
    	long endDateInMS = endDate.getTime();
    	
    	if (endDateInMS < startDateInMS) {
    		throw new Exception();
    	}
    	System.out.println(startDate.toString());
    	Calendar calStart = Calendar.getInstance();
    	calStart.setTime(startDate);
    	System.out.println("cal"+calStart.get(Calendar.YEAR)+","+calStart.get(Calendar.MONTH)+","+calStart.get(Calendar.DAY_OF_MONTH));
    	
    	String monthStart = ""+(calStart.get(Calendar.MONTH)+1);
    	String dayStart=""+calStart.get(Calendar.DAY_OF_MONTH);
    	if ((calStart.get(Calendar.MONTH)+1)<10) {
    		monthStart = "0"+monthStart;
    	}
    	if(calStart.get(Calendar.DAY_OF_MONTH)<10){
    		dayStart = "0"+dayStart;
    	}
    	String dateStartForSql = calStart.get(Calendar.YEAR)+"-"+monthStart+"-"+dayStart;
    	
    	Calendar calEnd = Calendar.getInstance();
    	calEnd.setTime(endDate);
    	System.out.println("cal"+calEnd.get(Calendar.YEAR)+","+calEnd.get(Calendar.MONTH)+","+calEnd.get(Calendar.DAY_OF_MONTH));
    	
    	String monthEnd = ""+(calEnd.get(Calendar.MONTH)+1);
    	String dayEnd=""+calEnd.get(Calendar.DAY_OF_MONTH);
    	if ((calEnd.get(Calendar.MONTH)+1)<10) {
    		monthEnd = "0"+monthEnd;
    	}
    	if(calEnd.get(Calendar.DAY_OF_MONTH)<10){
    		dayEnd = "0"+dayEnd;
    	}
    	String dateEndForSql = calEnd.get(Calendar.YEAR)+"-"+monthEnd+"-"+dayEnd;

    	System.out.println(dateStartForSql);
    	System.out.println(dateEndForSql);

    	String sql="SELECT SUM(caloriesConsumed), SUM(protein), SUM(fat), SUM(carbohydrates), SUM(sugar), SUM(caloriesBurned) FROM healthInfoLog WHERE date>='"+dateStartForSql+"' AND date<='"+dateEndForSql+"'";
    	
    	HealthInfo output = new HealthInfo();
    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
               // loop through the result set
    		 while(rs.next()) {
              //System.out.println(rs.getInt("SUM(caloriesConsumed)"));
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
    	//System.out.println(output);
    	
    	return output;
    	
    }
    
public ArrayList<NutrientNameAndAmount> retrieveAdvancedDataBetweenDates(Date startDate, Date endDate, int amountListed) throws Exception{
    	

    	long startDateInMS = startDate.getTime();
    	long endDateInMS = endDate.getTime();
    	
    	ArrayList<NutrientNameAndAmount> resultArrayList = new ArrayList<NutrientNameAndAmount>();
    	
    	if (endDateInMS < startDateInMS) {
    		throw new Exception();
    	}
    	System.out.println(startDate.toString());
    	Calendar calStart = Calendar.getInstance();
    	calStart.setTime(startDate);
    	System.out.println("cal"+calStart.get(Calendar.YEAR)+","+calStart.get(Calendar.MONTH)+","+calStart.get(Calendar.DAY_OF_MONTH));
    	
    	String monthStart = ""+(calStart.get(Calendar.MONTH)+1);
    	String dayStart=""+calStart.get(Calendar.DAY_OF_MONTH);
    	if ((calStart.get(Calendar.MONTH)+1)<10) {
    		monthStart = "0"+monthStart;
    	}
    	if(calStart.get(Calendar.DAY_OF_MONTH)<10){
    		dayStart = "0"+dayStart;
    	}
    	String dateStartForSql = calStart.get(Calendar.YEAR)+"-"+monthStart+"-"+dayStart;
    	
    	Calendar calEnd = Calendar.getInstance();
    	calEnd.setTime(endDate);
    	System.out.println("cal"+calEnd.get(Calendar.YEAR)+","+calEnd.get(Calendar.MONTH)+","+calEnd.get(Calendar.DAY_OF_MONTH));
    	
    	String monthEnd = ""+(calEnd.get(Calendar.MONTH)+1);
    	String dayEnd=""+calEnd.get(Calendar.DAY_OF_MONTH);
    	if ((calEnd.get(Calendar.MONTH)+1)<10) {
    		monthEnd = "0"+monthEnd;
    	}
    	if(calEnd.get(Calendar.DAY_OF_MONTH)<10){
    		dayEnd = "0"+dayEnd;
    	}
    	String dateEndForSql = calEnd.get(Calendar.YEAR)+"-"+monthEnd+"-"+dayEnd;

    	System.out.println(dateStartForSql);
    	System.out.println(dateEndForSql);
    	
    	
    	String sql="SELECT `nutrient name`.NutrientName AS NutrientName, (V.NutrientAmountSum / IF(STRCMP(`nutrient name`.NutrientUnit,\"mg\") = 0, 1000, 1) /  IF(STRCMP(`nutrient name`.NutrientUnit,\"µg\") = 0, 1000000, 1) / IF(STRCMP(`nutrient name`.NutrientUnit,\"IU\") = 0, 3333000, 1) /  IF(STRCMP(`nutrient name`.NutrientUnit,\"NE\") = 0, 1000, 1)) "
    			+ "AS NutrientAmountSum FROM (SELECT nutrientID, SUM(NutrientAmount) AS NutrientAmountSum FROM (SELECT nutrientamount.foodID, nutrientamount.nutrientID, (nutrientamount.NutrientValue * healthinfolog.amountRatio) AS NutrientAmount FROM nutrientamount INNER JOIN healthinfolog ON nutrientamount.foodID = healthinfolog.foodID AND healthinfolog.date<='"+dateEndForSql+"' AND healthinfolog.date>='"+dateStartForSql+"')"
    					+ " AS nutrients GROUP BY nutrientID) AS V, `nutrient name` WHERE `nutrient name`.NutrientID = V.NutrientID AND `nutrient name`.NutrientCode != 208 AND `nutrient name`.NutrientCode != 268 AND `nutrient name`.NutrientCode != 207 AND `nutrient name`.NutrientCode != 255 AND `nutrient name`.NutrientCode != 287 AND (`nutrient name`.NutrientCode < 210 OR `nutrient name`.NutrientCode > 214) AND "
    					+ "(`nutrient name`.NutrientCode < 501 OR `nutrient name`.NutrientCode > 521) AND `nutrient name`.NutrientCode < 605 ORDER BY NutrientAmountSum DESC LIMIT "+amountListed;
    	
    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
               // loop through the result set
    		 while(rs.next()) {
              //System.out.println(rs.getInt("SUM(caloriesConsumed)"));
    			 
    			resultArrayList.add(new NutrientNameAndAmount(rs.getString("NutrientName"),rs.getDouble("NutrientAmountSum")));
    		 } 
               
               
               conn.close();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
    	//System.out.println(output);
    	
    	for (int i =0; i<resultArrayList.size(); i++) {
    		System.out.println(resultArrayList.get(i));
    	}
    	
    	return resultArrayList;
    	
    }
   
    
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
     * This 
     * @return
     */
    public String[] foodNames() {
    	String[] output = new String[5689];
    	//String[] output = new String[5];
    	int i = 0;
    	String sql = "SELECT FoodDescription FROM `food name`"; 
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
    		while (rs.next()) {
            	output[i] =rs.getString("FoodDescription");
            	if (i<5688) {
            		i++;
            	}
            	
    		}
    		System.out.println("in conn foodnames()");
    	} catch (SQLException e) {
    		System.out.println("there has been an error in  foodnames()");
            System.out.println(e.getMessage());
        }
   	 
    	return output;
    }
    
    public int IDOfAGivenFood(String foodName) {
    	String output = "";
    	System.out.println(foodName);
    	System.out.println("in id of a given food");
    	String sql = "SELECT FoodID FROM `food name` WHERE FoodDescription='"+foodName+"'";

    	
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
              
    		rs.next();
    		System.out.println("in try conn for IDoffood()");
    		System.out.println(rs.toString());
    		output = rs.getString("FoodID");
    		
    		System.out.println("in try conn for IDoffood()");
    		conn.close();
    		
    	} catch (SQLException e) {
    		System.out.println("there has been an error in IDoffood()");
            System.out.println(e.getMessage());
        }
    	
    	System.out.println("gonna return: " + output);
    	
    	return Integer.parseInt(output);
    	
    }
    
    public void storeExercise(int caloriesBurned, Date date) throws Exception {
    	
        
        
        storeIntoHealthLogTable(date,'n',0,0,0,0.0,0.0,0.0,0.0, caloriesBurned);
        
    }
    
    
    public void storeMeal(char mealChar, int foodID, int amount, Date date) throws Exception {
        
        
        long dateInMS = date.getTime();
        System.out.println(dateInMS);
        
        
        
        /*if (mealChar != 's' && mealChar != 'n' && doesMealExist(mealChar, dateInMS)==true ) {

        	throw new Exception();
        };*/
    	
    	
    	String sql = "SELECT NutrientValue FROM nutrientamount WHERE FoodID="+foodID+" AND (NutrientID=203 OR NutrientID=208 OR NutrientID=204 OR NutrientID = 205 OR NutrientID= 269);";
        
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
                /*System.out.println(rs.getInt("NutrientValue") +  "\t" + 
                                   rs.getString("cityName") + "\t" +
                                   rs.getDouble("temperature"));*/
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
        System.out.println("Calories Consumed " + caloriesConsumed + "protein" +protein+"fat"+fat+"carbs"+carbohydrates+"suagr"+ sugar);
        
        
        storeIntoHealthLogTable(date,mealChar, foodID,ratioToHundredGrams,caloriesConsumed,fat,protein,carbohydrates, sugar, 0);
        
        //insert(date, )
        
    }
    
    
    
    private void storeIntoHealthLogTable(Date date, char meal, int foodID, double amountRatio, int caloriesConsumed, double fat, double protein, double carbohydrates, double sugar, int caloriesBurned) {
        String sql = "INSERT INTO healthInfoLog(date, meal, foodID, amountRatio, caloriesConsumed, fat, protein, carbohydrates, sugar, caloriesBurned) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
           // pstmt.setInt(0,id);
            pstmt.setDate(1, date);
            pstmt.setString(2, String.valueOf(meal));
            pstmt.setInt(3,foodID);
            pstmt.setDouble(4,amountRatio);
            pstmt.setInt(5,caloriesConsumed);
            pstmt.setDouble(6, fat);
            pstmt.setDouble(7, protein);
            pstmt.setDouble(8, carbohydrates);
            pstmt.setDouble(9, sugar);
            pstmt.setInt(10, caloriesBurned);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
    	@Override
	public <T> T readTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTable() {
		// TODO Auto-generated method stub
		
	}
   
  
    /*public static void main(String[] args) throws IOException, InterruptedException, Exception {  
        ReadWriteController test = new ReadWriteController();
        /*test.connect();  
        test.createNewTable();
        Date d1 = new Date(123,9,16); 
        test.storeMeal(MealType.SNACK,5,100,d1);
        test.debugDumpDatabase();
        
        //test.insert()
        System.out.println(d1);
    	
    	Date d1 = new Date(123,9,16); 
    	Date d2 = new Date(0,0,0);
        //test.storeMeal(MealType.SNACK,5,100,d1);
        HealthInfo test2 = test.retrieveDataBetweenDates(d2, d1);
        
        System.out.println(test2);
        
        
    	
    	
    }  */
}  
