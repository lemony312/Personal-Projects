package boggle;

import java.util.HashSet;
import java.util.Set;


public class BoggleCharacter{
	public static final int MAX_EDGES = 8;
	
	public Character letter;
	public boolean isVisited;
	public Set<BoggleCharacter> edges;

	public BoggleCharacter(char letter) {
		this.letter = letter;
		this.isVisited = false;
		edges = new HashSet<BoggleCharacter>(MAX_EDGES);
	}
	
	@Override
	public String toString(){
		return letter.toString();
	}
}
