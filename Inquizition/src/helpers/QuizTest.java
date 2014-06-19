package helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuizTest {

	// testing quiz addition to database
	@Test
	public void test() {
		Quiz quiz = new Quiz("name", "descript", false, "James", 2, false);
		assertTrue(quiz.addToDB());
	}

}
