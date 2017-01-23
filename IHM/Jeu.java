package IHM;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Jeu extends JFrame{
	
	public static Jeu instance;
	public Jeu() {
		this.setSize(1000,500);
		
		panelJeu jeu = new panelJeu();
		this.add(jeu);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
   	 instance = new Jeu();
   }
}
