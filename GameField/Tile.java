package GameField;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;


public class Tile {

	boolean plein;
	int nb;
	File fichierBmp;
	Image img;
	
	public Tile(String statut, int nb, File fichierBmp) {
		if(statut.equals("ciel"))
			plein = false;
		else
			plein = true;
		this.nb = nb;
		this.fichierBmp = fichierBmp;
		img = BMPImage.readBMP(fichierBmp, nb);
	}

	public boolean isPlein() {
		return plein;
	}

	public void setPlein(boolean plein) {
		this.plein = plein;
	}
	
	public void drawTile(Graphics g, int i, int y, int taille) {
		//taille = taille*2;
		g.drawImage(img, i*(taille*2), y*taille*2,(i+1)*(taille*2), (y+1)*(taille*2), taille*nb, 0,taille*(nb+1), 16, null);
	}

	
}
