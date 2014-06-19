package servlets.createQuiz;

import helpers.Question;
import helpers.QuestionHTML;
import helpers.Quiz;
import helpers.SimpleQuestionHTML;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
