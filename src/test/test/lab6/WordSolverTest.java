package test.lab6;

import lab6.java.WordSolver;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordSolverTest {
    private WordSolver solver;

    @Before
    public void init() throws Exception {
        solver = new WordSolver("words0.txt");
    }

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
        solver.sort();
        assertEquals(solver.getFullyConcatenatedWords().size(), 9);
        assertEquals(solver.getPartlyConcatenatedWords().size(), 6);
        assertEquals(solver.getSimpleWords().size(), 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getConcatenatedWordByLengthAtNegative() throws Exception {
        solver.getConcatenatedWordByLengthAt(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getConcatenatedWordByLengthAtOutOfBound() throws Exception {
        solver.getConcatenatedWordByLengthAt(20);
    }
    @Test
    public void getConcatenatedWordByLengthAt1WithUnsorted() throws Exception {
        ArrayList<String> words = new ArrayList<>();
        words.add("catcatcatcatscat");
        assertEquals(solver.getConcatenatedWordByLengthAt(1), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt1() throws Exception {
        WordSolver solver = new WordSolver("words0.txt");
        ArrayList<String> words = new ArrayList<>();
        words.add("catcatcatcatscat");
        assertEquals(solver.getConcatenatedWordByLengthAt(1), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt2() throws Exception {
        ArrayList<String> words = new ArrayList<>();
        words.add("ratsdogscatsdog");
        assertEquals(solver.getConcatenatedWordByLengthAt(2), words);
    }
    @Test
    public void getConcatenatedWordByLengthAt4() throws Exception {
        ArrayList<String> words = new ArrayList<>();
        words.add("catsdogcats");
        words.add("catsdogscat");
        assertEquals(solver.getConcatenatedWordByLengthAt(4), words);
    }
}