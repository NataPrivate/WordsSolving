package lab6.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordSolver {
    private String filePath;
    private List<String> simpleWords;
    private List<String> concatenatedWords;

    public void setFilePath(String filePath) throws FileNotFoundException {
        if (!new File(filePath).isFile())
            throw new FileNotFoundException("The file under this path doesn't exist");
        this.filePath = filePath;
    }
    public List<String> getSimpleWords() {
        return simpleWords;
    }
    public List<String> getConcatenatedWords() {
        return concatenatedWords;
    }

    public WordSolver(String path) throws FileNotFoundException {
        this();
        setFilePath(path);
    }
    public WordSolver() {
        simpleWords = new ArrayList<>();
        concatenatedWords = new ArrayList<>();
    }

    /**
     * Sort all words form file
     * to simpleWords list or concatenatedWords list
     */
    public void sort() {
        List<String> allWords = getAllWords();
        String substractedWord;
        List<String> possibleStartWords;
        String startWord;
        boolean isConcatenated;
        boolean isEntireWord;
        for (String word : allWords) {
            substractedWord = word;
            isConcatenated = false;
            isEntireWord = true;
            while (substractedWord.length() != 0) {
                isConcatenated = false;
                possibleStartWords = getStartStrings(isEntireWord, substractedWord, allWords);
                if (possibleStartWords.size() == 0) {
                    simpleWords.add(word);
                    break;
                }
                else {
                    isConcatenated = true;
                    isEntireWord = false;
                    startWord = getMaxStartWord(possibleStartWords);
                    substractedWord = substractedWord.substring(startWord.length());
                }
            }
            if (isConcatenated)
                concatenatedWords.add(word);
        }
    }

    private String getMaxStartWord(List<String> startStrings) {
        String maxStartWord = startStrings.get(0);
        int maxLength = startStrings.get(0).length();
        int currentLength;
        for (String startWord : startStrings) {
            currentLength = startWord.length();
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStartWord = startWord;
            }
        }
        return maxStartWord;
    }

    private List<String> getStartStrings(boolean isEntireWord, String expectedString, List<String> allWords) {
        List<String> startStrings = new ArrayList<>();
        for (String word : allWords)
            if (expectedString.startsWith(word)) {
                if (isEntireWord && expectedString.equals(word))
                    continue;

                startStrings.add(word);
            }
        return startStrings;
    }

    private List<String> getAllWords() {
        List<String> allWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String newWord;
            while ((newWord = reader.readLine()) != null)
                allWords.add(newWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allWords;
    }
}
