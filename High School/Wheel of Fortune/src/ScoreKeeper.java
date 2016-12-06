import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScoreKeeper extends Spaces{

	public void fixScore(Wheel []player){
		score=new JLabel[player.length];
	}
	public void scoreBoard(Wheel[] player){
		for(int i=0;i<player.length;i++){
			score[i]=new JLabel(player[i].getName()+": "+player[i].getPotentialScore());
			panel.add(score[i]);
		}
	}
	public void clear(){
		panel.removeAll();
		score=null;
		panel.updateUI();
	}
	public void updateScoreBoardInTurn(Wheel[] player){
		score[whichPlayer].setText(player[whichPlayer].getName()+": "+(player[whichPlayer].getPotentialScore()+player[whichPlayer].getTurnScore()));	
	}
	public void updateScoreBoard(Wheel[] player){
		score[whichPlayer].setText(player[whichPlayer].getName()+": "+(player[whichPlayer].getPotentialScore()));
	}
	public void updateScoreBoardAfter(Wheel[] player){
		for(int i=0;i<player.length;i++){
			score[i].setText(player[i].getName()+": 0");
		}
	}
}
