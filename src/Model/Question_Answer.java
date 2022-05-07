package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question_Answer {
    private String question;
    private String answer;

    Question_Answer() {
        question = "";
        answer = "";
    }

    //CHANGE THIS FROM PUBLIC, FOR TESTING PURPOSES ONLY
    public void getQuestionAnswerFromDatabase(Statement stmt) {

        //query the database table for a random row
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        try (ResultSet rs = stmt.executeQuery(query);) {

            question = rs.getString("QUESTION");
            answer = rs.getString("ANSWER");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
