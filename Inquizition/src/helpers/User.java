package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class User {
	private int id;
	private String username;
	private boolean admin;
	private static DBConnection db;
	
	public User(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.username = rs.getString("username");
			this.admin = rs.getBoolean("admin");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDB(DBConnection connection){
		db = connection;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isAdmin() {
		return admin;
	}

		
}
