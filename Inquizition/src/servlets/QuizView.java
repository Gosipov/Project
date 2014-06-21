package servlets;

import helpers.Quiz;
import helpers.questions.QuestionHTML;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		ArrayList<QuestionHTML> questions = quiz.getQuestions();
		Random rgen = new Random();
		if(quiz.shuffle()){
			for(int i = 0; i < n; i++){
				int rand = rgen.nextInt(n);
				QuestionHTML temp = questions.get(i);
				questions.add(i, questions.get(rand));
				questions.add(rand, temp);
			}
		}
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
		for(int i = 0; i < n; i++){
			questions.get(i).generateHTML(out);
		}
		out.println("<input class=\"button\" type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
