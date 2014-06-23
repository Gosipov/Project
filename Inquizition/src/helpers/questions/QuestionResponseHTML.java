package helpers.questions;

import java.io.PrintWriter;

public class QuestionResponseHTML extends QuestionHTML {

	public QuestionResponseHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out) {
		out.println("<form class=''>");
		out.println("<p> " + question.getText() + " </p>");
		out.println("<input type='text' id='answer'>");
		addHiddenAnswers(out);
		out.println("</form>");
	}

}
