package IHM;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame{
	
	public static Game instance;
	public Game() {
		this.setSize(1000,500);
		
		panelGame jeu = new panelGame();
		this.add(jeu);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
   	 instance = new Game();
   }
}
