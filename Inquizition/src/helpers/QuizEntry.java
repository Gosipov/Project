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
			rs = stat.executeQuery("SELECT * FROM users WHERE id = " + super.getCreatorID());
			creator = rs.getString("user");
			rs = stat.executeQuery("");
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
}
