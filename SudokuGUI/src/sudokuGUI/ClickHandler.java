package sudokuGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClickHandler implements ActionListener {
	
	// Initialize Sudoku game
	SudokuGUI game;
	
	// Constructor
	public ClickHandler(SudokuGUI game) {
		// Sets this.game to the current game being played
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		// If source is an instance of a Button 
		// object, cast to Button
		if (e.getSource() instanceof Button) {
			Button b = (Button)e.getSource();
			// if statement ensures user doesn't input 
			// zero before selecting a numpad number 
			// (since curNum = 0 upon initialization)
			if (game.getCurNum() != 0) {
				// Change the number displayed to the
				// number selected on the number pad
				b.setCurDisplayed(game.getCurNum(), b);
				// Checks if game is won after each Button click 
				if (game.checkWin() == true) {
					game.displayWon();
					
				}
			}
		}

		// If source is an instance of a NumPadButton 
		// object, cast to NumPadButton
		if (e.getSource() instanceof NumPadButton) {
			NumPadButton n = (NumPadButton)e.getSource();
			// Set the current number to the number
			// on the number pad
			game.setCurNum(n.getCurNumPad());
		}	
	} 

}
