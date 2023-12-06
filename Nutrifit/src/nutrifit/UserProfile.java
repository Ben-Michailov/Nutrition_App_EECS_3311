package nutrifit;


import nutrifit.patterns.User;
import nutrifit.patterns.UserBuilder;

import java.sql.*;

public class UserProfile extends Database{


	/**
	 * Create a new UserProfile table in the database
	 *
	 */
	@Override
	public void createNewTable() {
		try(Connection conn = super.connect();
			Statement stmt = conn.createStatement()
		) {
			String sql = "CREATE TABLE IF NOT EXISTS user_profile " +
					"(id INTEGER not NULL, " +
					" name VARCHAR(255), " +
					" age INTEGER, " +
					" DOB VARCHAR(255), " +
					" height double, " +
					" weight double, " +
					" sex VARCHAR(255), " +
					" PRIMARY KEY ( id ))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * insert a user to UserProfile table
	 *
	 */
	public void insertTable(User user) {
		String sql = "INSERT INTO user_profile(id,name, age, DOB, height, weight, sex) VALUES(?,?,?,?,?,?,?)";
		System.out.println("Inserting records into the table...");
		updateTable(sql,user);
		System.out.println("Inserted records into the table...");

	}




	/**
	 * read and return user from UserProfile table
	 *
	 */
	@Override
	public User readTable() {
		User user = new User();
		String sql = "SELECT name, age , DOB , height, weight,sex FROM user_profile WHERE id = 1";
		try(Connection conn = super.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()){
				//set user values
				// rs.getInt("id");
				UserBuilder userbuilder = new UserBuilder();
				userbuilder.setName(rs.getString("name"));
				userbuilder.setAge(rs.getInt("age"));
				userbuilder.setDOB(rs.getString("DOB"));
				userbuilder.setHeight(rs.getDouble("height"));
				userbuilder.setWeight(rs.getDouble("weight"));
				userbuilder.setSex(rs.getString("sex"));

				user = userbuilder.build();


			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}



	/**
	 * edit user profile in UserProfile table
	 *
	 */

	public void editTable(User user) {

		String sql = "UPDATE user_profile SET name=?, age=?, DOB=?, height=?, weight=?, sex=? where id =?";
		System.out.println("updating records into the table...");
		updateTable(sql,user);
		System.out.println("updated records into the table...");


	}
	private void updateTable(String sql, User user){
		try(Connection conn = super.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)
		){

			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.setString(3, user.getDOB());
			pstmt.setDouble(4, user.getHeight());
			pstmt.setDouble(5, user.getWeight());
			pstmt.setString(6, user.getSex());
			pstmt.setInt(7, 1);

			pstmt.executeUpdate();


			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * remove UserProfile table in database
	 *
	 */

	@Override
	public void removeTable() {
		try(Connection conn = super.connect();
			Statement stmt = conn.createStatement();
		) {
			String sql = "DROP TABLE user_profile";
			stmt.executeUpdate(sql);
			System.out.println("Table deleted in given database...");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




}	
