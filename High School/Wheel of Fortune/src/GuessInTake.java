import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GuessInTake extends WheelGui{
	public boolean enterGuess(){
		final JLabel label = new JLabel();
		int timerDelay = 1000;
		timer=30;
		new Timer(timerDelay , new ActionListener() {
			int timeLeft = 30;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					label.setText("You have " + timeLeft + " seconds to guess a letter!");
					timeLeft--;
				} else {
					((Timer)e.getSource()).stop();
					Window win = SwingUtilities.getWindowAncestor(label);
					win.setVisible(false);
				}
				timer=timeLeft;
			}
		}){
			{setInitialDelay(0);}}.start();
			guess=JOptionPane.showInputDialog(frame,label,null);
			if(timer==0&&guess==null)
				guess="-1";
			else if(guess==null){
				cancelGuess=true;
				first=false;
			}
			else{
				if (guess.trim().length() > 0){
					guess=guess.trim();
					if(guess.length()>1){
						JOptionPane.showMessageDialog(null,"Sorry, that is an invalid guess!");
						return false;
					}
					guess=guess.toLowerCase();
					first=true;
					nextTurn=true;
				}
				else{
					JOptionPane.showMessageDialog(null,"Sorry,That is an invalid guess!");	
					return false;
				}
			}
			return true;
	}
	public void enterSolution(){
		final JLabel label = new JLabel();
		int timerDelay = 1000;
		new Timer(timerDelay , new ActionListener() {
			int timeLeft = 30;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					label.setText("You have " + timeLeft + " seconds to solve the puzzle!");
					timeLeft--;
				} else {
					((Timer)e.getSource()).stop();
					Window win = SwingUtilities.getWindowAncestor(label);
					win.setVisible(false);
				}
				timer=timeLeft;
			}
		})
		{
			{setInitialDelay(0);}}.start();
			guess=JOptionPane.showInputDialog(frame,label,null);
			if(timer==0&&guess==null)
				guess="-1";
			else if(guess==null)
				cancelAnswer=true;
			else{
				nextTurn=true;
				guess=guess.trim();
				guess=guess.toLowerCase();
				first=true;
			}
	}
}
