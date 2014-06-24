package servlets;

import helpers.questions.Question;
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
@WebServlet("/QuizViewManyPages")
public class QuizViewManyPages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuizViewManyPages() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterator<QuestionHTML> questions = (Iterator<QuestionHTML>) request.getSession().getAttribute("questions");
		if(!questions.hasNext()){
			RequestDispatcher dispatch = request.getRequestDispatcher("Register");
			dispatch.forward(request, response);
			return;
		}
		QuestionHTML question = questions.next();
		String quizName = (String) request.getSession().getAttribute("quiz_name");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Welcome</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizCreateStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"header\">");
		out.println("<h1>" + quizName + "</h1>");
		out.println("</div>");
		out.println("<div class=\"wrapper\">");
		request.getSession().setAttribute("questions", questions);
		request.getSession().setAttribute("question", question.getQuestion());
		question.generateHTML(out, false);
		out.println("<button> Submit </button>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Question question = (Question) request.getSession().getAttribute("question");
		int score = (int) request.getSession().getAttribute("score");
		String answer = (String) request.getParameter("answer").trim();
		long finishTime = System.currentTimeMillis();
		long startTime = (long) request.getSession().getAttribute("time");
		long elapsed = (long) request.getSession().getAttribute("elapsed");
		elapsed = elapsed + (finishTime - startTime);
		request.getSession().setAttribute("elapsed", elapsed);
		request.getSession().setAttribute("time", finishTime);
		if(question.isRightAnswer(answer)){
			score++;
		}
		request.getSession().setAttribute("score", new Integer(score));
		doGet(request, response);
	}

}
