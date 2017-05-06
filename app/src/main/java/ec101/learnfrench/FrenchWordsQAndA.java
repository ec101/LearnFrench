package ec101.learnfrench;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FrenchWordsQAndA extends AppCompatActivity {

    private Quiz quiz;
    private TextView questionField;
    private EditText answerField;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_french_words_qand);

        Set<Learnable> allLearnableItems = newLearnableItems();

        this.quiz = new DefaultQuiz(allLearnableItems);

        nextButton = (Button)findViewById(R.id.button);
        View.OnClickListener next_listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveAnswer();
                if(quiz.isFinished()){
                    displayResults();
                    nextButton.setEnabled(false);
                }else {
                    displayNextQuestion();
                    if(quiz.isFinished()){
                        nextButton.setText("Finished");
                    }
                }
            }
        };

        nextButton.setOnClickListener(next_listener);

        questionField = (TextView)findViewById(R.id.editText2);

        answerField = (EditText)findViewById(R.id.editText);

        displayNextQuestion();
    }

    private void displayResults() {
        QuestionsResults quizResults = this.quiz.getQuizResults();
        String generatedReport = generateResultsReport(quizResults);
        questionField.setText(generatedReport, TextView.BufferType.NORMAL);
        answerField.setText("", TextView.BufferType.NORMAL);
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

    private AnsweredQuestion createAnsweredQuestion(Question question, Answer answer) {
        return new DefaultAnsweredQuestion(question, answer);
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

    private Set<Learnable> newLearnableItems() {
        HashSet<Learnable> allLearnableItems = new HashSet<>();
        allLearnableItems.add(new DefaultWord("to improve", "améliorer"));
        allLearnableItems.add(new DefaultWord("to cancel", "annuler"));
        allLearnableItems.add(new DefaultWord("to arrive", "arriver"));
        allLearnableItems.add(new DefaultWord("to swallow", "avaler"));
        allLearnableItems.add(new DefaultWord("to confess", "avouer"));
        allLearnableItems.add(new DefaultWord("to chat", "bavarder"));
        allLearnableItems.add(new DefaultWord("to do DIY", "bricoler"));
        allLearnableItems.add(new DefaultWord("to burn", "brûler"));
        allLearnableItems.add(new DefaultWord("to move house", "déménager"));
        allLearnableItems.add(new DefaultWord("to over take", "dépasser"));
        return allLearnableItems;
    }
}
