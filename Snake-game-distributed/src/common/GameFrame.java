package common;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame(GamePanel gp){
		
		this.add(gp);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
