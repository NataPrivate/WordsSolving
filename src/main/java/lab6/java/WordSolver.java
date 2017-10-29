package lab6.java;

import java.io.*;
import java.util.*;

public class WordSolver {
    private String filePath;
    private List<String> allWords;
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
        allWords = getAllWords();
        for (String word : allWords) {
            if(isConcatenated(true, word))
                concatenatedWords.add(word);
            else
                simpleWords.add(word);
        }
    }
    private List<String> getAllWords() throws IllegalArgumentException {
        List<String> allWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String newWord;
            while ((newWord = reader.readLine()) != null && !allWords.contains(newWord))
                allWords.add(newWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (allWords.size() == 0)
            throw new IllegalArgumentException();
        return allWords;
    }
    private boolean isConcatenated(boolean isEntire, String word) {
        List<String> possibleStartWords = getStartStrings(isEntire, word);
        if (possibleStartWords.size() == 0)
            return false;

        for (String startWord : possibleStartWords)
            if (word.equals(startWord) || isConcatenated(false, word.substring(startWord.length())))
                return true;
        return false;
    }
    private List<String> getStartStrings(boolean isEntireWord, String expectedString) {
        List<String> startStrings = new ArrayList<>();
        for (String word : allWords)
            if (expectedString.startsWith(word)) {
                if (isEntireWord && expectedString.equals(word))
                    continue;

                startStrings.add(word);
            }
        return startStrings;
    }

    public List<String> getConcatenatedWordByLengthAt(int indexFromEnd) throws IllegalArgumentException {
        if (indexFromEnd < 1)
            throw new IllegalArgumentException();

        if (allWords == null)
            sort();
        Map<Integer, List<String>> wordsByLength = getWordsByLength();
        Map<Integer, List<String>> sortedWordsByLength = new TreeMap<>(wordsByLength);
        return (List<String>)sortedWordsByLength.values().toArray()[sortedWordsByLength.size() - indexFromEnd];
    }
    private Map<Integer, List<String>> getWordsByLength() {
        Map<Integer, List<String>> wordsByLengths = new HashMap<>();
        List<String> wordsWithCurrentLength = new ArrayList<>();
        for (String concatenatedWord : concatenatedWords) {
            wordsWithCurrentLength.clear();
            int currentLength = concatenatedWord.length();
            if (!wordsByLengths.containsKey(currentLength)) {
                for (String word : concatenatedWords)
                    if (word.length() == currentLength)
                        wordsWithCurrentLength.add(word);
                wordsByLengths.put(currentLength, new ArrayList<>(wordsWithCurrentLength));
            }
        }
        return wordsByLengths;
    }
}
