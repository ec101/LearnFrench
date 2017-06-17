package ec101.learnfrench.Test;

import ec101.learnfrench.Learn.Learnable;

/**
 * Created by Emmet on 05/04/2017.
 */

interface QuestionBuilder<A extends Learnable> {

    Question buildQuestion(A learnable);
}
