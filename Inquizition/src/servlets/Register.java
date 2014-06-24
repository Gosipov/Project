package servlets;

import helpers.Activity;
import helpers.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		int qid = (int) request.getSession().getAttribute("quiz_id");
		long time_elapsed = (long) request.getSession().getAttribute("elapsed");
		int score = (int) request.getSession().getAttribute("score");
		Activity act = new Activity(u.getID(), qid, time_elapsed, score);
		System.out.println(act.addToDB());
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizDone.jsp");
		dispatch.forward(request, response);
	}

}
