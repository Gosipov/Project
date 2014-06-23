package helpers.questions;

import java.io.PrintWriter;

public class PictureQuestionHTML extends QuestionHTML {

	public PictureQuestionHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out) {
		out.println("<form class=''>");
		out.println("<img src=\"" + ((PictureQuestion) question).getImageURL() + "\" alt='Image not found'>");
		out.println("<p> " + question.getText() + " </p>");
		out.println("<input type='text' id='answer'>");
		addHiddenAnswers(out);
		out.println("</form>");		
	}

}
