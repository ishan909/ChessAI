package chess;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import java.awt.*;
//import javax.swing.*;


public class ChessGraphics  {
	// change to a constructor later
	
	// public static BufferedImage outline;
	
//	public static void main(String[] args) throws IOException {
	public ChessGraphics() {
		
		// JFrame - whole box (GUI container) 
		JFrame window = new JFrame();
		window.setSize(700,700);
		window.setVisible(true);
		JPanel panel = new JPanel();
		
		JButton[][] board = new JButton[8][8];
		boolean offset = false;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				int colorCount;
				colorCount = offset ? (row * 8 + col) : (row * 8 + col + 1); 
				if (colorCount % 2 == 0) {
					board[row][col] = new JButton();
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setIcon(new ImageIcon("images/transparent.png"));
				}
				else {
					board[row][col] = new JButton();
					board[row][col].setBackground(Color.GRAY);
					board[row][col].setIcon(new ImageIcon("images/transparent.png"));
				}
				board[row][col].setBorderPainted(false);
				board[row][col].setOpaque(true);
				board[row][col].setVisible(true);
				panel.add(board[row][col]);
				
				
			}
			offset = !offset;
		}
		window.add(panel);
		panel.setVisible(true);
		window.setVisible(true);
	}
	
	
}
// Chess Board - container (Panel)
	// make the board our background

