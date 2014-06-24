package servlets.createQuiz;

import helpers.Quiz;
import helpers.questions.PictureQuestion;
import helpers.questions.PictureQuestionHTML;
import helpers.questions.Question;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatePictureResponseAdd
 */
@WebServlet("/CreatePictureResponseAdd")
public class CreatePictureResponseAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public CreatePictureResponseAdd() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizCreated.html");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
		String question = request.getParameter("question");
		String answers = request.getParameter("answers");
		String image = request.getParameter("url");
		
		PictureQuestion qu = new PictureQuestion(image, question, quiz.getID());
		StringTokenizer tk = new StringTokenizer(answers, ";");
		while(tk.hasMoreTokens()) {
			qu.addAnswer(tk.nextToken().trim());
		}
		qu.addToDB();
		quiz.addQuestion(new PictureQuestionHTML(qu));
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/CreatePictureResponse");
		dispatch.forward(request, response);
	}

}
