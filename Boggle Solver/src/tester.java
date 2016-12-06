import java.util.Collections;
import java.util.List;
import boggle.BoggleBoard;
import boggle.Solver;

public class tester {

	public static void main (String[] args) {
		char [][] myLetteres = {{'t', 'm', 'd', 'o', 'a'},
								{'i', 'n', 'e', 'c', 'b'},
								{'t', 'r', 'a', 'n', 'c'},
								{'l', 's', 'o', 'm', 'd'},
								{'a', 'd', 'e', 'm', 'f'}};
		
		BoggleBoard board = new BoggleBoard(myLetteres);//(BoggleBoard.generateBoard(4, 4));
		Solver solver = new Solver();
		List<String> validWords = solver.solve(board);
		Collections.sort(validWords);
		for(String word : validWords){
			System.out.println(word);
		}
		System.out.print(validWords.size() + " size");
	}
}
