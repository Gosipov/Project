package servlets;

import helpers.questions.QuestionHTML;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
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
		Iterator<QuestionHTML> questions = (Iterator<QuestionHTML>) request.getSession().getAttribute("questions");
		long startTime = System.currentTimeMillis();
		request.getSession().setAttribute("time", new Long(startTime));
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title> " + quizName + " </title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizCreateStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"header\">");
		out.println("<h1>" + quizName + "</h1>");
		out.println("</div>");
		out.println("<div class=\"wrapper\">");
		while(questions.hasNext()){
			questions.next().generateHTML(out, true);
		}
		out.println("<br>");
		out.print("<form action='QuizViewOnePage' method='post'>");
		out.println("<input type='hidden' name='result'>");
		out.println("<button type='button' onclick='endQuiz(this.form)'> Sumbit quiz </button>");
		out.println("</form>");
		out.println("<p id='sum'> </p>");
		out.println("<script src='submit.js'> </script>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long finishTime = System.currentTimeMillis();
		long startTime = (long) request.getSession().getAttribute("time");
		request.getSession().setAttribute("elapsed", finishTime-startTime);
		System.out.println((String) request.getParameter("result"));
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome.html");
		dispatch.forward(request, response);
	}

}
