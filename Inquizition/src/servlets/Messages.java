package servlets;

import helpers.Message;
import helpers.MessageManager;
import helpers.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Messages
 */
@WebServlet("/Messages")
public class Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Messages() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		ArrayList<Message> list = MessageManager.getMessages(user.getID());
		//ArrayList<Message> list = MessageManager.getMessages(1);
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Messages</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Messages</h2>");
		out.println("<table>");
        out.println("<tr>");
        out.println("<th>From</th>");
        out.println("<th>Subject</th>");
        out.println("<th>Read</th>");
        out.println("</tr>");
        //TODO: read/unread indicator
        for(Message m : list){
        	out.println("<tr>");				
        	out.println("<td>From: " + m.getSender() + " </td>");
			out.println("<td>Subject :" + m.getSubject() + " </td>");
			out.println("<td> <form action=\"Messages\" method=\"get\"> "
					+ "<input type=\"hidden\" name=\"id\" value=" + 
			m.getID() + "> <input type=\"submit\" value=\"Read\"> </form> </td>");
			out.println("</tr>");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Message</title>");
		out.println("<body>");
		try{
			String idString = request.getParameter("id");
			int userID = Integer.parseInt(idString);
			Message m = new Message(userID);
			out.println("From: " + m.getSender());
			out.println("Subject: " + m.getSubject());
			out.println(m.getText());
		}
		catch(SQLException e){
			out.println("<h3>ERROR</h3>");
		}
		finally{
			out.println("</body>");
			out.println("</html>");
		}
	}

}
