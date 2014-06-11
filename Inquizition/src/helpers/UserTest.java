package helpers;

import static org.junit.Assert.*;

import org.junit.Test;

import db.DBConnection;

public class UserTest {
	
	public UserTest() {
		User.setDB(new DBConnection());
	}
	
	@Test
	public void test() {
		// add a user
		User user = new User("Nick", "Cave");
		assertTrue(user.getUsername().equals("Nick"));
		assertFalse(user.isAdmin());
		int id = user.getID();
		// retrieve
		user = new User("Nick");
		assertTrue(user.getUsername().equals("Nick"));
		assertFalse(user.isAdmin());
		assertTrue(id == user.getID());
	}

}
