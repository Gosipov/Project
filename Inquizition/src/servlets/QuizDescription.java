package servlets;

import helpers.Activity;
import helpers.QuizEntry;
import helpers.QuizManager;
import helpers.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizDescription
 */
@WebServlet("/QuizDescription")
public class QuizDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizDescription() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");		
		int quizID = Integer.parseInt(request.getParameter("id"));
		QuizEntry q = new QuizEntry(quizID);
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>" + q.getName() +"</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"Babdustyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class = \"header\">");
		out.println("<h1>" + q.getName() + "</h1>");
		out.println("</div>");
		out.println("<div class=\"content\">");
		out.println("<div class=\"oneColumn\">");
		//TODO: hyperlink to creator's page
		out.println("<p><em> Creator: " + q.getCreator() + "</em></p>"); 
		out.println("<p>" + q.getDescription() + "</p>");
		out.println("<a href=\"QuizView/?id=" + quizID + "\" target=\"_blank\"> Go For It! </a>");
		//TODO: hyperlink to quiz itself
		
		//Generate Tables:
		buildLists(QuizManager.getUsersQuizHistory(user.getID(), quizID), "Your History", out);
		buildLists(QuizManager.getAllTimeTopFive(quizID), "Daily Top", out);
		buildLists(QuizManager.getAllTimeTopFive(quizID), "All Time Top", out);
		//buildLists(QuizManager.getDailyTopFive(quizID), "Daily Top", out);
		buildLists(QuizManager.getLatestFive(quizID), "Latest Activity", out);
        
		//TODO: top & recent score tables
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private void buildLists(ArrayList<Activity> list, String name, PrintWriter out){
		if(list.isEmpty()) return;
		out.println("<div class=\"box\">");
		out.println("<h6>" + name + "</h6>");
		out.println("<table>");
        out.println("<tr>");
        out.println("<th>User</th>");
        out.println("<th>Score</th>");
        out.println("<th>Time</th>");
        out.println("</tr>");
        for(Activity a : list){
        	out.println("<tr>");
        	out.println("<td>" + a.getUserName() + "</td>"); 	//TODO: hyperlink
        	out.println("<td>" + a.getScore() + "</td>");
        	out.println("<td>" + a.getTimeElapsed() + "</td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
