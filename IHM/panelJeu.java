package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import jeu.Monstre;
import jeu.Personnage;

public class panelJeu extends JPanel {
	
	Personnage p;
	Image perso;
	Image background;
	Image background2;
	ArrayList<Monstre> listeMonstre;
	ArrayList<Image> imageMonstre;
	
	int xPerso = 20;
	int yPerso = 300;
	int xImage = 0;
	int compteur = 0;
	
	public panelJeu(){
		this.setSize(1000,500);
		this.setVisible(true);
		
		listeMonstre = new ArrayList<>();
		p = new Personnage(xPerso, yPerso, 56,86, "/IHM/Ressources/bob.png");
		
		try {
			perso = p.creerImage();
			//perso = ImageIO.read(getClass().getResourceAsStream("Ressources/bob.png"));
			background = ImageIO.read(getClass().getResource("Ressources/background.jpg"));
			background2 = ImageIO.read(getClass().getResource("Ressources/background.jpg"));
			//boo = ImageIO.read(getClass().getResourceAsStream("Ressources/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {

        g.drawImage(background, xImage, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(background2, xImage+this.getWidth(), 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(perso, xPerso, yPerso, 56,86, this);
        resetBackground();
        if(compteur==3) {
        xImage--;
        compteur=0;
        }
        compteur++;
        
        genererMonstre();
        deplacerMonstre();
        afficherMonstre(g);
        repaint();
       // revalidate();
    }
	
	public void resetBackground() {
		if(xImage <= -this.getWidth())
			xImage = 0;
	}
	
	public void genererMonstre()  {
		Random r = new Random();
		int valeur  = r.nextInt(1000);
		if(valeur < 5 && listeMonstre.size() < 3) {
			System.out.println("oui");
			Monstre m = new Monstre(this.getWidth()+96, r.nextInt(this.getHeight()), 96,96, "/IHM/Ressources/fantome.png");
			listeMonstre.add(m);
			/*try {
				//Image monstre = m.creerImage();
				//imageMonstre.add(monstre);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}	
	}
	
	public void deplacerMonstre() {
		for(int i=0; i<listeMonstre.size(); i++) {
			listeMonstre.get(i).setX(listeMonstre.get(i).getX() - 1);
			if(listeMonstre.get(i).getX() < -96)
				listeMonstre.remove(listeMonstre.get(i));
		}
	}
	
	public void afficherMonstre(Graphics g) {
		for(int i=0; i<listeMonstre.size(); i++) {
			try {
				System.out.println("non : " + listeMonstre.get(i).getX() + " " + listeMonstre.get(i).getY());
				g.drawImage(listeMonstre.get(i).creerImage(), listeMonstre.get(i).getX(), listeMonstre.get(i).getY(), listeMonstre.get(i).getLargeur(), listeMonstre.get(i).getLongueur(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

