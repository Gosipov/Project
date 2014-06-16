package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import db.DBConnection;


public class Message {
	
	private int id;
	private String text;
	private String datetime;
	private String sender;
	private String subject;
	private boolean read;
	private static DBConnection db;
	
	public Message(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.text = rs.getString("message");
		this.datetime = rs.getString("dtime");
		this.sender = rs.getString("username");
		this.subject = rs.getString("subject");
		this.read = !rs.getBoolean("unread");
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
	
	public boolean isRead() {
		return read;
	}
	
	public void markAsRead() {
		read = false;
		Statement stat = null;
		try { 
			db.getStatement().executeUpdate("UPDATE messages SET unread = \"false\" WHERE id = " + id); 
		}
		catch(Exception ignored) { }
		
		finally{
			if(stat != null) try{ stat.close(); } catch(Exception e) { };
		}
	}

	public static void sendMessage(int from_id, String to_name, String subject, String text) {
		to_name = "\"" + to_name + "\"";
		subject = "\"" + subject + "\"";
		text = "\"" + text + "\"";
		Statement stat = null;
		try { 
			db.getStatement().executeUpdate("INSERT INTO messages(sender_id, receiver_id, subject, message) "
					+ "VALUES(" + from_id + ", (SELECT id FROM USERS WHERE name=" + to_name + "), "
							+ subject + ", " + text + ");"); 
		}
		catch(Exception ignored) { }
		
		finally{
			if(stat != null) try{ stat.close(); } catch(Exception e) { };
		}
	}
}
