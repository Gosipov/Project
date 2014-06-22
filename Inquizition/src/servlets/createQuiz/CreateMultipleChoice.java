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
 * Servlet implementation class CreateMultipleChoice
 */
@WebServlet("/CreateMultipleChoice")
public class CreateMultipleChoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateMultipleChoice() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
    	PrintWriter out = response.getWriter();
    	QuizCreationHTML.upperPart(out, quiz.getQuestionNum() + 1, "CreateMultipleChoice");
		out.print("Enter your question here <br>");
		out.print(QuizCreationHTML.question_html);
		out.print("<br> Enter your answers here and check the right one <br>");
		out.print("<div id='myList'> </div>");
		out.print("<button type='button' onclick='myFunction(false)'> Add another option </button>");
		out.println("<script src='add_options.js'> </script>");
		out.print("<br><br>");
		QuizCreationHTML.middlePart(out, "CreateMultipleChoice");
		QuizCreationHTML.lowerPart(out);
    	    	
	}

}
