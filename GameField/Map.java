package GameField;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
	
	ArrayList<Tile> listTile;
	String fichier;
	int sizeTile = 24;
	String imageBmp = "src/IHM/Ressources/Level/";
	int nbTile = 0;
	int plateauTileLargeur = 0;
	int plateauTileLongueur = 0;
	String lignePlateau = "";
	
	public int[][] positionTile;
	 
	public Map(String fichier) {
		this.fichier = fichier;
		//liste contenant les tile
		listTile = new ArrayList<Tile>();
		
		try {
		InputStream ips = getClass().getResourceAsStream(fichier);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);

		//on récupère le chemin du fichier bmp (1ère ligne du fichier txt)
		imageBmp += br.readLine();
		
		//On créé un fichier contenant l'image
		System.out.println(imageBmp);
		File fichierBmp = new File(imageBmp);
		
		//on recup la 2eme ligne correspondant aux nombre de tile
		nbTile = Integer.parseInt(br.readLine());
		
		//on récupère le type de chaque tile et on l'ajoute à la liste
		for(int i = 0; i<nbTile; i++) {
			listTile.add(new Tile(br.readLine(), i, fichierBmp));
		}
		
		plateauTileLargeur = Integer.parseInt(br.readLine());
		plateauTileLongueur = Integer.parseInt(br.readLine());
		positionTile = new int[plateauTileLargeur][plateauTileLongueur];
		br.close(); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void chargerMap(Graphics g) {
		
		try{
			InputStream ips = getClass().getResourceAsStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			br.readLine();
			br.readLine();
			br.readLine(); br.readLine(); br.readLine(); br.readLine(); br.readLine(); br.readLine(); br.readLine(); br.readLine();
			br.readLine();
			br.readLine();
			
			for(int j=0; j<plateauTileLongueur; j++) {
				lignePlateau = br.readLine();
				for(int y=0; y<lignePlateau.length(); y+=2) {
					positionTile[y/2][j] = Integer.parseInt(""+lignePlateau.charAt(y));
					listTile.get(Integer.parseInt(""+lignePlateau.charAt(y))).drawTile(g, y, j, sizeTile);
				}
			}

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
