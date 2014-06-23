package servlets;

import helpers.Sign;
import helpers.User;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;

/**
 * Servlet implementation class SignUpRetry
 */
@WebServlet("/SignUpRetry")
public class SignupRetry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupRetry() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("header1", "Sign Up");
		request.getServletContext().setAttribute("header2", "Please enter a username and password");
		RequestDispatcher dispatch = request.getRequestDispatcher("SignUp.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sign signup = (Sign) request.getSession().getAttribute("sign");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirect = "SignUp.jsp";
		String header2 = "Try again.";
		
		switch(signup.register(username, password)) {
		case Sign.LONG_USERNAME:
			header2 = "User name should not be longer than " + Sign.USERNAME_MAX_LENGTH + " characters. " + header2;
			break;
		case Sign.USED_USERNAME:
			header2 = "Sorry, this user name is not available. " + header2;
			break;
		case Sign.ERROR:
			header2 = "An error occured. " + header2;
			break;
		case Sign.SUCCESS:
			request.getSession().setAttribute("user", new User(username, password));
			redirect = "HomePage";
			break;
		}
		
		request.getServletContext().setAttribute("header2", header2);
		RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
		dispatch.forward(request, response);
	}
	
}
