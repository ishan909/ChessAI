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
    	  if (super.getRow() == row && super.getCol() == col) {
            return false;
        }
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }
        if ((super.getRow() != row && super.getCol() != col) && (Math.abs(row - super.getRow()) != Math.abs(col - super.getCol()))) {
        		return false;
        }
        if (super.getRow() == row) {
            if (col > super.getCol()) {
                for (int i = super.getCol() + 1; i < col; i++) {
                    if (board.getPiece(super.getRow(), i) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = super.getCol() - 1; i > col; i--) {
                    if (board.getPiece(super.getRow(), i) != null) {
                        return false;
                    }
                }
            }
        } else if (super.getCol() == col) {
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
        } else if ((row - super.getRow()) > 0 &&  (col - super.getCol()) > 0) {
            for (int i = super.getRow() + 1, j = super.getCol() + 1; i < row; i++, j++) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else if ((row - super.getRow()) > 0 && (col - super.getCol()) < 0) {
            for (int i = super.getRow() + 1, j = super.getCol() - 1; i < row; i++, j--) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else if ((row - super.getRow()) < 0 && (col - super.getCol()) < 0) {
            for (int i = super.getRow() - 1, j = super.getCol() - 1; i > row; i--, j--) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        } else {
            for (int i = super.getRow() - 1, j = super.getCol() + 1; i > row; i--, j++) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
        }

        if (board.getPiece(row, col) == null) {
        	return true;
        }
        return super.getColor() != board.getPiece(row, col).getColor();
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
