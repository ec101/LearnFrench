package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

public class WordQuestionBuilder implements QuestionBuilder<Word> {

    public WordQuestionBuilder(){
        super();
    }

    @Override
    public Question buildQuestion(Word word) {
        return new DefaultWordQuestion(word);
    }
}
