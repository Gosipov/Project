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
		try{
			DBConnection db = (DBConnection) request.getServletContext().getAttribute("database");
			Statement stat = db.getStatement();
			String userName = request.getParameter("username");
			/// username shouldn't be longer than 20 characters
			String password = request.getParameter("password");
			ResultSet result = stat.executeQuery("SELECT password FROM users WHERE name = \"" + userName + "\"");
			MessageDigest md = (MessageDigest) request.getSession().getAttribute("md");
			String encryptedPassword = hexToString(md.digest(password.getBytes()));
			String redirect = "Login.jsp";
			String header2 = "Username does not exist";
			if(result.next()){
				String enteredPassword = result.getString("password");
				if(enteredPassword.equals(encryptedPassword)){
					redirect = "welcome.html";
					header2 = "";
				}
				else header2 = "Password does not match";
			}
			request.getServletContext().setAttribute("header2", header2);
			RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
			dispatch.forward(request, response);
		} catch(SQLException e){
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
