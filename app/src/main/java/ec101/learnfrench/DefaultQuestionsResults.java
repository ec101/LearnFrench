package ec101.learnfrench;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultQuestionsResults implements QuestionsResults {

    private List<AnsweredQuestion> correctlyAnsweredQuestions = new ArrayList<>();
    private List<AnsweredQuestion> inCorrectlyAnsweredQuestions = new ArrayList<>();

    public DefaultQuestionsResults(){
        super();
    }

    @Override
    public List<AnsweredQuestion> getCorrectlyAnsweredQuestions() {
        return correctlyAnsweredQuestions;
    }

    @Override
    public List<AnsweredQuestion> getIncorrectlyAnsweredQuestions() {
        return inCorrectlyAnsweredQuestions;
    }

    @Override
    public void addAnsweredQuestion(AnsweredQuestion answeredQuestion) {
        if(answeredQuestion.isAnswerCorrect()){
            correctlyAnsweredQuestions.add(answeredQuestion);
        }else{
            inCorrectlyAnsweredQuestions.add(answeredQuestion);
        }
    }
}
