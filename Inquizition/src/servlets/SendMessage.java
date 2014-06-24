package servlets;


import helpers.Quiz;
import helpers.User;
import helpers.messaging.Message;
import helpers.messaging.MessageManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String to;
	private String subject;
	private String text;
	private String res;
	private String typeName;
       
    public SendMessage() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		to = (String) request.getParameter("to");
		int type = Integer.parseInt(request.getParameter("type"));
		User user = (User)request.getSession().getAttribute("user");
		int from = user.getID();
		subject = "";
		text = "";
		res = "";
		typeName = "";
		switch(type){
			case MessageManager.MESSAGE:
				BuildMessage(request);
				break;
			case MessageManager.REQUEST:
				BuildRequest(user, request);
				break;
			case MessageManager.CHALLENGE:
				BuildChallenge(user, request);
				break;
		}
		
		String result = res + " Failed";
		if(Message.sendMessage(from, to, subject, text, typeName))
			result = res + " Sent";
		
		//depending on sendMessage's value, prints one of two statements
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>" + result + "</h2>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private void BuildMessage(HttpServletRequest request){
		subject = (String) request.getParameter("subject");
		text = (String) request.getParameter("text");
		res = "Message";	
		typeName = "message";
	}
	
	private void BuildRequest(User user, HttpServletRequest request){
		subject = "Friend request from " + user.getUsername();
		text = user.getUsername() + " sent you a friend request.<br> \n"
				+ "<form id=\\\"form2\\\" name=\\\"form2\\\" \\n"
					+ "method=\\\"post\\\" action=\\\"FriendUsers\\\"> \n"
				+ "<input type=\\\"hidden\\\" name=\\\"from_id\\\" "
					+ "id=\\\"from\\\" value =" + user.getID() + "> \n"
					+"<input type=\\\"submit\\\" name=\\\"button\\\" \n"
					+ "class=\\\"button\\\" value=\\\"Accept\\\" /></form>";
		res = "Friend Request";
		typeName = "frequest";
	}
	
	private void BuildChallenge(User user, HttpServletRequest request){
		int qID = Integer.parseInt(request.getParameter("quizID"));
		Quiz q = new Quiz(qID);
		
		subject = "New Quiz Challenge!";
		text = user.getUsername() + " has challenged you to play "
				+ q.getName() +  ". Accept, if you dare!<br>";
		text+= "<a href=\\\"QuizDescription?id=" + q.getID() + "\\\">Accept</a>";
		res = "Challenge";
		typeName = "challenge";
	}

}