package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface QuestionBuilder<A extends Learnable> {

    Question buildQuestion(A learnable);
}
