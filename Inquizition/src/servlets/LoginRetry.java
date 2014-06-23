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
 * Servlet implementation class LoginRetry
 */
@WebServlet("/LoginRetry")
public class LoginRetry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginRetry() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("header1", "Welcome to Inquizition");
		request.getServletContext().setAttribute("header2", "Please Log In");
		RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sign signin = (Sign) request.getSession().getAttribute("sign");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirect = "Login.jsp";
		String header2 = "Try again.";
		
		switch(signin.check(username, password)) {
		case Sign.WRONG_USERNAME:
			header2 = "No such user found. " + header2;
			break;
		case Sign.WRONG_PASSWORD:
			header2 = "Password did not match. " + header2;
			break;
		case Sign.ERROR:
			header2 = "An error occured while signing in. " + header2;
			break;
		case Sign.SUCCESS:
			request.getSession().setAttribute("user", new User(username));
			redirect = "HomePage";
			//redirect = "HomePage"; 
			break;
		}
		
		request.getServletContext().setAttribute("header2", header2);
		RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
		dispatch.forward(request, response);
	}
	

}
