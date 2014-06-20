package helpers.questions;

import java.io.PrintWriter;

public abstract class QuestionHTML {
	private Question question;
	
	public QuestionHTML(Question qu) {
		this.question = qu;
	}
	
	public abstract void generateHTML(PrintWriter out);
}
