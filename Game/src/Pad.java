
/**
 * This class holds all info of a pad: size, position, and so on.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Pad extends Item{
	private static final int DEFAULTPOSX = 350;
	private static final int DEFAULTPOSY = 500;
	private static final int DEFAULTSIZEX = 80;
	private static final int DEFAULTSIZEY = 10;
	private static final int DEFAULTSPEED = 20;
	private boolean isSticky;	//A boolean telling whether this pad is sticky or not
	

	/**
	 * Will create a pad at default coordinates, with default sizes, and that is not sticky.
	 */
	public Pad() {
		pos = new XY(DEFAULTPOSX, DEFAULTPOSY);
		size = new XY(DEFAULTSIZEX, DEFAULTSIZEY);
		speed = new XY(DEFAULTSPEED, DEFAULTSPEED);
		this.isSticky = false;
	}
	
	/**
	 * Will create a pad at the specified coordinates, with default sizes, and that is not sticky.
	 * @param X	The pad's starting X coordinate.
	 * @param Y	The pad's starting Y coordinate.
	 */
	public Pad(int X, int Y) {
		pos = new XY(X, Y);
		size = new XY(DEFAULTSIZEX, DEFAULTSIZEY);
		this.isSticky = false;
	}

	/**
	 * Will create a pad at the specified coordinates and specified sizes, and that is not sticky.
	 * @param X		The pad's starting X coordinate.
	 * @param Y		The pad's starting Y coordinate.
	 * @param sizeX	The pad's starting X size.
	 * @param sizeY	The pad's starting Y size.

	 */
	public Pad(int X, int Y, int sizeX, int sizeY) {
		pos = new XY(X, Y);
		size = new XY(sizeX, sizeY);
		speed = new XY(DEFAULTSPEED, DEFAULTSPEED);
		this.isSticky = false;
	}
	
	/**
	 * Will create a pad at the specified coordinates and specified sizes, and is sticky if specified so.
	 * @param X			The pad's starting X coordinate.
	 * @param Y			The pad's starting Y coordinate.
	 * @param sizeX		The pad's starting X size.
	 * @param sizeY		The pad's starting Y size.
	 * @param sticky	Whether the pad is sticky or not at creation.
	 */
	public Pad(int X, int Y, int sizeX, int sizeY, int speed, boolean sticky) {
		pos = new XY(X, Y);
		size = new XY(sizeX, sizeY);
		this.speed = new XY(speed, speed);
		this.isSticky = sticky;
	}
	
	/**
	 * Checks whether this pad is sticky.
	 * @return boolean <Code>true</Code> if the pad is sticky, <Code>false</Code> otherwise.
	 */
	public boolean isSticky() {
		return isSticky;
	}
	
	/**
	 * Makes this pad sticky, or not sticky.
	 * @return boolean <Code>true</Code> if the pad is sticky, <Code>false</Code> otherwise.
	 */
	public boolean toggleIsSticky() {
		isSticky = !isSticky;
		return isSticky;
	}
	
	/**
	 * Set the speed for this pad in X-axis.
	 * @param speed The new speed.
	 */
	public void setSpeed(int speed) {
		this.speed.X = speed;
	}
	
	/**
	 * Get this pad's current speed in X-axis.
	 * @return The speed in X-axis.
	 */
	public int getSpeed() {
		return speed.X;
	}
	
	
	@Override
	public int getSpeedY() {
		throw new Error("Internal error.");	//	Cannot move Y-wise.
	}
	
	
	@Override
	public void setSpeedY(int speed) {
		throw new Error("Internal error.");	//	Cannot move Y-wise.
	}
	
	
}
