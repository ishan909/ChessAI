// This class will contain what is at each location of the board

public class Board {

    // grid holding the cells on the board
    Cell[][] matrix = new Cell[8][8];

    /**
     * Board constructor
     */
    public Board() {
        initializeBoard();
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
}
