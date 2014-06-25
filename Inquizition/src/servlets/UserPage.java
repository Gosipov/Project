package servlets;

import helpers.Activity;
import helpers.FriendManager;
import helpers.Quiz;
import helpers.QuizManager;
import helpers.User;
import helpers.messaging.MessageManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserPage
 */
@WebServlet("/UserPage")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User MainUser = (User)request.getSession().getAttribute("user");
		int id = Integer.parseInt(request.getParameter("other_id"));
		User user = new User(id);
		if(MainUser.getID() == id){
			RequestDispatcher dispatch = request.getRequestDispatcher("HomePage");
			dispatch.forward(request, response);
		}
		String username = user.getUsername();						
		
		ArrayList<User> friends = FriendManager.getFriends(id);
		
		ArrayList<Quiz> latestCreated = QuizManager.getLatestCreated(id);
		ArrayList<Quiz> latestSolved = QuizManager.getLatestSolved(id);
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Welcome</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"Babdustyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class = \"header\">");
		out.println("<div id = \"topbar\">");
		out.println("<div class = \"block\">");
		out.println(username);
		out.println("</div>");
		out.println("<div class = \"block\">");
		out.println("</div>");
		//friend request
		if(!FriendManager.areFriends(id, MainUser.getID()))
			button("Send Friend Request", out, user);
		out.println("</div>");
		out.println("</div>");
		out.println("<div class = \"content\">");
		out.println("<div class = \"column\" id = \"left\">");
		//box(achievements, false, out);
		buildFriendLists(friends, "Friends", out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"wall\">");
		out.println("<form action='HomePage' method='post'><button> Return to Homepage </button></form>");
		//box(wall, false, out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"right\">");
		buildQuizLists(latestCreated, "His/Her Latest Created", out);
		buildQuizLists(latestSolved, "His/Her Latest Solved", out);
		out.println("</div> </div> </body> </html>");
	}

	private void buildQuizLists(ArrayList<Quiz> list, String name, PrintWriter out){
		if(list.isEmpty()) return;
		out.println("<h6> <a href = \"Quizzes\">" + name + "</a> </h6>");
		out.println("<div class=\"box\">");
		out.println("<table>");
        for(Quiz a : list){
        	out.println("<tr>");
        	out.println("<td> <a href=\"QuizDescription?id=" + a.getID() + "\" target=\"_blank\">" + a.getName() + "</a></td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");
	}
	
	private void buildFriendLists(ArrayList<User> list, String name, PrintWriter out){
		if(list.isEmpty()) return;
		out.println("<h6>" + name + "</h6>");
		out.println("<div class=\"box\">");
		out.println("<table>");
        for(User a : list){
        	out.println("<tr>");
        	out.println("<td> <a href=\"UserPage?other_id=" + a.getID() + "\" target=\"_blank\">" + a.getUsername() + "</a></td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");
	}
	
	private void button(String str, PrintWriter out, User user){
		out.println("<div class = \"block\">");
		out.println("<form action=\"SendMessage\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"to\" "
					+ "id=\"to\" value =" + user.getUsername() + ">");
		out.println("<input type=\"hidden\" name=\"type\" "
				+ "id=\"type\" value =" + MessageManager.REQUEST + ">");
		out.println("<input type = \"submit\" value = \"" + str + "\">");
		out.println("</form>");
		out.println("</div>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
