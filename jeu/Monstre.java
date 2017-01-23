package jeu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monstre{
	
	int x;
	int y;
	int largeur;
	int longueur;
	String location;
	
	public Monstre(int x, int y, int largeur, int longueur, String location) {
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

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public BufferedImage creerImage() throws IOException {
		return ImageIO.read(getClass().getResourceAsStream(location));
	}

}
