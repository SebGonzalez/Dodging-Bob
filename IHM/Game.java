package IHM;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import jeu.Character;
import GameField.Map;
import PhysicEngine.ShiftingManager;
import PhysicEngine.keyListener;

public class Game extends JFrame{
	
	public static Character bob;
	public static Map map;
	public static Game instance;
	public static int[] borders = new int[4];
	panelGame jeu;
	
	public static keyListener kl = new keyListener();
	
	public Game() {
		this.setSize(720+20,624+40);
		
		jeu = new panelGame();
		this.setContentPane(jeu);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addKeyListener(kl);
		//kl.shifting();
		ShiftingManager.shifting();
		
	}
	
	
	public static void main(String[] args) {
	 bob = new Character();
	 map = new Map("/IHM/Ressources/Level/level1.txt");
   	 instance = new Game();
   }
}
