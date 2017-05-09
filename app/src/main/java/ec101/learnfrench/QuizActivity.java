package ec101.learnfrench;

import android.content.Intent;
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

public class QuizActivity extends AppCompatActivity {

    public static final String QUIZ_RESULTS = "ec101.learnfrench.quizResults";

    private Quiz quiz;
    private TextView questionField;
    private EditText answerField;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Set<Learnable> allLearnableItems = null;
        try {
            allLearnableItems = newLearnableItems();
        } catch (IOException e) {
            Toast message = Toast.makeText(this, "Unable to load words from file", Toast.LENGTH_LONG);
            message.show();
        }

        this.quiz = new DefaultQuiz(allLearnableItems);

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
                    if (quiz.isFinished()) {
                        nextButton.setText(R.string.finished);
                    }
                }
            }
        };

        nextButton.setOnClickListener(next_listener);

        questionField = (TextView) findViewById(R.id.editText2);

        answerField = (EditText) findViewById(R.id.editText);

        displayNextQuestion();
    }

    private void finishQuiz() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(QUIZ_RESULTS, quiz.getQuizResults());
        startActivity(intent);
    }

    private void displayNextQuestion() {
        Question question = this.quiz.getNextQuestion();
        questionField.setText(question.getQuestionText(), TextView.BufferType.NORMAL);
        answerField.setText("", TextView.BufferType.NORMAL);
    }

    private void saveAnswer() {
        Answer answer = new DefaultAnswer(answerField.getText().toString());
        this.quiz.setAnswer(answer);
    }

    private Set<Learnable> newLearnableItems() throws IOException {
        Resources resources = getResources();
        InputStream inputStream = resources.getAssets().open("french_words.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        HashSet<Learnable> allLearnableItems = new HashSet<>();
        String csvLine = reader.readLine();
        while (csvLine != null) {
            String[] csvLineValues = csvLine.split(",");
            allLearnableItems.add(new DefaultWord(csvLineValues[2], csvLineValues[1]));
            csvLine = reader.readLine();
        }
        reader.close();
        return allLearnableItems;
    }
}
