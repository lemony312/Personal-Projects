package prefixtree;

public class TrieNode {
	char letter;
    TrieNode[] links;
    boolean fullWord;
  
    TrieNode(char letter, boolean fullWord)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = fullWord;
    }

}
