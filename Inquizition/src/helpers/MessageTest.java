package helpers;

import static org.junit.Assert.*;
import helpers.messaging.Message;
import helpers.messaging.MessageManager;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class MessageTest {

	boolean third = true;
	
	// Initially, database includes dummy-users 
	// 1	John
	// 2	James
	// 3	Peter
	// 4	Paul
	
	@Test
	public void test1() {
		if(third) return;
		// Adding two messages to database
		assertTrue(Message.sendMessage(1, "James", "testing", "testing messages", "message"));
		try { Thread.sleep(600); } catch (InterruptedException e) { }
		assertTrue(Message.sendMessage(3, "James", "testing2", "double-testing", "message"));
		// Checking MessageManager::getMessages method
		ArrayList<Message> al = MessageManager.getMessages(2);
		assertEquals(2, al.size());
		Message m1 = al.get(0);
		Message m2 = al.get(1);
		assertTrue(m1.getSender().equals("John"));
		assertTrue(m2.getSender().equals("Peter"));
		assertTrue(m1.getSubject().equals("testing"));
		assertTrue(m2.getSubject().equals("testing2"));
		assertTrue(m1.getType().equals("message"));
		assertTrue(m2.getType().equals("message"));
		assertTrue(m1.getText().equals("testing messages"));
		assertTrue(m2.getText().equals("double-testing"));
		assertFalse(m1.isRead());
		assertFalse(m2.isRead());
	}
	
	// Testing read/unread methods and message counter from MessageManager
	@Test
	public void test2() {
		if(third) return;
		assertTrue(Message.sendMessage(1, "Paul", "readunread", "readunread", "message"));
		ArrayList<Message> al = MessageManager.getMessages(4);
		assertEquals(1, al.size());
		Message ms = al.get(0);
		assertFalse(ms.isRead());
		assertEquals(1, MessageManager.countMessages(4));
		ms.markAsRead();
		assertEquals(0, MessageManager.countMessages(4));
	}
	
	@Test
	public void test3() {
		if(!third) return;
		// a dummy message is already in database
		Message m = null;
		m = new Message(1);
		assertTrue(m != null);
		assertTrue(m.getSender().equals("James"));
		assertTrue(m.getSubject().equals("uhuuu"));
		assertTrue(m.getText().equals("Song 2"));
		assertTrue(m.getType().equals("message"));
	}
	

}
