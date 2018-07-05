package chess;

import java.util.*; // get scanner

public class Game {
    private int moveCount;

    public Game() {
		ChessGraphics gui = new ChessGraphics();
        Board board = new Board();
        playGame(board);
    }

	public void playGame(Board board) {
        Scanner input = new Scanner(System.in);
        moveCount = 0; // Initialize move counter

        while (true) { // true for the black player
        	board.printBoard();
            if (!board.checkmate(true)) {
                System.out.println("It is black's turn.");
                if (board.check(true)) { // true for the black player
                    System.out.println("Black, you are in check");
                }

                // TODO: make sure input is valid -- must make sure there is a piece there,
                //      that piece is that player's piece, and the string is formatted correctly
                boolean first_move = true;

                // TODO: figure out how to work with bad input (throw exceptions later)
                // TODO: we need to re-add that "|| board.check(true)" condition, but we also need to update the check after each input
                // Naming has been updated to all be col/row
                // TODO: switch rows and cols to be in the right order
                int current_col, current_row, new_col, new_row;
                do {
	                if (!first_move) {
	                		System.out.println("Invalid Move.");
	                }
                    System.out.print("Black, which piece would you like to move?\nEnter row number: ");
                    current_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    current_col = Integer.parseInt(input.nextLine());
                    System.out.print("Black, where would you like to move this piece?\nEnter row number: ");
                    new_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    new_col = Integer.parseInt(input.nextLine());
                    first_move = false;
                } while (!board.movePiece(current_row, current_col, new_row, new_col, moveCount) || board.check(true));
                if (new_row == 7 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
            			setPawnToPiece(new_row, new_col, board, input);
                }
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
                
                // Naming has been updated to all be col/row
                boolean first_move = true;
                int current_col, current_row, new_col, new_row;
                do {
	                if (!first_move) {
	                		System.out.println("Invalid Move.");
	                }
	                System.out.print("White, which piece would you like to move?\nEnter row number: ");
                    current_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    current_col = Integer.parseInt(input.nextLine());
                    System.out.print("White, where would you like to move this piece?\nEnter row number: ");
                    new_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    new_col = Integer.parseInt(input.nextLine());
                    first_move = false;
                } while (!board.movePiece(current_row, current_col, new_row, new_col, moveCount) || board.check(false));
                if (new_row == 0 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
        				setPawnToPiece(new_row, new_col, board, input);
                }
                System.out.println("White, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
        }
        System.out.println("Game Over! Player " + (((moveCount + 1) % 2) + 1) + " won!");
        input.close();
    }
	
	/**
     * If a pawn reaches the end, and has to be changed to a different piece
     * which is not a pawn or a king
     * @param x - the x location of the pawn
     * @param y - the y location of the pawn
     * @param board - the board we are playing on
     * @param in - Scanner for user input
     */
    public void setPawnToPiece(int r, int c, Board board, Scanner in) {
    		if (board.matrix[r][c] != null && board.matrix[r][c] instanceof Pawn) {
    	        String piece;
    	        do {
    	            System.out.print("Please enter what piece would you like to change the pawn to: ");
    	            piece = in.nextLine();
    	        } while (!piece.equals("Queen") && !piece.equals("Bishop") && !piece.equals("Knight") && !piece.equals("Rook"));
    	        boolean color = board.matrix[r][c].getColor();
    	        board.matrix[r][c] = null;
    	        if ("Queen".equals(piece)) {
    	            board.matrix[r][c] = new Queen(r, c, color);
    	        } else if ("Bishop".equals(piece)) {
    	            board.matrix[r][c] = new Bishop(r, c, color);
    	        } else if ("Knight".equals(piece)) {
    	            board.matrix[r][c] = new Knight(r, c, color);
    	        } else if ("Rook".equals(piece)) {
    	            board.matrix[r][c] = new Rook(r, c, color);
    	        }
    		}
    }
	
	
}
