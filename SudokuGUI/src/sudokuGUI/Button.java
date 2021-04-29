package sudokuGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {
	private int realNum;  // original, correct value displayed
	private boolean isDisplayed = false;
	private int curDisplayed;  // current value displayed by user 
							   // (may not be correct)
	
	public Button(int num, boolean display) {
		realNum = num;
		isDisplayed = display;

		// Only displays numbers on buttons that are set to true
		if (isDisplayed) {
			this.setText(Integer.toString(realNum));
			this.setFont(new Font(Font.SERIF, Font.BOLD, 50));
			this.setBackground(Color.LIGHT_GRAY);
		} else {
			this.setPreferredSize(new Dimension(90,90));	
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	// Change the number displayed on the button
	public void setCurDisplayed(int num, Button b) {
		curDisplayed = num;
		// Do not change button if boolean value is true. 
		// A true value means the button is part of the 
		// original grid and should not be changeable.
		if (!isDisplayed) {
			b.setText(Integer.toString(curDisplayed));
			b.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		}
	}
	
	// Gets the current value stored showed on the button 
	public int getCurButtonValue() {
		return curDisplayed;
	}
	// Returns the current boolean value of the button
	// (If it was originally displayed or not
	public boolean isCurBool() {
		return isDisplayed;
	}
}
