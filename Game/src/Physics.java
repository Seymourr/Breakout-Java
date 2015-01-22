import java.util.ArrayList;

/**
 * This class control the movement and collision of the given ball object. The ball can 
 * either collide with "sides", bricks or a moving pad, and should it collide with any it would 
 * bounce away. No gravity is taken into consideration with a bounce.
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Physics {

	private Ball ball;
	private Pad pad;
	private ArrayList<Item> bricks;  //All bricks in the game
	private ArrayList<Powerup> powerups;	//All powerups in the game
	
	//Boundaries of the window
	private final int LEFTSIDE = 0;			
	private final int RIGHTSIDE = 745;
	private final int ROOFSIDE = 0;
	private final int GROUND = 560; 	//Not used in game
	
	//The diameter of the ball in the game
	private final int DIAMETER;



	/**
	 * Constructor for class Physics which specify items to use for movement and collisions.
	 * 
	 * @param items The items to use for calculations.
	 */
	public Physics(ArrayList<Item> items, ArrayList<Powerup> powerups) {
		this.bricks = items;
		this.powerups = powerups;
		ball = (Ball)items.get(0);
		pad = (Pad)items.get(1);
		DIAMETER = ball.getSizeX();
	}
	
	
	
	
	public void newGame() {
		ball.reset();
		
		//	TODO
	}

	/**
	 * Calculates the next position of the ball, which either move regularly or bounce to 
	 * a new direction if a collision has been made with any other object.
	 */
	private void detectBoundariesCollision(){
		
		if(ball.getPosX() <= LEFTSIDE - DIAMETER) {
			ball.setSpeedX(ball.getSpeedX() * -1);

		} else if( ball.getPosX() >= RIGHTSIDE - 2 * DIAMETER) {
			ball.setSpeedX(ball.getSpeedX() * -1);

		}

		if(ball.getPosY() <= (ROOFSIDE + DIAMETER)) {
			ball.setSpeedY(ball.getSpeedY() * -1);

			//Only occuring if hasLost() method is not called in Game
		} else if(ball.getPosY() >= (GROUND - DIAMETER)) {
			ball.setSpeedY(ball.getSpeedY() * -1);
		} 
	}	

	/**
	 * Checks whether a spawned powerup p intersect the pad. If so, the method 
	 * returns true - indicating that p should be activated.
	 * @param p The spawned powerup
	 * @return <Code>true</code> If p has intersected the pad, <Code>false</Code> otherwise.
	 * 
	 */
	private boolean powerPadCollision(Powerup p) {
		int size = p.getSizeX();
		
		if(p.getPosY() >= pad.getPosY() - size && p.getPosY() <= pad.getPosY()) {
			
			if(p.getPosX() >= pad.getPosX() - size && p.getPosX() <= (pad.getPosX() + pad.getSizeX() + size)) {
			return true;
			}
			
		}
		return false;
	}
	
	/**
	 * A method that detects whether the ball in the game collide with the pad. If so, change the
	 * ball's speed according to where it collided on the pad. 
	 */
	private void detectPadCollision() {
		int size = ball.getSizeX();
		
		if (ball.getPosY() == pad.getPosY() - size) {
			
			if(ball.getPosX() >= pad.getPosX() - size && ball.getPosX() <= (pad.getPosX() + pad.getSizeX() + size)) {
				if(ball.getPosX() >= pad.getPosX() - size && ball.getPosX() <= (pad.getPosX() + (pad.getSizeX()/3))) {
					if(ball.getSpeedX() > 0 && ball.getSpeedY() > 0) {
						ball.setSpeedX(ball.getSpeedX() * -1);		//Push the ball back the way it came from
						ball.setSpeedY(ball.getSpeedY() * -1);
					} else if(ball.getSpeedX() < 0 && ball.getSpeedY() > 0) {
						ball.setSpeedY(ball.getSpeedY() * -1);		//Make it bounce
					}
				} else if(ball.getPosX() >= pad.getPosX() + (pad.getSizeX() - pad.getSizeX()/3) && ball.getPosX() <= (pad.getPosX() + pad.getSizeX() + size)){
					if(ball.getSpeedX() > 0 && ball.getSpeedY() > 0) {	//Make it bounce
						ball.setSpeedY(ball.getSpeedY() * -1);
					} else if(ball.getSpeedX() < 0 && ball.getSpeedY() > 0) {
						ball.setSpeedX(ball.getSpeedX() * -1);		//Push the ball back the way it came from
						ball.setSpeedY(ball.getSpeedY() * -1);
					}
				} else {
					ball.setSpeedY(ball.getSpeedY() * -1);		//Just make it bounce
				}			
			}
		}
	}

	/**
	 * Detects whether the ball in the game is colliding with a brick b. The ball's speed is
	 * changed, so that it animates a bouncing effect, if the ball collides on any of the brick's sides or corners.
	 * 
	 * @param b The brick to check upon
	 * @return didHit A boolean indicating whether a brick was hit in the ball's movement or not. 
	 */
	private boolean detectBrickCollision(Brick b) {
		int size = DIAMETER;
		boolean didHit = false;
		
		
		//Detect top collision
		if(ball.getPosY() + size <= b.getPosY() && ball.getPosY() + size >= (b.getPosY() - size)) {
			if(ball.getPosX() >= b.getPosX() && ball.getPosX() <= (b.getPosX() + b.getSizeX())){
				ball.setSpeedY(ball.getSpeedY() * -1);
				didHit = true;
			}
		}
		
		//Detect bottom collision
		if(ball.getPosY() >= (b.getPosY() + b.getSizeY()) && ball.getPosY() <= (b.getPosY() + b.getSizeY() + size)) {
			if(ball.getPosX() >= b.getPosX() && ball.getPosX() <= (b.getPosX() + b.getSizeX())){
				ball.setSpeedY(ball.getSpeedY() * -1);
				didHit = true;
			}
		}
		
		//Detect left collision
		if(ball.getPosX() <= b.getPosX() && ball.getPosX() >= (b.getPosX() - size)) {
			if(ball.getPosY() >= b.getPosY() && ball.getPosY() <= (b.getPosY() + b.getSizeY())) {
				ball.setSpeedX(ball.getSpeedX() * -1);
				didHit = true;
			}
		}
		//Detect right collision
		if(ball.getPosX() >= b.getPosX() + b.getSizeX() && ball.getPosX() <= (b.getPosX() + b.getSizeX() + size)) {
			if(ball.getPosY() >= b.getPosY() && ball.getPosY() <= (b.getPosY() + b.getSizeY())) {
				ball.setSpeedX(ball.getSpeedX() * -1);
				didHit = true;
			}
		}
		
		//Corners
		
		//Top left
		if(ball.getPosX() <= b.getPosX() && ball.getPosY() <= b.getPosY()) {
			if(ball.getPosX() >= b.getPosX() - size && ball.getPosY() >= b.getPosY() - size) {
			ball.setSpeedY(ball.getSpeedY() * -1);
			didHit = true;
			}
		}
		
		//Top right
		if(ball.getPosX() >= b.getPosX() + b.getSizeX() && ball.getPosY() <= b.getPosY()) {
			if(ball.getPosX() <= b.getPosX() + b.getSizeX() + size && ball.getPosY() >= b.getPosY() - size) {
			ball.setSpeedY(ball.getSpeedY() * -1);
			didHit = true;
			}
		}
		
		//Bottom left
		if(ball.getPosX() <= b.getPosX() && ball.getPosY() >= b.getPosY() + b.getSizeY()) {
			if(ball.getPosX() >= b.getPosX() - size && ball.getPosY() <= b.getPosY() + b.getSizeY() + size) {
			ball.setSpeedY(ball.getSpeedY() * -1);
			didHit = true;
			}
		}
		
		//Bottom right
		if(ball.getPosX() >= b.getPosX() + b.getSizeX() && ball.getPosY() >= b.getPosY() + b.getSizeY()) {
			if(ball.getPosX() <= b.getPosX() + b.getSizeX() + size && ball.getPosY() <= b.getPosY() + b.getSizeY() + size){
			ball.setSpeedY(ball.getSpeedY() * -1);
			didHit = true;
			}
		}

		if (didHit) {
			Powerup p = b.hit(); 	//Try to spawn a new Powerup
			if (p != null) {
				powerups.add(p);
				
			}
		}
		
		return didHit;
	}

	/**
	 * Checks if the ball moved below the pad. If so, return true (indicating that the game has been lost).
	 * @return boolean <Code>true</Code> if the Game has been lost, <Code>false</Code> otherwise.
	 */
	public boolean hasLost() {
		return (ball.getPosY() > pad.getPosY());
	}
	
	/**
	 * Looks whether a pad is sticky and if the ball has collided with the pad. 
	 * @return boolean <Code>true</Code> if the conditions meet, <Code>false</Code> otherwise.
	 */
	public boolean sticky() {
		if(pad.isSticky() && ball.getPosY() == pad.getPosY() - ball.getSizeX()) {
			if(ball.getPosX() >= pad.getPosX() - ball.getSizeX() && ball.getPosX() <= pad.getPosX() + pad.getSizeX() + ball.getSizeX()){
			return true;
		} 
		}
		return false;
	}
	
	/**
	 * Method used to move the ball object. Calculations and checks are made in order to determine the ball's speed.
	 * Then, the ball is moved to a new X and Y coordinate. 
	 */
	public void moveBall() {
		if(sticky()) {
			return;		//It should not move until the user has fired away the ball
		}	
		detectBoundariesCollision();	//If the ball collide with game boundaries
		detectPadCollision();			//If the ball collide with pad
		for (int i = 2; i < bricks.size(); i++) {
			Brick b = (Brick)bricks.get(i);
			if (detectBrickCollision(b)) {		//If the ball collide with a brick
				if (b.getHitsLeft() == 0) {
					bricks.remove(i);		//If the ball killed the brick
					try {
						Thread.sleep(20);	//allow system to update between movement and removal of brick
					} catch (InterruptedException e) {
						throw new Error("Internal error.");	//	Failed to sleep
					}
				}
			}
		}
		
		//Move ball to new positions
		ball.setPosX(ball.getPosX() + ball.getSpeedX());
		ball.setPosY(ball.getPosY() + ball.getSpeedY());
	}
	
	
	/**
	 * Moves the spawned Powerup's, if any. Also, checks whether the Powerup collide with the pad. If so, 
	 * activate the Powerup.
	 */
	public void movePowerups(Game game) {
		ArrayList<Powerup> toRemove = new ArrayList<Powerup>();
		for (Powerup p : powerups) {
			p.setPosY(p.getPosY() + p.getSpeedY());
			if (p.getPosY() > GROUND) {
				toRemove.add(p);
			} else if(powerPadCollision(p)) {
				p.activate(bricks, pad, game);	//activate the Powerup
				toRemove.add(p);		//and then remove it
			}
		}
		for (Powerup p : toRemove) {	//Removes Pwerups, if any should be removed. 
			powerups.remove(p);	
		}		
	}
	
	/**
	 * Makes the ball follow the pad.
	 */
	public void followPad() {
		ball.setPosX(pad.getPosX() + (pad.getSizeX() /2));
	}
	/**
	 * Move the pad.
	 * @param direction	A string representing the direction to move. Will move right if the string matches "right", ignoring case, and left if it matches "left", ignoring case.
	 */
	public void movePad(String direction) {
		//Used to not make the pad glitch out
		int extraBorderLeft = 10; 
		int extraBorderRight = 20;
		if (direction.equalsIgnoreCase("right")) {
			if (pad.getPosX() < RIGHTSIDE - pad.getSizeX() - extraBorderRight) {
				pad.setPosX(pad.getPosX() + pad.getSpeed());
			}
		} else if (direction.equalsIgnoreCase("left")) {
			if (pad.getPosX() > LEFTSIDE +(pad.getSizeY() + extraBorderLeft)) {
				pad.setPosX(pad.getPosX() - pad.getSpeed());
			}
		} else {
			throw new Error("Internal error.");	// Bad parameter.
		}
		
		//Makes the ball follow if the pad if sticky
		if(sticky())  {
			followPad();
		}
	}
}