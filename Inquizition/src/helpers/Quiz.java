package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class Quiz {
	
	private int id;
	private String name;
	private String descript;
	private String creation_time;
	private int creator_id;
	private int times_taken;
	private boolean one_page;
	private boolean shuffle;
	private String type;
	private static DBConnection db;
	
	private ArrayList<Question> questions;
	
	public static void setDB(DBConnection connection){
		db = connection;
	}
	
	public Quiz() {
		questions = new ArrayList<Question>();
	}
	
	// Constructor used while creating a new quiz
	public Quiz(String name, String descript, boolean one_page, String type) {
		this();
		this.name = name;
		this.descript = descript;
		this.one_page = one_page;
		this.type = type;
	}
	
	public void addQuestion(Question qu) {
		questions.add(qu);
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() { 
		return id;
	}
}
