import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.util.ArrayList;

/**
 * The class Graphics is responsible for all graphic handling and user input. 
 * 
 * @author Jonathan Olergård
 * @author Jack Shabo
 * @version 2013-05-14
 */
public class GUI implements KeyListener{
	private static final Dimension GAMESIZE = new Dimension(750, 600);
	private Graphics g;	
	private DrawPanel canvas;	
	private ArrayList<Item> items;	//Hold Ball, Pad and Brick objects
	private ArrayList<Powerup> powerups;	//Holds Powerup objects
	private Pad pad;
	
	private JFrame frame;
	private Physics phys;
	private boolean start; //True if the game has started
	private boolean over;
	private boolean won;

	/**
	 * Constructior for class GUI. Acquires all items necessary for handling drawing and user input.
	 * @param game		The instance of the Game class.
	 * @param p			The instance of the Physics class.
	 * @param items		A collection of bricks in the level.
	 * @param powerups	A collection of powerups to draw. 
	 * @param pad		The intance of the Pad in the game.
	 */
	public GUI(Physics p, ArrayList<Item> items, ArrayList<Powerup> powerups, Pad pad) {
		System.setProperty("sun.awt.noerasebackground", "true");
		this.items = items;
		this.powerups = powerups;
		this.canvas = new DrawPanel(new Color(5, 35, 35));
		this.phys = p;
		//this.level = new Level();
		this.pad = pad;

		BufferedImage image = new BufferedImage(GAMESIZE.height, GAMESIZE.width, BufferedImage.TYPE_INT_ARGB);
		g = image.getGraphics();
		canvas.setFocusable(true);
		canvas.addKeyListener(this);
	}
	
	/**
	 * @param over True if the game is finished (or lost), otherwise false.
	 */
	public void setOver(boolean over) {
		this.over = over;
	}
	
	/**
	 * @param won True if the game is finished and won, false otherwise.
	 */
	public void setWon(boolean won) {
		this.won = won;
	}

	/**
	 * @return True if the game is not yet started, otherwise false.
	 */
	public boolean getStart() {
		return start;
	}

	
	/**
	 * Detects the pressing of keys and performs the right action in return.
	 */
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
		case KeyEvent.VK_LEFT:
			phys.movePad("left");
			break;
		case KeyEvent.VK_RIGHT:
			phys.movePad("right");
			break;
		case KeyEvent.VK_Q:
			quit();
			break;
		case KeyEvent.VK_SPACE:
			start = true;
			if(phys.sticky()) {
				pad.toggleIsSticky();
			}
			break;

		}
	} 

	//Not used 
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e) {}

	/**
	 * Redraws the canvas.
	 */
	public void paint() {
		canvas.paintComponent(g);
	}

	/**
	 * Redraws the canvas.
	 */
	public void redraw() {

		canvas.repaint();
	}

	/**
	 * Constructs the game window and all components.
	 */
	public void initialize() {
		makeFrame();
	}
	
	/**
	 * Create the graphical interface, which contains menu and canvas (drawable surface).
	 */
	private void makeFrame() {
		frame = new JFrame("d-Bounce");
		frame.setPreferredSize(GAMESIZE);
		frame.setJMenuBar(makeMenu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		frame.getContentPane().add(canvas);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);	//Cannot resize the frame
	}

	/**
	 * Make the menuBar for this GUI and add new Menu's with new options.
	 * @return menuBar The menuBar for this GUI, which hold all the Menu's. 
	 */
	private JMenuBar makeMenu() {
		JMenuBar menubar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help");
		menubar.add(gameMenu);
		menubar.add(helpMenu);

		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();		// Used for shortcuts.
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));		//	Shortcut ctrl-q to quit application.
		JMenuItem aboutItem = new JMenuItem("About");
		quitItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {quit();}});
		aboutItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {about();}});
		gameMenu.add(quitItem);
		helpMenu.add(aboutItem);
		return menubar;
	}
	
	/**
	 * Prints that the game is over. What a shame. 
	* @param g The drawpanel to print on.
	 */
	public void printGameover(Graphics g) {
		Font font = new Font("TimesRoman", Font.PLAIN, 100);
		canvas.setBackground(Color.black);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Game Over", 145, 275);
	}
	
	/**
	 * Prints that the game was won, hooray!
	 * @param g The drawpanel to print on.
	 */
	public  void printVictory(Graphics g) {
		Font font = new Font("TimesRoman", Font.PLAIN, 80);
		canvas.setBackground(Color.pink);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("GAME COMPLETE!", 0, 75);
		g.drawString("Du är en sann datalog!", 0, 175);
		font = null;
		font = new Font("Monospaced", Font.PLAIN, 40);
		g.setFont(font);
		g.drawString("J&J Studios <3" , 0, 475);
	}
	
	/**
	 * Ends this program.
	 */
	private void quit() {
		System.exit(0);
	}
	
	/**
	 * This method pop up a Dialog-Pane which inform the user how the game works. 
	 */
	private void about() {
		JOptionPane.showMessageDialog(frame,
				"D-Bounce\n" + "2013-04-25\n" + "\n" + "This game is a replica of the old game Breakout.\n"
						+ "The goal of this game is to clear all of the blocks at the top of the field by bouncing the\n"
						+ "ball on the pad, trying to hit the bricks.\n"
						+ "The bricks may need to be hit several times, but eventually they will break.\n"
						+ "While playing, one might encounter powerups that have either "
						+ "positive or negative effects.\n"
						+ "These powerups can affect either the ball, the pad or the bricks.\n\n"
						+ "Controls\nTo start the game, press 'space'.\n"
						+ "Use the 'left' and 'right' arrowkeys to move the pad.",
						"About the Game",
						JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Clear all graphical components from the GUI.
	 */
	public void clear() {
		canvas.removeAll();
	}
	
	/**
	 * Adds a Powerup to the array "powerups". 
	 * @param p The Powerup to be added to an array.
	 */
	public void addPowerup(Powerup p) {
		powerups.add(p);
	}
	
	/**
	 * Creates a new panel to draw geographical figures on. 
	 * 
	 * @author Jack Shabo
	 * @author Jonathan Olergård
	 * @version 2013-05-14
	 */
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel {

		/**
		 * Set the background color for this DrawPanel
		 * @param c
		 */
		public DrawPanel(Color c) {
			this.setBackground(c);	
		}
		
		/**
		 * The ongoing Paint method, which paint (or re-paint) all graphical components used in the game.
		 * @param g Reference to the graphical window used to show graphics.
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if(!over) { //Game in progress
				for (Item i : items) {

					if (i instanceof Ball) {
						g.setColor(new Color(230, 230, 250));	//	Color white.
						g.fillOval(i.getPosX(), i.getPosY(), i.getSizeX(), i.getSizeY());
					} else if (i instanceof Pad) {
						g.setColor(new Color(218, 165, 32));	//	Color yellow.
						g.fillRect(i.getPosX(), i.getPosY(), i.getSizeX(), i.getSizeY());
					} else if (i instanceof Brick) {
						switch (((Brick) i).getHitsLeft()) {
						case 1:
							g.setColor(new Color(70, 130, 180));		//	Color blue.
							break;
						case 2:
							g.setColor(new Color(178, 34, 34));			//	Color red.
							break;
						case 3:
							g.setColor(new Color(138, 43, 226));		//	Color purple.
							break;
						case 4:
							g.setColor(new Color(255, 165, 0));			//	Color orange.
							break;
						case 5:
							g.setColor(new Color(0, 100, 0));			//	Color green.
							break;
						default:

							throw new Error("Internal error");	//	Unhandled amount of hits left.
						}
						g.fillRect(i.getPosX(), i.getPosY(), i.getSizeX(), i.getSizeY());
					} else {
						throw new Error("Internal error");	//	Unhandled class instance.
					}
				}
				
				if (powerups != null) {
					for (Powerup p : powerups) {
						switch (p.getType()) {
						case IncPad:
							g.setColor(new Color(255, 0, 0));		//	Color red.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						case DecPad:
							g.setColor(new Color(0, 255, 0));		//	Color green.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						case IncGameSpeed:
							g.setColor(new Color(0, 0, 255));		//	Color blue.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						case DecGameSpeed:
							g.setColor(new Color(0, 255, 255));		//	Color turquoise.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						case StickyPad:
							g.setColor(new Color(255, 255, 0));		//	Color yellow.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						case SuperCerisePower:
							g.setColor(new Color(255, 0, 255));		//	Color cerise.
							g.fillOval(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
							break;
						default: 
						}
					}
				}
				return;
			}
			if(won) {
				printVictory(g);	//Player won
			} else {
			printGameover(g);		//Player lost
			}
		}	
	}
}