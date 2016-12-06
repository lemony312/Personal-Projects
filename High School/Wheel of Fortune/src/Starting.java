import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Starting extends WheelGui{
	static ActionListener resetSpinQuote;
	static ActionListener resetBuyVowel;
	static ActionListener resetSolv;

	public void intro(final Wheel []player){
		final JLabel label = new JLabel();
		int timerDelay = 1000;
		new Timer(timerDelay , new ActionListener() {
			int timeLeft = 4;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					label.setText(player[whichPlayer].getName()+" ready?!");
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
	public void setName(Wheel[]player){
		for(int i=0;i<player.length;i++){
			player[i].setName(JOptionPane.showInputDialog(frame,"Name of player "+(i+1)+": ",null));
		}
	}
	public void setter(){
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.setTitle("Wheel of fortune");
		frame.setLocation(290, 5);
	}
	public void howManyPlayers(boolean f){
		if(f)
			setter();
		String number="";
		boolean temp=false;
		while(!temp){
			number=JOptionPane.showInputDialog(frame,"How many players do you have?",null);
			if(number==null){
				int c=JOptionPane.showConfirmDialog(null, "would you like to quit?");
				if(c==JOptionPane.CANCEL_OPTION){}
				else if(c==JOptionPane.NO_OPTION){}
				else
					System.exit(0);
			}
			else{
				number=number.trim();
				if(number.trim().length() == 0||number.length()>1||!(isDigit(number)))
					JOptionPane.showMessageDialog(null,"Sorry, you have to say how many people");
				else{
					numberOfPlayers=Integer.parseInt(number);
					if(numberOfPlayers<3)
						JOptionPane.showMessageDialog(null,"Sorry, that is not enough people to play");
					else	
						temp=true;
				}
			}
		}
	}
	public void reSetUp(final Wheel []player, final phraser p) {	
		//for spinner
		resetSpinQuote=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!first){
					final JLabel label = new JLabel();
					int timerDelay = 1000;
					new Timer(timerDelay , new ActionListener() {
						int timeLeft = 4;

						@Override
						public void actionPerformed(ActionEvent e) {
							if (timeLeft > 0) {
								label.setText("You have already spun: "+check);
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
				else
					check=player[whichPlayer].spin();
				pause=false;
			}
		};
		spinQuote.addActionListener(resetSpinQuote);

		//to buy a vowel
		resetBuyVowel=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buyVow=true;
				if(player[whichPlayer].getPotentialScore()>=250||player[whichPlayer].getTurnScore()>=250){
					v.buyVowel(player,whichPlayer,p);
				}
				else
					JOptionPane.showMessageDialog(null,"Sorry, you don't have enough money to buy a vowel");
				pause=false;
			}
		};
		buyVowel.addActionListener(resetBuyVowel);

		//to solve baord
		resetSolv=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pause=false;
				solvBoard=true;
				boolean temp=false;
				while(!temp){
					inTake.enterSolution();
					if(cancelAnswer){
						pause2=false;
						go=true;
						return;
					}
					else if(guess.equals("-1")){
						JOptionPane.showMessageDialog(null,"Sorry, you took too long");
						temp=true;
					}
					else if(guess.length()>1){
						if(p.checkGuessedAnswer(guess))
							JOptionPane.showMessageDialog(null,"Sorry, that phrase has already been guessed");
						else if(player[whichPlayer].checkForWin(guess)){
							newGame=true;
							sp.replaceCorrectLetter(underScores,guess);
							player[whichPlayer].setFinalScore(player[whichPlayer].getFinalScore()+player[whichPlayer].getPotentialScore()+player[whichPlayer].getTurnScore());
							JOptionPane.showMessageDialog(null,"Congrats you won the round!");
							whichPlayer=0;
							temp=true;
						}
						else{
							JOptionPane.showMessageDialog(null,"Sorry, better luck next time!");
							p.addGuessedAnswer(guess);
							LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
							temp=true;
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Sorry,That is an invalid guess!");	
				}
				go=false;
				pause2=false;
			}
		};
		solv.addActionListener(resetSolv);
	}

	public void setUp( final Wheel []player, final phraser p) throws IOException {		
		final JPanel scorePanel=new JPanel();
		final JPanel namePanel=new JPanel();
		panel=new JPanel(new GridLayout(1,player.length,1,1));
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel(new GridLayout(2,1,1,1));

		panel3.add(new JLabel("Dead letters/phrases:"));
		panel3.add(LettersOrPhGuessed);
		JMenuBar menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu file=new JMenu("File");		
		JMenuItem exit=new JMenuItem("Exit");
		JMenuItem reset=new JMenuItem("Reset");
		file.add(exit);
		file.addSeparator();
		file.add(reset);
		menuBar.add(file);

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		reset.addActionListener(new ActionListener(){//help me
			public void actionPerformed(ActionEvent e){
				spinQuote.removeActionListener(resetSpinQuote);
				buyVowel.removeActionListener(resetBuyVowel);
				solv.removeActionListener(resetSolv);
				resets=true;
				test.intializeWheel();
				sp.clear(underScores);
				LettersOrPhGuessed.setText("");
			}
		});

		//for spinner
		resetSpinQuote=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!first){
					final JLabel label = new JLabel();
					int timerDelay = 1000;
					new Timer(timerDelay , new ActionListener() {
						int timeLeft = 4;

						@Override
						public void actionPerformed(ActionEvent e) {
							if (timeLeft > 0) {
								label.setText("You have already spun: "+check);
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
				else
					check=player[whichPlayer].spin();
				pause=false;
			}
		};
		spinQuote.addActionListener(resetSpinQuote);

		//to buy a vowel
		resetBuyVowel=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buyVow=true;
				if(player[whichPlayer].getPotentialScore()>=250||player[whichPlayer].getTurnScore()>=250)
					v.buyVowel(player,whichPlayer,p);
				else
					JOptionPane.showMessageDialog(null,"Sorry, you don't have enough money to buy a vowel");
				pause=false;
			}
		};
		buyVowel.addActionListener(resetBuyVowel);

		//to solve baord
		resetSolv=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pause=false;
				solvBoard=true;
				boolean temp=false;
				while(!temp){
					inTake.enterSolution();
					if(cancelAnswer){
						pause2=false;
						go=true;
						return;
					}
					else if(guess.equals("-1")){
						JOptionPane.showMessageDialog(null,"Sorry, you took too long");
						temp=true;
					}
					else if(guess.length()>1){
						if(p.checkGuessedAnswer(guess))
							JOptionPane.showMessageDialog(null,"Sorry, that phrase has already been guessed");
						else if(player[whichPlayer].checkForWin(guess)){
							newGame=true;
							sp.replaceCorrectLetter(underScores,guess);
							player[whichPlayer].setFinalScore(player[whichPlayer].getFinalScore()+player[whichPlayer].getPotentialScore()+player[whichPlayer].getTurnScore());
							JOptionPane.showMessageDialog(null,"Congrats you won the round!");
							whichPlayer=0;
							temp=true;
						}
						else{
							JOptionPane.showMessageDialog(null,"Sorry, better luck next time!");
							p.addGuessedAnswer(guess);
							LettersOrPhGuessed.setText(p.getAlreadyGuessed()+"");
							temp=true;
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Sorry,That is an invalid guess!");	
				}
				go=false;
				pause2=false;
			}
		};
		solv.addActionListener(resetSolv);

		final JPanel catagoryPanel = new JPanel();
		catagoryPanel.add(catagory);
		scorePanel.add(new JLabel("Score Board:"));	 
		panel4 = test;
		panel4.setSize(800, 600);//this does nothing in the program
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		namePanel.add(playerName,gbc);
		frame.getContentPane().add(namePanel, gbc);
		frame.getContentPane().add(scorePanel, gbc);
		frame.getContentPane().add(panel, gbc);
		frame.getContentPane().add(catagoryPanel, gbc);
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(panel4, gbc);
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		frame.getContentPane().add(panel2, gbc); 
		frame.getContentPane().add(panel1, gbc);
		frame.getContentPane().add(panel3, gbc);
		frame.setVisible(true);
	}
	public void resetBoard(){
		resets=false;
		howManyPlayers(false);
		Wheel [] player=new Wheel[numberOfPlayers];
		phraser p= new phraser();		
		answer=p.pickPhrase();
		for(int i = 0; i < player.length; i++) {
			player[i] = new Wheel(answer);
		}
		setName(player);
		s.clear();
		s.fixScore(player);
		s.scoreBoard(player);
		int gamesplayed=0;
		reSetUp(player,p);
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
					intro(player);
					nextTurn=false;
					cancelGuess=false;
					cancelAnswer=false;
					first=true;
					while(go){
						int starter=p.getPositionStart();
						System.out.println("s "+starter);
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
						System.out.println("e "+player[whichPlayer].getPosition());
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
	}

}
