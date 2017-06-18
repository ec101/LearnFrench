package ec101.learnfrench.Learn;

import java.util.List;
import java.util.Set;

/**
 * Created by Emmet on 17/06/2017.
 */

public interface LearnableCollection {

    List<String> getSubjects();

    Set<Learnable> getAllLearnablesForASubject(String subject);

    Set<Learnable> getAllLearnablesItems();
}
