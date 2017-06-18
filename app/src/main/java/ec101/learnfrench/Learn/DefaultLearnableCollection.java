package ec101.learnfrench.Learn;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Emmet on 17/06/2017.
 */

public class DefaultLearnableCollection implements LearnableCollection {

    public static DefaultLearnableCollection LEARNABLES;

    private Set<Learnable> allLearnableItems;
    private Set<String> subjects;

    DefaultLearnableCollection(Set<Learnable> learnableItems) {
        allLearnableItems = learnableItems;
        subjects = new TreeSet<String>();
        for(Learnable learnable : allLearnableItems){
            if(learnable.getLearnableType().equals(LearnableType.WORD)){
                Word word = (Word)learnable;
                if(!subjects.contains(word.getSubject())){
                    subjects.add(word.getSubject());
                }
            }
        }
        LEARNABLES = this;
    }

    @Override
    public Set<String> getSubjects() {
        return subjects;
    }

    @Override
    public Set<Learnable> getAllLearnablesForASubject(String subject) {
        HashSet<Learnable> learnables = new HashSet<Learnable>();
        for(Learnable learnable : allLearnableItems){
            if(learnable.getLearnableType().equals(LearnableType.WORD)){
                Word word = (Word)learnable;
                if(subject.equals(word.getSubject())){
                    learnables.add(learnable);
                }
            }
        }
        return learnables;
    }

    @Override
    public Set<Learnable> getAllLearnablesItems() {
        return allLearnableItems;
    }
}
