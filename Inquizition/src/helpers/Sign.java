package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.DBConnection;

public class Sign {
	
	private static DBConnection db;
	private MessageDigest md;
	
	public static final int SUCCESS = 0;
	public static final int WRONG_USERNAME = 1;
	public static final int WRONG_PASSWORD = 2;
	public static final int LONG_USERNAME = 3;
	public static final int USED_USERNAME = 4;
	public static final int ERROR = 5;
	public static final int USERNAME_MAX_LENGTH = 20; 
	
	
	public Sign() {
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public int check(String username, String password) {
		try {
			Statement stat = (Statement) db.getStatement();
			ResultSet rs = stat.executeQuery("SELECT password FROM users WHERE name = \"" + username + "\"");
			stat.close();
			
			if(!rs.next()) // means no match found in database for this user name
				return WRONG_USERNAME;
			
			String encryptedPassword = hexToString(md.digest(password.getBytes()));		
			String realPassword = rs.getString("password");
			
			if(!encryptedPassword.equals(realPassword))
				return WRONG_PASSWORD;
			
			return SUCCESS;
		} catch (SQLException e) { 
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public int register(String username, String password) {
		System.out.println(username.length());
		if(username.length() > USERNAME_MAX_LENGTH) 
			return LONG_USERNAME;
		
		Statement stat;		
		try {
			stat = (Statement) db.getStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM users WHERE name = \"" + username + "\"");
			
			if(rs.next()){ // means some user with this user name already exists
				stat.close();
				return USED_USERNAME;			
			}
				
			// adding new user to database
			username = "\"" + username + "\"";
			String encryptedPassword = "\"" + hexToString(md.digest(password.getBytes())) + "\"";
			stat.executeUpdate("INSERT INTO users(name, password) "
					+ "VALUES(" + username + ", " + encryptedPassword + ");");
			
			stat.close();
			
			return SUCCESS;
		} catch (SQLException e) { 
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
}
