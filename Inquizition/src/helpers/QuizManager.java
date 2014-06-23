package helpers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBConnection;

public class QuizManager {
	
	private static DBConnection db;
	private static final int limit = 20;
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public static ArrayList<QuizEntry> getAllQuizEntries(){
		//purpose unclear, might delete later
		if(db == null)
			db = new DBConnection();
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<QuizEntry> quizzes = new ArrayList<QuizEntry>();
		try{
			rs = stat.executeQuery("SELECT * FROM quizzes");
			while(rs.next()){
				quizzes.add(new QuizEntry(rs));
			}
			return quizzes;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			try{ stat.close(); } catch(Exception e) { };
		}
	}
	
	private static ArrayList<Quiz> executeQuiz(String query){
		if(db == null) db = new DBConnection();
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try{
			rs = stat.executeQuery(query);
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
		}
	}
	
	private static ArrayList<Activity> executeActivity(String query){
		if(db == null) db = new DBConnection();
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try{
			rs = stat.executeQuery(query);
			while(rs.next()){
				activities.add(new Activity(rs));
			}
			return activities;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			try{ stat.close(); } catch(Exception e) { };
		}
	}
	
	public static ArrayList<Quiz> getTopQuizzes() {
		return executeQuiz("SELECT * FROM quizzes ORDER BY quizzes.times_taken DESC LIMIT " + limit);
	}
	
	public static ArrayList<Quiz> getLatestQuizzes() {
		return executeQuiz("SELECT * FROM quizzes ORDER BY quizzes.time_created DESC LIMIT " + limit);
	}

	public static ArrayList<Quiz> getLatestCreated(int id) {
		return executeQuiz("SELECT * FROM quizzes WHERE quizzes.creator_id = \"" + id + "\" ORDER BY quizzes.time_created DESC LIMIT " + limit);
	}

	public static ArrayList<Quiz> getLatestSolved(int id) {
		return executeQuiz("SELECT * FROM quizzes JOIN history ON quizzes.id = history.quiz_id "
				+ "WHERE history.user_id = \"" + id + "\" ORDER BY history.tdate DESC LIMIT " + limit);
	}
	
	//return a given user's all quiz complitions
	public static ArrayList<Activity> getUsersQuizHistory(int user_id, int quiz_id){
		return executeActivity("SELECT * FROM history JOIN quizzes ON history.quiz_id = quizzes.id JOIN users ON history.user_id = users.id "
				+ "WHERE users.id = \"" + user_id + "\" AND quizzes.id = \"" + quiz_id + "\"");
	}
	
	//get a quiz's latest five takers
	public static ArrayList<Activity> getLatestFive(int quiz_id){
		return executeActivity("SELECT * FROM history JOIN quizzes ON history.quiz_id = quizzes.id JOIN users ON history.user_id = users.id "
				+ "WHERE quizzes.id = \"" + quiz_id + "\" ORDER BY history.tdate DESC LIMIT " + limit);
	}
	
	//get a quiz's all time top five takers
	public static ArrayList<Activity> getAllTimeTopFive(int quiz_id){
		return executeActivity("SELECT * FROM history JOIN quizzes ON history.quiz_id = quizzes.id JOIN users ON history.user_id = users.id "
				+ "WHERE quizzes.id = \"" + quiz_id + "\" ORDER BY history.score DESC LIMIT " + limit);
	}
	
	//get a quiz's daily top five takers
	public static ArrayList<Activity> getDailyTopFive(int quiz_id){
		return executeActivity(""); // au sasha tu dzma xar dghevandelobaze ar shemamocmebino raa :DDD
	}
}
