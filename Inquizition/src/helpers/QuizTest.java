package helpers;

import static org.junit.Assert.*;

import java.util.Iterator;

import helpers.questions.MultipleChoiceQuestion;
import helpers.questions.Question;
import helpers.questions.QuestionHTML;
import helpers.questions.QuestionResponseHTML;

import org.junit.Test;

public class QuizTest {

	// testing quiz addition to database
	@Test
	public void test1() {
		Quiz quiz = new Quiz("name", "descript", false, 2, false);
		assertTrue(quiz.addToDB());
	}

	// testing quiz retrieve from database
	@Test
	public void test2() {
		Quiz quiz = new Quiz("Test", "", true, 1, true);
		assertTrue(quiz.addToDB());
		Question q = new Question("What is the weather like today?", quiz.getID());
		q.addAnswer("Hot");
		q.addToDB();
		quiz.addQuestion(new QuestionResponseHTML(q));
		
		Quiz res = new Quiz(quiz.getID());
		assertEquals(quiz.getID(), res.getID());
		assertTrue(quiz.getDescription().equals(res.getDescription()));
		assertTrue(quiz.getName().equals(res.getName()));
		
		assertEquals(quiz.getQuestionNum(), res.getQuestionNum());
		Iterator<QuestionHTML> it1 = quiz.getQuestions();
		Iterator<QuestionHTML> it2 = res.getQuestions();
		while(it1.hasNext()){
			assertTrue(it1.next().getQuestion().getText().equals(it2.next().getQuestion().getText()));
		}
	}
	
}
