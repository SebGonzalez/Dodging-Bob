package GameField;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
	
	ArrayList<Tile> listTile;
	String fichier;
	int sizeTile = 24;
	String imageBmp = "/IHM/Ressources/Level/";
	int nbTile = 0;
	int plateauTileLargeur = 0;
	int plateauTileLongueur = 0;
	String lignePlateau = "";
	
	//defilement
	public static int xScroll = 0;
	public static int sizeFenetre = 720;
	
	public int[][] positionTile;
	 
	public Map(String fichier) {
		this.fichier = fichier;
		//liste contenant les tile
		listTile = new ArrayList<Tile>();
		
		try (
		InputStream ips = getClass().getResourceAsStream(fichier);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr); ) {

		//on récupère le chemin du fichier bmp (1ère ligne du fichier txt)
		imageBmp += br.readLine();
		
		//On créé un fichier contenant l'image
		System.out.println(imageBmp);
		//File fichierBmp = new File(imageBmp);
		
		//on recup la 2eme ligne correspondant aux nombre de tile
		nbTile = Integer.parseInt(br.readLine());
		
		//on récupère le type de chaque tile et on l'ajoute à la liste
		for(int i = 0; i<nbTile; i++) {
			listTile.add(new Tile(br.readLine(), i, imageBmp));
		}
		
		plateauTileLargeur = Integer.parseInt(br.readLine());
		plateauTileLongueur = Integer.parseInt(br.readLine());
		positionTile = new int[plateauTileLargeur][plateauTileLongueur];
		
		for(int j=0; j<plateauTileLongueur; j++) {
			lignePlateau = br.readLine();
			//System.out.println(lignePlateau);
			for(int y=0; y<lignePlateau.length(); y+=2) {
				positionTile[y/2][j] = lignePlateau.charAt(y)-'0';
			}
		}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void afficherTab() {
		for(int i=0; i<13; i++) {
			System.out.println("");
			for(int y=0; y<30; y++) {
				System.out.print(" " + positionTile[y][i]);
			}
		}
	}
	
	public void chargerMap(Graphics g) {
		int compteur = 0;
		for(int j=0; j<plateauTileLongueur; j++) {
			for(int y=xScroll/48; y<( (sizeFenetre+xScroll)/48); y++) {
				//System.out.println(xScroll/48);
				if(y < 30) {
					//System.out.println(y);
					listTile.get(positionTile[y][j]).drawTile(g, compteur, j, sizeTile);
					compteur++;
				}
				else {
					listTile.get(0).drawTile(g, compteur, j, sizeTile);
					compteur++;
				}
					
			}
			compteur = 0;

		}
	}
}
