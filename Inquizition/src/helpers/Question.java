package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import db.DBConnection;

public class Question {
	protected String text;
	protected ArrayList<String> answers;
	protected static DBConnection db;
	protected int quizID;
	
	public Question(String text, int quizID) {
		this.text = text;
		this.quizID = quizID;
	}
	
	public String getText() {
		return text;
	}
	
	public ArrayList<String> getAnswers() {
		return answers;
	}
	
	public int getQuizID() {
		return quizID;
	}
	
	public DBConnection getDB() {
		return db;
	}
	
	public void addToDB() {
		String question = "\"" + text + "\"";
		String quiz_id = "\"" + quizID + "\"";
		Statement stat = (Statement) db.getStatement();
		ResultSet rs = null;
		try{
			stat.executeUpdate("INSERT INTO questions(question, quiz_id) VALUES("
					+ question + ", " + quiz_id + ")");
			rs = stat.executeQuery("SELECT ID FROM questions ORDER BY ID DESC LIMIT 1");
			rs.next();
			String id = "\"" + rs.getString(1) + "\"";
			for(String ans : answers) {
				String add = "\"" + ans + "\"";
				stat.executeUpdate("INSERT INTO answers(answer, question_id) "
						+ "VALUES(" + add + ", " + id + ")");
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{
			try{ stat.close(); } catch(SQLException ignored){}
			try{ if(rs != null) rs.close(); } catch(SQLException ignored){}
		}
	}

}
