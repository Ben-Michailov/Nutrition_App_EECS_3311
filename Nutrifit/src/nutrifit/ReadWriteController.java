package nutrifit;

import java.sql.Statement;

import java.io.IOException;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Date;
   
public class ReadWriteController extends Datebase {  
     
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

    	String sql="SELECT SUM(caloriesConsumed), SUM(protein), SUM(fat), SUM(carbohydrates), SUM(sugar), SUM(caloriesBurned) FROM healthInfoLog WHERE date>='"+startDateInMS+"' AND date<='"+endDateInMS+"'";
    	
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
    
    
    
    public boolean doesMealExist(char meal, long date) {
    	if (meal == 's' || meal == 'n') {
    		return false;
    	}
    	String sql = "SELECT COUNT(*) FROM HealthInfoLog WHERE date = "+date+" AND meal ='"+meal+"'"; 
    	 try (Connection conn = super.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){
                
    		 	System.out.println(rs.getInt(1));

            	if (rs.getInt(1)!=0) {
            		
            		return true;
                }

                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    	 
    	 	return false;
    }
    
    public String[] foodNames() {
    	String[] output = new String[5689];
    	//String[] output = new String[5];
    	int i = 0;
    	String sql = "SELECT FoodDescription FROM foodname"; 
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
    		while (rs.next()) {
            	output[i] =rs.getString("FoodDescription");
            	if (i<5688) {
            		i++;
            	}
            	
    		}
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
   	 
    	return output;
    }
    
    public int IDOfAGivenFood(String foodName) {
    	int output = 2;
    	String sql = "SELECT FoodID FROM foodname WHERE FoodDescription='"+foodName+"'"; 
    	try (Connection conn = super.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
    		output = rs.getInt("FoodID");
    		
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	return output;
    	
    }
    
    public void storeExercise(int caloriesBurned, Date date) throws Exception {
    	
        
        
        storeIntoHealthLogTable(date,'n',0,0.0,0.0,0.0,0.0, caloriesBurned);
        
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
        
        
        storeIntoHealthLogTable(date,mealChar, caloriesConsumed,fat,protein,carbohydrates, sugar, 0);
        
        //insert(date, )
        
    }
    
    
    
    private void storeIntoHealthLogTable(Date date, char meal, int caloriesConsumed, double fat, double protein, double carbohydrates, double sugar, int caloriesBurned) {
        String sql = "INSERT INTO healthInfoLog(date, meal, caloriesConsumed, fat, protein, carbohydrates, sugar, caloriesBurned) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
           // pstmt.setInt(0,id);
            pstmt.setDate(1, date);
            pstmt.setString(2, String.valueOf(meal));
            pstmt.setInt(3,caloriesConsumed);
            pstmt.setDouble(4, fat);
            pstmt.setDouble(5, protein);
            pstmt.setDouble(6, carbohydrates);
            pstmt.setDouble(7, sugar);
            pstmt.setInt(8, caloriesBurned);
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
