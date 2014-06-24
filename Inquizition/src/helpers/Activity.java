package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.DBConnection;

public class Activity {
	
	private String quiz;
	private int quiz_id;
	private String user;
	private int user_id; 
	private String type;
	private int time_elapsed;
	private String dtime;
	private int score;
	
	private static DBConnection db;
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public Activity(int user_id, int quiz_id, int time_elapsed, int score) {
		this.user_id = user_id;
		this.quiz_id = quiz_id;
		this.time_elapsed = time_elapsed;
		this.score = score;
	}
	
	// retrieving from database
	public Activity(ResultSet rs) throws SQLException {
		this.quiz = rs.getString("quizzes.name");
		this.quiz_id = rs.getInt("quizzes.id");
		this.user = rs.getString("users.name");
		this.user_id = rs.getInt("users.id");
		this.type = rs.getString("history.type");
		this.time_elapsed = rs.getInt("history.time_elapsed");
		this.dtime = rs.getString("history.tdate");
		this.score = rs.getInt("history.score");
	}
	
	public void addToDB() {
		// ???
		if(db == null) db = new DBConnection();
		Statement stat = (Statement) db.getStatement();
		try{
			stat.executeUpdate("insert into history(user_id, quiz_id, time_elapsed, score) "
					+ "values(" + user_id + " ," + quiz_id + ", " + time_elapsed + ", " + score + ")");
		}
		catch(SQLException ignored){}
		finally{
			try { stat.close(); } catch (SQLException e) {}
		}
	}
	
	public int getUserID() {
		return user_id;
	}
	
	public int getQuizID() {
		return quiz_id;
	}
	
	public String getUserName() {
		return user;
	}
	
	public String getAction() {
		return type + "d";
	}
	
	public String getQuizName() { 
		return quiz;
	}
	
	public int getTimeElapsed() {
		return time_elapsed;
	}
	
	public String getDateTime() {
		return dtime;
	}
	
	public int getScore(){
		return score;
	}
	
	
}
