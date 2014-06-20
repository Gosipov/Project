package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class QuizEntry extends Quiz {
	private String creator;
	private int times_taken;
	private int best_score;
	private String champion;
	
	public QuizEntry(ResultSet rs) throws SQLException{
		super(rs);
		times_taken = rs.getInt("times_taken");
		setInfo();
		
	}
	
	private void setInfo(){
		if(db == null) db = new DBConnection();
		Statement stat = (Statement) db.getStatement();
		ResultSet rs = null;
		try{
			//getting creator's name
			rs = stat.executeQuery("SELECT * FROM users WHERE id = " + creator_id +";");
			creator = rs.getString("name");
			//getting the best score
			rs = stat.executeQuery("Select * FROM history WHERE id = (SELECT best_entry_id "
					+ "from best_score WHERE quiz_id = " + id +");");
			best_score = rs.getInt("score");
			rs = stat.executeQuery("Select * FROM users WHERE id = " + rs.getInt("user_id") + ";");
			champion = rs.getString("name");
		}catch(SQLException e){ }
		finally{
			try{ stat.close(); } catch(SQLException ignored) {}
			if(rs != null) try{ rs.close(); } catch(SQLException ignored) {}
		}
	}
	
	public String getCreator(){
		return creator;
	}
	
	public int getBestScore(){
		return best_score;
	}
	
	public String getChampion(){
		return champion;
	}
	
	public int timesTaken(){
		return times_taken;
	}
}
