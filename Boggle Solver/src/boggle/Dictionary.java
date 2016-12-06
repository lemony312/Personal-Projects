package boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import prefixtree.Trie;

public class Dictionary {

	private static Dictionary singleton = new Dictionary();
	
	private Trie dictionary;
	
	public static void main (String[] args) {
		Dictionary dictionary = Dictionary.getInstance();
//		for(String wString : dictionary.getAllStrings()){
//			System.out.println(wString);
//		}
		System.out.println(dictionary.contains("piupiu"));
	}
	
	private Dictionary() {
		dictionary = new Trie();
		readDictionaryFile();
	}

	protected void readDictionaryFile() {
		File data = new File("dictionaryForJava.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(data);
			while (scanner.hasNextLine()) {
				dictionary.addWord(scanner.nextLine().toLowerCase());
			}
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("Could not open file");
		}
	
		if (scanner != null) {
			scanner.close();
		}
	}
	
	public static Dictionary getInstance(){
		return singleton;
	}
	
	public boolean isValidPrefix(String prefix){
		return dictionary.isValidPrefix(prefix);
	}
	
	public boolean contains(String word){
		return dictionary.contains(word);
	}
	
	public List<String> getAllStrings() {
		return dictionary.getAllStrings();
	}
	
	public int size(){
		return dictionary.size();
	}
}
