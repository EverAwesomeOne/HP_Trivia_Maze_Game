package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionAnswer {
    private String myQuestion;
    private String myCorrectAnswer;
    private String myAnswer2;
    private String myAnswer3;
    private String myAnswer4;
    private final String[] myQuestionList;

    QuestionAnswer() {
        myQuestion = "";
        myCorrectAnswer = "";
        myAnswer2 = "";
        myAnswer3 = "";
        myAnswer4 = "";
        myQuestionList = new String[5];
    }
    //make it static and return QuestionAnswer object
    //return new QuestionAnswer(questions and the answers)
    public void getQuestionAnswerFromDatabase(final Statement theStatement) {

        //query the database table for a random row
        final String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        //don't need to close cause we do try - catch - resources
        try (ResultSet rs = theStatement.executeQuery(query)) {

            myQuestion = rs.getString("QUESTION");
            myQuestionList[0] = myQuestion;
            myCorrectAnswer = rs.getString("CORRECT_ANSWER");
            myQuestionList[1] = myCorrectAnswer;
            myAnswer2 = rs.getString("ANSWER_2");
            myQuestionList[2] = myAnswer2;
            myAnswer3 = rs.getString("ANSWER_3");
            myQuestionList[3] = myAnswer3;
            myAnswer4 = rs.getString("ANSWER_4");
            myQuestionList[4] = myAnswer4;

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String[] getQuestionList() {
        return myQuestionList;
    }

    public boolean selectedCorrectAnswer(final String theUserAnswer) {
        return theUserAnswer.equals(myCorrectAnswer);
    }
}
