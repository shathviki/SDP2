// Shathviki Krishnaraj - Pacman Game - File: Icons

import javax.swing.ImageIcon;

// Class - icons - holds all necessary icons 
public class Icons {

	// Constants allow us to insert image without typing out full "Images/name.bmp"
	public static final ImageIcon WALL = new ImageIcon("images/Wall.bmp");
	public static final ImageIcon FOOD = new ImageIcon("images/Food.bmp");
	public static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");
	public static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");
	public static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	public static final ImageIcon GATE = new ImageIcon("images/Gate.png");
	public static final ImageIcon CHERRY = new ImageIcon("images/Cherry.bmp");

	// Array of Pacmans - there are 4 that will face left, right, up, down 
	public static final ImageIcon[] PACMAN = { 
			new ImageIcon("images/Pacman0.gif"), 
			new ImageIcon("images/Pacman1.gif"),
			new ImageIcon("images/Pacman2.gif"), 
			new ImageIcon("images/Pacman3.gif"), 
		};
	
	// Array of 3 ghosts 
	public static final ImageIcon[] GHOST = { 
			new ImageIcon("images/Ghost0.bmp"), 
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp"),  
			new ImageIcon("images/BlueGhost.bmp")
		};
	
}