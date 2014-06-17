package servlets;

import helpers.Activity;
import helpers.FriendManager;
import helpers.MessageManager;
import helpers.Quiz;
import helpers.QuizManager;
import helpers.User;

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
		User user = (User) request.getSession().getAttribute("user");
		String username = user.getUsername();
		int id = user.getID();
		//AchievementManeger AM = new AchievementManeger();
		ArrayList<User> friends = FriendManager.getFriends(id);
		ArrayList<Activity> friendActivity = FriendManager.getFriendActivity(id);
		//ArrayList<String> achievements = AM.getAchievements(id);
		int newMessages = MessageManager.countMessages(id);
		int newChallenges = MessageManager.countChallenges(id);
		int newFriendRequests = MessageManager.countRequests(id);
		
		ArrayList<Quiz> topQuizzes = QuizManager.getTopQuizzes();
		ArrayList<Quiz> latestQuizzes = QuizManager.getLatestQuizzes();
		ArrayList<Activity> latestCreated = QuizManager.getLatestCreated(id);
		ArrayList<Activity> latestSolved = QuizManager.getLatestSolved(id);
		//ArrayList<String> wall = MC.getWall();
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Welcome</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"babdustyle.css\">");
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
//		box(friends, true, out);
		box(friendActivity, false, out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"wall\">");
		//box(wall, false, out);
		out.println("</div>");
		out.println("<div class = \"column\" id = \"right\">");
//		box(topQuizzes, true, out);
//		box(latestQuizzes, true, out);
		box(latestCreated, true, out);
		box(latestSolved, true, out);
		out.println("</div> </div> </body> </html>");
	}
	
	private void box(ArrayList<Activity> ar, boolean isLink, PrintWriter out){
		if(ar.size() > 0){
			out.println("<div class = \"box\"> <ul>");
			for(int i = 0; i < ar.size(); i++){
				if(!isLink) out.println("<li>" + ar.get(i).toString() + "</li>");
				else out.println("<li> <a href = \"" + "raghac linki" + "\">" + ar.get(i).toString() + "</a></li>");
			}
			out.println("</ul> </div>");
		}
	}
	
	private void button(String str, int n, PrintWriter out){
		String classs = "active block";
		if(n == 0) classs = "inactive block";
		else str = str + " " + n;
		out.println("<div class = \"" + classs + "\">");
		out.println("<form action = \"Messages\" method = \"post\">");
		out.println("<input type = \"button\" value = \"" + str + "\">");
		out.println("</form>");
		out.println("</div>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}