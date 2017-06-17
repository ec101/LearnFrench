package ec101.learnfrench.Test;

import ec101.learnfrench.Learn.Learnable;
import ec101.learnfrench.Learn.LearnableType;
import ec101.learnfrench.Learn.Word;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultQuestionBuilder implements QuestionBuilder<Learnable> {

    private final QuestionBuilder wordQuestionBuilder = new WordQuestionBuilder();

    public DefaultQuestionBuilder(){
        super();
    }

    @Override
    public Question buildQuestion(Learnable learnable) {
        if(LearnableType.WORD.equals(learnable.getLearnableType())){
            return wordQuestionBuilder.buildQuestion((Word)learnable);
        }
        return null;
    }
}
