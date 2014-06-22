package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Activity {
	
	private String quiz;
	private int quiz_id;
	private String user;
	private int user_id; 
	private String type;
	private int time_elapsed;
	private String dtime;
	private int score;
	
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
