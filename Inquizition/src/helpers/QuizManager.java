package helpers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBConnection;

public class QuizManager {
	
	private static DBConnection db;
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public static ArrayList<Quiz> getAllQuizzes(){
		//purpose unclear, might delete later
		if(db == null)
			db = new DBConnection();
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try{
			rs = stat.executeQuery("SELECT * FROM quizzes");
			while(rs.next()){
				quizzes.add(new Quiz(rs));
			}
			return quizzes;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			try{ stat.close(); } catch(Exception e) { };
			if(rs != null) try{ rs.close(); } catch(Exception e) { };
		}
		return null;
	}
	
	public static ArrayList<Quiz> getTopQuizzes() {
		return null;
	}
	
	public static ArrayList<Quiz> getLatestQuizzes() {
		return null;
	}

	public static ArrayList<Activity> getLatestCreated(int id) {
		return null;
	}

	public static ArrayList<Activity> getLatestSolved(int id) {
		return null;
	}
	
}
