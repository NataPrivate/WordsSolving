package lab6.java;


public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }

    /**
     * Method extends trie with new word
     * @param word
     * @throws IllegalArgumentException
     */
    public void insert(String word) throws IllegalArgumentException {
        if (word.isEmpty())
            throw new IllegalArgumentException("Empty string isn't real value to insert, agree?");

        TrieNode currentNode = root;
        char currentLetter;
        TrieNode nextNode;

        for (int i = 0; i < word.length(); i++) {
            currentLetter = word.charAt(i);
            nextNode = currentNode.getNodeWithLetter(currentLetter);
            if (nextNode == null) {
                nextNode = new TrieNode(currentLetter);
                currentNode.values.add(nextNode);
                currentNode = nextNode;
            }
            else
                currentNode = nextNode;
        }

        currentNode.isFinal = true;
    }

    public TrieNode getFinalNode(String word) {
        TrieNode currentNode = root;
        if (word.isEmpty())
            return currentNode;

        char currentLetter;
        TrieNode nextNode;
        for (int i = 0; i < word.length(); i++) {
            currentLetter = word.charAt(i);
            nextNode = currentNode.getNodeWithLetter(currentLetter);
            if (nextNode != null)
                currentNode = nextNode;
            else
                return null;
        }

        if (currentNode.isFinal)
            return currentNode;

        return null;
    }
}
