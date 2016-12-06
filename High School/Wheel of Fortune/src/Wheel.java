import java.util.Random;

public class Wheel {
	private static String phrase="";
	private String[]wheelValues= new String[24];
	private int finalScore;
	private int potentialScore;
	private int turnScore;
	private String name;
	int position=0;
	private int freeSpinCount=0;
	
	public Wheel(String answer){
		createWheel();
		phrase=answer;
	}
	public Wheel(){}
	public int spin(){
		Random rand = new Random();
		position = rand.nextInt(24);
		if (position==3)
			return -1;
		else if (position==12)
			return -2;
		else if (position==17)
			return -3;
		else 
			return Integer.parseInt(wheelValues[position]);	
	}
	public int check(String letter){
		int count=0;
		for(int i=0;i<getPhrase().length();i++){
			if(letter.equals(getPhrase().charAt(i)+""))
				count++;
		}
		return count;
	}
	public boolean checkForWin(String words){
		if(words.equals(phrase))
			return true;
		return false;
	}
	public void createWheel(){
		wheelValues[0]="300";
		wheelValues[1]="100";
		wheelValues[2]="700";
		wheelValues[3]="Bankrupt";
		wheelValues[4]="300";
		wheelValues[5]="400";
		wheelValues[6]="600";
		wheelValues[7]="400";
		wheelValues[8]="100";
		wheelValues[9]="200";
		wheelValues[10]="300";
		wheelValues[11]="400";
		wheelValues[12]="Lose a Turn";
		wheelValues[13]="500";
		wheelValues[14]="300";
		wheelValues[15]="200";
		wheelValues[16]="100";
		wheelValues[17]="Spin Again!";
		wheelValues[18]="200";
		wheelValues[19]="300";
		wheelValues[20]="400";
		wheelValues[21]="500";
		wheelValues[22]="100";
		wheelValues[23]="200";

	}
	public void clear(){
		finalScore=0;
		potentialScore=0;
		turnScore=0;
		position=0;
		name=null;
	}
	public static String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		Wheel.phrase = phrase;
	}
	public int getPotentialScore() {
		return potentialScore;
	}

	public void setPotentialScore(int potentialScore) {
		this.potentialScore = potentialScore;
	}

	public int getTurnScore() {
		return turnScore;
	}

	public void setTurnScore(int turnScore) {
		this.turnScore = turnScore;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getFreeSpinCount() {
		return freeSpinCount;
	}
	public void setFreeSpinCount(int freeSpinCount) {
		this.freeSpinCount = freeSpinCount;
	}

}
