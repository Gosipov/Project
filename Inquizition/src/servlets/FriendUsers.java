package servlets;

import helpers.FriendManager;
import helpers.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FriendUsers
 */
@WebServlet("/FriendUsers")
public class FriendUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendUsers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int u1 = Integer.parseInt(request.getParameter("from_id"));
		User u = (User)request.getSession().getAttribute("user");
		int u2 = u.getID();
		String result = "<h3>Something went wrong</h3>";
		if(FriendManager.makeFriends(u1, u2) && FriendManager.makeFriends(u2, u1)){
			result = "<strong>User succesfully friended!<strong><br> <a href = \"UserPage?other_id=" + u1
					+ "\">Go to their profile</a>";
		}
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Friend Request</title>");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"QuizCreateStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"header\">");
		out.println("</div>");
		out.println("<div class=\"wrapper\">");
		out.println(result);
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
