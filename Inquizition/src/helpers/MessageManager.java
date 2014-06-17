package helpers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//commenting out this import because two different Statement classes conflict otherwise.
//import com.mysql.jdbc.Statement;


import db.DBConnection;

public class MessageManager {
	
	private static DBConnection db;
	
	public static void setDB(DBConnection connection) {
		db = connection;
	}
	
	public static ArrayList<Message> getMessages(int id) {
		// ???
		if(db == null){
			db = new DBConnection();
		}
		Statement stat = db.getStatement();
		ResultSet rs = null;
		ArrayList<Message> messages = new ArrayList<Message>();
		try{
			rs = stat.executeQuery("SELECT * FROM messages JOIN users ON messages.sender_id = users.id "
					+ "WHERE receiver_id=" + id);
			while(rs.next()){
				messages.add(new Message(rs));
			}
			return messages;
		}
		catch(Exception ignored) {
			return null;
		}
		finally{
			try{ stat.close(); } catch(Exception e) { };
			if(rs != null) try{ rs.close(); } catch(Exception e) { };
		}
	}
	
	public static int countMessages(int id) {
		return count(id, "message");
	}
	
	public static int countChallenges(int id) {
		return count(id, "challenge");
	}
	
	public static int countRequests(int id) {
		return count(id, "frequest");
	}
	
	private static int count(int id, String restrict) {
		restrict = "\"" + restrict + "\"";
		Statement stat = db.getStatement();
		ResultSet rs = null;
		try{
			rs = stat.executeQuery("SELECT COUNT(id) FROM messages WHERE receiver_id="
					+ id + " AND type=" + restrict + " AND UNREAD = 1");
			int cnt = 0;
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			return cnt;
		}
		catch(Exception e) {
			return -1;
		}
		finally{
			try{ stat.close(); } catch(Exception e) { };
			if(rs != null) try{ rs.close(); } catch(Exception e) { };
		}
	}
}
