package helpers.questions;

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
	protected String type;
	protected int id;
	
	public Question(String text, int quizID) {
		answers = new ArrayList<String>();
		this.text = text;
		this.quizID = quizID;
		this.type = "qr";
	}
	
	// retrieving information from database
	public Question(ResultSet rs) {
		// TODO Auto-generated constructor stub
	}

	public void makeBlank() {
		this.type = "blank";
	}
	
	public void addAnswer(String answer) {
		answers.add(answer);
	}
	
	public String getText() {
		return text;
	}
	
	public Iterator<String> getAnswers() {
		return answers.iterator();
	}
	
	public int getID() {
		return id;
	}
	
	public void addToDB() {
		String question = "\"" + text + "\"";
		String quiz_id = "\"" + quizID + "\"";
		String qtype = "\"" + this.type + "\"";
		// ???
		if(db == null) db = new DBConnection();
		Statement stat = (Statement) db.getStatement();
		ResultSet rs = null;
		try{
			stat.executeUpdate("INSERT INTO questions(question, quiz_id, type) VALUES("
					+ question + ", " + quiz_id + ", (SELECT id FROM question_types WHERE name=" + qtype + "))");
			rs = stat.executeQuery("SELECT id FROM questions ORDER BY ID DESC LIMIT 1");
			rs.next();
			id = rs.getInt(1);
			for(String ans : answers) {
				String add = "\"" + ans + "\"";
				stat.executeUpdate("INSERT INTO answers(answer, question_id) "
						+ "VALUES(" + add + ", " + id + ")");
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{
			try{ stat.close(); } catch(SQLException ignored){}
		}
	}


}
