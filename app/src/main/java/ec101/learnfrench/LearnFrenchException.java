package ec101.learnfrench;

/**
 * Created by Emmet on 09/05/2017.
 */

public class LearnFrenchException extends RuntimeException {

    public LearnFrenchException(String message){
        super(message);
    }

    public LearnFrenchException(String message, Exception ex){
        super(message, ex);
    }
}
