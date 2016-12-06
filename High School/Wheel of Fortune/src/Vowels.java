import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Vowels extends ScoreKeeper{
	public void buyVowel(Wheel[]player, int whichPlayer,phraser p){
		boolean temp=true;
		while(temp){
			if(p.getVowels().size()==0){
				JOptionPane.showMessageDialog(null,"Sorry, all vowels have been bought");
				return;
			}
			String vow=enterVowel(p);	
			if(vow.equals("-3")){
				return;
			}
			else if(vow.equals("-2"))
				JOptionPane.showMessageDialog(null,"Sorry,That is not a vowel!");
			else if(vow.equals("-1"))
				JOptionPane.showMessageDialog(null,"Sorry, you took to long!");
			for(int i=0;i<p.getVowels().size();i++){
				if(vow.equals(p.getVowels().get(i))){
					player[whichPlayer].setTurnScore(player[whichPlayer].getTurnScore()-250);
					int count=player[whichPlayer].check(vow);
					if(count>0){
						JOptionPane.showMessageDialog(null,vow+" is on the board!");
						replaceCorrectLetter(underScores,vow);
						p.addGuessedAnswer(vow);
						LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
					}
					else{
						JOptionPane.showMessageDialog(null,"Sorry, "+vow+" is not on the board");
						p.addGuessedAnswer(vow);
						LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
						go=false;
					}
					updateScoreBoardInTurn(player);
					p.removeBoughtVowel(vow);
					return;
				}
			}
			if(temp==true)
				JOptionPane.showMessageDialog(null,"Sorry, That is an invalid answer");
		}
	}
	public String enterVowel(final phraser p){
		final JLabel label = new JLabel();
		int timerDelay = 1000;
		timer=30;
		new Timer(timerDelay , new ActionListener() {
			int timeLeft = 30;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					label.setText("You have " + timeLeft + " seconds to chose a vowel!"+ p.getVowels());
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
			String vow=JOptionPane.showInputDialog(frame,label,null);
			if(timer==0&&vow==null){
				vow="-1";
			}
			else if(vow==null){
				return"-3";
			}
			else{
				if (vow.trim().length() > 0){
					vow=vow.trim();
					if(vow.length()>1){
						vow= "-2";
					}
					vow=vow.toLowerCase();
				}
				else{
					vow= "-2";
				}
			}
			return vow;
	}

}
