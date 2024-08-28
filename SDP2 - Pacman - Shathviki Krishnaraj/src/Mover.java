// Shathviki Krishnaraj - Pacman Game - File: Mover

import javax.swing.JLabel;

// Create class mover to move objects 
public class Mover extends JLabel{

	// Set the rows & columns
	private int row; 
	private int column; 
	
	private int dRow; 
	private int dColumn;
	
	private boolean isDead;
	
	// Create the constructor
	public Mover(int row, int column) {
		super();
		this.row = row; 
		this.column = column; 
	}

	// Get and set methods 
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getdColumn() {
		return dColumn;
	}

	public void setdColumn(int dColumn) {
		this.dColumn = dColumn;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	// Utility methods 
	// To move accordingly 
	public void move() {
		row += dRow;
		column += dColumn; 
	}
	
	// Method setDirection() - Changes direction based on column & row 
	public void setDirection(int direction) {
		
		dRow = 0; 
		dColumn = 0; 
		
		if (direction == 0)
			dColumn = -1;
		else if (direction == 2)
			dColumn = 1;
		else if (direction == 1)
			dRow = -1;
		else if (direction == 3)
			dRow = 1;
	}
	
	// Get the row, and return an according value 
	public int getDirection() {
		
		if (dRow == 1)
			return 3; 
		else if (dRow == -1)
			return 1;
		else if (dColumn == 1)
			return 2; 
		else
			return 0;
	}
	
	// Method - Get the next row, based on current one
	public int getNextRow(){
		return row + dRow;
	}
	
	// Method - Get the next column, based on current one
	public int getNextColumn(){
		return column + dColumn;
	}
}
