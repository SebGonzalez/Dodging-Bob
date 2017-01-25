package IHM;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import jeu.Character;
import GraphicMotor.ShiftingManager;
import GraphicMotor.keyListener;

public class Game extends JFrame{
	
	public static Character bob;
	public static Game instance;
	public static int[] borders = new int[4];
	panelGame jeu;
	
	public static keyListener kl = new keyListener();
	
	public Game() {
		this.setSize(1000,500);
		
		jeu = new panelGame();
		this.setContentPane(jeu);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addKeyListener(kl);
		//kl.shifting();
		ShiftingManager.shifting();
		
	}
	
	
	public static void main(String[] args) throws Exception {
	 bob = new Character();
   	 instance = new Game();
   }
}
