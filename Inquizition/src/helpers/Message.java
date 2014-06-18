package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;


public class Message {
	
	private int id;
	private String text;
	private String datetime;
	private String sender;
	private String subject;
	private String type;
	private boolean read;
	private static DBConnection db;
	
	public Message(ResultSet rs) throws SQLException {
		MessageBuilder(rs);
	}
	
	public Message(int id) throws SQLException{
		Statement stat = db.getStatement();
		ResultSet rs = null;
		try{
			rs = stat.executeQuery("SELECT * FROM messages WHERE id=" + id);
		}catch(SQLException e){ }
		finally{
			try{ stat.close(); } catch(SQLException ignored) {}
			if(rs != null) try{ rs.close(); } catch(SQLException ignored) {}
		}
		MessageBuilder(rs);
	}
	
	private void MessageBuilder(ResultSet rs) throws SQLException{
		this.id = rs.getInt("id");
		this.text = rs.getString("message");
		this.datetime = rs.getString("dtime");
		this.sender = rs.getString("name");
		this.subject = rs.getString("subject");
		this.type = rs.getString("type");
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
	
	public String getSubject() {
		return subject;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isRead() {
		return read;
	}
	
	public void markAsRead() {
		read = false;
		try { 
			db.getStatement().executeUpdate("UPDATE messages SET unread = 0 WHERE id = " + id); 
		}
		catch(SQLException ignored) { }
	}

	public static boolean sendMessage(int from_id, String to_name, String subject, String text) {
		to_name = "\"" + to_name + "\"";
		subject = "\"" + subject + "\"";
		text = "\"" + text + "\"";
		try { 
			// ???
			if(db == null) {
				System.out.println("DB WAS NULL");
				db = new DBConnection();
			}
			db.getStatement().executeUpdate("INSERT INTO messages(sender_id, receiver_id, subject, message) "
					+ "VALUES(" + from_id + ", (SELECT id FROM USERS WHERE name=" + to_name + "), "
							+ subject + ", " + text + ");");
			return true;
		}
		catch(SQLException e) { return false; }
	}
	
}
