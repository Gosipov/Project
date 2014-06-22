package helpers.questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

public class MultipleChoiceQuestion extends Question {
	private ArrayList<String> wrongAnswers;
	private int index;
	
	public MultipleChoiceQuestion(String text, int quizID) {
		super(text, quizID);
		super.type = "mcq";
		wrongAnswers = new ArrayList<String>();
	}

	public MultipleChoiceQuestion(ResultSet rs) {
		super(rs);
	}

	public Iterator<String> getWrongAnswers() {
		return wrongAnswers.iterator();
	}
	
	public void addWrongAnswer(String answer) {
		wrongAnswers.add(answer);
	}
	
	public void setRightIndex(int index) {
		this.index = index;
	}
	
	public int getRightIndex() {
		return index;
	}
	
	@Override
	public void addToDB() {
		super.addToDB();
		Statement stat = (Statement) db.getStatement();
		// a little hint used to store answers in right order:
		// all questions, but the wrong ones, are stored with
		// index=-1, wrong ones have 'index' equal to the place 
		// the right answer should be
		try{
			for(String ans : wrongAnswers) {
				String add = "\"" + ans + "\"";
				stat.executeUpdate("INSERT INTO answers(answer, question_id, ind) "
						+ "VALUES(" + add + ", " + super.id + ", "+ index + ")");
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {
			try{ stat.close(); } catch(SQLException ignored) {}
		}
	}
}
