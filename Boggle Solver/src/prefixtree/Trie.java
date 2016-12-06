package prefixtree;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Trie {

	private TrieNode root;
	public static final char NULL = '0';
	public static final int ZERO = 97;
	public static final int SIZE_OF_ALPHEBET = 26;

	private int size;

	public Trie() {
		root = new TrieNode(NULL, false);
		size = 0;
	}

	public void addWord(String word) {
		word = word.toLowerCase();

		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			if (current.children[word.charAt(i) - ZERO] == null) {
				current.children[word.charAt(i) - ZERO] = new TrieNode(word.charAt(i), false);
			}
			current = current.children[word.charAt(i) - ZERO];
		}
		if (!current.endOfWord) {
			current.endOfWord = true;
			size++;
		}
	}

	public boolean contains(String word) {
		word = word.toLowerCase();

		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			if (current.children[word.charAt(i) - ZERO] == null) {
				return false;
			}
			current = current.children[word.charAt(i) - ZERO];
		}
		return current.endOfWord;
	}

	public List<String> getAllStrings() {
		return getStrings(root, "", new LinkedList<String>());
	}

	private List<String> getStrings(TrieNode current, String s, List<String> list) {
		if (current != null) {
			if (current.endOfWord) {
				list.add(s);
			}

			for (int i = 0; i < SIZE_OF_ALPHEBET; i++) {
				if (current.children[i] != null) {
					getStrings(current.children[i], s + current.children[i], list);
				}
			}
		}
		return list;
	}

	public List<String> getStartsWith(String prefix) {
		TrieNode current = root;

		for (int i = 0; i < prefix.length(); i++) {
			if (current.children[prefix.charAt(i) - ZERO] != null) {
				current = current.children[prefix.charAt(i) - ZERO];
			} else {
				return new ArrayList<String>();
			}
		}
		return getStrings(current, prefix, new ArrayList<String>());
	}

	public boolean isValidPrefix(String prefix) {
		TrieNode current = root;

		for (int i = 0; i < prefix.length(); i++) {
			if (current.children[prefix.charAt(i) - ZERO] != null) {
				current = current.children[prefix.charAt(i) - ZERO];
			} else {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb, NULL);
		return sb.toString().trim();
	}

	private void buildString(TrieNode node, StringBuilder sb, int layer) {
		for (int i = 0; i < layer; i++) { // print some indentation
			sb.append(" ");
		}
		sb.append(node); // print the node itself
		sb.append("\n");
		for (TrieNode child : node.children) { // recursively print each subtree
			if (child != null) {
				buildString(child, sb, layer + 1);
			}
		}
	}

	public int size() {
		return size;
	}

	private class TrieNode {
		public char letter;
		public boolean endOfWord;
		public TrieNode[] children;

		public TrieNode(char letter, boolean endOfWord) {
			this.letter = letter;
			this.endOfWord = endOfWord;
			children = new TrieNode[SIZE_OF_ALPHEBET];
		}

		public String toString() {
			return Character.toString(letter);
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.addWord("apples");

		trie.addWord("hello");
		trie.addWord("hellos");
		trie.addWord("help");
		trie.addWord("how");
		trie.addWord("heep");
		trie.addWord("apple");

		System.out.println(trie.getAllStrings());
		System.out.println(trie.getStartsWith("he"));
	}
}
