package ec101.learnfrench;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private TextView resultsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button takeTestButton = (Button)findViewById(R.id.button2);
        View.OnClickListener next_listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                takeTest();
            }
        };
        takeTestButton.setOnClickListener(next_listener);

        resultsField = (TextView)findViewById(R.id.textView);

        resultsField.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        QuestionsResults quizResults = intent.getParcelableExtra(QuizActivity.QUIZ_RESULTS);

        displayResults(quizResults);
    }

    private void takeTest(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void displayResults(QuestionsResults quizResults) {
        String generatedReport = generateResultsReport(quizResults);
        resultsField.setText(generatedReport, TextView.BufferType.NORMAL);
    }

    private String generateResultsReport(QuestionsResults questionResults) {
        final List<AnsweredQuestion> correctlyAnsweredQuestions = questionResults.getCorrectlyAnsweredQuestions();
        final List<AnsweredQuestion> incorrectlyAnsweredQuestions = questionResults.getIncorrectlyAnsweredQuestions();
        int total = correctlyAnsweredQuestions.size() + incorrectlyAnsweredQuestions.size();
        StringBuilder builder = new StringBuilder();
        builder.append("Total ").append(total).append(" Correct ").append(correctlyAnsweredQuestions.size()).append(" Incorrect ").append(incorrectlyAnsweredQuestions.size()).append("\n");

        if(incorrectlyAnsweredQuestions.size() > 0) {
            builder.append("\nIncorrect\n");
            for (AnsweredQuestion answeredQuestion : incorrectlyAnsweredQuestions) {
                builder.append("Expected: ").append(answeredQuestion.getQuestion().getExpectedAnswer().getAnswer()).append(" Submitted: ").append(answeredQuestion.getAnswer().getAnswer()).append("\n");
            }
        }

        if(correctlyAnsweredQuestions.size() > 0) {
            builder.append("\nCorrect\n");
            for (AnsweredQuestion answeredQuestion : correctlyAnsweredQuestions) {
                builder.append("Expected: ").append(answeredQuestion.getQuestion().getExpectedAnswer().getAnswer()).append(" Submitted: ").append(answeredQuestion.getAnswer().getAnswer()).append("\n");
            }
        }

        return builder.toString();
    }
}
