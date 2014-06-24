package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//commenting this out until tested, refer to MessageManager
//import com.mysql.jdbc.Statement;



import db.DBConnection;

public class FriendManager {
	
	private static DBConnection db;
	public static final int MAX_ACTIVITIES = 20;
	private static final String friendQuery = 
			"SELECT * FROM friendship LEFT JOIN users ON friendship.second_id = users.id WHERE friendship.first_id = ";
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public static ArrayList<User> getFriends(int id) {
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<User> friends = new ArrayList<User>();
		try{
			rs = stat.executeQuery(friendQuery + id);
			while(rs.next()){
				friends.add(new User(rs.getInt("id"), rs.getString("name"), rs.getBoolean("admin")));
			}
			return friends;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			if(stat != null) try{ stat.close(); } catch(Exception e) { };
		}
	}
	
	public static ArrayList<Activity> getFriendActivity(int id) {
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<Activity> result = new ArrayList<>();
		try{
			stat = (Statement) db.getStatement();
			rs = stat.executeQuery(activityQuery(id));
			while(rs.next()){
				result.add(new Activity(rs));
			}
			return result;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			if(stat != null) try{ stat.close(); } catch(Exception e) { };
		}
	}
	
	private static String activityQuery(int id) {
		return "SELECT * FROM friendship " +
				"JOIN users ON friendship.second_id = users.id " + 
				"JOIN history ON history.user_id = users.id " + 
				"JOIN quizzes ON quizzes.id = history.quiz_id " + 
				"WHERE friendship.first_id = " + id + " " +
				"AND history.type = \"solve\"" +
				"ORDER BY history.tdate DESC " +
				"LIMIT " + MAX_ACTIVITIES;
	}
	
	public static boolean makeFriends(int u1, int u2){
		Statement stat = db.getStatement();
		try{
			stat.executeUpdate("INSERT INTO friendship(first_id, second_id) VALUES(" + u1 + ", " + u2 +");");
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean areFriends(int u1, int u2){
		Statement stat = db.getStatement();
		ResultSet rs = null;
		try{
			rs = stat.executeQuery("SELECT * FROM friends WHERE first_id=" + u1 + " AND second_id=" + u2 + ";");
			if(!rs.isBeforeFirst())
				return false;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
}
