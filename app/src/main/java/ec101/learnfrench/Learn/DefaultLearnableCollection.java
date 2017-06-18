package ec101.learnfrench.Learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Emmet on 17/06/2017.
 */

public class DefaultLearnableCollection implements LearnableCollection {

    public static DefaultLearnableCollection LEARNABLES;

    private Map<String, Set<Learnable>> allLearnableItemsBySubject;

    DefaultLearnableCollection(Set<Learnable> learnableItems) {
        buildMapOfLearnableItems(learnableItems);
        LEARNABLES = this;
    }

    private void buildMapOfLearnableItems(Set<Learnable> learnableItems) {
        allLearnableItemsBySubject = new HashMap<String, Set<Learnable>>();
        for(Learnable learnable : learnableItems){
            if(learnable.getLearnableType().equals(LearnableType.WORD)){
                Word word = (Word)learnable;
                Set<Learnable> learnables = allLearnableItemsBySubject.get(word.getSubject());
                if(learnables == null){
                    learnables = new HashSet<>();
                    allLearnableItemsBySubject.put(word.getSubject(), learnables);
                }
                learnables.add(word);
            }
        }
    }

    @Override
    public List<String> getSubjects() {
        List<String> result = new ArrayList<>(allLearnableItemsBySubject.keySet());
        Collections.sort(result);
        return result;
    }

    @Override
    public Set<Learnable> getAllLearnablesForASubject(String subject) {
        return allLearnableItemsBySubject.get(subject);
    }

    @Override
    public Set<Learnable> getAllLearnablesItems() {
        HashSet<Learnable> allItems = new HashSet<>();
        for(Set<Learnable> learnables : allLearnableItemsBySubject.values()){
            allItems.addAll(learnables);
        }
        return allItems;
    }
}
