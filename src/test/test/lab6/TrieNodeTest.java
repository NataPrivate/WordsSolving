package test.lab6;

import lab6.java.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrieNodeTest {
    private Trie trie;
    private TrieNode node;

    @Before
    public void init() {
        trie  = new Trie();
        trie.insert("cat");
        node = trie.getFinalNode("cat");
    }

    @Test
    public void getNodeWithExistingLetter() {
        trie.insert("cats");
        assertTrue(node.getNodeWithLetter('s') != null);
    }
    @Test
    public void getNodeWithUnexistingLetter() {
        assertTrue(node.getNodeWithLetter('s') == null);
    }
}