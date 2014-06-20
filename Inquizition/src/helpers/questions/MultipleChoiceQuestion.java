package helpers.questions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

public class MultipleChoiceQuestion extends Question {
	private ArrayList<String> wrongAnswers;
	
	public MultipleChoiceQuestion(String text, int quizID) {
		super(text, quizID);
		super.type = "mcq";
		wrongAnswers = new ArrayList<String>();
	}

	public Iterator<String> getWrongAnswers() {
		return wrongAnswers.iterator();
	}
	
	public void addWrongAnswer(String answer) {
		wrongAnswers.add(answer);
	}
	
	@Override
	public void addToDB() {
		super.addToDB();
		Statement stat = (Statement) db.getStatement();
		try{
			for(String ans : wrongAnswers) {
				String add = "\"" + ans + "\"";
				stat.executeUpdate("INSERT INTO answers(answer, question_id, yes) "
						+ "VALUES(" + add + ", " + super.id + ", 0)");
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {
			try{ stat.close(); } catch(SQLException ignored) {}
		}
	}
}
