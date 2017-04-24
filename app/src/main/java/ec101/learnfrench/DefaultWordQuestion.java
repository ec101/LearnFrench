package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultWordQuestion implements Question {

    private final Word word;
    private final Answer answer;

    public DefaultWordQuestion(Word word){
        super();
        //TODO - check validity of word
        this.word = word;
        this.answer = new DefaultAnswer(this.word.getFrench());
    }

    @Override
    public String getQuestionText() {
        return "What is the french word for "+word.getEnglish()+"?";
    }

    @Override
    public Answer getExpectedAnswer() {
        return this.answer;
    }
}
