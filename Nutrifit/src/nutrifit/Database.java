package nutrifit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {
	/* connector for MySQL*/
	public  Connection connect() {  
 
		// please modify your own source
  
		Connection conn = null; 
		String databaseName = "3311_project";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String username = "root";
		String password = "password";
		
	        try {  
	            
	        	Class.forName("com.mysql.cj.jdbc.Driver");  
	        	conn=DriverManager.getConnection(url,username,password);  
	              
	            System.out.println("Connection to DataBase has been established.");  
	        }catch(Exception e){ System.out.println(e);}  
	    
	        return conn;
	        
	    }
	 
	/**
     	* check if table in database by "table name"
     	*
     	*/
	
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
