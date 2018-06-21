// This class will contain what is at each location of the board

import java.util.*;


public class Board {

    // grid holding the cells on the board
    // Cell[][] matrix = new Cell[8][8];
    GamePiece[][] matrix = new GamePiece[8][8];

    // TODO: may not need blackInCheck and whiteInCheck
    public boolean blackInCheck;
    public boolean whiteInCheck;

    public static int[] blackKingLocation = new int[2];
    public static int[] whiteKingLocation = new int[2];

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
                // states that there will be at peice at r,c
                if (r > 1 && r < 6) {
                    matrix[r][c] = null;
                }
            }
        }

        // insert the pieces
        // all of the pawns
        for (int c = 0; c < 8; c++) {
            matrix[1][c].setPiece(new Pawn(1, c, true));  // top - black
        }
        for (int c = 0; c < 8; c++) {
            matrix[6][c].setPiece(new Pawn(6, c, false)); // bottom - white
        }

        // all the rooks
        matrix[0][0].setPiece(new Rook(0, 0, true)); // top - black
        matrix[0][7].setPiece(new Rook(0, 7, true));
        matrix[7][0].setPiece(new Rook(7, 0, false)); // bottom - white
        matrix[7][7].setPiece(new Rook(7, 7, false));

        // knights
        matrix[1][0].setPiece(new Knight(1, 0, true)); // top - black
        matrix[6][0].setPiece(new Knight(6, 0, true));
        matrix[1][7].setPiece(new Knight(1, 7, false)); // bottom - white
        matrix[6][7].setPiece(new Knight(6, 7, false));

        // bishops
        matrix[2][0].setPiece(new Bishop(2, 0, true)); // top - black
        matrix[5][0].setPiece(new Bishop(5, 0, true));
        matrix[2][7].setPiece(new Bishop(2, 7, false)); // bottom - white
        matrix[5][7].setPiece(new Knight(1, 7, false));

        //Queens
        matrix[3][0].setPiece(new Queen(3, 0, true)); // top - black
        matrix[3][7].setPiece(new Queen(3, 7, false)); // bottom - white

        // Kings
        matrix[4][0].setPiece(new King(4, 0, true)); // top - black
        // fill
        blackKingLocation[0] = 4;
        blackKingLocation[1] = 0;

        matrix[4][7].setPiece(new King(4, 7, false)); // bottom - white
        // fill
        whiteKingLocation[0] = 4;
        whiteKingLocation[1] = 7;
    }

    public boolean movePiece(int r1, int c1, int r2, int c2, int turn) {
        // checks if the piece at r1,c1 can move to r2,c2
        // if it can move to that spot, move it and update the board
        GamePiece tmp = board.getPiece(r1,c1);

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
            // tmp.move(r2,c2) make it happen
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
            blackInCheck = this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheck();
            return blackInCheck;
        } else {
            whiteInCheck = this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheck();
            return whiteInCheck;
        }
    }

    public boolean checkmate(boolean player) {
        // loop through all of the possible moves for the player's king and see if it will
        // still be in check in all of those moves
        if (player) {
            return this.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheckmate();
        } else {
            return this.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheckmate();
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
        return this.matrix[x][y];
    }

    /**
     * Sets a piece to a new location
     * @param piece - the piece we are moving
     * @param x - the x location that we are accessing
     * @param y - the y location that we are accessing
     * @return if the move was successful
     */
    public boolean setPiece(Piece piece, int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        this.matrix[x][y] = piece;
        return true;
    }

    /**
     * Prints a board to the console for testing purposes
     */
    public static void printBoard() {
        System.out.println("-11-12-13-14-15-16-17-18-");
        System.out.println("-------------------------");
        for (int r = 0; r < 8; r++) {
            System.out.print("" + (r + 1) + "|");
            for (int c = 0; c < 8; c++) {
                if (!matrix[r][c].containsPiece()) {
                    System.out.print("  ")
                } else {
                    Piece p = matrix[r][c].getPiece();
                    if (p.getColor()) {
                        System.out.print("B");
                    } else {
                        System.out.print("W");
                    }
                    if (p.getType().equals("Rook")) {
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
                System.out.print("|")
            }
        }
        System.out.println("-------------------------");
    }

    // if a pawn reaches the end, and has to be changed, cannot be pawn
    public void setPawnToPiece(int r, int c, Pawn p) {
      Scanner tmp = new Scanner(System.in);
      System.out.print("Please enter what piece you would like to change the pawn to:  ");
      // throw exceptions or make it ask again if the user types in a pawn again and no new King
      String piece = in.next();
      while(!piece.equals("Queen") || !piece.equals("Bishop") || !piece.equals("Knight") || !piece.equals("Rook")) {
        System.out.print("Please enter what piece would you like to change the pawn to:  ");
        piece = in.next();
      }
      
      // queen
      if("Queen".equals(piece)) {
        matrix[r][c].setPiece(new Queen(r, c, p.getColor()),r,c);
      }
      // bishop
      if("Bishop".equals(piece)) {
        matrix[r][c].setPiece(new Bishop(r, c, p.getColor()),r,c);
      }
      // knight
      if("Knight".equals(piece)) {
        matrix[r][c].setPiece(new Knight(r, c, p.getColor()),r,c);
      }
      // rook
      if("Rook".equals(piece)) {
        matrix[r][c].setPiece(new Rook(r,c,p.getColor()),r,c);
      }

    }

}
