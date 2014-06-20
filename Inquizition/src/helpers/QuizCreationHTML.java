package helpers;

import java.io.PrintWriter;

public final class QuizCreationHTML {
	public static final String question_html = "<textarea rows=\"3\" cols=\"50\" name=\"question\"></textarea> <br>";
	public static final String answer_simple_html = "<textarea rows=\"1\" cols=\"50\" name=\"answers\"></textarea> <br>";
	
	public static void upperPart(PrintWriter out, int num, String caller) {
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Adding a question to quiz</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>");
		out.print("Adding #" + num + " question");
		out.print("</h1>");
		out.print("<form action=\"" + caller + "Add" + "\" method=\"post\">");
	}
	
	public static void middlePart(PrintWriter out, String caller) {
		out.print("<input type=\"submit\" value=\"Add question\">");
		out.print("</form>");
		out.print("<br>");
		out.print("<form action=\"" + caller + "Add" + "\" method=\"get\">");
		out.print("<input type=\"submit\" value=\"Submit Quiz\"> <br>");
		out.print("If you submit, this question will not be added to quiz");
		out.print("</form>");
	}
	
	public static void lowerPart(PrintWriter out) {
		out.print("</body>");
		out.print("</html>");
	}
			
}
