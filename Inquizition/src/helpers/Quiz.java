package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class Quiz {
//	create table quizzes(
//			id int not null auto_increment primary key,
//			name varchar(64),
//			descript text,
//			time_created timestamp,
//			creator_id int,
//			times_taken int default 0,
//			foreign key(creator_id) 
//				references users(id)
//				on delete set null
//		);
	
	private int id;
	private String name;
	private String descr;
	private String creation_time;
	private int creator_id;
	private int times_taken;
	private static DBConnection db;
	
	public Quiz() {
		
	}
	
	
	public static void setDB(DBConnection connection){
		db = connection;
	}
}
