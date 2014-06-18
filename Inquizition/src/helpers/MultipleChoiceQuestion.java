package helpers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class MultipleChoiceQuestion extends Question {
	private ArrayList<String> wrongAnswers;
	
	public MultipleChoiceQuestion(String text, int quizID) {
		super(text, quizID);
		wrongAnswers = new ArrayList<String>();
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
		Statement stat = (Statement) db.getStatement();
		try{
			for(String ans : wrongAnswers) {
				String add = "\"" + ans + "\"";
				stat.executeUpdate("INSERT INTO answers(answer, question_id) "
						+ "VALUES(" + add + ", " + super.quizID + ")");
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {
			try{ stat.close(); } catch(SQLException ignored) {}
		}
	}
}
