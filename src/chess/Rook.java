package chess;

public class Rook extends GamePiece {
    /**
     * Constructor for a new Rook
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public Rook(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "Rook";
    }

    /**
     * Checks if the rook can attack a piece
     * @param row - row location of new position
     * @param col - col location of new position
     * @param board - the board we are playing on
     * @return if rook can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
        if (super.getRow() == row && super.getCol() == col) {
            return false;
        }
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        // same x, but different y
        if (super.getRow() != row && super.getCol() != col) {
        	return false;
        }
        if (super.getRow() == row) {
            // positive or negative y direction
            if (col > super.getCol()) {
                // see if all the spots in between are empty
                for (int i = super.getCol() + 1; i < col; i++) {
                    if (board.getPiece(row, i) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = super.getCol() - 1; i > col; i--) {
                    if (board.getPiece(row, i) != null) {
                        return false;
                    }
                }
            }
        } else if (super.getCol() == col) { // same y, but different x
            // positive or negative x direction
            if (row > super.getRow()) {
                for (int i = super.getRow() + 1; i < row; i++) {
                    if (board.getPiece(i, col) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = super.getRow() - 1; i > row; i--) {
                    if (board.getPiece(i, col) != null) {
                        return false;
                    }
                }
            }
        }

        // cannot attack your own piece
        if (board.getPiece(row, col) == null) {
        	return true;
        }
        if (board.getPiece(row, col) == null) {
        	return true;
        }
        return super.getColor() != board.getPiece(row, col).getColor();
    }

    /**
     * Moves a piece on the board to a new location
     * @param newRow - the new row location of the piece
     * @param newCol - the new row location of the piece
     * @param board - the board we are playing on
     * @return if the move was successful
     */
    public boolean move(int newRow, int newCol, Board board) {
        if (canMove(newRow, newCol, board)) {
        	super.pieceMoved();
            board.setPiece(this, newRow, newCol);
            board.setPiece(null, super.getRow(), super.getCol());
            super.setRow(newRow);
            super.setCol(newCol);
            return true;
        }
        return false;
    }
}
