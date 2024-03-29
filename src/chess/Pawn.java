package chess;

public class Pawn extends GamePiece {
    /**
     * Constructor for a new Pawn
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public Pawn(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "Pawn";
    }

    /**
     * Checks if there is a clear path for the pawn to move given an (row, col) pairing
     * @param row - the row location of new position
     * @param col - the col location of new position
     * @param board - the board we are playing on
     * @return if the pawn can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
        if (row == super.getRow() && col == super.getCol()) {
            return false;
        }

        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        if (!super.getFirstMove()) {
            if (super.getColor()) { // black color
                if (row - super.getRow() != 1) {
                    return false;
                } else {
                    if (col - super.getCol() == 1 || col - super.getCol() == -1) {
                        if (board.getPiece(row, col) != null) {
                            return super.getColor() != board.getPiece(row, col).getColor();
                        } else {
                            return false;
                        }
                    } else if (col - super.getCol() > 1 || col - super.getCol() < -1) {
                    	return false;
                    } else {
                    	if (board.getPiece(row, col) != null) {
                    		return false;
                    	} else {
                    		return true;
                    	}
                    }
                }
            } else { // white color
                // make sure you can only go down
                if (row - super.getRow() != -1) {
                    return false;
                } else {
                    if (col - super.getCol() == 1 || col - super.getCol() == -1) {
                        if (board.getPiece(row, col) != null) {
                            return super.getColor() != board.getPiece(row, col).getColor();
                        } else {
                            return false;
                        }
                    } else if (col - super.getCol() > 1 || col - super.getCol() < -1) {
                    	return false;
                    } else {
                    	if (board.getPiece(row, col) != null) {
                    		return false;
                    	} else {
                    		return true;
                    	}
                    }
                }
            }
        } else {
            if (super.getColor()) { // black color
                if (row - super.getRow() != 1 && row - super.getRow() != 2) {
                    return false;
                } else {
                	if (col - super.getCol() == 1 || col - super.getCol() == -1) {
                        if (row - super.getRow() == 1) {
                            if (board.getPiece(row, col) != null) {
                                return super.getColor() != board.getPiece(row, col).getColor();
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else if (col - super.getCol() > 1 || col - super.getCol() < -1) {
                    	return false;
                    } else {
                    	if (row - super.getRow() != 2) {
                    		return board.getPiece(row + 1, col) == null && board.getPiece(row, col) == null;
                    	} else {
                    		return board.getPiece(row, col) == null;
                    	}
                    }
                }
            } else { // white color
                if (row - super.getRow() != -1 && row - super.getRow() != -2) {
                    return false;
                } else {
                    if (col - super.getCol() == 1 || col - super.getCol() == -1) {
                        if (row - super.getRow() == -1) {
                            if (board.getPiece(row, col) != null) {
                                return super.getColor() != board.getPiece(row, col).getColor();
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else if (col - super.getCol() > 1 || col - super.getCol() < -1) {
                    	return false;
                    } else {
                    	if (row - super.getRow() != -2) {
                    		return board.getPiece(row - 1, col) == null && board.getPiece(row, col) == null;
                    	} else {
                    		return board.getPiece(row, col) == null;
                    	}
                    }
                }
            }
        }
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
