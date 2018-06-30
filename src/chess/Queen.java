package chess;

public class Queen extends GamePiece {
    /**
     * Constructor for a new Queen
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public Queen(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "Queen";
    }

    /**
     * Checks if there is a clear path for the queen to move given an (row, col) pairing
     * @param row - row location of new position
     * @param col - col location of new position
     * @param board - the board we are playing on
     * @return if Queen can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
    	if (currentX == row && currentY == col) {
            return false;
        }
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        // same x, but different y
        if ((currentX != row && currentY != col) && (Math.abs(row - currentX) != Math.abs(col - currentY))) {
        		return false;
        }
        if (currentX == row) {
            // positive or negative y direction
            if (col > currentY) {
                // see if all the spots in between are empty
                // pass in Board
                for (int i = currentY + 1; i < col; i++) {
                    if (board.getPiece(currentX, i) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentY - 1; i > col; i--) {
                    if (board.getPiece(currentX, i) != null) {
                        return false;
                    }
                }
            }
        } else if (currentY == col) { // same y, but different x
            // positive or negative x direction
            if (row > currentX) {
                for (int i = currentX + 1; i < row; i++) {
                    if (board.getPiece(i, col) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentX - 1; i > row; i--) {
                    if (board.getPiece(i, col) != null) {
                        return false;
                    }
                }
            }
        } else if ((row - currentX) > 0 &&  (col - currentY) > 0) { // positive x, positive y
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
            return true;
        }
        return false;
    }
}
