package chess;

import java.util.Scanner;

// This class will contain what is at each location of the board

public class Board {

    // grid holding the cells on the board
    private GamePiece[][] matrix = new GamePiece[8][8];

    // Array-implementation of the location of black/white king
    public int[] blackKingLocation = new int[2];
    public int[] whiteKingLocation = new int[2];

    /**
     * Board constructor
     */
    public Board() {
        initializeNewBoard();

    }

    /**
     * Board initializer to setup a fresh board
     */
    public void initializeNewBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // intialize empty spaces in board at start of game
                if (row > 1 && row < 6) {
                    matrix[row][col] = null;
                }
            }
        }


        // Insert all the pawns
        for (int col = 0; col < 8; col++) {
            setPiece(new Pawn(1, col, true), 1, col);  // top - black
        }
        for (int col = 0; col < 8; col++) {
            setPiece(new Pawn(6, col, false), 6, col); // bottom - white
        }

        // Initialize Rooks
        setPiece(new Rook(0, 0, true), 0, 0); // top - black
        setPiece(new Rook(0, 7, true), 0, 7);
        setPiece(new Rook(7, 0, false), 7, 0); // bottom - white
        setPiece(new Rook(7, 7, false), 7, 7);

        // Initialize Knights
        setPiece(new Knight(0, 1, true), 0, 1); // top - black
        setPiece(new Knight(0, 6, true), 0, 6);
        setPiece(new Knight(7, 1, false), 7, 1); // bottom - white
        setPiece(new Knight(7, 6, false), 7, 6);

        // Initialize Bishops
        setPiece(new Bishop(0, 2, true), 0, 2); // top - black
        setPiece(new Bishop(0, 5, true), 0, 5);
        setPiece(new Bishop(7, 2, false), 7, 2); // bottom - white
        setPiece(new Bishop(7, 5, false), 7, 5);

        // Initialize Queen
        setPiece(new Queen(0, 3, true), 0, 3); // top - black
        setPiece(new Queen(7, 3, false), 7, 3); // bottom - white

        // Initialize Kings (using array-implementation for x,y of king)
        setPiece(new King(0, 4, true), 0, 4); // top - black
        blackKingLocation[0] = 0;
        blackKingLocation[1] = 4;

        setPiece(new King(7, 4, false), 7, 4); // bottom - white
        whiteKingLocation[0] = 7;
        whiteKingLocation[1] = 4;
    }
    /**
     * Checks if an arbitrary piece can move to a new location and then updates the board
     * @param current_row - current row location of a piece
     * @param current_col - current column location of a piece
     * @param new_row - the new row location for a piece
     * @param new_col - the new column location for a piece
     * @param turn - current turn (white/black)
     */
    public boolean movePiece(int current_row, int current_col, int new_row, int new_col, int turn) {

        GamePiece temp = getPiece(current_row, current_col);

        // Null Check
        if (temp == null) {
            return false;
        }
        // true is black
        if (temp.getColor()) {
            // odd is white
            if (turn % 2 == 1) {
                return false;
            }
        } else {
            // even is black
            if (turn % 2 == 0) {
                return false;
            }
        }
        if (temp.canMove(new_row, new_col, this)) {
        	temp.move(new_row, new_col, this);
            this.matrix[current_row][current_col] = null;
            return true;
        }
        return false;
    }
    /**
     * Checks if a player is in check
     * @param player - white or black
     */
    public boolean check(boolean player) {
        if (player) {
            return this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheck(this);
        } else {
            return this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheck(this);
        }
    }
    /**
     * Checks if a player is in checkmate
     * @param player - white or black
     */
    public boolean checkmate(boolean player) {
        // loop through all of the possible moves for the player's king and see if it will
        // still be in check in all of those moves
        if (player) {
            return this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheckmate(this);
        } else {
            return this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheckmate(this);
        }
    }

    /**
     * Returns the piece at a given location
     * @param row - the x location that we are accessing
     * @param col - the y location that we are accessing
     * @return the piece at the x and y location
     */
    public GamePiece getPiece(int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return null;
        }
        return matrix[row][col];
    }

    /**
     * Sets a piece to a new location
     * @param piece - the piece we are moving
     * @param row - the row location that we are accessing
     * @param col - the col location that we are accessing
     * @return if the move was successful
     */
    public boolean setPiece(GamePiece piece, int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }
        matrix[row][col] = piece;
        return true;
    }

    /**
     * If a pawn reaches the end, and has to be changed to a different piece
     * which is not a pawn or a king
     * @param x - the x location of the pawn
     * @param y - the y location of the pawn
     * @param board - the board we are playing on
     * @param in - Scanner for user input
     */
    public void setPawnToPiece(int r, int c, Scanner in) {
		if (this.getPiece(r, c) != null && this.getPiece(r, c) instanceof Pawn) {
	        String piece;
	        do {
	            System.out.print("Please enter what piece would you like to change the pawn to: ");
	            piece = in.nextLine();
	        } while (!piece.equals("Queen") && !piece.equals("Bishop") && !piece.equals("Knight") && !piece.equals("Rook"));
	        boolean color = this.getPiece(r, c).getColor();
	        this.setPiece(null, r, c); // remove the old piece
	        if ("Queen".equals(piece)) {
	        	this.setPiece(new Queen(r, c, color), r, c);
	        } else if ("Bishop".equals(piece)) {
	        	this.setPiece(new Bishop(r, c, color), r, c);
	        } else if ("Knight".equals(piece)) {
	        	this.setPiece(new Knight(r, c, color), r, c);
	        } else if ("Rook".equals(piece)) {
	        	this.setPiece(new Rook(r, c, color), r, c);
	        }
	   	}
   }

    /**
     * Prints a board to the console for testing purposes
     */
    // TODO: switch rows and col and rename r/c to row/col
    public void printBoard() {
        System.out.println(" - 0- 1- 2- 3- 4- 5- 6- 7-");
        System.out.println("---------------------------");
        for (int row = 0; row < 8; row++) {
            System.out.print("" + row + "|");
            for (int col = 0; col < 8; col++) {
                if (matrix[row][col] == null) {
                    System.out.print("  ");
                } else {
                    GamePiece p = matrix[row][col];
                    if (p == null) {
                			System.out.print("  |");
                			continue;
                    }
                    if (p.getColor()) {
                        System.out.print("B");
                    } else {
                        System.out.print("W");
                    }
                    if (p instanceof Rook) {
                        System.out.print("R");
                    } else if (p instanceof Knight) {
                        System.out.print("k");
                    } else if (p instanceof Bishop) {
                        System.out.print("B");
                    } else if (p instanceof King) {
                        System.out.print("K");
                    } else if (p instanceof Queen) {
                        System.out.print("Q");
                    } else if (p instanceof Pawn) {
                        System.out.print("P");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
