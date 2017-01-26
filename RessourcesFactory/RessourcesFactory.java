package RessourcesFactory;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RessourcesFactory {
	private static String imageCharacter = "/IHM/Ressources/bob_sprite.png";
	private static String imageMonster = "/IHM/Ressources/fantome.png";
	
	static BufferedImage imageCharacterB;
	static BufferedImage imageMonsterB;
	
	public static void loadImage() {
		try {
			imageCharacterB = ImageIO.read(RessourcesFactory.class.getResourceAsStream(imageCharacter));
			imageMonsterB = ImageIO.read(RessourcesFactory.class.getResourceAsStream(imageMonster));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Image getImage(String type) {
		if(type.equals("character"))
				return imageCharacterB;

		else if(type.equals("monster"))
			return imageMonsterB;
		
		return null;
	}
	
}
