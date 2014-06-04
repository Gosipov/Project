package servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpRetry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			MessageDigest md = MessageDigest.getInstance("SHA");
			String encryptedPassword = hexToString(md.digest(password.getBytes()));
			stat.executeUpdate("INSERT INTO users (name, password) VALUES "
					+ "(" + userName + ", " + encryptedPassword + ")");
			RequestDispatcher dispatch = request.getRequestDispatcher("welcome");
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
