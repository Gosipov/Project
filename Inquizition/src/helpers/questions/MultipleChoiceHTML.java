package helpers.questions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class MultipleChoiceHTML extends QuestionHTML {

	public MultipleChoiceHTML(Question qu) {
		super(qu);
	}

	@Override
	public void generateHTML(PrintWriter out) {
		out.println("<form class=''>");
		out.println("<p> " + question.getText() + " </p>");
		for(String a : answers()){
			out.println("<input type='radio' id='answer' value=\"" + a + "\">");
		}
		addHiddenAnswers(out);
		out.println("</form>");
	}
	
	private ArrayList<String> answers() {
		Iterator<String> wrong = ((MultipleChoiceQuestion) question).getWrongAnswers();
		Iterator<String> right = question.getAnswers();
		int rightIndex = ((MultipleChoiceQuestion) question).getRightIndex();
		
		ArrayList<String> result = new ArrayList<String>();
		while(wrong.hasNext()){
			result.add(wrong.next());
		}
		result.set(rightIndex, right.next());
		
		return result;
	}
}
