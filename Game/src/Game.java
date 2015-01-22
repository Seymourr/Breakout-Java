import java.util.ArrayList;

/**
 * The main game class. Will govern all aspects of the game.
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */

public class Game {
	private int gameSpeed;
	private Physics physics;
	private Level level;
	private GUI gui;
	private static ArrayList<Item> items;
	private ArrayList<Powerup> powerups;
	private int currentLevel;
	private Pad pad;

	/**
	 * Main method to start the game.
	 */
	public static void main(String[] args) {
		Game game = new Game();
	}

	/**
	 * Constructor of the class Game. Initializes the game and then starts it.
	 */
	public Game() {
		initialize();
		play();
	}

	/**
	 * Initialize game by creating necessary classes.
	 */
	private void initialize() {
		currentLevel = 1;
		gameSpeed = 50;
		items = new ArrayList<Item>();
		powerups = new ArrayList<Powerup>();
		getNewLevel();
		pad = level.getPad();
		physics = new Physics(items, powerups);
		gui = new GUI(physics, items, powerups, pad);
		gui.initialize();
	}

	/**
	 * Read the levels and get the current one.
	 */
	public void getNewLevel() {
		level = new Level();
		for(Item i : level.getLevel(currentLevel)) {
			
			items.add(i);
		}
	}
	
	/**
	 * The Game loop, in which all events take place.
	 */
	private void play() {
		boolean begin = false;
		boolean finished = false;
		gui.paint();		//Draw up the graphical interface
		
		while(!begin){		//Initial loop
			gui.redraw();		
			physics.followPad();		//Make it follow the pad initially.
			if(gui.getStart()) {
				begin = true;	//Start the game
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new Error("Internal error.");	//	Failed to sleep.
			}
		}
	
		while (!finished) {		//Main loop
			physics.moveBall();	
			physics.movePowerups(this);
			gui.redraw();
			try {
				Thread.sleep(gameSpeed);	
			} catch (InterruptedException e) {
				throw new Error("Internal error.");	//	Failed to sleep.
			}

			if (items.size() == 2) {	//Contains only the ball and the pad
				if(Level.amountOfLevels == currentLevel) {
					finished = true;			//Victory to the player!
					gui.setWon(true);
				} else {
				currentLevel++; //Get the next level
				gui.clear();
				getNewLevel();	
				}
			}
			
			if (physics.hasLost()) {	//The player failed to win..
				finished = true;
			}
			
		}
	
		gui.setOver(true);	//The game is over
		gui.redraw();
	}
	
	/**
	 * @return The current game size. A higher value means a slower game.
	 */
	public int getGameSpeed() {
		return gameSpeed;
	}
	
	/**
	 * @param gameSpeed	The new value to set the game speed to. A higher value means a slower game.
	 */
	public void setGameSpeed(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}
}