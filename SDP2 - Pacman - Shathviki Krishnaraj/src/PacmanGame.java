// Project Header

/* Name: Shathviki Krishnaraj
   Date: Dec. 18, 2022
   Course Code: ICS3U1-02 Mr. Fernandes
   Title: SDP 2 - Pacman Game 

   Description: Pac-Man is an action video game. The player controls the character through an enclosed maze. 
				The goal of the game is to eat all of the pellets placed in the maze while avoiding
 				colored ghosts that seek Pac-Man. Note that Pacman cannot enter the ghost house & that eating a 
				certain number of pellets will cause a bonus item (cherry). In this game, the player has 3 lives.
   
   Major Skills:
   	- Methods
   	- Arrays
   	- Classes
   	- Constructors, get and set methods
   	- Conditional statements
   	- For loops & while loops 
   	- Using audio
   	- Using timer
   	- Swing GUI components 
   	- JButton, JFrame, JOptionPane, JLabel, JPanel, JRadioButton (also JColor, JFont, other styling)
   	
   Added Features: 
   	- Score (increases as PacMan eats pellets)
   	- High Score 
   	- PacMan cannot enter the ghosts house (the game will end)
	- Ghost chase AI 
	- Cherry bonus item (Appears when player has a score of 25)
	- Lives (Player has 3 lives)
	- Sounds (chomping, dying, intro music)
	- Themes (Player can choose between light & dark mode)
	- Instructions & Rules
	- Play Again Screen (Reloads game if user wants to play again)
	- Power Up: Speed - As Pacman eats more pellets, the game speeds up 
	- Levels (Normal/Hard)
	- Code to help ghosts to get out of the house (and stay out till they ‘die’)

   Areas of Concern: High score accumulates, rather than replaces 

*/

// Application Class - Pacman Game 
public class PacmanGame {

	// Main method - runs the program
	public static void main(String[] args) {

		// Method PacmanGUI - Creates our Pacman GUI Screen 
		new PacmanGUI();	
	}
}
