package lab6.java;

import java.io.*;
import java.util.*;



public class WordSolver {
    public void setFilePath(String filePath) throws FileNotFoundException {
        if (!new File(filePath).isFile())
            throw new FileNotFoundException("The file under this path doesn't exist");
        this.filePath = filePath;
    }
    public List<String> getSimpleWords() {
        return simpleWords;
    }
    public List<String> getPartlyConcatenatedWords() {
        return partlyConcatenatedWords;
    }
    public List<String> getFullyConcatenatedWords() {
        return fullyConcatenatedWords;
    }

    private String filePath;
    private List<String> allWords;
    private List<String> simpleWords;
    private List<String> partlyConcatenatedWords;
    private List<String> fullyConcatenatedWords;

    public WordSolver(String path) throws FileNotFoundException {
        this();
        setFilePath(path);
    }
    public WordSolver() {
        simpleWords = new ArrayList<>();
        partlyConcatenatedWords = new ArrayList<>();
        fullyConcatenatedWords = new ArrayList<>();
    }

    /**
     * Sort all words form file
     * to simpleWords list or concatenatedWords list
     */
    public void sort() {
        allWords = getAllWords();
        for (String word : allWords) {
            if(isConcatenated(true, word))
                fullyConcatenatedWords.add(word);
            else if (!partlyConcatenatedWords.contains(word))
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

        partlyConcatenatedWords.add(word);
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

    @SuppressWarnings("unchecked")
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
        for (String concatenatedWord : fullyConcatenatedWords) {
            wordsWithCurrentLength.clear();
            int currentLength = concatenatedWord.length();
            if (!wordsByLengths.containsKey(currentLength)) {
                for (String word : fullyConcatenatedWords)
                    if (word.length() == currentLength)
                        wordsWithCurrentLength.add(word);
                wordsByLengths.put(currentLength, new ArrayList<>(wordsWithCurrentLength));
            }
        }
        return wordsByLengths;
    }
}
