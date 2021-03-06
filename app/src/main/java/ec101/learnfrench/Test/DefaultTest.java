package ec101.learnfrench.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ec101.learnfrench.Learn.Learnable;
import ec101.learnfrench.Learn.LearnableCollection;

/**
 * Created by Emmet on 18/04/2017.
 */

public class DefaultTest implements Test {

    private final TestConfig config;
    private final LearnableCollection learnableCollection;
    private final QuestionsResults questionResults;
    private final Iterator<Question> questionsIterator;
    private Question currentQuestion;

    public DefaultTest(LearnableCollection learnableCollection, TestConfig config) {
        super();
        this.learnableCollection = learnableCollection;
        this.config = config;
        Set<Learnable> itemsToLearn = generateSetOfLearnableItems();
        Set<Question> questionsToAsk = generateQuestions(itemsToLearn);
        questionResults = newQuestionResultsContainer();
        questionsIterator = questionsToAsk.iterator();
    }

    private Set<Learnable> generateSetOfLearnableItems() {
        List<Learnable> learnableItemsList = generateListOfLearnableItems();
        Random rnd = new Random(System.currentTimeMillis());
        Collections.shuffle(learnableItemsList, rnd);
        Set<Learnable> itemsToLearn = new HashSet<>();
        Iterator<Learnable> iter = learnableItemsList.iterator();
        for (int i = 0; i < config.getTestSize(); i++) {
            itemsToLearn.add(iter.next());
        }
        return itemsToLearn;
    }

    private List<Learnable> generateListOfLearnableItems() {
        if (config.getSubjects() == null || config.getSubjects().length == 0) {
            return new ArrayList<>(this.learnableCollection.getAllLearnablesItems());
        }
        String[] subjects = config.getSubjects();
        ArrayList<Learnable> learnableItemsList = new ArrayList<>();
        for(String subject : subjects){
            learnableItemsList.addAll(this.learnableCollection.getAllLearnablesForASubject(subject));
        }
        return learnableItemsList;
    }

    private Set<Question> generateQuestions(Set<Learnable> itemsToLearn) {
        //TODO - replace implementation with stream
        QuestionBuilder questionBuilder = new DefaultQuestionBuilder();
        HashSet<Question> questions = new HashSet<>();
        for (Learnable learnable : itemsToLearn) {
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
