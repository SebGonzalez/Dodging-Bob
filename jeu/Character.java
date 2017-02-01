package jeu;

import java.awt.Graphics;

import IHM.Game;
import RessourcesFactory.RessourcesFactory;

public class Character extends Entity {
	
	int nbImage = 0;
	public boolean moving = false;
	
	public Character() {
		this.x = 20;
		this.y = 500;
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
	
	public boolean collisionWall(String type) {
		if(type.equals("right")) {
			int xTile = (x+width-25)/48;
			int yTile = (y)/48;
			int yTilebas = (y+height-10)/48;
			if(Game.map.positionTile[xTile][yTile] != 0 || Game.map.positionTile[xTile][yTilebas] != 0) {
				return true;
			}
		}
		else if(type.equals("left")) {
			int xTile = (x)/48;
			int yTile = (y)/48;
			int yTilebas = (y+height-10)/48;
			if(Game.map.positionTile[xTile][yTile] != 0 || Game.map.positionTile[xTile][yTilebas] != 0) {
				return true;
			}
		}
		else if(type.equals("down")) {
			int xTile = (x+width/2)/48;
			int yTile = (y+height)/48;
			if(Game.map.positionTile[xTile][yTile] != 0) {
				return true;
			}
		}
		else if(type.equals("up")) {
			int xTile = (x+width/2)/48;
			int yTile = (y-3)/48;
			if(Game.map.positionTile[xTile][yTile] != 0) {
				return true;
			}
		}
		return false;
			
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
		
		if(moving) {	
			nbImage++;
			if(nbImage == 4) {
				nbImage = 0;
				}
		}
		else {
			nbImage = 2;
		}
	}
}
