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
			long finishTime = System.currentTimeMillis();
			long startTime = (long) request.getSession().getAttribute("time");
			request.getSession().setAttribute("elapsed", finishTime-startTime);
			RequestDispatcher dispatch = request.getRequestDispatcher("QuizSummary.java");
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
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + quizName + "</h1>");
		request.getSession().setAttribute("questions", questions);
		request.getSession().setAttribute("question", question.getQuestion());
		question.generateHTML(out);
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Question question = (Question) request.getSession().getAttribute("question");
		Iterator<String> answers = question.getAnswers();
		int score = (int) request.getSession().getAttribute("score");
		String answer = (String) request.getSession().getAttribute("answer");
		while(answers.hasNext()){
			if(answers.next().equals(answer)) {
				score++;
				break;
			}
		}
		request.getSession().setAttribute("score", new Integer(score));
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizViewManyPages.java");
		dispatch.forward(request, response);
	}

}