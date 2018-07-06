package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Graphics class
 */
public class ChessGraphics  {
	public JFrame window;
	public JPanel panel;
	public JToggleButton[][] buttonBoard;
	public int firstX = -1, firstY = -1, secondX = -1, secondY = -1;

	public ChessGraphics(Board gameBoard) {
		// JFrame - whole box (GUI container)
		window = new JFrame();
		window.setSize(800, 700);
		window.setVisible(true);
		panel = new JPanel();
		// each button is a square on the chess board
		buttonBoard = new JToggleButton[8][8];
		update(gameBoard);
	}
	
	/**
	 * Update the chess board as each player makes a move
	 * @param gameBoard - an instance of the board
	 */
	public void update(Board gameBoard) {
		window.remove(panel); // remove the old panel
		panel = new JPanel();
		
		// loops used to alternate between colors for the chess board
		for (int row = 0; row < buttonBoard.length; row++) {
			for (int col = 0; col < buttonBoard[row].length; col++) {
				
				final Integer innerRow = Integer.valueOf(row);
				final Integer innerCol = Integer.valueOf(col);
				
				buttonBoard[row][col] = new JToggleButton("0");
				buttonBoard[row][col].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        JToggleButton btn =  (JToggleButton) e.getSource();
				        btn.setText(btn.isSelected() ? "1" : "0");
				        if (firstX == -1) {
				        		firstX = innerRow;
				        		firstY = innerCol;
				        } else if (secondX == -1) {
				        		secondX = innerRow;
				        		secondY = innerCol;
				        }
//				     	btn.setSelected(false);
//				     	btn.setText(btn.isSelected() ? "1" : "0");
				     	//btn.doClick();
				     	
				    }
				});
//				buttonBoard[row][col].addActionListener(null);
			}
		}
		drawBoard(gameBoard);
	}
	
	
	public void drawBoard(Board gameBoard) {
		boolean offset = false;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int colorCount = offset ? (row * 8 + col) : (row * 8 + col + 1);
				if (colorCount % 2 == 0) {
					buttonBoard[row][col].setBackground(Color.WHITE);
				} else {
					buttonBoard[row][col].setBackground(Color.GRAY);
				}

				GamePiece p = gameBoard.getPiece(row, col);
				if (p == null) {
					buttonBoard[row][col].setIcon(new ImageIcon("images/transparent.png"));
				} else {
					if (p.getColor()) { // black piece
						if (p instanceof Pawn) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BP.png"));
						} else if (p instanceof Rook) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BR.png"));
						} else if (p instanceof Knight) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BKn.png"));
						} else if (p instanceof Bishop) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BB.png"));
						} else if (p instanceof Queen) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BQ.png"));
						} else if (p instanceof King) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/BK.png"));
						}
					} else { // white piece
						if (p instanceof Pawn) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WP.png"));
						} else if (p instanceof Rook) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WR.png"));
						} else if (p instanceof Knight) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WKn.png"));
						} else if (p instanceof Bishop) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WB.png"));
						} else if (p instanceof Queen) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WQ.png"));
						} else if (p instanceof King) {
							buttonBoard[row][col].setIcon(new ImageIcon("images/WK.png"));
						}
					}
				}
				buttonBoard[row][col].setBorderPainted(false);
				buttonBoard[row][col].setOpaque(true);
				buttonBoard[row][col].setVisible(true);
				panel.add(buttonBoard[row][col]);
				panel.setVisible(false);
			}
			// for alternation of board colors row-by-row
			offset = !offset;
		}
		window.add(panel);
		panel.setVisible(true);
		window.setVisible(true);
	}

}
