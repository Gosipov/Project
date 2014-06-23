package helpers.questions;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public abstract class QuestionHTML {
	protected Question question;
	
	public QuestionHTML(Question qu) {
		this.question = qu;
	}
	
	public abstract void generateHTML(PrintWriter out);
	
	public static QuestionHTML wrap(ResultSet rs) throws SQLException {
		QuestionHTML ret = null;
		switch(rs.getInt("questions.type")){
		case 1: ret = new QuestionResponseHTML(new Question(rs)); break;
		case 2: ret = new BlankQuestionHTML(new Question(rs)); break;
		case 3: ret = new MultipleChoiceHTML(new MultipleChoiceQuestion(rs)); break;
		case 4: ret = new PictureQuestionHTML(new PictureQuestion(rs)); break;
		}
		return ret;
	}
	
	public Question getQuestion(){
		return question;
	}
	
	protected void addHiddenAnswers(PrintWriter out) {
		Iterator<String> it = question.getAnswers();
		while(it.hasNext()){
			out.println("<input type='hiiden' id='answers' "
					+ "value=\"" + it.next() + "\">");
		}
		// adding submit button
		out.println("<button type='button' id='button' "
				+ "onclick='myFunction(this.form)'> Submit </button>");
	}
}
