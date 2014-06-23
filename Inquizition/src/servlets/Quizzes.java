package servlets;

import helpers.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quizzes
 */
@WebServlet("/Quizzes")
public class Quizzes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quizzes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<QuizEntry> list = QuizManager.getAllQuizEntries();
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Quizzes</title>");
		out.println("<body>");
		out.println("<h2>Messages</h2>");
		out.println("<table>");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Creator</th>");
        out.println("<th>Times Taken</th>");
        out.println("<th>High Score</th>");
        out.println("<th>Champion</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");
        for(QuizEntry q : list){
        	out.println("<tr>");				
        	out.println("<td>" + q.getName() + " </td>");
        	out.println("<td>" + q.getCreator() + " </td>");
        	out.println("<td>" + q.getTimesTaken() + " </td>");
        	out.println("<td>" + q.getBestScore() + " </td>");
        	out.println("<td>" + q.getChampion() + " </td>");
        	//link:
        	
        	out.println("<td>");
        	out.println("<form method=\"get\" action=\"QuizDescription/?id="+ q.getID() + "\">");
        	out.println("<button type=\"submit\">View</button>");
        	out.println("</form>");
            out.println("</td>");
            
			out.println("</tr>");
			out.println("</table");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
