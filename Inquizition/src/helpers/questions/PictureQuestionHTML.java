package helpers.questions;

import java.io.PrintWriter;

public class PictureQuestionHTML extends QuestionHTML {

	public PictureQuestionHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out, boolean one_page) {
		out.print("<form class=''");
		if(!one_page) out.print(" action='QuizViewManyPages' method='post'");
		out.println(">");
		out.println("<img src=\"" + ((PictureQuestion) question).getImageURL() + "\" alt='Image not found'>");
		out.println("<p> " + question.getText() + " </p>");
		out.println("<input type='text' name='answer' id='answer'>");
		if(one_page){
			addHiddenAnswers(out);
			out.println("</form>");
		}
	}

}
