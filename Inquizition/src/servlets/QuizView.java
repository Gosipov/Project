package servlets;

import helpers.Quiz;
import helpers.questions.QuestionHTML;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizView
 */
@WebServlet("/QuizView")
public class QuizView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuizView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Quiz quiz = new Quiz(id);
		quiz.shuffle(); // shuffles if needed
		//quiz.print();
		Iterator<QuestionHTML> questions = quiz.getQuestions();
		request.getSession().setAttribute("questions", questions);
		request.getSession().setAttribute("quiz_name", quiz.getName());
		request.getSession().setAttribute("score", new Integer(0));
		long startTime = System.currentTimeMillis();
		request.getSession().setAttribute("time", new Long(startTime));
		String redirect = quiz.onePage() ? "QuizViewOnePage": "QuizViewManyPages";
		RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
