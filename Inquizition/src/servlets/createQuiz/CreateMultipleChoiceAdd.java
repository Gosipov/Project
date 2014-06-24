package servlets.createQuiz;

import helpers.Quiz;
import helpers.questions.MultipleChoiceHTML;
import helpers.questions.MultipleChoiceQuestion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateMultipleChoiceAdd
 */
@WebServlet("/CreateMultipleChoiceAdd")
public class CreateMultipleChoiceAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public CreateMultipleChoiceAdd() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome.html");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
		String question = request.getParameter("question");
		int rightAnswer = Integer.parseInt(request.getParameter("answer"));
		
		MultipleChoiceQuestion qu = new MultipleChoiceQuestion(question, quiz.getID());
		
		for(int i = 0; ; i++){
			String answer = request.getParameter("answer" + i);
			if(answer == null) break;
			if(i == rightAnswer){
				qu.addAnswer(answer);
				qu.setRightIndex(rightAnswer);
			}
			else{
				qu.addWrongAnswer(answer);
			}
		}
		
		qu.addToDB();
		quiz.addQuestion(new MultipleChoiceHTML(qu));
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/CreateMultipleChoice");
		dispatch.forward(request, response);
	}

}
