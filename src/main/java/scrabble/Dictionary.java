package scrabble;

import java.io.FileNotFoundException;

public interface Dictionary {
    void readWordsFromFile(String path) throws FileNotFoundException;

    boolean isWord(String word);
}
