package chess;

public class King extends GamePiece {
    /**
     * Constructor for a new King
     * @param row - the row location of the new piece
     * @param col - the col location of the new piece
     * @param color - the color of the new piece
     */
    public King(int row, int col, boolean color) {
        super(row, col, color);
    }

    /**
     * Returns the type of this piece
     * @return the type of the piece
     */
    public String getType() {
        return "King";
    }

    /**
     * Checks if there is a clear path for the king to move given an (row, col) pairing
     * @param row - row location of new position
     * @param col - col location of new position
     * @param board - the board we are playing on
     * @return if king can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
        if (super.getRow() == row && super.getCol() == col) {
            return false;
        }
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        double distance = Math.sqrt((super.getRow() - row) * (super.getRow() - row)
                + (super.getCol() - col) * (super.getCol() - col));
        if (distance > Math.sqrt(2)) {
            return false;
        }
        if (board.getPiece(row, col) == null) {
        		return true;
        }
        return super.getColor() != board.getPiece(row, col).getColor();
    }

    /**
     * Determines if a king is currently in check
     * @param board - the board we are playing on
     * @return if the king is currently in check
     */
    public boolean isInCheck(Board board) {
        return isInCheckAt(super.getRow(), super.getCol(), board);
    }

    /**
     * Determines if a king would be in check if it was at a given location
     * @param row - the row location to be checked
     * @param col - the col location to be checked
     * @param board - the board we are playing on
     * @return whether the king is in checkmate
     */
    public boolean isInCheckAt(int row, int col, Board board) {
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                GamePiece piece = board.getPiece(r, c);
                if (piece != null) {
                    if (piece.getColor() != super.getColor()) {
                        if (piece.canMove(super.getRow(), super.getCol(), board)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if a king is in checkmate by checking all possible moves
     * @param board - the board we are playing on
     * @return whether the king is in checkmate
     */
    public boolean isInCheckmate(Board board) {
        if (!isInCheckAt(super.getRow(), super.getCol(), board)) {
            return false;
        }
        for (int r = 0; r < 8; r++) {
    		for (int c = 0; c < 8; c++) {
    			GamePiece p = board.getPiece(r, c);
    			if (p != null && p.getColor() == super.getColor()) {
    				for (int newR = 0; newR < 8; newR++) {
    					for (int newC = 0; newC < 8; newC++) {
    						if (p.canMove(newR, newC, board)) {
    							Board clone = new Board();
    							for (int x = 0; x < 8; x++) {
    								for (int y = 0; y < 8; y++) {
    									if (board.getPiece(x, y) == null) {
    										clone.setPiece(null, x, y);
    									} else if (board.getPiece(x, y) instanceof Pawn) {
    										clone.setPiece(new Pawn(x, y, board.getPiece(x, y).getColor()), x, y);
    									} else if (board.getPiece(x, y) instanceof Rook) {
    										clone.setPiece(new Rook(x, y, board.getPiece(x, y).getColor()), x, y);
    									} else if (board.getPiece(x, y) instanceof Knight) {
    										clone.setPiece(new Knight(x, y, board.getPiece(x, y).getColor()), x, y);
    									} else if (board.getPiece(x, y) instanceof Bishop) {
    										clone.setPiece(new Bishop(x, y, board.getPiece(x, y).getColor()), x, y);
    									} else if (board.getPiece(x, y) instanceof Queen) {
    										clone.setPiece(new Queen(x, y, board.getPiece(x, y).getColor()), x, y);
    									} else if ((board.getPiece(x, y) instanceof King)) {
    										clone.setPiece(new King(x, y, board.getPiece(x, y).getColor()), x, y);
    									}
    								}
    							}
    							// move the piece in the clones board and see if it is still in check
    							clone.getPiece(r, c).move(newR, newC, clone);
								clone.setPiece(null, r, c);
								if (!clone.check(super.getColor())) {
									return false;
								}
    						}
    					}
    				}
    			}
    		}
        }
        return true;
    }

    /**
     * Moves a piece on the board to a new location
     * @param newRow - the new newRow location
     * @param newCol - the new newCol location
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
            board.setKingLocation(super.getColor(), newRow, newCol);
            return true;
        }
        return false;
    }
}
