package nutrifit;

import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Date;
   
public class ReadWriteController {  
     /** 
     * Connect to a sample database 
     */  
	
	
	public enum MealType {
		  BREAKFAST,
		  LUNCH,
		  DINNER,
		  SNACK
	}
	
    public  Connection connect() {  
        Connection conn = null;  
        try {  
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
        }  */
        
        return conn;
    }  
    
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
        
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
        	System.out.println("inside try");
            // create a new table
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   
    
    public String debugDumpDatabase(){
        String sql = "SELECT * FROM healthInfoLog";
        String output = "";
        try (Connection conn = this.connect();
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
    
    
    
    private boolean doesMealExist(char meal, long date) {
    	String sql = "SELECT COUNT(*) FROM HealthInfoLog WHERE date = "+date+" AND meal ='"+meal+"'"; 
    	 try (Connection conn = this.connect();
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
    	try (Connection conn = this.connect();
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
    	try (Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
               
    		output = rs.getInt("FoodID");
    		
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	return output;
    	
    }
    
    public void storeMeal(MealType mealType, int foodID, int amount, Date date) throws Exception {
    	
    	char mealChar='n';
        if (mealType == MealType.BREAKFAST) {
        	mealChar = 'b';
        }
        else if (mealType == MealType.LUNCH) {
        	mealChar = 'l';
        }
        if (mealType == MealType.DINNER) {
        	mealChar = 'd';
        }
        if (mealType == MealType.SNACK) {
        	mealChar = 's';
        }
        
        
        long dateInMS = date.getTime();
        System.out.println(dateInMS);
        
        
        
        if (mealChar != 's' && mealChar != 'n' && doesMealExist(mealChar, dateInMS)==true ) {

        	throw new Exception();
        };
    	
    	
    	String sql = "SELECT NutrientValue FROM nutrientamount WHERE FoodID="+foodID+" AND (NutrientID=203 OR NutrientID=208 OR NutrientID=204 OR NutrientID = 205 OR NutrientID= 269);";
        
    	int caloriesConsumed=0;
    	double protein=0;
    	double fat=0;
    	double carbohydrates=0;
    	double sugar=0;
    	
    	double ratioToHundredGrams = amount/100.0;
    	
    	int i =0;
    	
        try (Connection conn = this.connect();
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

        try (Connection conn = this.connect();
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
    
   
  
    public static void main(String[] args) throws IOException, InterruptedException, Exception {  
        /*ReadWriteController test = new ReadWriteController();
        test.connect();  
        test.createNewTable();
        Date d1 = new Date(123,9,16); 
        test.storeMeal(MealType.SNACK,5,100,d1);
        test.debugDumpDatabase();
        
        //test.insert()
        System.out.println(d1);*/
    }  
}  
