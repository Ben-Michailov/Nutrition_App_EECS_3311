package nutrifit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Datebase {
	
	 
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
	
	boolean tableExists(String tableName) throws SQLException {
		boolean found = false;
		Connection conn = this.connect();
	    DatabaseMetaData meta = conn.getMetaData();
	    ResultSet rs = meta.getTables(null, null, tableName, null);
	    while(rs.next()) {
	    	String name = rs.getString("TABLE_NAME");
	        if (tableName.equals(name)) {
	            found = true;
	            break;
	        }
	    }
	    
	    conn.close();
	    return found;
	}
	
	 public abstract void createNewTable();
	 
	 public abstract <T> T readTable();
	 
	 public abstract void removeTable();
	
}
