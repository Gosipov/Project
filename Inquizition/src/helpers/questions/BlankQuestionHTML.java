package helpers.questions;

import java.io.PrintWriter;

public class BlankQuestionHTML extends QuestionHTML {

	public BlankQuestionHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out, boolean one_page) {
		out.print("<form class=''");
		if(!one_page) out.print(" action='QuizViewManyPages' method='post'");
		out.println(">");
		// inserting input field into question text
		out.println("<p> " + question.getText().replaceFirst("<>", " <input type='text' name='answer' id='answer'> ") + " </p>");
		if(one_page){
			addHiddenAnswers(out);
			out.println("</form>");
		}
		
	}

}
