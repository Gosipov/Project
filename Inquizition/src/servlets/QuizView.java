package servlets;

import helpers.Quiz;
import helpers.questions.QuestionHTML;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
		int id = (int) request.getSession().getAttribute("quiz_id");
		Quiz quiz = new Quiz(id);
		int n = quiz.getQuestionNum();
		quiz.shuffle(); // shuffles if needed
		Iterator<QuestionHTML> questions = quiz.getQuestions();
		boolean onePage = quiz.onePage();
		if(onePage){
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Welcome</title>");
			out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizStyle.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>" + quiz.getName() + "</h1>");
			out.println("<form action=\"QuizView\" method=\"post\">");
			while(questions.hasNext()){
				questions.next().generateHTML(out);
			}
			out.println("<input class=\"button\" type=\"submit\" value=\"Submit\">");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
		else{
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
