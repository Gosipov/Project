package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	int id;
	String username;
	boolean admin;
	
	public User(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.username = rs.getString("username");
			this.admin = rs.getBoolean("admin");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
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
