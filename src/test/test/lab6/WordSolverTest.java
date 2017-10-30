package test.lab6;

import lab6.java.WordSolver;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordSolverTest {
    @Test(expected = FileNotFoundException.class)
    public void WordSolverWithInvalidPath() throws Exception {
        WordSolver solver = new WordSolver("fantom.txt");
    }
    @Test(expected = IllegalArgumentException.class)
    public void sortEmpty() throws Exception {
        WordSolver solver = new WordSolver("noWords.txt");
        solver.sort();
    }
    @Test
    public void sort() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        solver.sort();
        assertEquals(solver.getConcatenatedWords().size(), 9);
        assertEquals(solver.getSimpleWords().size(), 9);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getConcatenatedWordByLengthAtNegative() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        solver.getConcatenatedWordByLengthAt(-1);
    }
    @Test
    public void getConcatenatedWordByLengthAt1WithUnsorted() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        ArrayList<String> words = new ArrayList<>();
        words.add("catcatcatcatscatcat");
        assertEquals(solver.getConcatenatedWordByLengthAt(1), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt1() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        ArrayList<String> words = new ArrayList<>();
        words.add("catcatcatcatscatcat");
        assertEquals(solver.getConcatenatedWordByLengthAt(1), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt2() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        ArrayList<String> words = new ArrayList<>();
        words.add("ratsdogscatsdog");
        assertEquals(solver.getConcatenatedWordByLengthAt(2), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt4() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        ArrayList<String> words = new ArrayList<>();
        words.add("catsdogcats");
        words.add("catsdogscat");
        assertEquals(solver.getConcatenatedWordByLengthAt(4), words);
    }
}