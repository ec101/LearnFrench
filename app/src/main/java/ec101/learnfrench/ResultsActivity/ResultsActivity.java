package ec101.learnfrench.ResultsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ec101.learnfrench.FirstActivity.FirstActivity;
import ec101.learnfrench.R;
import ec101.learnfrench.Test.AnsweredQuestion;
import ec101.learnfrench.Test.DefaultTestConfig;
import ec101.learnfrench.Test.QuestionsResults;
import ec101.learnfrench.Test.TestConfig;
import ec101.learnfrench.TestActivity.QuizActivity;

public class ResultsActivity extends AppCompatActivity {

    private TextView resultsField;
    private TestConfig testConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button takeTestButton = (Button)findViewById(R.id.button2);
        View.OnClickListener next_listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                takeSameTestAgain();
            }
        };
        takeTestButton.setOnClickListener(next_listener);

        Button startAgainButton = (Button)findViewById(R.id.button9);
        View.OnClickListener startAgainListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                returnToBeginning();
            }
        };
        startAgainButton.setOnClickListener(startAgainListener);

        Button viewStatsButton = (Button)findViewById(R.id.button10);
        View.OnClickListener viewStatsListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewStats();
            }
        };
        viewStatsButton.setOnClickListener(viewStatsListener);

        resultsField = (TextView)findViewById(R.id.textView);

        resultsField.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        QuestionsResults quizResults = intent.getParcelableExtra(QuizActivity.QUIZ_RESULTS);
        testConfig = intent.getParcelableExtra(QuizActivity.QUIZ_CONFIG);

        displayResults(quizResults);
    }

    private void takeSameTestAgain(){
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.QUIZ_CONFIG, testConfig);
        startActivity(intent);
    }

    private void returnToBeginning(){
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    private void viewStats(){
//        Intent intent = new Intent(this, QuizActivity.class);
//        startActivity(intent);
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
