package chess;

public class Knight extends GamePiece {
    /**
     * Constructor for a new Knight
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public Knight(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "Knight";
    }

    /**
     * Checks if there is a clear path for the knight to move given an (x,y) pairing
     * @param row - row location of new position
     * @param col - col location of new position
     * @param board - the board we are playing on
     * @return if knight can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board){
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }
        if (row == currentX && col == currentY) {
            return false;
        }

        // use the board
        if ((currentX - row) * (currentX - row) + (currentY - col) * (currentY - col) != 5) {
            return false;
        }

        if (board.getPiece(row, col) == null) {
            return true;
        }
        // cannot attack your own piece
        return this.color != board.getPiece(row, col).color;
    }

    /**
     * Moves a piece on the board to a new location
     * @param newRow - the new row location
     * @param newCol - the new col location
     * @param board - the board we are playing on
     * @return if the move was successful
     */
    public boolean move(int newRow, int newCol, Board board) {
        if (canMove(newRow, newCol, board)) {
            firstMove = false;
            board.setPiece(this, newRow, newCol);
            board.setPiece(null, currentX, currentY);
            currentX = newRow;
            currentY = newCol;
          
        }
        return false;
    }
}
