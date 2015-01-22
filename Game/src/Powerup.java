import java.util.Random;
import java.util.ArrayList;

/**
 * This class holds all info of a powerup: type, activation implementation, and so on.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Powerup extends Item {
	private static final int DEFAULTSPEED = 5;
	private static final int DEFAULTSIZE = 10;
	private Type type;	//The type of this Powerup's power

	public enum Type {
		SuperCerisePower, IncPad, DecPad, IncGameSpeed, DecGameSpeed, StickyPad; 
	}

	/**
	 * Create a new Powerup on given coordinates X and Y, with default size and speed. 
	 * @param X The X coordinate to spawn upon.
	 * @param Y	The Y cordinate to spawn upon.
	 */
	public Powerup(int X, int Y) {
		pos = new XY(X, Y);
		size = new XY(DEFAULTSIZE, DEFAULTSIZE);
		speed = new XY(DEFAULTSPEED, DEFAULTSPEED);
		type = getRandomType(); //get a power
	}
	
	/**
	 * Returns the type of this Powerup
	 * @return type The power of this Powerup
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Returns a random power type.
	 * @return power A random power.
	 */
	private Type getRandomType() {
		return Type.values()[new Random().nextInt(Type.values().length)];
	}
	
	/**
	 * Activate this Powerup's power
	 * @param bricks The bricks used in the game.
	 * @param pad The pad in the game.
	 * @param game	The game in question.
	 */
	public void activate(ArrayList<Item> bricks, Pad pad, Game game) {
		switch (type) {
			case SuperCerisePower:
				superCerisePower(bricks);
				break;
			case IncPad:
				increasePad(pad);
				break;
			case DecPad:
				reducePad(pad);
				break;
			case IncGameSpeed:
				increaseGameSpeed(game);
				break;
			case DecGameSpeed:
				reduceGameSpeed(game);
				break;
			case StickyPad:
				stickyPad(pad);
				break;
		}
	}
	
	
	
	// Private methods for implementing powerups.
	
	/**
	 * Makes every brick with more than 1 hit left (until kill) decrease its life by 1.
	 * @param bricks All the bricks in the game.
	 */
	private void superCerisePower(ArrayList<Item> bricks) {
		for (int i  = 2; i < bricks.size(); i++) {
			Brick b = (Brick)bricks.get(i);
			if (b.getHitsLeft() > 1) {
				b.setHitsLeft(b.getHitsLeft() - 1);
			}
		}
	}
	
	/**
	 * Increases the pad's length in x-axis.
	 * @param pad The pad in the game.
	 */
	private void increasePad(Pad pad) {
		pad.setSizeX(pad.getSizeX() + 20);
	}
	
	/**
	 * Reduces the pad's length in x-axis.
	 * @param pad The pad in the game.
	 */
	private void reducePad(Pad pad) {
		pad.setSizeX(pad.getSizeX() - 20);
	}
	
	/**
	 * Increases the game speed. This makes the ball, pad and Powerups move faster.
	 * @param game The game in question.
	 */
	private void increaseGameSpeed(Game game) {
		game.setGameSpeed(game.getGameSpeed() - 10);
	}
	
	/**
	 * Reduces the game speed. This makes the ball, pad and Powerups move slower.
	 * @param game The game in question.
	 */
	private void reduceGameSpeed(Game game) {
		game.setGameSpeed(game.getGameSpeed() + 10);
	}
	
	/**
	 * Makes the pad sticky, which means that if the ball in the game hits the pad in this state then the ball will
	 * follow the pad and not move until the user press "Space". 
	 * @param pad The pad in the game.
	 */
	private void stickyPad(Pad pad) {
		if (!pad.isSticky()) {
			pad.toggleIsSticky();
		}
	}
}