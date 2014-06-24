package helpers.questions;

import java.io.PrintWriter;

public class QuestionResponseHTML extends QuestionHTML {

	public QuestionResponseHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out, boolean one_page) {
		out.print("<form class=''");
		if(!one_page) out.print(" action='QuizViewManyPages' method='post'");
		out.println(">");
		out.println("<p> " + question.getText() + " </p>");
		out.println("<input type='text' name='answer' id='answer'>");
		if(one_page){
			addHiddenAnswers(out);
			out.println("</form>");
		}
	}

}
