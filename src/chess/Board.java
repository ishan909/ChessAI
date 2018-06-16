// This class will contain what is at each location of the board

public class Board {

    // grid holding the cells on the board
    Cell[][] matrix = new Cell[8][8];

    private boolean red_king;
    private boolean black_king;


    // key is the piece
    // values are like the alive, etc.

    




    /**
     * Board constructor
     */
    public Board() {
        initializeBoard();
        red_king = true;
        black_king = true;
    }

    /**
     * Board initializer to setup a fresh board
     */
    public void initializeBoard() {
        // initialization of Chess Board -- (w/ Colors -- set as different colors than red/black)
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                int colorChoice = r * 8 + c;
                if (colorChoice % 2 == 0) {
                    matrix[r][c] = new Cell(r, c, false, new Piece(r, c), false);
                } else {
                    matrix[r][c] = new Cell(r, c, false, new Piece(r, c), true);
                }

                // states that there will be at peice at r,c
                if (r < 2 || r > 5) {
                    matrix[r][c].setContainsPiece = true;
                }
            }
        }

        // insert the pieces
        // all of the pawns
        for(int c = 0; c < 8; c++){
            matrix[1][c].setPiece(new Pawn(1,c,true));  // top - black
        }
        for(int c = 0; c < 8; c++){
            matrix[6][c].setPiece(new Pawn(6,c,false)); // bottom - white
        }

        // all the rooks
        matrix[0][0].setPiece(new Rook(0,0,true)); // top - black
        matrix[0][7].setPiece(new Rook(0,7,true));
        matrix[7][0].setPiece(new Rook(7,0,false)); // bottom - white
        matrix[7][7].setPiece(new Rook(7,7,false));

        // knights
        matrix[1][0].setPiece(new Knight(1,0,true)); // top - black
        matrix[6][0].setPiece(new Knight(6,0,true));
        matrix[1][7].setPiece(new Knight(1,7,false)); // bottom - white
        matrix[6][7].setPiece(new Knight(6,7,false));

        // bishops
        matrix[2][0].setPiece(new Bishop(2,0,true)); // top - black
        matrix[5][0].setPiece(new Bishop(5,0,true));
        matrix[2][7].setPiece(new Bishop(2,7,false)); // bottom - white
        matrix[5][7].setPiece(new Knight(1,7,false));

        //Queens
        matrix[3][0].setPiece(new Queen(3,0,true)); // top - black
        matrix[3][7].setPiece(new Queen(3,7,false)); // bottom - white

        // Kings
        matrix[4][0].setPiece(new King(4,0,true)); // top - black
        matrix[4][7].setPiece(new King(4,7,false)); // bottom - white
    }

    public boolean movePiece(int r1, int c1, int r2, int c2) {
        // checks if the piece at r1,c1 can move to r2,c2
        // if it can move to that spot, move it and update the board
    }

    public boolean check(boolean player) {
        // know where the player's king is
        // loop through all of the opposing player's pieces
        // check the opposing player's pieces to see if they can attack the King
        // the "canAttack" method for each piece must accept a r,c for where the king is
    }

    public boolean checkmate(boolean player) {
        // loop through all of the possible moves for the player's king and see if it will
        // still be in check in all of those moves
    }
    public Cell getCell(int x, int y){
      return matrix[x][y];
    }
    /**
     * Prints a board to the console for testing purposes
     */
    public static void printBoard() {
        System.out.println("-A -B -C -D -E -F -G -H -");
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
}
