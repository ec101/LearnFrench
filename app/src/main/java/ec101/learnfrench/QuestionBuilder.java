package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

interface QuestionBuilder<A extends Learnable> {

    Question buildQuestion(A learnable);
}
