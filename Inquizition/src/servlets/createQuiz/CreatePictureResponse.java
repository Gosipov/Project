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
 * Servlet implementation class CreatePictureResponse
 */
@WebServlet("/CreatePictureResponse")
public class CreatePictureResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final int preview_width = 304;
	private final int preview_height = 228;
	
    public CreatePictureResponse() {
        super();

    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
    	PrintWriter out = response.getWriter();
    	QuizCreationHTML.upperPart(out, quiz.getQuestionNum() + 1, "CreatePictureResponse");
    	out.print("<form>");
		out.print("Enter picture URL here <br>");
		out.print("<input type='url' maxlength='128' id='imageurl' name='url'> <br>");
		out.print("<input type=\"button\" id=\"btn\" value=\"Preview\"");
		out.print("</form> <br>");
		out.print("If you want to add text, enter it here <br>");
		out.print(QuizCreationHTML.question_html);
		out.print("<br> Enter all possible answers here, terminating them by semicolon(;) <br>");
		out.print(QuizCreationHTML.answer_simple_html);
		out.print("<br>");
		QuizCreationHTML.middlePart(out, "CreatePictureResponse");
		out.print("<br><br>");
		out.print("<br> <img id=\"image\" border=\"0\" src=\"\" width=\"" + preview_width + "\" height=\"" + preview_height + "\">");
		// adding image preview script
		out.print("<script>");
		out.print("document.getElementById('btn').onclick = function() { "
				+ "var src = document.getElementById('imageurl').value; "
				+ "document.getElementById('image').src = src;  };");
		out.print("</script>");
		QuizCreationHTML.lowerPart(out);
	}

}