package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBConnection;

public class Quiz {
	
	private int id;
	private String name;
	private String descript;
	private String creation_time;
	private int creator_id;
	private boolean one_page;
	private boolean shuffle;
	protected static DBConnection db;
	
	private ArrayList<QuestionHTML> questions;
	
	public static void setDB(DBConnection connection){
		db = connection;
	}
	
	// Constructor used while creating a new quiz
	public Quiz(String name, String descript, boolean one_page, String creator, int creator_id, boolean shuffle) {
		QuizBuilder(name, descript, one_page, creator_id, shuffle);
	}
	
	public Quiz(ResultSet rs) throws SQLException {
		QuizBuilder(rs.getString("name"), rs.getString("descript"), rs.getBoolean("one_page"), rs.getInt("creator_id"), rs.getBoolean("shuffle"));
	}
	
	private void QuizBuilder(String name, String descript, boolean one_page, int creator_id, boolean shuffle){
		questions = new ArrayList<QuestionHTML>();
		this.name = name;
		this.descript = descript;
		this.one_page = one_page;
		this.creator_id = creator_id;
		this.shuffle = shuffle;
	}

	public boolean addToDB() {
		// ???
		if(db == null) db = new DBConnection();
		Statement stat = (Statement) db.getStatement();
		ResultSet rs = null;
		try{
			stat.executeUpdate("INSERT INTO quizzes(name, descript, creator_id, one_page, shuffle) "
					+ "VALUES('" + name + "', '" + descript + "', " + creator_id + 
					", " + one_page + ", " + shuffle + ");");
			rs = stat.executeQuery("SELECT id FROM quizzes WHERE creator_id=" + creator_id + 
					" ORDER BY id DESC LIMIT 1;");
			rs.next();
			id = rs.getInt(1);
			return true;
		}catch(SQLException e){ return false; }
		finally{
			try{ stat.close(); } catch(SQLException ignored) {}
			if(rs != null) try{ rs.close(); } catch(SQLException ignored) {}
		}
	}

	public void addQuestion(QuestionHTML qu) {
		questions.add(qu);
	}
	
	public int getQuestionNum() {
		return questions.size();
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() { 
		return id;
	}
	
	public String getDescription() { 
		return descript;
	}
	
	public String getCreationTime() {
		return creation_time;
	}
	
	
	public boolean shuffle() {
		return shuffle;
	}
	
	
	public boolean onePage() {
		return one_page;
	}
	
	public int getCreatorID(){
		return creator_id;
	}
	
}
