package ec101.learnfrench.Learn;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import ec101.learnfrench.R;
import ec101.learnfrench.Test.DefaultWord;

/**
 * Created by Emmet on 17/06/2017.
 */

public class LearnableItemsLoader {

    public LearnableItemsLoader() {
    }

    public LearnableCollection loadAllLearnableItems(Resources resources) throws IOException {
        String fileName = String.valueOf("french_words.csv");
        InputStream inputStream = resources.getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        HashSet<Learnable> allLearnableItems = new HashSet<>();
        String csvLine = reader.readLine();
        while (csvLine != null) {
            String[] csvLineValues = csvLine.split(",");
            allLearnableItems.add(new DefaultWord(csvLineValues[0], csvLineValues[2], csvLineValues[1]));
            csvLine = reader.readLine();
        }
        reader.close();
        return new DefaultLearnableCollection(allLearnableItems);
    }
}
