import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



@SuppressWarnings("serial")
public class phraser extends WheelGui {
	private ArrayList<String> phrases=new ArrayList<String>();
	private ArrayList<String> alreadyGuessed=new ArrayList<String>();
	private ArrayList<String> vowels=new ArrayList<String>();
	private int positionStart=0;
	public phraser() {
		loadPhrases();
		loadNouns();
		intializeVowels();
	}
	public void loadPhrases(){//reads/intializes arraylist phrases from a file
		File data = new File("phrases.txt");
		Scanner scanner=null;
		try {
			scanner = new Scanner(data);
			while (scanner.hasNextLine()) {
				phrases.add(scanner.nextLine().substring(7).toLowerCase());
			}
		} catch (FileNotFoundException fnfe) {}
		if(scanner!=null)scanner.close();
	}
	public void loadNouns(){//reads/intializes arraylist phrases from a file
		File data = new File("nouns.txt");
		Scanner scanner=null;
		try {
			scanner = new Scanner(data);
			while (scanner.hasNextLine()) {
				phrases.add(scanner.nextLine().substring(5).toLowerCase());
			}
		} catch (FileNotFoundException fnfe) {}
		if(scanner!=null)scanner.close();
	}
	public void intializeVowels(){
		vowels.add("a");
		vowels.add("e");
		vowels.add("i");
		vowels.add("o");
		vowels.add("u");
	}
	public boolean checkGuessedVowels(String guess){
		if(vowels.size()==0)
			return false;
		for (int i=0;i<vowels.size();i++){
			if(vowels.get(i).equals(guess))
				return true;
		}
		return false;
	}
	public void addGuessedAnswer(String guess){
		alreadyGuessed.add(guess);
	}
	public void removeBoughtVowel(String guess){
		vowels.remove(guess);
	}
	public boolean checkGuessedAnswer(String guess){
		if(alreadyGuessed.size()==0)
			return false;
		for (int i=0;i<alreadyGuessed.size();i++){
			if(alreadyGuessed.get(i).equals(guess))
				return true;
		}
		return false;
	}
	public String pickPhrase(){
		Random rand = new Random();
		int index = rand.nextInt(phrases.size());
		if(index<242)
			catagory.setText("Catagory: common phrase");
		else
			catagory.setText("Catagory: Nouns");
		System.out.println(phrases.get(index));
		return phrases.get(index);
	}

	public ArrayList<String> getAlreadyGuessed() {
		insertionSort();
		return alreadyGuessed;
	}
	public void insertionSort(){
		for(int i=1;i<alreadyGuessed.size();i++){
			int index=i;
			String temp=alreadyGuessed.get(i);
			while(index>0&&alreadyGuessed.get(index-1).compareTo(temp)>0){
				alreadyGuessed.set(index,alreadyGuessed.get(index-1));
				index--;
			}
			alreadyGuessed.set(index, temp);
		}
	}
	public void reset(){
		alreadyGuessed.clear();;
		vowels.clear();
		intializeVowels();
		positionStart=0;
	}
	public ArrayList<String> getPhrases() {
		return phrases;
	}
	public void setPhrases(ArrayList<String> phrases) {
		this.phrases = phrases;
	}
	public ArrayList<String> getVowels() {
		return vowels;
	}
	public void setVowels(ArrayList<String> vowels) {
		this.vowels = vowels;
	}
	public void setAlreadyGuessed(ArrayList<String> alreadyGuessed) {
		this.alreadyGuessed = alreadyGuessed;
	}
	public int getPositionStart() {
		return positionStart;
	}
	public void setPositionStart(int positionStart) {
		this.positionStart = positionStart;
	}
}
