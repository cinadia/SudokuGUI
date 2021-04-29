package sudokuGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import sudokuGUI.ClickHandler;

public class SudokuGUI extends JFrame {
	
	Button[][] buttons; 
	static int[][] NUMS; 
	static boolean[][] ISDISPLAYED; 
	static int ROWS = 3;
	static int COLS = 3;
	
	// Stores current number selected from the numpad
	private int curNum;
	
	
	public SudokuGUI() {
		
		super("Sudoku"); // Window title

		// Set x button to close when clicked
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Make frame visible
		this.setVisible(true);
		
		// Make outer JFrame to hold smaller JFrames
		JPanel bigGrid = new JPanel();
		// Outer JFrame is 3x3 grid to hold 9 more 3x3 grids
		bigGrid.setLayout(new GridLayout(ROWS, COLS));
		// Make padding
		Border padding1 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		bigGrid.setBorder(padding1);
		
		// Set correct button values 
		NUMS = new int[][] {
			{1,2,3,6,7,8,9,4,5},
			{5,8,4,2,3,9,7,6,1},
			{9,6,7,1,4,5,3,2,8},
			{3,7,2,4,6,1,5,8,9},
			{6,9,1,5,8,3,2,7,4},
			{4,5,8,7,9,2,6,1,3},
			{8,3,6,9,2,4,1,5,7},
			{2,1,9,8,5,7,4,3,6},
			{7,4,5,3,1,6,8,9,2} };
	
		// Set boolean values 
		// False values are what users have to input/are not displayed
		
		ISDISPLAYED = new boolean[][] {
			{false, true, false, true, false, true, false, false, false},
			{true, true, false, false, false, true, true, false, false},
			{false, false, false, false, true, false, false, false, false},
			{true, true, false, false, false, false, true, false, false},
			{true, false, false, false, false, false, false, false, true},
			{false, false, true, false, false, false, false, true, true},
			{false, false, false, false, true, false, false, false, false},
			{false, false, true, true, false, false, false, true, true},
			{false, false, false, true, false, true, false, true, false}};
		
			
		// Boolean values for testing win methods 
		
		/*
		 * ISDISPLAYED = new boolean[][] { {true, false, true, true, true, true, true,
		 * true, true}, {true, true, true, true, true, true, true, true, true}, {true,
		 * true, true, true, true, true, true, true, true}, {true, true, true, true,
		 * true, true, true, true, true}, {true, true, true, true, true, true, true,
		 * true, true}, {true, true, true, true, true, true, true, true, true}, {true,
		 * true, true, true, true, true, true, true, true}, {true, true, true, true,
		 * true, true, true, true, true}, {true, true, true, true, true, true, false,
		 * false, false}};
		 */
		
		// Initialize buttons list to hold buttons
		buttons = new Button[9][9];
		
		// Make 9 more smaller panels to hold a 3x3 grid of buttons each
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				JPanel smallGrid = new JPanel();
				smallGrid.setLayout(new GridLayout(ROWS,COLS));
				smallGrid.setBorder(BorderFactory.createLineBorder(Color.black));
				for (int k = 0; k < ROWS; k++) {
					for (int m = 0; m < COLS; m++) {
						// Send integer values and boolean values to Button constructor 
						Button b = new Button(NUMS[i*3 + k][j*3 + m], ISDISPLAYED[i*3 + k][j*3 + m]);
						// Add button to list of buttons
						buttons[i*3 + k][j*3 + m] = b;
						smallGrid.add(b);
						// Add actionListener to track mouse clicks 
						b.addActionListener(new ClickHandler(this));
					}
				}
				bigGrid.add(smallGrid);
			}
		}
		
		// Make number pad panel
		JPanel numPad = new JPanel();
		// Num panel is 3x3
		numPad.setLayout(new GridLayout(ROWS, COLS));
		// Make padding
		numPad.setBorder(BorderFactory.createLineBorder(Color.black));
		Border padding2 = BorderFactory.createEmptyBorder(280, 10, 280, 10);
		numPad.setBorder(padding2);
		// Add numbers 1-9 to the number pad 
		int num = 1;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				// Make new numpad button to add to frame
				NumPadButton n = new NumPadButton(num);
				numPad.add(n);
				// Add actionListener to track mouse clicks.
				n.addActionListener(new ClickHandler(this)); // "this" is the current game
				num++;
			}
		}
		
		this.add(bigGrid);
		// Add numPad to frame, put on the right 
		this.add(numPad, BorderLayout.EAST);
		// Have window match layout of JPanels 
		this.pack();	
	}	

	// Checks if game has been won
	// Called by ClickHandler class after each click
	public boolean checkWin() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!buttons[i][j].isCurBool()) { // Only checks values that user can change
					if (buttons[i][j].getCurButtonValue() != NUMS[i][j]) {
						// Return false and stops check if at least one value is incorrect
						return false; 
					}
				} 
			}
		}
		// If method makes it here, all values are correct
		return true;
	}

	// Displays new frame to tell player they've won
	// Called by ClickHandler class if checkWin() returns true
	public void displayWon() {
		JFrame winFrame = new JFrame("Sudoku");
		winFrame.setSize(900,60);
		winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel winPanel = new JPanel();
		JLabel winLabel = new JLabel("Winnah winnah Suduku Dinnah! Congratulations on beating this epic game of Sudoku!");
		winPanel.add(winLabel);
		winFrame.add(winPanel);
		winFrame.setVisible(true);
		this.pack();
	}
	
	// Gets the current number selected on the grid
	public int getCurNum() {
		return curNum;
	}
	
	// Set the current number selected on the grid
	public void setCurNum(int num) {
		curNum = num;
	}

	// Make new instance of the game
	public static void main(String[] args) {
		SudokuGUI game = new SudokuGUI();
	}

}
