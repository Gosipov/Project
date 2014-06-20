package helpers;

import static org.junit.Assert.*;
import helpers.questions.MultipleChoiceQuestion;
import helpers.questions.Question;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class QuestionTest {

	@Test
	public void test1() {
		// dummy quiz already in database
		Question qu = new Question("kacia adamiani?", 1);
		qu.addAnswer("ara");
		qu.addAnswer("ki");
		assertTrue(qu.getText().equals("kacia adamiani?"));
		Iterator<String> it = qu.getAnswers();
		assertTrue(it != null);
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("ara"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("ki"));
		qu.addToDB();
		// last action tested in database
	}
	
	@Test
	public void test2() {
		MultipleChoiceQuestion qu = new MultipleChoiceQuestion("ha?", 1);
		qu.addAnswer("o");
		qu.addWrongAnswer("e");
		qu.addWrongAnswer("i");
		qu.addToDB();
		Iterator<String> it = qu.getAnswers();
		assertTrue(it != null);
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("o"));
		assertFalse(it.hasNext());
		it = qu.getWrongAnswers();
		assertTrue(it != null);
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("e"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("i"));
		assertFalse(it.hasNext());
		// last action tested in database
	}

}
