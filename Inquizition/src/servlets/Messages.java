package servlets;

import helpers.User;
import helpers.messaging.Message;
import helpers.messaging.MessageManager;

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
		//User user = (User)request.getSession().getAttribute("user");
		//ArrayList<Message> list = MessageManager.getMessages(user.getID());
		ArrayList<Message> list = MessageManager.getMessages(2);
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
        out.println("<th>Status</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");
        String status;
        for(Message m : list){
        	out.println("<tr>");				
        	out.println("<td>" + m.getSender() + " </td>");
			out.println("<td>" + m.getSubject() + " </td>");
			//status (read/unread):
			status = m.isRead() ? "opened" : "unopened";
			out.println("<td>" + status + " </td>");
			//the read button
			out.println("<td> <form action=\"Messages\" method=\"post\"> "
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
			int mesID = Integer.parseInt(idString);
			Message m = new Message(mesID);
			m.markAsRead(); //since we're already here, the message has been read.
			out.println("<p><strong>From:</strong> " 
			+ m.getSender() + "</p>");
			out.println("<p><strong>Subject:</strong> " 
			+ m.getSubject() + "</p>");
			out.println("<p>" + m.getText() + "</p>");
			
			//the replying prompt:
			out.println("<form id=\"form1\" name=\"form1\" "
					+ "method=\"post\" action=\"SendMessage\">");		
			//passing the receiver and the subject through hidden fields
			out.println("<input type=\"hidden\" name=\"to\" "
					+ "id=\"to\" value =" + m.getSender() + ">");
			out.println("<input type=\"hidden\" name=\"subject\" "
					+ "id=\"subject\" value= \"Re:" + m.getSubject() + "\" />");
			out.println("<textarea name=\"text\" "
					+ "id=\"textarea\" cols=\"45\" rows=\"5\"></textarea>");
			out.println("<input type=\"submit\" name=\"button\" "
					+ "id=\"button\" value=\"Reply\" />");
		}
		catch(Exception e){
			out.println("<h3>ERROR</h3>");
		}
		finally{
			out.println("</body>");
			out.println("</html>");
		}
	}

}
