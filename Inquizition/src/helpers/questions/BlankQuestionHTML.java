package helpers.questions;

import java.io.PrintWriter;

public class BlankQuestionHTML extends QuestionHTML {

	public BlankQuestionHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out) {
		out.println("<form class=''>");
		// inserting input field into question text
		out.println("<p> " + question.getText().replaceFirst("<>", " <input type='text' id='answer'> ") + " </p>");
		addHiddenAnswers(out);
		out.println("</form>");
	}

}
