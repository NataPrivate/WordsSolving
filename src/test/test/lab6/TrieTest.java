package test.lab6;

import org.junit.Before;
import org.junit.Test;
import lab6.java.Trie;

import static org.junit.Assert.*;

public class TrieTest {
    private Trie trie;

    @Before
    public void init() {
        trie = new Trie();
        trie.insert("cat");
    }

    @Test
    public void getNodeWithEmptyChar() throws Exception {
        assertTrue(trie.getFinalNode("") == null);
    }
    @Test
    public void getUnexistedNode() throws Exception {
        assertTrue(trie.getFinalNode("dog") == null);
    }
    @Test
    public void getExistedNode() throws Exception {
        assertTrue(trie.getFinalNode("cat") != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertEmprty() throws Exception {
        trie.insert("");
    }
    @Test
    public void insert() throws Exception {
        trie.insert("cats");
        assertTrue(trie.getFinalNode("cats") != null);
    }
}