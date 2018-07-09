package chess;

import java.util.Scanner;

public class Game {
	// keeps count of how many successful moves have been made
    private int moveCount;
    private Board board;
    private ChessGraphics gui;
    
    /**
     * Constructor for a new Game
     */
    public Game() {
        board = new Board();
        gui = new ChessGraphics(board);
        playGame(board, gui);
    }
    

	
	public boolean validSelection(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return false;
		}
		if (board.getPiece(row, col) != null) {
  			if (moveCount % 2 == 0) {
  				return board.getPiece(row, col).getColor();
  			} else {
  				return !board.getPiece(row, col).getColor();
  			}
  		}
  		return false;
  	}
	
	
    
    /**
     * User interface for playing game
     * @param board - an instance of the chess board
     * @param gui - an instance of the Chess Graphics
     */
  	public void playGame(Board board, ChessGraphics gui) {
        Scanner input = new Scanner(System.in);
        moveCount = 0; // Initialize move counter
        gui.drawBoard();

        while (true) { // true for the black player
        	board.printBoard();
            if (!board.checkmate(true)) {
                System.out.println("It is black's turn.");
                if (board.check(true)) { // true for the black player
                    System.out.println("Black, you are in check");
                }
                
                int current_col = -1, current_row = -1, new_col = -1, new_row = -1;
                while (true) {
	                System.out.print("BLACK "); // to allow piece to move
//	                System.out.println("BLACK'S TURN");
	                System.out.println("cr " + current_row + " cc " + current_col  + " nr " + new_row  + " nc " + new_col + " gfx " + gui.firstX + " gfy " + gui.firstY + " gsx " + gui.secondX + " gsy " + gui.secondY);
	                while (!validSelection(gui.firstX, gui.firstY)) {
	                	System.out.print(""); // to allow piece to move
	                	if (gui.firstX != -1 && gui.firstY != -1) {
		                	gui.buttonBoard[gui.firstX][gui.firstY].setSelected(false);
	                	}
	                	gui.firstX = -1;
	                    gui.firstY = -1;
	                	current_row = gui.firstX;
	                    current_col = gui.firstY;
//	                    System.out.println("BLACK'S TURN");
	                    System.out.println("BLACK INSIDE cr " + current_row + " cc " + current_col  + " nr " + new_row  + " nc " + new_col + " gfx " + gui.firstX + " gfy " + gui.firstY + " gsx " + gui.secondX + " gsy " + gui.secondY);
	                }
	                current_row = gui.firstX;
                    current_col = gui.firstY;
                    new_row = gui.secondX;
                    new_col = gui.secondY;
                    if (board.movePiece(current_row, current_col, new_row, new_col, moveCount) && !board.check(true)) {
                    	gui.buttonBoard[current_row][current_col].setSelected(false);
                    	gui.buttonBoard[new_row][new_col].setSelected(false);
                    	break;
                    }
                    new_row = -1;
                    gui.secondX = -1;
                    new_col = -1;
                    gui.secondY = -1;
                }
                if (new_row == 7 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
            		board.setPawnToPiece(new_row, new_col, input);
                }
                gui.drawBoard();
                gui.firstX = -1;
                gui.firstY = -1;
                gui.secondX = -1;
                gui.secondY = -1;
                System.out.println("Black, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
            board.printBoard();
            if (!board.checkmate(false)) { // false for the white player
                System.out.println("It is white's turn.");
                if (board.check(false)) { // false for the white player
                    System.out.println("White, you are in check");
                }

                int current_col = -1, current_row = -1, new_col = -1, new_row = -1;
                while (true) {
	                System.out.print("WHITE"); // to allow piece to move
//	                System.out.println("WHITE'S TURN");
	                System.out.println("cr " + current_row + " cc " + current_col  + " nr " + new_row  + " nc " + new_col + " gfx " + gui.firstX + " gfy " + gui.firstY + " gsx " + gui.secondX + " gsy " + gui.secondY);
	                while (!validSelection(gui.firstX, gui.firstY)) {
	                	System.out.print(""); // to allow piece to move
	                	if (gui.firstX != -1 && gui.firstY != -1) {
		                	gui.buttonBoard[gui.firstX][gui.firstY].setSelected(false);
	                	}
	                	gui.firstX = -1;
	                    gui.firstY = -1;
	                	current_row = gui.firstX;
	                    current_col = gui.firstY;
//	                    System.out.println("WHITE'S TURN");
	                    System.out.println("WHITE INSIDE cr " + current_row + " cc " + current_col  + " nr " + new_row  + " nc " + new_col + " gfx " + gui.firstX + " gfy " + gui.firstY + " gsx " + gui.secondX + " gsy " + gui.secondY);
	                }
                    current_row = gui.firstX;
                    current_col = gui.firstY;
                    new_row = gui.secondX;
                    new_col = gui.secondY;
                    if (board.movePiece(current_row, current_col, new_row, new_col, moveCount) && !board.check(true)) {
                    	gui.buttonBoard[current_row][current_col].setSelected(false);
                    	gui.buttonBoard[new_row][new_col].setSelected(false);
                    	break;
                    }
                    new_row = -1;
                    gui.secondX = -1;
                    new_col = -1;
                    gui.secondY = -1;
                }
                if (new_row == 0 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
        			board.setPawnToPiece(new_row, new_col, input);
                }
                gui.drawBoard();
                gui.firstX = -1;
                gui.firstY = -1;
                gui.secondX = -1;
                gui.secondY = -1;
                System.out.println("White, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
        }

        System.out.println("Game Over! " + ((moveCount + 1) % 2 == 0 ? "Black" : "White") + " won!");
        input.close();
    }
}