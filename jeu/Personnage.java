package jeu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personnage{
	
	int x;
	int y;
	int largeur;
	int longueur;
	String location;
	
	public Personnage(int x, int y,int largeur, int longueur, String location) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.longueur = longueur;
		this.location = location;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public BufferedImage creerImage() throws IOException {
		return ImageIO.read(getClass().getResource(location));
	}
	
	public boolean collisionMonstre(Monstre m) {
		if( ((x+largeur) > m.x) && y>m.y+m.longueur && y+longueur< m.y)
			return false;
		else
			return true;
	}
}
