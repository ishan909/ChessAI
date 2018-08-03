package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Graphics class
 */
public class ChessGraphics  {
	public JFrame window;
	public JPanel panel;
	public JToggleButton[][] buttonBoard;
	public JButton close;
	public Board gameBoard;
	public JLabel turn;
	private Engine e;
	
//	public int firstX = -1, firstY = -1, secondX = -1, secondY = -1;
	public int[] movesArray = {-1, -1, -1, -1}; // {oldRow, oldCol, newRow, newCol}

	public ChessGraphics(Board gameBoard, Engine e) {
		this.e = e;
		window = new JFrame();
		window.setSize(700, 710);
		window.setVisible(true);
		panel = new JPanel();
		this.gameBoard = gameBoard;
		buttonBoard = new JToggleButton[8][8];
		initialize();
	}

	/**
	 * Update the chess board as each player makes a move
	 * @param gameBoard - an instance of the board
	 */
	public void initialize() {
		window.remove(panel);
		panel = new JPanel();
		for (int row = 0; row < buttonBoard.length; row++) {
			for (int col = 0; col < buttonBoard[row].length; col++) {

				final Integer innerRow = Integer.valueOf(row);
				final Integer innerCol = Integer.valueOf(col);

				buttonBoard[row][col] = new JToggleButton();
				buttonBoard[row][col].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
//				        JToggleButton btn =  (JToggleButton) e.getSource();
				        if (movesArray[0] == innerRow && movesArray[1] == innerCol) {
				        	movesArray[0] = -1;
				        	movesArray[1] = -1;
				        } else if (movesArray[0] == -1) {
				        	movesArray[0] = innerRow;
				        	movesArray[1] = innerCol;
				        } else if (movesArray[2] == -1) {
				        	movesArray[2] = innerRow;
				        	movesArray[3] = innerCol;
				        }
//				        btn.setSelected(false);
				    }
				});
				buttonBoard[row][col].addActionListener(null);
			}
		}
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Game has been terminated.");
		        System.exit(0);
		    }
		});
		setTurn(true);
		drawBoard();
	}

	public void drawBoard() {
		window.remove(panel);
		panel = new JPanel();
		boolean offset = false;
		// loops used to alternate between colors for the chess board
		for (int row = 0; row < buttonBoard.length; row++) {
			for (int col = 0; col < buttonBoard[row].length; col++) {
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
		panel.add(turn);
		panel.add(close);
		window.add(panel);
		panel.setVisible(true);
		window.setVisible(true);
	}
	
	void setTurn(boolean player) {
		if (!player) {
			turn = new JLabel("It is Black's turn. " + (e != null ? e.current_black_score(gameBoard) : " NULL BLACK"));
		} else {
			turn = new JLabel("It is White's turn. " + (e != null ? e.current_white_score(gameBoard) : " NULL WHITE"));
		}
	}
}
