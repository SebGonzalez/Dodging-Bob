package GraphicMotor;

import IHM.Game;

public class ShiftingManager {

	public static boolean montee = false;
	
	public static void shifting() {
		
		while (true) {
			System.out.println(montee);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// deplacement droite gauche
			if (Game.bob.getX() >= Game.borders[0] && keyListener.Left) {
				Game.bob.moveHorizontal(-1);
			} else if (Game.bob.getX() <= Game.borders[2] && keyListener.Right) {
				Game.bob.moveHorizontal(1);
			}
			
			// saut (hard)
			if(keyListener.Up && !montee) {
				
				montee = true;
				for(int i=0; i<50; i++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Game.bob.moveVertical(-1);
					montee = false;
				}
			}
			if(!montee && Game.bob.getY() < (Game.borders[3] - Game.bob.getHeight())) {
				Game.bob.moveVertical(2);
			}
		}

	}
}
