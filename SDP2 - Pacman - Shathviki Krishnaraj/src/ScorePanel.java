// Shathviki Krishnaraj - Pacman Game - File: ScorePanel

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

// Create the class for the score panel 
public class ScorePanel extends JPanel{

	// Creating labels
	public static JLabel BackgroundLabel = new JLabel();
	public static JLabel TitleLabel = new JLabel(new ImageIcon("images/pacmanLogo.png"), JLabel.CENTER);
	public static JLabel ScoreLabel = new JLabel("Score: " + Integer.toString(Board.score));
	public static JLabel HighScoreLabel = new JLabel("High Score (Shown at the End): " + Integer.toString(Board.highScore));
	
	// Create constructor 
	public ScorePanel() {
		
		// Set the layout, size, and bounds
		setLayout(null);
		setSize(600, 160);
		setBounds(0, 0, 600, 160);
		
		// Based on board - if the user wants to play in light mode,
		// Set background colour to white
		if (Board.mode == JOptionPane.NO_OPTION) 
			setBackground(Color.WHITE);
		// If they want to remain in dark mode, 
		// Set background colour to black  
		else if (Board.mode == JOptionPane.YES_OPTION)
			setBackground(Color.BLACK);
		
		// Set up the Background
		BackgroundLabel.setBounds(0,0,600,600);
		BackgroundLabel.setLayout(null);
		add(BackgroundLabel);
		
		// Set location & add title label to panel		
		TitleLabel.setBounds(20, 20, 600, 100);
		TitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		TitleLabel.setForeground(Color.WHITE);
		add(TitleLabel);
		
		// Score: Label
		ScoreLabel.setBounds(50, 95, 400, 50);
		ScoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));	
		
		// Highscore: Label
		HighScoreLabel.setBounds(50, 115, 400,50);
		HighScoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));

		// Based on board - if the user wants to play in light mode,
		// Set FONT colour to black (white on white -> no visibility)
		if (Board.mode == JOptionPane.NO_OPTION) {
			ScoreLabel.setForeground(Color.BLACK);	
			HighScoreLabel.setForeground(Color.BLACK);	
		// Based on board - if the user wants to play in dark mode,
		// Set FONT colour to white (black on black -> no visibility)
		} else if (Board.mode == JOptionPane.YES_OPTION) {
			ScoreLabel.setForeground(Color.WHITE);		
			HighScoreLabel.setForeground(Color.WHITE);		
		}
		
		// Add the score label 
		add(ScoreLabel);
		
		// Add the high score label 
		add(HighScoreLabel);
		
		// Make panel visible
		setVisible(true);
	}
	
	// Method - displays the current score. Sets the text using the variable int score
	public static void currentScore(int score) {
		ScoreLabel.setText("Score: " + score);
	}
	
	// Method - displays the high score. Sets the text using the variable int highScore
	public static void highScore(int highScore) {
		HighScoreLabel.setText("High Score (Shown at the End): " + highScore);
	}
}
