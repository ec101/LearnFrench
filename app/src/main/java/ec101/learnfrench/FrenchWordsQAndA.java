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
    private EditText questionField;
    private EditText answerField;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_french_words_qand);

        Set<Learnable> allLearnableItems = newLearnableItems();

        final Quiz quiz = new DefaultQuiz(allLearnableItems);

        nextButton = (Button)findViewById(R.id.button5);
        View.OnClickListener next_listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveAnswer();
                displayNextQuestion();
                if(quiz.isFinalQuestion()){
                    nextButton.setText("Finish");
                }
            }
        };
        nextButton.setOnClickListener(next_listener);

        questionField = (EditText)findViewById(R.id.editText3);

        answerField = (EditText)findViewById(R.id.editText4);

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        Question question = this.quiz.getNextQuestion();
        questionField.setText(question.getQuestionText(), TextView.BufferType.NORMAL);
    }

    private void saveAnswer() {
        Answer answer = new DefaultAnswer(answerField.getText().toString());
    }

    private AnsweredQuestion createAnsweredQuestion(Question question, Answer answer) {
        return new DefaultAnsweredQuestion(question, answer);
    }

//    private void reportResults(QuestionsResults questionResults) {
//        //TODO - improve output
//        final List<AnsweredQuestion> correctlyAnsweredQuestions = questionResults.getCorrectlyAnsweredQuestions();
//        final List<AnsweredQuestion> incorrectlyAnsweredQuestions = questionResults.getIncorrectlyAnsweredQuestions();
//        int total = correctlyAnsweredQuestions.size() + incorrectlyAnsweredQuestions.size();
//        System.out.println("Total # Questions "+total);
//        System.out.println("Total # Correct "+correctlyAnsweredQuestions.size());
//        System.out.println("Total # Incorrect "+incorrectlyAnsweredQuestions.size());
//        System.out.println();
//
//        for(AnsweredQuestion answeredQuestion : incorrectlyAnsweredQuestions){
//            System.out.println("Question: "+answeredQuestion.getQuestion().getQuestionText());
//            System.out.println("Expected Answer: "+answeredQuestion.getQuestion().getExpectedAnswer());
//            System.out.println("User Answer: "+answeredQuestion.getAnswer().getAnswer());
//            System.out.println();
//        }
//    }

    private Set<Learnable> newLearnableItems() {
        HashSet<Learnable> allLearnableItems = new HashSet<>();
        allLearnableItems.add(new DefaultWord("to improve", "améliorer"));
        allLearnableItems.add(new DefaultWord("to cancel", "annuler"));
        allLearnableItems.add(new DefaultWord("to arrive", "arriver"));
        allLearnableItems.add(new DefaultWord("to swallow", "awaler"));
        allLearnableItems.add(new DefaultWord("to confess", "avouer"));
        allLearnableItems.add(new DefaultWord("to chat", "bavarder"));
        allLearnableItems.add(new DefaultWord("to do DIY", "bricoler"));
        allLearnableItems.add(new DefaultWord("to burn", "brûler"));
        allLearnableItems.add(new DefaultWord("to move house", "déménager"));
        allLearnableItems.add(new DefaultWord("to over take", "dépasser"));
        return allLearnableItems;
    }
}
