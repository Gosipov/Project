package servlets;

import helpers.Activity;
import helpers.FriendManager;
import helpers.Quiz;
import helpers.QuizManager;
import helpers.User;
import helpers.messaging.MessageManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;


@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomePage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		User user;
//		DBConnection db = new DBConnection();
//		Statement stat = db.getStatement();
//		ResultSet rs = null;
//		String username = "";
//		int id = 0;
//			try {
//				rs = stat.executeQuery("SELECT name FROM users WHERE id = 1"); 
//				rs.next();
//				username = rs.getString("name");
//				user = new User(username);
//				id = user.getID();
//				request.getSession().setAttribute("user", user);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		User user = (User) request.getSession().getAttribute("user");	// HomePage-s dasatestad
		String username = user.getUsername();							// es sami xazi daakomentare 
		int id = user.getID();											// da zedebi ganakomentare
		//AchievementManeger AM = new AchievementManeger();
		ArrayList<User> friends = FriendManager.getFriends(id);
		ArrayList<Activity> friendActivity = FriendManager.getFriendActivity(id);
		//ArrayList<String> achievements = AM.getAchievements(id);
		int newMessages = MessageManager.countMessages(id);
		int newChallenges = MessageManager.countChallenges(id);
		int newFriendRequests = MessageManager.countRequests(id);
		
		ArrayList<Quiz> topQuizzes = QuizManager.getTopQuizzes();
		ArrayList<Quiz> latestQuizzes = QuizManager.getLatestQuizzes();
		ArrayList<Quiz> latestCreated = QuizManager.getLatestCreated(id);
		ArrayList<Quiz> latestSolved = QuizManager.getLatestSolved(id);
		//ArrayList<String> wall = MC.getWall();
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
		out.println("<form action = \"Search\" method = \"post\">");
		out.println("<input type = \"text\" name = \"friend\">");
		out.println("<input type = \"submit\" value = \"Search\">");
		out.println("</form>");
		out.println("</div>");
		button("Messages", newMessages, out);
		button("Challenges", newChallenges, out);
		button("Friend Requests", newFriendRequests, out);
		out.println("</div>");
		out.println("</div>");
		out.println("<div class = \"content\">");
		out.println("<div class = \"column\" id = \"left\">");
		//box(achievements, false, out);
		buildFriendLists(friends, "Friends", out);
		buildActivityLists(friendActivity, "Friend Activity", out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"wall\">");
		//box(wall, false, out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"right\">");
		buildQuizLists(topQuizzes, "Top Quizzes", out);
		buildQuizLists(latestQuizzes, "Latest Quizzes", out);
		buildQuizLists(latestCreated, "My Latest Created", out);
		buildQuizLists(latestSolved, "My Latest Solved", out);
		out.println("</div> </div> </body> </html>");
	}
	
	private void buildQuizLists(ArrayList<Quiz> list, String name, PrintWriter out){
		if(list.isEmpty()) return;
		out.println("<h6> <a href = \"Quizzes\">" + name + "</a> </h6>");
		out.println("<div class=\"box\">");
		out.println("<table>");
        for(Quiz a : list){
        	out.println("<tr>");
        	out.println("<td> <a href=\"QuizDescription/?id=" + a.getID() + "\" target=\"_blank\">" + a.getName() + "</a></td>");
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
        	out.println("<td> <a href=\"User/?id=" + a.getID() + "\" target=\"_blank\">" + a.getUsername() + "</a></td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");
	}
	
	private void buildActivityLists(ArrayList<Activity> list, String name, PrintWriter out){
		if(list.isEmpty()) return;
		out.println("<h6>" + name + "</h6>");
		out.println("<div class=\"box\">");
		out.println("<table>");
        out.println("<tr>");
        out.println("<th>User</th>");
        out.println("<th>Quiz</th>");
        out.println("<th>Score</th>");
        out.println("<th>Time</th>");
        out.println("</tr>");
        for(Activity a : list){
        	out.println("<tr>");
        	out.println("<td> <a href=\"User/?id=" + a.getUserID() + "\" target=\"_blank\">" + a.getUserName() + "</a></td>");
        	out.println("<td> <a href=\"QuizDescription/?id=" + a.getQuizID() + "\" target=\"_blank\">" + a.getQuizName() + "</a></td>");
        	out.println("<td>" + a.getScore() + "</td>");
        	out.println("<td>" + a.getTimeElapsed() + "</td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");
	}
	
	private void button(String str, int n, PrintWriter out){
		String classs = "active";
		if(n == 0) classs = "inactive";
		else str = str + " " + n;
		out.println("<div class = \"block\">");
		out.println("<form action = \"Messages\" method = \"get\">");
		out.println("<input type = \"submit\" class=\"" + classs + "\" value = \"" + str + "\">");
		out.println("</form>");
		out.println("</div>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}