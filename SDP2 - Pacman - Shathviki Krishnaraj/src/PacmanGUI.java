// Shathviki Krishnaraj - Pacman Game - File: PacmanGUI

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// The class creates the GUI screen
public class PacmanGUI extends JFrame {

	// Seperating  panels
	private Board board = new Board();
	private ScorePanel scorePanel = new ScorePanel(); 

	// Constructor
	public PacmanGUI() {

		// Set screen size & make the title
		setSize(615, 635);
		setTitle("Shathviki's Pacman Game");
		
		// Closes the frame when program is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		// Keyboard input
		addKeyListener(board);
		
		// Set bounds & add board 
		board.setBounds(0, 150, 600, 450);	
		add(board);
		
		// Add score panel		
		scorePanel.setBounds(0,0,600,450);
		add(scorePanel);
		
		// Makes the frame visible to the screen
		setVisible(true);
	}

}