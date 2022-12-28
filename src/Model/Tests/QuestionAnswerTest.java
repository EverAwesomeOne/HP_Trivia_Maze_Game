package Model.Tests;

import Model.QuestionAnswer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionAnswerTest {

    //Changed QuestionAnswer constructor visibility from package to public to test
    //Changed myQuestion field visibility from private to public to test
    //Changed myCorrectAnswer field visibility from private to public to test
    //Changed myAnswer2 field visibility from private to public to test
    //Changed myAnswer3 field visibility from private to public to test
    //Changed myAnswer4 field visibility from private to public to test
    /*@Test
    public void testQuestionAnswerConstructor() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        assertEquals("", questionAnswer.myQuestion);
        assertEquals("", questionAnswer.myCorrectAnswer);
        assertEquals("", questionAnswer.myAnswer2);
        assertEquals("", questionAnswer.myAnswer3);
        assertEquals("", questionAnswer.myAnswer4);
        assertNotNull(questionAnswer.getQuestionList());

    }

    //Not testing getQuestionAnswerFromDatabase since it will be very
    //difficult with the randomness of it.

    //Changed QuestionAnswer constructor visibility from package to public to test
    //Changed myQuestionList field visibility from private to public to test
    @Test
    public void testGetQuestionList() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        assertEquals(questionAnswer.myQuestionList, questionAnswer.getQuestionList());
    }

    //Changed QuestionAnswer constructor visibility from package to public to test
    //Changed myCorrectAnswer field visibility from private to public to test
    @Test
    public void testSelectedCorrectAnswerCorrectly() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.myCorrectAnswer = "Harry";
        String userAnswer = "Harry";
        boolean correctAnswer = questionAnswer.selectedCorrectAnswer(userAnswer);
        assertTrue(correctAnswer);
    }

    //Changed QuestionAnswer constructor visibility from package to public to test
    //Changed myCorrectAnswer field visibility from private to public to test
    @Test
    public void testSelectedCorrectAnswerIncorrectly() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.myCorrectAnswer = "Harry";
        String userAnswer = "NotHarry";
        boolean correctAnswer = questionAnswer.selectedCorrectAnswer(userAnswer);
        assertFalse(correctAnswer);
    }*/
}
