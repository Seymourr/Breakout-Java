
/**
 * An abstract class to define the basic things that all game items must have. This class has a position, a size and a speed.
 * In the case of a subclass not needing one or more of these properties, it should override the getters and setters of these
 * properties, defining them to throw an error instead of returning a value.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public abstract class Item {
	protected XY pos;
	protected XY size;
	protected XY speed;
	
	protected class XY {	//Contains X and Y coordinates 
		public int X;
		public int Y;
		
		public XY(int X, int Y) {	//Set the X and Y coordinates for this class
			this.X = X;
			this.Y = Y;
		}


	}
	/**
	 * Create a new Item object with standard values.
	 */
	public Item() {
		pos = new XY(1, 1);
		size = new XY(5, 5);
		speed = new XY(1, 1);
	}
	
	/**
	 * Create a new Item object with given values.
	 * @param coordX	X-coordinate of this object.
	 * @param coordY	Y-coordinate of this object.
	 * @param sizeX		Size in X-axis.
	 * @param sizeY		Size in Y-axis.
	 * @param speedX	The speed of this object, in x-axis terms.
	 * @param speedY	The speed of this object, in y-axis terms.
	 */
	public Item(int coordX, int coordY, int sizeX, int sizeY, int speedX, int speedY) {
		this.pos = new XY(coordX, coordY);
		this.size = new XY(sizeX, sizeY);
		this.speed = new XY(speedX, speedY);
	}
	
	/**
	 * @return	This item's current X position.
	 */
	public int getPosX() {
		return pos.X;
	}

	/**
	 * @return	This item's current Y position.
	 */
	public int getPosY() {
		return pos.Y;
	}

	/**
	 * @param coord	The X coordinate to place this item at. 
	 */
	public void setPosX(int coord) {
		this.pos.X = coord;
	}
	
	/**
	 * @param coord	The Y coordinate to place this item at.
	 */
	public void setPosY(int coord) {
		this.pos.Y = coord;
	}

	/**
	 * @return	This item's current X size.
	 */
	public int getSizeX() {
		return size.X;
	}
	
	/**
	 * @return	This item's current Y size.
	 */
	public int getSizeY() {
		return size.Y;
	}
		
	/**
	 * @param size	The X size to resize this item to.
	 */
	public void setSizeX(int size) {
		this.size.X = size; 
	}
	
	/**
	 * @param size	The Y size to resize this item to.
	 */
	public void setSizeY(int size) {
		this.size.Y = size;
	}
	
	/**
	 * @return	This item's current X speed.
	 */
	public int getSpeedX() {
		return speed.X;
	}
	
	/**
	 * @return	This item's current Y speed.
	 */
	public int getSpeedY() {
		return speed.Y;
	}
	
	/**
	 * @param speed	The X speed to set this item's speed to.
	 */
	public void setSpeedX(int speed) {
		this.speed.X = speed;
	}

	/**
	 * @param speed	The Y speed to set this item's speed to.
	 */
	public void setSpeedY(int speed) {
		this.speed.Y = speed;
	}
	
}
