package jeu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends Entity {
	
	public Character() {
		this.x = 20;
		this.y = 300;
		this.width = 56;
		this.height = 86;
		this.location = "/IHM/Ressources/bob.png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean collisionMonster(Monster m) {
		if( ((x+width) > m.x) && y>m.y+m.height && y+height< m.y)
			return false;
		else
			return true;
	}
}
