package Model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The QuestionAnswer class represents the question and answers that are
 * attached to each door for the player to answer.
 */
public class QuestionAnswer implements Serializable {
    private String myQuestion;
    private String myCorrectAnswer;
    private String myAnswer2;
    private String myAnswer3;
    private String myAnswer4;
    private final String[] myQuestionAndAnswerArray;

    @Serial
    private static final long serialVersionUID = 109174852462682090L;

    /**
     * Constructor for the QuestionAnswer class.
     * Initializes instance fields for the question and answers
     * as well as the array that hold them.
     */
    QuestionAnswer() {
        myQuestion = "";
        myCorrectAnswer = "";
        myAnswer2 = "";
        myAnswer3 = "";
        myAnswer4 = "";
        myQuestionAndAnswerArray = new String[5];
    }

    /**
     * Gets a random row from the "questions" database by using a query and
     * saves the question, correct answer, and other answers to the proper
     * instance fields.
     * @param theStatement - the statement needed to query the database
     */
    public void getQuestionAnswerFromDatabase(final Statement theStatement) {

        //query the database table for a random row
        final String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        //don't need to close cause we do try - catch - resources
        try (ResultSet rs = theStatement.executeQuery(query)) {

            myQuestion = rs.getString("QUESTION");
            myQuestionAndAnswerArray[0] = myQuestion;
            myCorrectAnswer = rs.getString("CORRECT_ANSWER");
            myQuestionAndAnswerArray[1] = myCorrectAnswer;
            myAnswer2 = rs.getString("ANSWER_2");
            myQuestionAndAnswerArray[2] = myAnswer2;
            myAnswer3 = rs.getString("ANSWER_3");
            myQuestionAndAnswerArray[3] = myAnswer3;
            myAnswer4 = rs.getString("ANSWER_4");
            myQuestionAndAnswerArray[4] = myAnswer4;

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Gets the questionList array that hold the question and answers.
     * @return - the questionList array that hold the question and answers
     */
    public String[] getQuestionList() {
        return myQuestionAndAnswerArray;
    }

    /**
     * Checks to see if the player answer matches the correctAnswer to
     * the question.
     * @param theUserAnswer - the player's answer to the question
     * @return - if the player got the answer correct or not
     */
    public boolean selectedCorrectAnswer(final String theUserAnswer) {
        return theUserAnswer.equals(myCorrectAnswer);
    }
}
