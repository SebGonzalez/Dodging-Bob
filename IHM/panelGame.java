package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import jeu.Character;
import jeu.Monster;

public class panelGame extends JPanel {
	
	Character p;
	Image perso;
	Image background;
	Image background2;
	ArrayList<Monster> listMonster;
	
	int xPerso = 20;
	int yPerso = 300;
	int xImage = 0;
	int compteur = 0;
	
	public panelGame(){
		this.setSize(1000,500);
		this.setVisible(true);
		
		listMonster = new ArrayList<>();
		p = new Character();
		
		try {
			perso = p.getImage();
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
		if(valeur < 5 && listMonster.size() < 3) {
			System.out.println("oui");
			Monster m = new Monster();
			listMonster.add(m);
		}	
	}
	
	public void afficherMonstre(Graphics g) {
		for(int i=0; i<listMonster.size(); i++) {
				System.out.println("non : " + listMonster.get(i).getX() + " " + listMonster.get(i).getY());
				listMonster.get(i).moveMonster();
				g.drawImage(listMonster.get(i).getImage(), listMonster.get(i).getX(), listMonster.get(i).getY(), listMonster.get(i).getWidth(), listMonster.get(i).getHeight(), this);
				
				if(listMonster.get(i).getX() <= -96)
					listMonster.remove(i);
		}
	}
	
}
