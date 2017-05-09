package ec101.learnfrench;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Emmet on 18/04/2017.
 */

public class DefaultQuiz implements Quiz {

    private static final int NUMBER_OF_QUESTIONS = 20;

    private final Set<Learnable> allLearnableItems;
    private final QuestionsResults questionResults;
    private final Iterator<Question> questionsIterator;
    private Question currentQuestion;

    public DefaultQuiz(Set<Learnable> allLearnableItems){
        super();
        this.allLearnableItems = allLearnableItems;
        Set<Learnable> itemsToLearn = generateSetOfLearnableItems();
        Set<Question> questionsToAsk = generateQuestions(itemsToLearn);
        questionResults = newQuestionResultsContainer();
        questionsIterator = questionsToAsk.iterator();
    }

    private Set<Learnable> generateSetOfLearnableItems() {
        List<Learnable> learnableItemsList = new ArrayList<>(this.allLearnableItems);
        Random rnd = new Random(System.currentTimeMillis());
        Collections.shuffle(learnableItemsList, rnd);
        Set<Learnable> itemsToLearn = new HashSet<>();
        Iterator<Learnable> iter = learnableItemsList.iterator();
        for(int i = 0; i < NUMBER_OF_QUESTIONS; i++){
            itemsToLearn.add(iter.next());
        }
        return itemsToLearn;
    }

    private Set<Question> generateQuestions(Set<Learnable> itemsToLearn) {
        //TODO - replace implementation with stream
        QuestionBuilder questionBuilder = new DefaultQuestionBuilder();
        HashSet<Question> questions = new HashSet<>();
        for(Learnable learnable : itemsToLearn){
            questions.add(questionBuilder.buildQuestion(learnable));
        }
        return questions;
    }

    private QuestionsResults newQuestionResultsContainer() {
        return new DefaultQuestionsResults();
    }

    @Override
    public Question getNextQuestion() {
        this.currentQuestion = questionsIterator.next();
        return currentQuestion;
    }

    @Override
    public void setAnswer(Answer answer) {
        AnsweredQuestion answeredQuestion = new DefaultAnsweredQuestion(this.currentQuestion, answer);
        this.questionResults.addAnsweredQuestion(answeredQuestion);
    }

    @Override
    public QuestionsResults getQuizResults() {
        return this.questionResults;
    }

    @Override
    public boolean isFinished() {
        return !this.questionsIterator.hasNext();
    }
}
