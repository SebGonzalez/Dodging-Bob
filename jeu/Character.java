package jeu;

import java.awt.Graphics;

import RessourcesFactory.RessourcesFactory;

public class Character extends Entity {
	
	int nbImage = 0;
	
	public Character() {
		this.x = 20;
		this.y = 300;
		this.width = 56;
		this.height = 86;
		/*this.location = "/IHM/Ressources/bob_sprite.png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public boolean collisionMonster(Monster m) {
		if( ((x+width) > m.x) && y>m.y+m.height && y+height< m.y)
			return false;
		else
			return true;
	}
	
	public void moveHorizontal(int x) {
		this.x += x;
	}
	
	public void moveVertical(int y) {
		this.y += y;
	}
	
	public void drawCharacter(Graphics g) {
		/*try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		g.drawImage(RessourcesFactory.getImage("character"), x, y, x+56, y+86, 128*nbImage,0,128*(nbImage+1),222,null);

	nbImage++;
	if(nbImage == 4) {
		nbImage = 0;
	}
	}
}
