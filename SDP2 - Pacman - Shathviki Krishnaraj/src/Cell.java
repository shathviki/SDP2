// Shathviki Krishnaraj - Pacman Game - File: Cell

import javax.swing.JLabel; 

// Create class cell
public class Cell extends JLabel{

	// Create character - item 
	private char item; 
	
	// Create constructor 
	public Cell(char item) {
		
		super();
		this.item = item;
		
		// Set the icon
		setCodeIcon();
	}

	// Get and set method 
	public char getItem(){
		return item; 
	}
	
	public void setItem(char item){
		this.item = item;
	}

	// Based on the characters, set the icon accordingly 
	private void setCodeIcon() {
		if (item == 'P')
			setIcon(Icons.PACMAN[0]);
		else if (item == '0')
			setIcon(Icons.GHOST[0]);
		else if (item == '1')
			setIcon(Icons.GHOST[1]);
		else if (item == '2')
			setIcon(Icons.GHOST[2]);
		else if (item == 'W')
			setIcon(Icons.WALL);
		else if (item == 'F')
			setIcon(Icons.FOOD);
		else if (item == 'D')
			setIcon(Icons.DOOR);
		else if (item == 'C')
			setIcon(Icons.CHERRY);
	}
	
}
