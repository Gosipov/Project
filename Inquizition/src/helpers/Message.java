package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;


public class Message {
	
	private int id;
	private String text;
	private String datetime;
	private String sender;
	private String subject;
	private boolean unread;
	private static DBConnection db;
	
	public Message(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.text = rs.getString("message");
			this.datetime = rs.getString("dtime");
			this.sender = rs.getString("username");
			this.subject = rs.getString("subject");
			this.unread = rs.getBoolean("unread");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setDB(DBConnection connection){
		db = connection;
	}
	
	public int getID() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public String getDateAndTime() {
		return datetime;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public boolean unread() {
		return unread;
	}
	
	public void markRead() {
		unread = false;
	}
	
}
