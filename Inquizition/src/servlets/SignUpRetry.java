package servlets;

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
public class SignUpRetry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpRetry() {
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
		try{
			DBConnection db = (DBConnection) request.getServletContext().getAttribute("database");
			Statement stat = db.getStatement();
			String userName = "\"" + request.getParameter("username") + "\"";
			String password = request.getParameter("password");
			ResultSet rs = stat.executeQuery("SELECT * FROM users where NAME = " + userName);
			String redirect = "welcome.html";
			if(rs.next()){ // this means a user with this name already exists
				request.getServletContext().setAttribute("header1", "Sorry, this username is not available");
				request.getServletContext().setAttribute("header2", "Please enter another username and password");
				redirect = "SignUp.jsp";
			}
			else{ // registering new user(adding to database)
				MessageDigest md = (MessageDigest) request.getSession().getAttribute("md");
				String encryptedPassword = "\"" + hexToString(md.digest(password.getBytes())) + "\"";
				stat.executeUpdate("INSERT INTO users (name, password) VALUES "
						+ "(" + userName + ", " + encryptedPassword + ")");
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
			dispatch.forward(request, response);
		} catch(SQLException  e){
			e.printStackTrace();
		}
	}
	
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

}
