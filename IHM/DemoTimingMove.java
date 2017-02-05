package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DemoTimingMove extends JPanel {
	 
	private static final int WIDTH=400;
	private static final int HEIGHT=WIDTH;
	private static final long TICK = 33;
	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = SPRITE_WIDTH;
	private static final int SPRITE_HORIZONTAL_SPEED = (int)TICK/2;
	private static final int SPRITE_JUMP_HEIGHT = SPRITE_HEIGHT+SPRITE_HEIGHT/2;
	private static final int JUMP_SPEED = (int)TICK*4;
 
 
	public DemoTimingMove() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.WHITE);
	}
 
	public static void main(String[] args) {
 
		DemoTimingMove panel = new DemoTimingMove();
		Model model = new Model();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e->keyListener(e,model));
 
		JFrame frame = showInFrame("Démo", panel);
		frame.createBufferStrategy(2);
 
    	Point offset=SwingUtilities.convertPoint(panel, new Point(), frame);
    	TimerTask gameLoop = new TimerTask() {
 
			@Override
			public void run() {
	    		gameIteration(frame.getBufferStrategy(), model, panel, offset);			}
    	};
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				gameLoop.cancel();
			}
		});
 
		model.start();
		new Timer().scheduleAtFixedRate(gameLoop,TICK,TICK);
		//renderLoop(frame, panel, model);
 
	}
 
	private static boolean keyListener(KeyEvent e, Model model) {
 
		boolean consumed=true;
		switch( e.getID() ) {
		case KeyEvent.KEY_PRESSED:
			switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
				model.setHorizontalMove(Model.Direction.RIGHT);
				consumed=true;
				break;
			case KeyEvent.VK_LEFT:
				model.setHorizontalMove(Model.Direction.LEFT);
				consumed=true;
				break;
			case KeyEvent.VK_UP:
				model.jump();
				consumed=true;
				break;
			}
			break;
		case KeyEvent.KEY_RELEASED:
			switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				model.setHorizontalMove(null);
				consumed=true;
				break;
			}
			break;
		}
		return !consumed;
 
	}
 
	private static void gameIteration(BufferStrategy strategy, Model model, DemoTimingMove panel, Point offset) {
 
		long globalTime=System.currentTimeMillis();
		model.doMoves(globalTime);
		model.collisions();
 
        final Graphics2D graphics = (Graphics2D)strategy.getDrawGraphics();
        try {
        	graphics.translate(offset.x,offset.y);
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		   	graphics.setColor(panel.getBackground());
		   	graphics.fillRect(0,0,panel.getWidth(),panel.getHeight());
        	model.render(graphics);
        }
        finally {
       	 	graphics.dispose();
        }
        strategy.show(); // repaint
 
	}
 
	public static class Model {
 
		public enum Direction {
			LEFT,RIGHT;
		}
 
 
		private double x,y;
		// si les types de move se multiplie, on fera un gestion en boucle (enum/enummap par exemple)
		private volatile Move horizontalMove; // modifié sur l'EDT, exploité par la gameLoop, donc pas le même thread
		private volatile Move jumpMove; // modifié sur l'EDT, exploité par la gameLoop, donc pas le même thread
		private long lastTime;
 
		public Model() {
			x=0;
			y=(HEIGHT-SPRITE_HEIGHT)/2d;
		}
 
		public void jump() {
			if ( jumpMove==null ) { // on n'annule jamis un saut (on ne peut pas sauter pendant qu'on saute, gérer le double saut se fera sur le saut lui même éventuellement
				jumpMove = new JumpMove(JUMP_SPEED,y,y-SPRITE_JUMP_HEIGHT);
			}
		}
 
		public void setHorizontalMove(Direction dir) {
			if ( dir==null ) {
				this.horizontalMove=null;
			}
			else {
				switch (dir) {
				case RIGHT:
					this.horizontalMove=new BoundMove(TICK,x,x+SPRITE_HORIZONTAL_SPEED,px->Math.min(WIDTH-SPRITE_WIDTH, px));
					break;
				case LEFT:
					this.horizontalMove=new BoundMove(TICK,x,x-SPRITE_HORIZONTAL_SPEED,px->Math.max(0,px));
					break;
				}
			}
		}
 
		public void start() {
			lastTime=System.currentTimeMillis();
		}
 
		public void doMoves(long globalTime) {
			long dtime = globalTime-lastTime;
			lastTime = globalTime;
			if ( horizontalMove!=null ) {
				x=horizontalMove.doMove(dtime);
				if (horizontalMove.isFinished() ) {
					horizontalMove=horizontalMove.next();
				}
			}
			if ( jumpMove!=null ) {
				y=jumpMove.doMove(dtime);
				if (jumpMove.isFinished() ) {
					jumpMove=jumpMove.next();
				}
			}
		}
 
		public double getX() {
			return x;
		}
 
		public void render(Graphics2D graphics) {
			graphics.setColor(Color.BLACK);
			graphics.fillOval((int)x, (int)y, SPRITE_WIDTH, SPRITE_HEIGHT);
		}
 
		public void collisions() {
		}
 
	}
 
	public static interface Move { // timing framework simplifié
 
		double doMove(long dtime);
 
		default Move next() {
			return null;
		}
 
		boolean isFinished();
 
	}
 
	public static class SimpleMove implements Move { // timing framework simplifié
 
		private volatile double lastPos;
		private double end;
		private volatile long lastTime;
		private long endTime;
		private volatile boolean finished;
 
		public SimpleMove(long time, double start, double end) {
			this.lastPos=start;
			this.end=end;
			this.lastTime=System.currentTimeMillis();
			this.endTime=lastTime+time;
		}
 
		public double doMove(long dtime) {
			if ( !finished ) {
				if (dtime>0) {
					lastPos+=(end-lastPos)/dtime;
				}
				lastTime+=dtime;
				if( lastTime>=endTime) {
					finished=true;
					lastPos=end;
				}
			}
			return lastPos;
		}
 
		public boolean isFinished() {
			return finished;
		}
 
	}
 
	public static class BoundMove extends SimpleMove { // timing framework simplifié
 
		private Function<Double,Double> boundFunction;
		private double start;
		private double end;
		private long time;
 
		public BoundMove(long time, double start, double end, Function<Double,Double> boundFunction) {
			super(time,start,end);
			this.time=time;
			this.start=end;
			this.end=end+end-start;
			this.boundFunction=boundFunction;
		}
 
		public Move next() {
			return new BoundMove(time, start, end, boundFunction);
		}
 
		public double doMove(long dtime) {
			return boundFunction.apply(super.doMove(dtime));
		}
 
	}
 
	// implémentation simple du saut :  on peut faire faire plus réaliste (parabolique)
	public static class JumpMove extends SimpleMove {
 
		private JumpMove jumpDown;
 
		public JumpMove(long time, double start, double end) {
			this(time, start, end, true);
		}
 
		private JumpMove(long time, double start, double end, boolean up) {
			super(time, start, end);
			this.jumpDown=up?new JumpMove(time, end, start, false):null;
		}
 
		@Override
		public Move next() {
			return jumpDown;
		}
 
	}
 
	public static JFrame showInFrame(String title, JComponent component) {
 
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		frame.add(component);
 
		frame.pack();
 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
 
	}
 
	public static void renderLoop(JFrame frame, DemoTimingMove panel, Model model) {
		 frame.createBufferStrategy(2);
		 BufferStrategy strategy = frame.getBufferStrategy();
 
		 AtomicBoolean done = new AtomicBoolean(true);
		 frame.addWindowListener(new WindowAdapter() {
			 @Override
			public void windowClosed(WindowEvent e) {
				 done.set(false);
			}
		});
 
		 while (!done.get()) {
		     do {
		         do {
		             final Graphics2D graphics = (Graphics2D)strategy.getDrawGraphics();
		             try {
		            	 graphics.setColor(panel.getBackground());
		            	 graphics.fillRect(0,0,panel.getWidth(),panel.getHeight());
		            	 model.render(graphics);
		             }
		             finally {
		            	 graphics.dispose();
		             }
		         } while (strategy.contentsRestored());
 
		         strategy.show();
 
		     } while (strategy.contentsLost());
		 }
 
	}
 
}
