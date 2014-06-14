package helpers;

import java.util.ArrayList;

import db.DBConnection;

public class QuizManager {
	
	private static DBConnection db;
	
	public static void setDB(DBConnection connection) {
		db = connection;
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
