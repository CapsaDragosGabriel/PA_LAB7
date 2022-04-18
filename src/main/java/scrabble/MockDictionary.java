package scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MockDictionary implements Dictionary {
    Set<String> myDictionary = new HashSet<>();

    public MockDictionary() {

    }

    @Override
    public void readWordsFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int index = line.indexOf('\'');
            if (index != -1)
                line = line.substring(0, index);

            index = line.indexOf('/');
            if (index != -1)
                line = line.substring(0, index);

            myDictionary.add(line.toLowerCase());
        }

    }

    @Override
    public boolean isWord(String str) {
        return myDictionary.contains(str);
    }
}
