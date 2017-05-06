package ec101.learnfrench;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private TextView resultsField;
    private Button takeTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        takeTestButton = (Button)findViewById(R.id.button2);
        View.OnClickListener next_listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                takeTest();
            }
        };
        takeTestButton.setOnClickListener(next_listener);

        resultsField = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        QuestionsResults quizResults = (QuestionsResults)intent.getParcelableExtra(QuizActivity.QUIZ_RESULTS);

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
        //TODO - improve output
        final List<AnsweredQuestion> correctlyAnsweredQuestions = questionResults.getCorrectlyAnsweredQuestions();
        final List<AnsweredQuestion> incorrectlyAnsweredQuestions = questionResults.getIncorrectlyAnsweredQuestions();
        int total = correctlyAnsweredQuestions.size() + incorrectlyAnsweredQuestions.size();
        StringBuilder builder = new StringBuilder();
        builder.append("Total "+total+" Correct "+correctlyAnsweredQuestions.size()+" Incorrect "+incorrectlyAnsweredQuestions.size()+"\n");

        for(AnsweredQuestion answeredQuestion : incorrectlyAnsweredQuestions){
            builder.append("Expected: "+answeredQuestion.getQuestion().getExpectedAnswer().getAnswer()+ " Submitted: "+answeredQuestion.getAnswer().getAnswer()+"\n");
        }

        for(AnsweredQuestion answeredQuestion : correctlyAnsweredQuestions){
            builder.append("Expected: "+answeredQuestion.getQuestion().getExpectedAnswer().getAnswer()+ " Submitted: "+answeredQuestion.getAnswer().getAnswer()+"\n");
        }

        return builder.toString();
    }
}
