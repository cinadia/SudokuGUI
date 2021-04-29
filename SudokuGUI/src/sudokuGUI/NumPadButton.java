package sudokuGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class NumPadButton extends JButton{
	private int curNum; // Number on the current numpad button
	
	// Constructor takes in number and displays it on button
	public NumPadButton(int num) {
		curNum = num;
	
		this.setPreferredSize(new Dimension(100,100));	
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setText(Integer.toString(num));
		this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
	}
	
	// Get the number on the specific numpad
	public int getCurNumPad() {
		return curNum;
	}
}
