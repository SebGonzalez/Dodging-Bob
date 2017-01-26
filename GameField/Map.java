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
	 int sizeTile = 16;
	 
	public Map() {
		listTile = new ArrayList<Tile>();
	}
	
	public void chargerMap(String fichier, Graphics g) {
		String imageBmp = "src/IHM/Ressources/Level/";
		int nbTile = 0;
		int plateauTileLargeur = 0;
		int plateauTileLongueur = 0;
		String lignePlateau = "";
		
		try{
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			//while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				//chaine+=ligne+"\n";
			//}
			imageBmp += br.readLine();
			//System.out.println("Image bmp : " + imageBmp);
			File fichierBmp = new File(imageBmp);
			nbTile = Integer.parseInt(br.readLine());
			//System.out.println("nbTile : " + nbTile);
			for(int i = 0; i<nbTile; i++) {
				listTile.add(new Tile(br.readLine(), i, fichierBmp));
			}
			
			plateauTileLargeur = Integer.parseInt(br.readLine());
			//System.out.println("plateauTileLargeur : " + plateauTileLargeur);
			plateauTileLongueur = Integer.parseInt(br.readLine());
			//System.out.println("plateauTileLongueur : " + plateauTileLongueur);
			
			for(int j=0; j<plateauTileLongueur; j++) {
				lignePlateau = br.readLine();
				for(int y=0; y<lignePlateau.length(); y+=2) {
					listTile.get(Integer.parseInt(""+lignePlateau.charAt(y))).drawTile(g, y, j, sizeTile);;
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
