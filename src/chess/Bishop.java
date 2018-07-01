package chess;

public class Bishop extends GamePiece {
    /**
     * Constructor for a new Bishop
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public Bishop(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "Bishop";
    }

    /**
     * Checks if there is a clear path for the Bishop to move given an (row, col) pairing
     * @param row - row location of new position
     * @param row - row location of new position
     * @param board - the board we are playing on
     * @return if bishop can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
        if (currentX == row && currentY == col) {
            // makes sure it is not the same location
            return false;
        }
        if (row < 0 || row > 7 || row < 0 || row > 7) {
            return false;
        }
        if (Math.abs(row - currentX) != Math.abs(col - currentY)) { 
        		return false;
        }
        // check the 4 diagonals
        // it is in the bishop's path (potentially)
        if ((row - currentX) > 0 &&  (col - currentY) > 0) { // positive x, positive y
            for (int i = currentX + 1, j = currentY + 1; i < row; i++, j++) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else if ((row - currentX) > 0 && (col - currentY) < 0) { // positive x, negative y
            for (int i = currentX + 1, j = currentY - 1; i < row; i++, j--) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else if ((row - currentX) < 0 && (col - currentY) < 0) { // negative x, negative y
            for (int i = currentX - 1, j = currentY - 1; i > row; i--, j--) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else { // negative x, positive y
            for (int i = currentX - 1, j = currentY + 1; i > row; i--, j++) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        }
       
        // cannot attack your own piece
        if (board.getPiece(row, col) == null) {
        		return true;
        }
        return this.color != board.getPiece(row, col).getColor();
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
            return true;
        }
        return false;
    }
}
