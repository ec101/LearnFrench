package ec101.learnfrench.Learn;

import ec101.learnfrench.Learn.Learnable;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface Word extends Learnable {

    String getFrench();

    String getEnglish();

    String getSubject();
}
