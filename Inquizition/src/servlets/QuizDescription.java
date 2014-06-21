package servlets;

import helpers.Activity;
import helpers.QuizEntry;

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
		int quizID = Integer.parseInt(request.getParameter("id"));
		QuizEntry q = new QuizEntry(quizID);
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>" + q.getName() +"</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p><strong>" + q.getName() + "</strong></p>");
		//TODO: hyperlink to creator's page
		out.println("<p><em>" + q.getCreator() + "</em></p>"); 
		out.println("<p>" + q.getDescription() + "</p>");
		//TODO: hyperlink to quiz itself
		
		//TODO: top & recent score tables
		out.println("</body>");
		out.println("</html>");
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
