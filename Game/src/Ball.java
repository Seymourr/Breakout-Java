
/**
 * This class holds all info of a ball: size, position, speed, and so on.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Ball extends Item {
	private static final int DEFAULTPOSX = 370;
	private static final int DEFAULTPOSY = 480; 
	private static final int DEFAULTSPEEDX = 10;
	private static final int DEFAULTSPEEDY = -10;
	private static final int DEFAULTSIZE = 10;
	
	/**
	 * Will create a ball with default position, speed and size.
	 */
	public Ball() {
		pos = new XY(DEFAULTPOSX, DEFAULTPOSY);
		speed = new XY(DEFAULTSPEEDX, DEFAULTSPEEDY);
		size = new XY(DEFAULTSIZE, DEFAULTSIZE);
	}
	
	/**
	 * Will create a ball using the specified parameters for start position, speed and size.
	 * @param coordX The ball's beginning X coordinate.
	 * @param coordY The ball's beginning Y coordinate.
	 * @param speedX The ball's beginning speed X-wise.
	 * @param speedY The ball's beginning speed Y-wise.
	 * @param size 	 The ball's beginning size.
	 */
	public Ball (int coordX, int coordY, int speedX, int speedY, int size) {
		pos = new XY(coordX, coordY);
		speed = new XY(speedX, speedY);
		this.size = new XY(size, size);
	}
	
	/**
	 * @return The size of the ball.
	 */
	public int getSize() {
		return size.X;
	}
	
	/**
	 * @param size The size to set the ball to.
	 */
	public void setSize(int size) {
		this.size.X = size;
		this.size.Y = size;
	}
	
	/**
	 * Will place the ball at the default (starting) position.
	 */
	public void reset() {
		pos.X = DEFAULTPOSX;
		pos.Y = DEFAULTPOSY;
	}
	
}
