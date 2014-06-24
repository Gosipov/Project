package db;

import java.sql.*;


/*
 * Connects to the database according to data from
 * MYDBInfo object. Returns a statement to other class 
 * objects to operate with database.
 */
public class DBConnection {

	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	private Connection con;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = (Connection) DriverManager.getConnection("jdbc:mysql://"
					+ server, account, password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		try {
			return con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CallableStatement getCallableStatement(String procedure) {
		try {
			return con.prepareCall(procedure);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// after program is over, statement and connection should be closed
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
