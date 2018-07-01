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
        if (currentX == row && currentY == col) {
            // makes sure it is not the same location
            return false;
        }
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        double distance = Math.sqrt((currentX - row) * (currentX - row)
                + (currentY - col) * (currentY - col));
        if (distance > Math.sqrt(2)) {
            return false;
        }
        // cannot attack your own piece
        if (board.getPiece(row, col) == null) {
        		return true;
        }
        return this.color != board.getPiece(row, col).color;
    }

    /**
     * Determines if a king is currently in check
     * @param board - the board we are playing on
     * @return if the king is currently in check
     */
    public boolean isInCheck(Board board) {
        return isInCheckAt(currentX, currentY, board);
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
                    if (piece.getColor() != this.color) {
                        if (piece.canMove(currentX, currentY, board)) {
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
        if (!isInCheckAt(currentX, currentY, board)) {
            return false;
        }
        // Where the King can potentially move
        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
	            	int newX = currentX + r;
	            	int newY = currentY + c;
	            	if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
	            		if (canMove(newX, newY, board) && !isInCheckAt(newX, newY, board)) {
	            			return false;
	            		}
                }
            }
        }
        // if piece attacking can be taken
        // if you can block path 
        // re-check
        
     
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
            firstMove = false;
            board.setPiece(this, newRow, newCol);
            board.setPiece(null, currentX, currentY);
            currentX = newRow;
            currentY = newCol;
            if (this.color) {
                board.blackKingLocation[0] = newRow;
                board.blackKingLocation[1] = newCol;
            } else {
                board.whiteKingLocation[0] = newRow;
                board.whiteKingLocation[1] = newCol;
            }
            return true;
        }
        return false;
    }
}
