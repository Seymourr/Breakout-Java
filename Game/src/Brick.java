
/**
 * This class holds all info of a brick: size, position, hits left, and so on.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Brick extends Item {
	private static final int DEFAULTPOSX = 10;
	private static final int DEFAULTPOSY = 10;
	private static final int DEFAULTHITSLEFT = 1;
	private static final int DEFAULTSIZEX = 30;
	private static final int DEFAULTSIZEY = 15;
	private int hitsLeft;

	/**
	 * Will create a block at default coordinates, and a default amount of hits left.
	 */
	public Brick() {
		pos = new XY(DEFAULTPOSX, DEFAULTPOSY);
		size = new XY(DEFAULTSIZEX, DEFAULTSIZEY);
		this.hitsLeft = DEFAULTHITSLEFT;
	}
	
	/**
	 * Will create a block at the specified coordinates, and a default amount of hits left.
	 * @param coordX	The block's X coordinate.
	 * @param coordY	The block's Y coordinate.
	 */
	public Brick(int coordX, int coordY) {
		pos = new XY(coordX, coordY);
		size = new XY(DEFAULTSIZEX, DEFAULTSIZEY);
		this.hitsLeft = DEFAULTHITSLEFT;
	}
	
	/**
	 * Will create a block at the specified coordinates, and a specified amount of hits left.
	 * @param coordX	The block's X coordinate.
	 * @param coordY	The block's Y coordinate.
	 * @param hitsLeft	The amount of hits this block can take.
	 */
	public Brick(int coordX, int coordY, int hitsLeft) {
		pos = new XY(coordX, coordY);
		size = new XY(DEFAULTSIZEX, DEFAULTSIZEY);
		this.hitsLeft = hitsLeft;
	}
	/**
	 * Will create a block at the given coordinates, with a given size and number of hits left.
	 * @param coordX 	The block's X coordinate.
	 * @param coordY	The block's Y coordinate.
	 * @param sizeX		The block's size in X length.
	 * @param sizeY		The block's size in Y length.
	 * @param hitsLeft	The amount of hits left until kill.
	 */
	public Brick(int coordX, int coordY, int sizeX, int sizeY, int hitsLeft) {
		pos = new XY(coordX, coordY);
		size = new XY(sizeX, sizeY);
		this.hitsLeft = hitsLeft;
	}

	/**
	 * @return  The amount of hits left until this brick is destroyed.
	 */
	public int getHitsLeft() {
		return hitsLeft;
	}
	
	/**
	 * @param amount  Amount of hits until this brick is destroyed.
	 */
	public void setHitsLeft(int amount) {
		this.hitsLeft = amount;
	}
	
	/**
	 * Decreases the amount of hits this block can handle, and has a 10% chance of returning a Powerup.
	 * Default method to run when a brick is hit. 
	 * @return Either a Powerup or null.
	 */
	public Powerup hit() {
		hitsLeft--;
		if (Math.random() < 0.1) {	// 10% chance of spawning a powerup.
			return new Powerup(pos.X, pos.Y + size.Y);
		} else {
			return null;
		}
	}
	
	@Override
	public int getSpeedX() {
		throw new Error("Internal error.");	//	Does not have a speed.
	}
	
	@Override
	public void setSpeedX(int speed) {
		throw new Error("Internal error.");	//	Does not have a speed.
	}
	
	@Override
	public int getSpeedY() {
		throw new Error("Internal error.");	//	Does not have a speed.
	}
	
	@Override
	public void setSpeedY(int speed) {
		throw new Error("Internal error.");	//	Does not have a speed.
	}
}
