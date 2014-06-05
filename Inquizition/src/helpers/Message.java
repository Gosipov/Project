package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Message {
	
	int id;
	String text;
	String datetime;
	String sender;
	boolean unread;
	
	public Message(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.text = rs.getString("message");
			this.datetime = rs.getString("dtime");
			this.sender = rs.getString("username");
			this.unread = rs.getBoolean("unread");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean unread() {
		return unread;
	}
	
	public void markRead() {
		unread = false;
	}
	
}
