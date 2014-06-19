package servlets.createQuiz;

import helpers.Quiz;
import helpers.QuizCreationHTML;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateFillInBlank
 */
@WebServlet("/CreateFillInBlank")
public class CreateFillInBlank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateFillInBlank() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
    	PrintWriter out = response.getWriter();
    	QuizCreationHTML.upperPart(out, quiz.getQuestionNum() + 1, "CreateFillInBlank");
		out.println("Enter your question here. Mark the place where you want blank space to be with <> <br>");
		out.println(QuizCreationHTML.question_html);
		out.println("<br> Enter all possible answers here, terminating them by semicolon(;) <br>");
		out.println(QuizCreationHTML.answer_simple_html);
		out.println("<br>");
		QuizCreationHTML.middlePart(out, "CreateFillInBlank");
		QuizCreationHTML.lowerPart(out);
	}

}
