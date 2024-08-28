// Shathviki Krishnaraj - Pacman Game - File: Board

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Class Board - uses KeyListener and ActionListener
@SuppressWarnings("serial")
public class Board extends JPanel implements KeyListener, ActionListener {

	// GUI Elements

	// Implement timer - has 250 milliseconds
	// Slower time = easier game 
	private Timer gameTimer = new Timer(250, this);

	// Array - holds the cells as a two-dimensional array
	private Cell[][] mazeArray = new Cell[25][27];

	// Creates mover object, Pacman 
	private Mover pacman;

	// Variable - declare & initialize number of lives 
	private int lives = 3; 
	
	// Array - holds our 3 ghosts
	private Mover[] ghostArray = new Mover[3];

	// Variable - sets number of pellets (which represents the food)
	// This will later accumulate as Pacman eats food 
	private int pellets = 0;

	// Variable - sets score & high score 
	public static int score = 0;
	public static int highScore = 0;
	
	// Variable - to speed up 
	private int speed = 200; 
	
	// Variable - see if the user wants to play again 
	private int playAgain;
	
	// Variable - see if the user wants to play in light/dark mode 
	public static int mode; 
	
	// Variable - see what level the user wants to play at 
	private int levelType;

	// Constructor Method - Board: Sets layout and method
	public Board() {

		// Set the grid layout & background colour
		setLayout(new GridLayout(25, 27));
		setBackground(Color.BLACK);
		
		//How to Play  - Screen
		JOptionPane.showMessageDialog(this, "How to Play: "
				+ "\nPacman is the yellow character. You control him by pressing the 4 different arrow keys."
				+ "\nThe goal of Pacman is to eat all the pellets throughout the maze without getting caught by the ghosts."
				+ "\nPress 'OK' to continue.");
		
		//Rules  - Screen
		JOptionPane.showMessageDialog(this, "Rules:"
				+ "\n1. You have 3 lives. If you get caught by a ghost, you lose a life."
				+ "\n2. No walking through walls."
				+ "\n3. No going inside the ghosts' cage."
				+ "\nPress 'OK' to continue.");
		
		// Ask user for light or dark mode 
		mode = JOptionPane.showConfirmDialog(this, "Remain in dark mode?",
				"Mode", JOptionPane.YES_NO_OPTION); 
		
		// If the user doesn't want to remain in dark mode, switch background to white for light mode 
		if (mode == JOptionPane.NO_OPTION) 
			setBackground(Color.WHITE);

		// Asking user what type of level they want to play
		levelType = JOptionPane.showConfirmDialog(this, "Choose 'No' for normal level.\n Choose 'Yes' for hard level.",
				"Level Type", JOptionPane.YES_NO_OPTION);

		// If user chooses No, display normal level
		if (levelType == JOptionPane.NO_OPTION) {
			setLayout(new GridLayout(25, 27));
			loadBoard();
		}
		// If user chooses Yes, display harder level
		else if (levelType == JOptionPane.YES_OPTION) {
			setLayout(new GridLayout(25, 27));
			loadBoard();
		}
	}	
	
	// File reading
	private void loadBoard() {

		// Create a sound effect for intro music 
		String startSound = "Sounds/GAMEBEGINNING.wav";
		
		// Call the method that plays the start music 
		PlayStartMusic(startSound);
		
		// Variable - keeps track of what row we are on
		int row = 0;

		// Use Scanner to allow input from the computer 
		Scanner input;

		// Use a try-catch structure to see any errors 
		try {

			// Add the maze text file to use in the game 
			input = new Scanner(new File("maze.txt"));

			// Run the following code until there are no more rows in the file
			while (input.hasNext()) {

				// Reads in the first line as a character array
				char[] lineArray = input.nextLine().toCharArray();


				for (int column = 0; column < lineArray.length; column++) {

					// For each spot, assign a corresponding letter
					mazeArray[row][column] = new Cell(lineArray[column]);

					// Checks if there is food. If f is in line, increments number of pellets
					if (lineArray[column] == 'F')
						pellets++;

					// If P (Pacman) is in the text file, call the 
					// Mover class so Pacman can move 
					else if (lineArray[column] == 'P') {

						pacman = new Mover(row, column);

						// Set up photo of Pacman
						pacman.setIcon(Icons.PACMAN[0]); // Left image

						// Set direction of Pacman
						pacman.setDirection(0); // Starts facing left
					}

					// Of there is 0, 1, or 2, (assigned to the ghosts)
					// Also call the mover class so the ghost can move
					else if (lineArray[column] == '0' || lineArray[column] == '1' || lineArray[column] == '2') {
						
						int gNum = Character.getNumericValue(mazeArray[row][column].getItem());
						ghostArray[gNum] = new Mover(row, column);
						ghostArray[gNum].setIcon(Icons.GHOST[gNum]);
					}

					// Add the mazeArray to GUI Screen 
					add(mazeArray[row][column]);

				}

				// Increment row number
				row++;
			}

			// Close the file
			input.close();


		} catch (FileNotFoundException exception) {

			System.out.println("File not found.");
		}
		
		// Set the gate icon
		mazeArray[10][13].setIcon(Icons.GATE);
	}
	
	// Method - to play the start sound at the beginning of the game 
	// Source to Play Audio: https://www.youtube.com/watch?v=wJO_cq5XeSA
	private void PlayStartMusic(String startSound) {
		
		// Use try and catch for errors 
		try {
			File startPath = new File(startSound);
			
			// If the path exists, play the audio 
			if (startPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(startPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else 
			{
				System.out.println("Can't find file ");
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}		
	}

	// Method - moves Pacman throughout the game 
	private void performMove(Mover mover) {

		// if mover is in column 1, move it to 24 & set the icon as the door
		if (mover.getColumn() == 1) {
			mover.setColumn(24);
			mazeArray[12][1].setIcon(Icons.DOOR);
			// if mover is in column 25, move it to 2 & set the icon as the door
		} else if (mover.getColumn() == 25) {
			mover.setColumn(2);
			mazeArray[12][25].setIcon(Icons.DOOR);
		}

		// Local variables used to reduce the code length
		// They represent the current cell that the mover is on
		// and the next cell that the mover will go to
		Cell currentCell = mazeArray[mover.getRow()][mover.getColumn()];
		Cell nextCell = mazeArray[mover.getNextRow()][mover.getNextColumn()];

		// For mover to move through maze without hitting walls
		if (nextCell.getIcon() != Icons.WALL) {

			// If character is not pacman & standing in row with food, 
			// they don't get food
			if (mover != pacman && currentCell.getItem() == 'F')
				currentCell.setIcon(Icons.FOOD);
			else
				currentCell.setIcon(Icons.BLANK);

			// Move the mover
			mover.move();
			currentCell = mazeArray[mover.getRow()][mover.getColumn()];

			// If character collides with the ghost, then they die
			if (collide())
				//Call death method 
				death();
			// Otherwise, the character moves as normal 
			else
				currentCell.setIcon(mover.getIcon());

			// Set the cherry icon if score is greater than 25
			if (score > 25)
				mazeArray[17][20].setIcon(Icons.CHERRY);
			
			// If character is pacman and lands on the cherry
			// Score AND high score goes up by 100
			if (mover == pacman && currentCell == mazeArray[17][20]) {
				score += 100;
				ScorePanel.currentScore(score);
				
				highScore += 100;

			}
			
			// If character is pacman & lands on a piece of food
			if (mover == pacman && currentCell.getItem() == 'F') {

				// Score goes up
				score++;
				highScore++;
				
				// Show current score here
				ScorePanel.currentScore(score);
				
				//Create a sound effect 
				String eatSound = "Sounds/pacchomp.wav";
				PlayEatMusic(eatSound);
				
				// Food is eaten
				currentCell.setItem('E');

				// To increase the speed as time goes on 
				for (int speedIncrease = 1; speedIncrease < 1000; speedIncrease++){
					if (levelType == JOptionPane.YES_OPTION)
						if (score > speedIncrease * 10)
							gameTimer.setDelay(speed - 7 * speedIncrease);
					if (score > speedIncrease * 10)
						gameTimer.setDelay(speed - (3 * speedIncrease));
				}
				// If Pacman ate all the food successfully, the user wins
				if (score == (pellets + 100)) {
					gameTimer.stop();
					JOptionPane.showMessageDialog(this, "You won!");
				}
				
			}
		}
	}
 
	// Method - to play the "eat" sound when pacman eats 
	private void PlayEatMusic(String eatSound) {
		
		// Use try and catch for errors 
		try {
			File eatPath = new File(eatSound);
			
			// If the path exists, play the audio 
			if (eatPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(eatPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else 
			{
				System.out.println("Can't find file ");
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}		
	}

	// Method - what happens when user dies
	private void death() {
				
				// If Pacman & ghosts collide, decrease number of lives by 1
				// Show a message stating that Pacman lost a life
				// Set Pacman to the original location to continue the game
				
				if (collide() && lives == 3) {
					JOptionPane.showMessageDialog(this, "You lost a life. 2 lives remaining. Press 'OK' to start again! ");
					
					// Reset the score to 0 (As user lost a life)
					score = 0; 

					// Decrease the number of lives by one
					lives--;
					
					// Reset pacman to original location 
					pacman.setColumn(13);
					pacman.setRow(16);
				}

				if (collide() && lives == 2) {
					JOptionPane.showMessageDialog(this, "You lost another life. 1 life remaining. Press 'OK' to start again!");

					//To reset the score to 0 (as user lost a life)
					score = 0; 
					
					// Decrease the number of lives by one
					lives--;
					
					// Reset pacman to original location 
					pacman.setColumn(13);
					pacman.setRow(16);
				}

				if (collide() && lives == 1) {
					mazeArray[pacman.getRow()][pacman.getColumn()].setIcon(Icons.SKULL);
					JOptionPane.showMessageDialog(this, "Game Over. Thank you for playing. Press 'OK' to close game. ");
					
					// Decrease the number of lives by one
					lives--;	
					
					// When Pacman dies, stop the timer
					gameTimer.stop();
					
					// Get accurate high score & display it 
					// Source: https://stackoverflow.com/questions/21476923/score-sytem-for-my-java-game
					if (score > highScore)
						highScore = score;
						
					ScorePanel.HighScoreLabel.setText("High Score (Shown at the End): " + highScore);
					
					// Once there are no more lives left, close the game
					setVisible(false);
					
					// Source: https://stackoverflow.com/questions/37436784/how-to-restart-the-game-in-java
					// Source: https://stackoverflow.com/questions/15853112/joptionpane-yes-no-option
					// Once  player is dead, ask if they want to play again
					playAgain = JOptionPane.showConfirmDialog(this, "Do you want to play again? ", "Game over, play again?",
							JOptionPane.YES_NO_OPTION);

					// If yes, open a new screen so they can play again 
					if (playAgain == JOptionPane.YES_OPTION) {
						
						new PacmanGUI();
						
						// Reset the score & high score 
						score = 0; 
						highScore = 0;

					// If no, display thank you message 
					} else if (playAgain == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(this, "Thank You For Playing. Try Again Next Time.");
						// To close program entirely 
						// System.exit(playAgain); 
							//-> Commented this out because it prevented if statement above from working
					}
				}	
	}
	
	// Method - to play the killed sound at pacman's death
	private void PlayDeathMusic(String killedSound) {
		
		// Use try and catch for errors 
		try {
			File killedPath = new File(killedSound);
			
			// If the path exists, play the audio 
			if (killedPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(killedPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else 
			{
				System.out.println("Can't find file ");
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	// Method - Checks if there is collision
	private boolean collide() {

		// Loop through ghosts
		for (Mover ghost : ghostArray) {

			// See if ghosts are standing on pacman
			if (ghost.getRow() == pacman.getRow() && ghost.getColumn() == pacman.getColumn()) {
				return true;
			}
		}

		return false;
	}

	// Method - move the ghosts
	private void moveGhosts() {

		// Enhanced for loop to iterate through ghost array
		for (Mover ghost : ghostArray) {

			// Set integer for direction that the ghost will move in
			int direction = 0;
			
			// If the ghost is on the gate, move it upwards
			// (So it is forced out)
			if (ghost.getRow() == 10 && ghost.getColumn() == 13) {
				direction = 2;	
			}
			
			// If Pacman & ghost are in the same row BUT not column
			// Move the ghost towards Pacman (Ghost Chase AI)
			if (pacman.getRow() == ghost.getRow()) {
				
				if (pacman.getColumn() > ghost.getColumn())
					direction = 2;
				else if (pacman.getColumn() < ghost.getColumn())
					direction = 0;
							
			// If Pacman & ghost are in the same column BUT not row
			// Move the ghost towards Pacman (Ghost Chase AI)
			} else if (pacman.getColumn() == ghost.getColumn()) {
				
				if (pacman.getRow() > ghost.getRow())
					direction = 3;
				else if (pacman.getRow() < ghost.getRow())
					direction = 1;
				
			} else {
				
				// use a do-while loop to ensure that the ghost moves logically
				do {

					// Use the Math class to generate a random number
					direction = (int) ((Math.random()) * 4);
					
				// If  difference between ghost direction is going and 
				// new selected direction is two (opposite), choose a new direction.
				// Prevents the ghost from going back and forth
				} while (Math.abs(ghost.getDirection() - direction) == 2);
			
			}// Set the direction
			ghost.setDirection(direction);

			// Stop the ghosts from moving when Pacman is dead
			if (!pacman.isDead())
				performMove(ghost);
		}

	}

	// Method - actionPerformed - To make everything move when the timer goes 'tick'
	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == gameTimer) {

			// If Pacman tries to enter the ghost house, game is over and screen goes away
			if (pacman.getColumn() == 13 && pacman.getRow() == 10) {
				JOptionPane.showMessageDialog(this, "Not allowed to enter the ghost house. Game Over! ");
				setVisible(false);
				// Exits the program
				System.exit(playAgain);						
			}
			
			// Otherwise, move Pacman & the ghosts 
			performMove(pacman);
			moveGhosts();

		}
	}

	// Method - allows user to begin game once a key is pressed 
	@Override
	public void keyPressed(KeyEvent key) {

		// Allows game to start ONLY when a key is pressed
		if (gameTimer.isRunning() == false && pacman.isDead() == false)
			gameTimer.start();

		// Continues the game when Pacman is alive & has not eaten all pellets
		if (pacman.isDead() == false && score != pellets) {

			// Based on ASCII code
			int direction = key.getKeyCode() - 37; 

			// Prevents bumpung into walls
			if ((direction == 0 && mazeArray[pacman.getRow()][pacman.getColumn() - 1].getIcon() != Icons.WALL)

					|| (direction == 1 && mazeArray[pacman.getRow() - 1][pacman.getColumn()].getIcon() != Icons.WALL)
					|| (direction == 2 && mazeArray[pacman.getRow()][pacman.getColumn() + 1].getIcon() != Icons.WALL)
					|| (direction == 3 && mazeArray[pacman.getRow() + 1][pacman.getColumn()].getIcon() != Icons.WALL)) {

				// Sets Pacman & the direction
				pacman.setIcon(Icons.PACMAN[direction]);
				pacman.setDirection(direction);
			}
		}
	}

	// This method is left blank, but must be included to satisfy the Key Listener
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// This method is left blank, but must be included to satisfy the Key Listener
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
