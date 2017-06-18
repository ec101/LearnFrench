package ec101.learnfrench.TestActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import ec101.learnfrench.Learn.DefaultLearnableCollection;
import ec101.learnfrench.R;
import ec101.learnfrench.ResultsActivity.ResultsActivity;
import ec101.learnfrench.StatsActivity.DefaultStatisticsHandler;
import ec101.learnfrench.StatsActivity.StatisticsHandler;
import ec101.learnfrench.Test.Answer;
import ec101.learnfrench.Test.DefaultAnswer;
import ec101.learnfrench.Test.DefaultTest;
import ec101.learnfrench.Test.DefaultWord;
import ec101.learnfrench.Learn.Learnable;
import ec101.learnfrench.Test.Question;
import ec101.learnfrench.Test.QuestionsResults;
import ec101.learnfrench.Test.Test;
import ec101.learnfrench.Test.TestConfig;

public class QuizActivity extends AppCompatActivity {

    public static final String QUIZ_RESULTS = "ec101.learnfrench.quizResults";
    public static final String QUIZ_CONFIG = "ec101.learnfrench.quizConfig";

    private Test quiz;
    private TextView questionField;
    private EditText answerField;
    private Button nextButton;
    private TestConfig quizConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        quizConfig = intent.getParcelableExtra(QUIZ_CONFIG);

        this.quiz = new DefaultTest(DefaultLearnableCollection.LEARNABLES, quizConfig);

        nextButton = (Button) findViewById(R.id.button);
        View.OnClickListener next_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                if (quiz.isFinished()) {
                    nextButton.setEnabled(false);
                    finishQuiz();
                } else {
                    displayNextQuestion();
                }
            }
        };

        nextButton.setOnClickListener(next_listener);

        questionField = (TextView) findViewById(R.id.editText2);

        answerField = (EditText) findViewById(R.id.editText);

        displayNextQuestion();
    }

    private void finishQuiz() {
        updateStats();
        moveToResultsActivity();
    }

    private void updateStats() {
        SharedPreferences settings = getSharedPreferences(StatisticsHandler.PREFS_NAME, 0);
        StatisticsHandler handler = new DefaultStatisticsHandler(settings);
        handler.updateStatistics(quiz.getQuizResults());
    }

    private void moveToResultsActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(QUIZ_RESULTS, quiz.getQuizResults());
        intent.putExtra(QUIZ_CONFIG, quizConfig);
        startActivity(intent);
    }

    private void displayNextQuestion() {
        Question question = this.quiz.getNextQuestion();
        questionField.setText(question.getQuestionText(), TextView.BufferType.NORMAL);
        answerField.setText("", TextView.BufferType.NORMAL);
        if (quiz.isFinished()) {
            nextButton.setText(R.string.button_finished);
        }
    }

    private void saveAnswer() {
        Answer answer = new DefaultAnswer(answerField.getText().toString());
        this.quiz.setAnswer(answer);
    }
}
