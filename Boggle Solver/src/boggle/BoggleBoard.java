package boggle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BoggleBoard implements Iterable<BoggleCharacter>{
	private int length;
	private int width;

	private List<BoggleCharacter> board;

	public BoggleBoard(char myLetteres[][]) {
		length = myLetteres.length;
		width = myLetteres[0].length;
		board = new ArrayList<BoggleCharacter>(length * width);
		constructGraph(myLetteres);
	}

	private void constructGraph(char myLetteres[][]) {
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < width; y++) {
				board.add(new BoggleCharacter(myLetteres[x][y]));
			}
		}
		
		BoggleCharacter cur;
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < width; y++) {
				cur = board.get((width * x) + y);
				if (x + 1 < length) {// up
					addConnectedLetters(cur, width * (x + 1) + y);
				}
				if (x - 1 >= 0) {// down
					addConnectedLetters(cur, width * (x - 1) + y);
				}
				if (y + 1 < width) {// right
					addConnectedLetters(cur, width * x + (y + 1));
				}
				if (y - 1 >= 0) {// left
					addConnectedLetters(cur, width * x + (y - 1));
				}
				if (x + 1 < length && y + 1 < width) {// right, up
					addConnectedLetters(cur, width * (x + 1) + (y + 1));
				}
				if (x - 1 >= 0 && y - 1 >= 0) {// down, left
					addConnectedLetters(cur, width * (x - 1) + (y - 1));
				}
				if (x - 1 >= 0 && y + 1 < width) {// down, right
					addConnectedLetters(cur, width * (x - 1) + (y + 1));
				}
				if (x + 1 < length && y - 1 >= 0) {// up, left
					addConnectedLetters(cur, width * (x + 1) + (y - 1));
				}
			}
		}
	}

	private void addConnectedLetters(BoggleCharacter x, int index) {
		x.edges.add(board.get(index));
	}

	public BoggleCharacter getCharacter(int index){
		return board.get(index);
	}
	
	public static char[][] generateBoard(int length, int width){
		Random r = new Random();
		
		char [][] myLetteres = new char [length][width];
		for(int i = 0; i < length; i++){
			for(int j = 0; j < width; j++){
				myLetteres[i][j] = (char)(r.nextInt(26) + 'a');
				System.out.print(" " + myLetteres[i][j]);
			}
			System.out.println();
		}
		return myLetteres;
	}
	
	@Override
	public Iterator<BoggleCharacter> iterator() {
		return board.iterator();
	}
}
