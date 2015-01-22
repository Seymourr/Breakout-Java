import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class is responsible for retrieving and storing info on levels.
 * The levels are read from file and then stored in this class so that other classes can retrieve them.
 * Levels are stored as an ArrayList[Item], where the item at index 0 is the Ball and at index 1 is the Pad.
 * The rest of the ArrayList contains only Bricks.  
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class Level {
	private Scanner in;
	public static int amountOfLevels;
	private HashMap<Integer, ArrayList<Item>> levels; 
	private Pad pad;
	


	/**
	 * Creates a new hashmap that contain all the available levels for this game.
	 */
	public Level() {
		levels = new HashMap<Integer, ArrayList<Item>>();
		importLevels();			//Get levels into the HashMap
	}
	
	/**
	 * Returns the pad used in the game.
	 * @return pad The pad in the game.
	 */
	public Pad getPad() {
		return pad;
	}
	
	/**
	 * Returns a level pack, if it exists.
	 * @param level The level to be returned.
	 * @return null If the desired level does not exist.
	 * @return pack The desired level.
	 */
	public ArrayList<Item> getLevel(int level) {
		if (levels.size() == 0) {
			return null;
		} else {
			return levels.get(level);
		}
	}

	/**
	 * Adds all the levels into a HashMap.
	 */
	private void importLevels() {
		//Get amount of levels.
		amountOfLevels = 0;
		int currentLevel = 1;
		boolean done = false;
		while (!done) {
			String path = "res/level" + currentLevel + ".lvl";
			File f = new File(path);
			if (f.exists() && f.isFile()) {
				amountOfLevels++;
				levels.put(currentLevel, readLevel(currentLevel));	// Put the level in the HashMap.
				currentLevel++;
			} else {
				done = true;
			}
		}
	}
	
	/**
	 * Read off a text and add the Item-objects in it to an ArrayList.
	 * @param level The level to read off.
	 * @return array An arrayList of Item-objects.
	 */
	private ArrayList<Item> readLevel(int level) {
		int index = 0;
		if(!levels.isEmpty()){
			index = 2; //Ball and Pad are already in place
		}
		try {
			ArrayList<Item> items = new ArrayList<Item>();
			String path = "res/level" + level + ".lvl";
			File f = new File(path);
			in = new Scanner(f);
			while (in.hasNext()) {
				String line = in.nextLine();
				String[] splitLine = line.split("\\s");
				
				switch (index) { 
				case 0:
					// First item will be the ball.
					Ball ball = new Ball();
					items.add(ball);
					break;
				case 1:
					// Second item will be the pad.
					Pad pad = new Pad();
					items.add(pad);
					this.pad = pad;
					break;
				default:
					// All other items will be bricks.
					Brick brick = new Brick(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]));
					items.add(brick);
					break;
				}
				index++;
			}
			return items;
		} catch (Exception e) {
		throw new Error("Internal error");	// Failed to read file.
		}	
	}
}
