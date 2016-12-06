import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Spaces extends WheelGui{
	public JLabel[] drawSpaces(){
		final JLabel[] underScores=new JLabel[answer.length()];
		for(int i=0;i<underScores.length;i++){
			if(answer.charAt(i)==' ')
				underScores[i]=new JLabel("   ");
			else
				underScores[i]=new JLabel("__ ");
			panel1.add(underScores[i]);
		}
		frame.repaint();
		return underScores;
	}
	public void clear(JLabel [] underScores){
		panel1.removeAll();
		underScores=null;
		panel1.updateUI();
	}
	public void replaceCorrectLetter(JLabel[] underScores,String guess){
		if(guess.length()==1){
			for(int i=0;i<answer.length();i++){
				if(guess.charAt(0)==answer.charAt(i))
					underScores[i].setText(answer.charAt(i)+"");
			}
		}
		else{
			for(int i=0;i<answer.length();i++){
				if(guess.charAt(i)==answer.charAt(i))
					underScores[i].setText(answer.charAt(i)+"");
			}
		}
	}
}
