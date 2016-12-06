import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class WheelGui extends JPanel{
	static JButton spinQuote=new JButton("Spin");
	static JFrame frame;
	static JPanel panel;
	static JPanel panel1;
	static JPanel panel2;
	static JPanel panel3;
	static JPanel panel4;
	static JLabel playerName=new JLabel();
	static JLabel LettersOrPhGuessed=new JLabel();
	static String guess="-5";
	static String answer="";
	static JButton solv=new JButton("Solve Board");
	static boolean solvBoard=false;
	static WheelClass test = new WheelClass();//for spinning wheel
	static boolean resets=false;
	static JLabel[]score=new JLabel[0];
	static int whichPlayer=0;
	static int check=0;
	static int timer=-1;
	static boolean nextTurn=false;
	static boolean first=true;
	static boolean newGame;
	static boolean pause=true;
	static boolean pause2=true;
	static JLabel[]underScores;
	static JButton buyVowel=new JButton("Buy vowel");
	static boolean go=true;
	static boolean buyVow=false;
	static boolean cancelGuess=false;
	static boolean cancelAnswer=false;
	static boolean cancelVowel=false;
	static boolean sameTurn=true;
	static int numberOfPlayers;
	static GuessInTake inTake = new GuessInTake();
	static Vowels v = new Vowels();
	static ScoreKeeper s = new ScoreKeeper();
	static Spaces sp = new Spaces();
	static Starting st = new Starting();
	static JLabel catagory= new JLabel();
	public static void main(String[] args) throws IOException {
		st.howManyPlayers(true);
		Wheel[] player=new Wheel[numberOfPlayers];
		s.fixScore(player);
		phraser p=new phraser();
		answer=p.pickPhrase();
		for(int i = 0; i < player.length; i++) {
			player[i] = new Wheel(answer);
		}
		st.setUp(player,p);
		st.setName(player);
		s.scoreBoard(player);
		panel2.add(buyVowel);
		panel2.add(spinQuote);
		panel2.add(solv);
		int gamesplayed=0;
		outerloop:
			while(gamesplayed<3){
				if(gamesplayed!=0)
					intializeAnswer(player,p);
				underScores=sp.drawSpaces();
				check=0;
				whichPlayer=gamesplayed;
				newGame=false;
				test.intializeWheel();
				while(!newGame){
					go=true;
					st.intro(player);
					nextTurn=false;
					cancelGuess=false;
					cancelAnswer=false;
					first=true;
					while(go){
						int starter=p.getPositionStart();
						playerName.setText("It is "+player[whichPlayer].getName()+"'s turn!");
						pause=true;
						pause2=true;		
						while(pause){//makes sure user hits spin or solve before it does anything
							try {
								Thread.sleep(250);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(resets)
								break outerloop;
							if (!pause){
								if(first&&solvBoard==false&&buyVow==false&&cancelGuess==false)
									test.spinner(starter,player[whichPlayer].getPosition());//wheel thing
								break;
							}
						}
						cancelGuess=false;
						cancelAnswer=false;
						if(buyVow){}
						else if(solvBoard){
							while(pause2){//makes sure to pause for user
								try {
									Thread.sleep(250);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (!pause2)
									break;
							}
						}
						else if(check==-1){
							if(player[whichPlayer].getFreeSpinCount()>0){
								JOptionPane.showMessageDialog(null,"Lucky you, you can spin again!" );
								player[whichPlayer].setFreeSpinCount(player[whichPlayer].getFreeSpinCount()-1);
							}
							else{
								JOptionPane.showMessageDialog(null,"Bankrupt!" );
								player[whichPlayer].setTurnScore(0);
								player[whichPlayer].setPotentialScore(0);
								go=false;
							}
						}
						else if(check==-2){
							if(player[whichPlayer].getFreeSpinCount()>0){
								JOptionPane.showMessageDialog(null,"Lucky you, you can spin again!" );
								player[whichPlayer].setFreeSpinCount(player[whichPlayer].getFreeSpinCount()-1);
							}
							else{
								JOptionPane.showMessageDialog(null,"Sorry, you lost your turn!");
								//player[whichPlayer].setTurnScore(0);//false rule
								go=false;
							}
						}
						else if(check==-3){
							if(first){
								final JLabel label = new JLabel();
								int timerDelay = 1000;
								new Timer(timerDelay , new ActionListener() {
									int timeLeft = 4;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (timeLeft > 0) {
											label.setText("Free spin!");
											timeLeft--;
										} else {
											((Timer)e.getSource()).stop();
											Window win = SwingUtilities.getWindowAncestor(label);
											win.setVisible(false);
										}
									}
								}){
									{setInitialDelay(0);}}.start();
									JOptionPane.showMessageDialog(null,label);
							}
							go=interpretAnswer(player,underScores,p,true);
							if(go)
								player[whichPlayer].setFreeSpinCount(player[whichPlayer].getFreeSpinCount()+1);
						}
						else{
							if(first){
								final JLabel label = new JLabel();
								int timerDelay = 1000;
								new Timer(timerDelay , new ActionListener() {
									int timeLeft = 4;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (timeLeft > 0) {
											label.setText("You spun: "+check);
											timeLeft--;
										} else {
											((Timer)e.getSource()).stop();
											Window win = SwingUtilities.getWindowAncestor(label);
											win.setVisible(false);
										}
									}
								}){
									{setInitialDelay(0);}}.start();
									JOptionPane.showMessageDialog(null,label);
							}
							go=interpretAnswer(player,underScores,p,false);
						}
						if(!go){
							if(player[whichPlayer].getFreeSpinCount()>0){
								int response=JOptionPane.showConfirmDialog(null, "would you like to use your Free Spin?");
								if(response==JOptionPane.CANCEL_OPTION||response==JOptionPane.NO_OPTION){}
								else if(response==JOptionPane.YES_OPTION){
									go=true;
									player[whichPlayer].setFreeSpinCount(player[whichPlayer].getFreeSpinCount()-1);
								}
							}
						}
						if(!newGame)
							s.updateScoreBoardInTurn(player);
						if(buyVow){buyVow=false;}
						else if(solvBoard){solvBoard=false;}
						else
							p.setPositionStart(player[whichPlayer].getPosition());
					}
					if(!newGame){
						player[whichPlayer].setPotentialScore((player[whichPlayer].getPotentialScore()+player[whichPlayer].getTurnScore()));
						s.updateScoreBoard(player);
						if(whichPlayer==player.length-1)
							whichPlayer=0;
						else
							whichPlayer++;
					}
					player[whichPlayer].setTurnScore(0);
				}
				for(int i=0;i<player.length;i++){
					player[i].setPotentialScore(0);
				}
				s.updateScoreBoardAfter(player);
				sp.clear(underScores);
				p.reset();
				LettersOrPhGuessed.setText("");
				gamesplayed++;
			}
		if(!resets){
			calcWinner(player);
			results(player);
			int response=JOptionPane.showConfirmDialog(null, "would you like to play again?");
			if(response==JOptionPane.CANCEL_OPTION||response==JOptionPane.NO_OPTION){
				JOptionPane.showMessageDialog(null,"BYEEE");
				System.exit(0);
			}
			else if(response==JOptionPane.YES_OPTION){
				resets=true;
				frame1.setVisible(false);
			}
		}
		while(resets){
			resets=false;
			st.resetBoard();
		}
	}
	public static boolean interpretAnswer(Wheel[]player,JLabel[]underScores,phraser p,boolean freeSpin){
		boolean temp=false;
		while(!temp){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean checking=inTake.enterGuess();
			int cont=0;
			if(!checking){}
			else if(cancelGuess){return true;}
			else if(guess.equals("-1")){
				JOptionPane.showMessageDialog(null,"Sorry, you took too long");
				sameTurn=false;
				return false;
			}
			else if(isAlpha(guess)&&(!guess.equals("a"))&&(!guess.equals("e"))&&(!guess.equals("i"))&&(!guess.equals("o"))&&(!guess.equals("u"))){
				if(p.checkGuessedAnswer(guess)){
					temp=false;
					JOptionPane.showMessageDialog(null,"Sorry, that letter has already been guessed");
				}
				else{
					cont=player[whichPlayer].check(guess);
					if(cont>0){
						JOptionPane.showMessageDialog(null,"Congrats you guessed correctly");
						if(!freeSpin)
							player[whichPlayer].setTurnScore(player[whichPlayer].getTurnScore()+cont*check);
						sp.replaceCorrectLetter(underScores,guess);
						p.addGuessedAnswer(guess);
						LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
						sameTurn=true;
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null,"Sorry, better luck next time!");
						p.addGuessedAnswer(guess);
						LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
						sameTurn=false;
						return false;
					}
				}
			}
			else if((guess.equals("a"))||(guess.equals("e"))||(guess.equals("i"))||(guess.equals("o"))||(guess.equals("u")))
				JOptionPane.showMessageDialog(null,"Sorry, you do not have that vowel!");
			else
				JOptionPane.showMessageDialog(null,"Sorry, that is an invalid answer!");
		}
		sameTurn=true;
		return true;
	}
	private static int winnerIndex=-1;
	static JFrame frame1;
	public static void results(Wheel[] player){
		frame1=new JFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLocation(350, 350);
		frame1.setSize(300, 300);
		frame1.setTitle("Results");
		final JPanel panel=new JPanel(new GridLayout(player.length,1,1,1));
		JLabel []score=new JLabel[player.length];

		for(int i=0;i<player.length;i++){
			score[i]=new JLabel(player[i].getName()+" ended with a total score of: "+player[i].getFinalScore());
			panel.add(score[i]);
		}
		String temper=player[winnerIndex].getName()+" won the whole game with a total score of: "+player[winnerIndex].getFinalScore();
		JOptionPane.showMessageDialog(null,temper);
		frame1.getContentPane().add(panel);
		frame1.setVisible(true);
	}
	public static void calcWinner(Wheel[] player){
		for(int i=0;i<player.length-1;i++){
			if(player[i].getFinalScore()>player[i+1].getFinalScore())
				winnerIndex=i;
		}
	}

	public static boolean isAlpha(String guess) {
		if(!Character.isLetter(guess.charAt(0))) 
			return false;
		return true;
	}

	public static boolean isDigit(String guess) {
		if(!Character.isDigit(guess.charAt(0))) 
			return false;
		return true;
	}
	public static void intializeAnswer(final Wheel []player, final phraser p){
		answer=p.pickPhrase();
		for(int i = 0; i < player.length; i++) {
			player[i].setPhrase(answer);
		}
	}
}

