package helpers.questions;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class PictureQuestion extends Question {
	private String imageURL;

	public PictureQuestion(String imageURL, String text, int quizID) {
		super(text, quizID);
		super.type = "prq";
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	@Override
	public void addToDB() {
		super.addToDB();
		Statement stat = (Statement) db.getStatement();
		try{
			stat.executeUpdate("UPDATE questions SET imageurl = '" + imageURL + 
					"' WHERE ID =" + super.id);
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {
			try{ stat.close(); } catch(SQLException ignored) {}
		}
	}
}
