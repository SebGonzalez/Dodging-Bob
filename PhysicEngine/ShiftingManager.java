package PhysicEngine;

import GameField.Map;
import IHM.Game;

public class ShiftingManager {

	public static boolean montee = false;
	
	public static void shifting() {
		
		while (true) {
			//System.out.println(montee);
			//System.out.println(" oui " + Game.bob.getX() + " " + Map.xScroll);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// deplacement droite gauche
			if (/*Game.bob.getX() > Game.borders[0]*/ Map.xScroll > Game.borders[0]  && KeyListenerCharacter.Left && !Game.bob.collisionWall("left")) {
				Game.bob.moving = true;
				Game.bob.moveHorizontal(-1);
			} else if (Game.bob.getX() <= Game.borders[2] && KeyListenerCharacter.Right && !Game.bob.collisionWall("right")) {
				Game.bob.moving = true;
				Game.bob.moveHorizontal(1);
			}
			else {
				Game.bob.moving = false;
			}
			
			// saut (hard)
			if(KeyListenerCharacter.Up && !montee && !Game.bob.collisionWall("up")) {
				Game.bob.moving = true;
				montee = true;
				for(int i=0; i<80; i++) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!Game.bob.collisionWall("up"))
						Game.bob.moveVertical(-3);
				}
			}
			else if( /*&& Game.bob.getY() < (Game.borders[3] - Game.bob.getHeight())*/  !Game.bob.collisionWall("down")) {
				Game.bob.moveVertical(2);
			}
			
			if(Game.bob.collisionWall("down"))
				montee = false;
		}

	}
}
