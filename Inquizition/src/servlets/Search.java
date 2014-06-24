package servlets;

import helpers.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Search")
public class Search extends HttpServlet {

	public Search() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String redirect = User.exists(username) ? "UserPage?other_user=" + username : "Oops.htlm";
		RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
		dispatch.forward(request, response);
	}

}