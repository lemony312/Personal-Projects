import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WheelClass extends JPanel{
	ImageIcon image = new ImageIcon("WheelofFortune.png");  
	JPanel rotationPanel;  
	final int WIDTH = 550;  
	final int HEIGHT = 550;  
	static double degrees;  

	public WheelClass() {  
		super();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));    
		setLayout(null);  
		setFocusable(true);  
		rotationPanel = new JPanel(); 

		rotationPanel = new turningCanvas();  
		rotationPanel.setPreferredSize(new Dimension(image.getIconWidth(),image.getIconHeight()));    
		add(rotationPanel);  
		rotationPanel.setBounds(WIDTH/4, HEIGHT/4, rotationPanel.getPreferredSize().width, rotationPanel.getPreferredSize().height);  
		degrees = 7;  
	}  
	public void intializeWheel(){
		degrees=7;
		repaint();
	}
	public void paintComponent (Graphics g)  {  
		super.paintComponent(g);  
	}  
	public class turningCanvas extends JPanel  
	{  
		public void paintComponent (Graphics g)  {  
			super.paintComponent(g);  
			Graphics2D g2d = (Graphics2D)g;  
			g2d.rotate(Math.toRadians(degrees),image.getIconWidth()/2,image.getIconHeight()/2);
			image.paintIcon(this, g2d, 0, 0);  
		}  
	}   
	public void rotate(int sleep, int degree, boolean positive){
		int decision;
		if(positive)
			decision=1;
		else
			decision=-1;
		for(int i=0;i<degree;i++){
			degrees+=decision;
			repaint();
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void spinner(int start,int end){
		if(start>end){
			end=(24-start)+end;
			start=0;
			if(end<15)
				end+=24;
		}
		else if((end-start)<15){
			end=(end-start)+24;
			start=0;
		}	
		for(int i=start;i<end;i++){
			if(i==end-1){
				rotate(13,15,true);
				rotate(30,6,true);
				rotate(40,12,false);
				rotate(50,6,true);
			}
			else if((end-i)<2)
				rotate(13,15,true);
			else if((end-i)<4)
				rotate(9,15,true);
			else if((end-i)<10)
				rotate(7,15,true);
			else 
				rotate(5,15,true);
		}
	}
	public Dimension getPreferredSize() {
		return image == null ? new Dimension(500, 500) : new Dimension(image.getIconWidth(), image.getIconHeight());
	}
}