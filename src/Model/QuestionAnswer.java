package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionAnswer {
    private String question;
    private String correctAnswer;
    private String answer2;
    private String answer3;
    private String answer4;
    private String[] questionList;

    QuestionAnswer() {
        question = "";
        correctAnswer = "";
        answer2 = "";
        answer3 = "";
        answer4 = "";
        questionList = new String[5];
    }

    public void getQuestionAnswerFromDatabase(Statement stmt) {

        //query the database table for a random row
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        //don't need to close cause we do try - catch - resources
        try (ResultSet rs = stmt.executeQuery(query)) {

            question = rs.getString("QUESTION");
            questionList[0] = question;
            correctAnswer = rs.getString("CORRECT_ANSWER");
            questionList[1] = correctAnswer;
            answer2 = rs.getString("ANSWER_2");
            questionList[2] = answer2;
            answer3 = rs.getString("ANSWER_3");
            questionList[3] = answer3;
            answer4 = rs.getString("ANSWER_4");
            questionList[4] = answer4;


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String[] getQuestionList() {
        return questionList;
    }

    public boolean selectedCorrectAnswer(String userAnswer) {
        return userAnswer.equals(correctAnswer);
    }

}
