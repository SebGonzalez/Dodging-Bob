package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import jeu.Monster;
import GameField.Map;
import RessourcesFactory.RessourcesFactory;

public class panelGame extends JPanel {
	
	Map m;
	Image perso;
	Image background;
	Image background2;
	ArrayList<Monster> listMonster;
	
	int xPerso = 20;
	int yPerso = 300;
	int xImage = 0;
	int compteur = 0;
	int nbImage = 0;
	
	//public static keyListener kl = new keyListener();

	public panelGame(){
		this.setSize(1000,500);
		this.setVisible(true);
		
		Game.borders[0] = 20;
		Game.borders[1] = 20;
		Game.borders[2] = 950;
		Game.borders[3] = 420;
		
		listMonster = new ArrayList<>();
		Map m = new Map();
		RessourcesFactory.loadImage();
		//this.addKeyListener(kl);
		
		try {

			background = ImageIO.read(getClass().getResource("Ressources/background.jpg"));
			background2 = ImageIO.read(getClass().getResource("Ressources/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//kl.shifting();
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(background, xImage, 0, this.getWidth(), this.getHeight(), this);
	    // g.drawImage(background2, xImage+this.getWidth(), 0, this.getWidth(), this.getHeight(), this);
		g.drawRect(Game.borders[0], Game.borders[1], Game.borders[2], Game.borders[3]);
		Game.bob.drawCharacter(g);
       
		m.chargerMap("src/IHM/Ressources/Level/level1.txt", g);

        
        //generateMonster();
        //displayMonster(g);
        
        repaint();
    }
	
	public void resetBackground() {
		if(xImage <= -this.getWidth())
			xImage = 0;
	}
	
	public void generateMonster()  {
		Random r = new Random();
		int valeur  = r.nextInt(1000);
		if(valeur < 5 && listMonster.size() < 50) {
			System.out.println("oui");
			Monster m = new Monster();
			listMonster.add(m);
		}	
	}
	
	public void displayMonster(Graphics g) {
		for(int i=0; i<listMonster.size(); i++) {
				System.out.println("non : " + listMonster.get(i).getX() + " " + listMonster.get(i).getY());
				listMonster.get(i).moveMonster();
				//g.drawImage(listMonster.get(i).getImage(), listMonster.get(i).getX(), listMonster.get(i).getY(), listMonster.get(i).getWidth(), listMonster.get(i).getHeight(), this);
				
				if(listMonster.get(i).getX() <= -96)
					listMonster.remove(i);
		}
	}
	
}

