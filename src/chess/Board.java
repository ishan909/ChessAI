package chess;

// This class will contain what is at each location of the board

import java.util.*;


public class Board {

    // grid holding the cells on the board
    // Cell[][] matrix = new Cell[8][8];
    public GamePiece[][] matrix = new GamePiece[8][8];

    // TODO: may not need blackInCheck and whiteInCheck
    public boolean blackInCheck;
    public boolean whiteInCheck;

    public int[] blackKingLocation = new int[2];
    public int[] whiteKingLocation = new int[2];

    /**
     * Board constructor
     */
    public Board() {
        initializeNewBoard();
        blackInCheck = false;
        whiteInCheck = false;
    }

    /**
     * Board initializer to setup a fresh board
     */
    public void initializeNewBoard() {
        // initialization of Chess Board -- (w/ Colors -- set as different colors than red/black)
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                // states that there will be at piece at r,c
                if (r > 1 && r < 6) {
                    matrix[r][c] = null;
                }
            }
        }

        // insert the pieces
        // all of the pawns
        for (int c = 0; c < 8; c++) {
            setPiece(new Pawn(1, c, true), 1, c);  // top - black
        }
        for (int c = 0; c < 8; c++) {
            setPiece(new Pawn(6, c, false), 6, c); // bottom - white
        }

        // all the rooks
        setPiece(new Rook(0, 0, true), 0, 0); // top - black
        setPiece(new Rook(0, 7, true), 0, 7);
        setPiece(new Rook(7, 0, false), 7, 0); // bottom - white
        setPiece(new Rook(7, 7, false), 7, 7);

        // knights
        setPiece(new Knight(0, 1, true), 0, 1); // top - black
        setPiece(new Knight(0, 6, true), 0, 6);
        setPiece(new Knight(7, 1, false), 7 ,1); // bottom - white
        setPiece(new Knight(7, 6, false), 7, 6);

        // bishops
        setPiece(new Bishop(0, 2, true), 0, 2); // top - black
        setPiece(new Bishop(0, 5, true), 0, 5);
        setPiece(new Bishop(7, 2, false), 7, 2); // bottom - white
        setPiece(new Bishop(7, 5, false), 7, 5);

        //Queens
        setPiece(new Queen(0, 3, true), 0, 3); // top - black
        setPiece(new Queen(7, 3, false), 7, 3); // bottom - white

        // Kings
        setPiece(new King(0, 4, true), 0, 4); // top - black
        // fill
        blackKingLocation[0] = 0;
        blackKingLocation[1] = 4;

        setPiece(new King(7, 4, false), 7, 4); // bottom - white
        // fill
        whiteKingLocation[0] = 7;
        whiteKingLocation[1] = 4;
    }

    public boolean movePiece(int r1, int c1, int r2, int c2, int turn) {
        // checks if the piece at r1,c1 can move to r2,c2
        // if it can move to that spot, move it and update the board
        GamePiece tmp = getPiece(r1, c1);

        // if it's there or not

        // do we automatically set a square to null when there's no piece on it
        if (tmp == null) {
            return false;
        }
        // true is black?
        if (tmp.getColor()) {
            // even is white
            if (turn % 2 == 0) {
                return false;
            }
        } else {
            // odd is black
            if (turn % 2 == 1) {
                return false;
            }
        }

        if (tmp.canMove(r2, c2, this)) {
            this.matrix[r1][c1] = null;
            tmp.move(r2, c2, this);
            return true;
        }
        return false;

        // null square, if you can reach it place
        // if it's your own piece then you can't in terms of color
        // if it's an opponent piece and is not a king, then you can take it
    }

    public boolean check(boolean player) {
        // know where the player's king is
        // loop through all of the opposing player's pieces
        // check the opposing player's pieces to see if they can attack the King
        // the "canAttack" method for each piece must accept a r,c for where the king is
        if (player) {
            blackInCheck = this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheck(this);
            return blackInCheck;
        } else {
            whiteInCheck = this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheck(this);
            return whiteInCheck;
        }
    }

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
     * @param x - the x location that we are accessing
     * @param y - the y location that we are accessing
     * @return the piece at the x and y location
     */
    public GamePiece getPiece(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return matrix[x][y];
    }

    /**
     * Sets a piece to a new location
     * @param piece - the piece we are moving
     * @param x - the x location that we are accessing
     * @param y - the y location that we are accessing
     * @return if the move was successful
     */
    public boolean setPiece(GamePiece piece, int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        matrix[x][y] = piece;
        return true;
    }

    /**
     * Prints a board to the console for testing purposes
     */
    public void printBoard() {
        System.out.println(" - 0- 1- 2- 3- 4- 5- 6- 7-");
        System.out.println("---------------------------");
        for (int r = 0; r < 8; r++) {
            System.out.print("" + r + "|");
            for (int c = 0; c < 8; c++) {
                if (matrix[r][c] == null) {
                    System.out.print("  ");
                } else {
                    GamePiece p = matrix[r][c];
                    if (p == null) {
                			System.out.print("  |");
                			continue;
                    }
                    if (p.getColor()) {
                        System.out.print("B");
                    } else {
                        System.out.print("W");
                    }
                    if (p instanceof Rook/*p.getType().equals("Rook")*/) {
                        System.out.print("R");
                    } else if (p.getType().equals("Knight")) {
                        System.out.print("k");
                    } else if (p.getType().equals("Bishop")) {
                        System.out.print("B");
                    } else if (p.getType().equals("King")) {
                        System.out.print("K");
                    } else if (p.getType().equals("Queen")) {
                        System.out.print("Q");
                    } else if (p.getType().equals("Pawn")) {
                        System.out.print("P");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    /**
     * If a pawn reaches the end, and has to be changed to a different piece
     * which is not a pawn or a king
     * @param x - the x location of the pawn
     * @param y - the y location of the pawn
     */
    public void setPawnToPiece(int r, int c) {
    		if (matrix[r][c] != null && matrix[r][c] instanceof Pawn) {
    			Scanner in = new Scanner(System.in);
    	        System.out.print("Please enter what piece you would like to change the pawn to:  ");
    	        // throw exceptions or make it ask again if the user types in a pawn again and no new King
    	        String piece = in.next();
    	        while (!piece.equals("Queen") || !piece.equals("Bishop") || !piece.equals("Knight") || !piece.equals("Rook")) {
    	            System.out.print("Please enter what piece would you like to change the pawn to:  ");
    	            piece = in.next();
    	        }
    	        // queen
    	        if ("Queen".equals(piece)) {
    	            matrix[r][c] = new Queen(r, c, matrix[r][c].getColor());
    	        }
    	        // bishop
    	        if ("Bishop".equals(piece)) {
    	            matrix[r][c] = new Bishop(r, c, matrix[r][c].getColor());
    	        }
    	        // knight
    	        if ("Knight".equals(piece)) {
    	            matrix[r][c] = new Knight(r, c, matrix[r][c].getColor());
    	        }
    	        // rook
    	        if ("Rook".equals(piece)) {
    	            matrix[r][c] = new Rook(r, c, matrix[r][c].getColor());
    	        }
    	        in.close();
    		}
    }

}
