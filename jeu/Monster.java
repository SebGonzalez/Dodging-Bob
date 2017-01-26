package jeu;

import java.util.Random;

public class Monster extends Entity {
	
	public Monster() {
		Random r = new Random();
		
		this.x = 1096;
		this.y = r.nextInt(500);
		this.width = 96;
		this.height = 96;
		/*this.location = "/IHM/Ressources/fantome.png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void moveMonster() {
			x--;
	}
	
}
