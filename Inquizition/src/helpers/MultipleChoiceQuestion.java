package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class MultipleChoiceQuestion extends Question {
	private ArrayList<String> wrongAnswers;
	
	public MultipleChoiceQuestion(String text, int quizID) {
		super(text, quizID);
	}

	public ArrayList<String> getWrongAnswers() {
		return wrongAnswers;
	}
	
	public void addWrongAnswer(String answer) {
		wrongAnswers.add(answer);
	}
	
	@Override
	public void addToDB() {
		super.addToDB();
		Statement stat = (Statement) super.getDB().getStatement();
		ResultSet rs = null;
//		try{
//			
//		}
//		catch(SQLException e) { e.printStackTrace(); }
	}
}
