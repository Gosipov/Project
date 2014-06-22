package servlets;

import helpers.questions.QuestionHTML;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizViewOnePage
 */
@WebServlet("/QuizViewOnePage")
public class QuizViewOnePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuizViewOnePage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quizName = (String) request.getSession().getAttribute("quiz_name");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Welcome</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + quizName + "</h1>");
		Iterator<QuestionHTML> questions = (Iterator<QuestionHTML>) request.getSession().getAttribute("questions");
		while(questions.hasNext()){
			questions.next().generateHTML(out);
		}
		out.println("<br>");
		out.print("<form action='QuizSummary' method='post'>");
		out.println("<input type='hidden' name='result'>");
		out.println("<button type='button' onclick='endQuiz(this.form)'> </button>");
		out.println("</form>");
		out.println("<script src='submit.js'> </script>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
