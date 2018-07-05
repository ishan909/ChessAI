package chess;

// This class will contain what is at each location of the board

public class Board {

    // grid holding the cells on the board
    private GamePiece[][] matrix = new GamePiece[8][8];

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
        // initialization of Chess Board -- (w/ Colors -- set as different colors than red/black)
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // states that there will be at piece at r, c
                if (row > 1 && row < 6) {
                    matrix[row][col] = null;
                }
            }
        }

        // insert the pieces
        // all of the pawns
        for (int col = 0; col < 8; col++) {
            setPiece(new Pawn(1, col, true), 1, col);  // top - black
        }
        for (int col = 0; col < 8; col++) {
            setPiece(new Pawn(6, col, false), 6, col); // bottom - white
        }

        // all the rooks
        setPiece(new Rook(0, 0, true), 0, 0); // top - black
        setPiece(new Rook(0, 7, true), 0, 7);
        setPiece(new Rook(7, 0, false), 7, 0); // bottom - white
        setPiece(new Rook(7, 7, false), 7, 7);

        // knights
        setPiece(new Knight(0, 1, true), 0, 1); // top - black
        setPiece(new Knight(0, 6, true), 0, 6);
        setPiece(new Knight(7, 1, false), 7, 1); // bottom - white
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
        blackKingLocation[0] = 0;
        blackKingLocation[1] = 4;

        setPiece(new King(7, 4, false), 7, 4); // bottom - white
        whiteKingLocation[0] = 7;
        whiteKingLocation[1] = 4;
    }

    public boolean movePiece(int current_row, int current_col, int new_row, int new_col, int turn) {
        // checks if the piece at r1,c1 can move to r2,c2
        // if it can move to that spot, move it and update the board
        GamePiece temp = getPiece(current_row, current_col);

        // if it's there or not
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

    public boolean check(boolean player) {
        // know where the player's king is
        // loop through all of the opposing player's pieces
        // check the opposing player's pieces to see if they can attack the King
        // the "canAttack" method for each piece must accept a row, col for where the king is
        if (player) {
            return this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheck(this);
        } else {
            return this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheck(this);
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
