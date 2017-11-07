package lab6.java;

import java.util.ArrayList;


/**
 * Class represent a node of prefix tree which has:
 * a list of subnodes
 * its value(letter) presented by char
 */
public class TrieNode {
    public ArrayList<TrieNode> values;
    public char value;
    public boolean isFinal;

    public TrieNode(char value) {
        values = new ArrayList<>();
        this.value = value;
    }

    public TrieNode getNodeWithLetter(char letter) {
        for (TrieNode node: values)
            if (node.value == letter)
                return node;

        return null;
    }
}
