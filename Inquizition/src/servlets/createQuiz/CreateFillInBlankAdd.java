package servlets.createQuiz;

import helpers.Quiz;
import helpers.questions.BlankQuestionHTML;
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
 * Servlet implementation class CreateFillInBlankAdd
 */
@WebServlet("/CreateFillInBlankAdd")
public class CreateFillInBlankAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateFillInBlankAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome.html");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
		String question = request.getParameter("question");
		String answers = request.getParameter("answers");
		
		Question qu = new Question(question, quiz.getID());
		qu.makeBlank();
		StringTokenizer tk = new StringTokenizer(answers, ";");
		while(tk.hasMoreTokens()) {
			qu.addAnswer(tk.nextToken().trim());
		}
		qu.addToDB();
		quiz.addQuestion(new BlankQuestionHTML(qu));
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/CreateFillInBlank");
		dispatch.forward(request, response);
	}

}
