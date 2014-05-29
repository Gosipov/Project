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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRetry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "login.jsp";
		String title1 = "Welcome To Inquizition";
		String title2 = "Please Log In";
		request.getServletContext().setAttribute("header1", title1);
		request.getServletContext().setAttribute("header2", title2);
		RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			DBConnection db = (DBConnection) request.getServletContext().getAttribute("database");
			Statement stat = db.getStatement();
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			ResultSet result = stat.executeQuery("SELECT password FROM users WHERE name = " + userName);
			MessageDigest md = MessageDigest.getInstance("SHA");
			String encryptedPassword = hexToString(md.digest(password.getBytes()));
			String redirect = "retry.jsp";
			String title2 = "Username Does Not Exist";
			if(result.next()){
				redirect = "welcome.java";
				String takenPassword = result.getString("password");
				title2 = "";
				if(!takenPassword.equals(encryptedPassword)){
					title2 = "Password Is Incorrect";
					redirect = "retry.jsp";
				}
			}
			request.getServletContext().setAttribute("title2", title2);
			RequestDispatcher dispatch = request.getRequestDispatcher(redirect);
			dispatch.forward(request, response);
		} catch(SQLException | NoSuchAlgorithmException e){
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
