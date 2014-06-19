package helpers;

import java.io.PrintWriter;

public final class QuizCreationHTML {
	public static final String question_html = "<textarea rows=\"3\" cols=\"50\" name=\"question\"></textarea> <br>";
	public static final String answer_simple_html = "<textarea rows=\"1\" cols=\"50\" name=\"answers\"></textarea> <br>";		
	
	public static void upperPart(PrintWriter out, int num, String caller) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Adding a question to quiz</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("Adding #" + num + " question");
		out.println("</h1>");
		out.println("<form action=\"" + caller + "Add" + "\" method=\"post\">");
	}
	
	public static void middlePart(PrintWriter out, String caller) {
		out.println("<input type=\"submit\" value=\"Add question\">");
		out.println("</form>");
		out.println("<br>");
		out.println("<form action=\"" + caller + "Add" + "\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"Submit Quiz\"> <br>");
		out.println("If you submit, this question will not be added to quiz");
	}
	
	public static void lowerPart(PrintWriter out) {
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
			
}
