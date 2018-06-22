package chess;

public class Queen extends GamePiece {
    /**
     * Constructor for a new Queen
     */
    public Queen(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Queen";
    }

    /**
     * Checks if there is a clear path for the queen to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if Queen can move to and/or attack a piece
     */
    public boolean canMove(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
          return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        // check vertical and horizontal moves
        if (currentX == x) { // same x, but different y
            // positive or negative y direction
            if (y > currentY) {
                // see if all the spots in between are empty
                for (int i = currentY + 1; i < y; i++) {
                    if (board.getPiece(i, y) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentY - 1; i > y; i--) {
                    if (board.getPiece(i, y) != null) {
                        return false;
                    }
                }
            }
        } else if (currentY == y) { // same y, but different x
            // positive or negative x direction
            if (x > currentX) {
                for (int i = currentX + 1; i < x; i++) {
                    if (board.getPiece(i, y) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentX - 1; i > x; i--) {
                    if (board.getPiece(i, y) != null) {
                        return false;
                    }
                }
            }
        } else if (Math.abs(x - currentX) == Math.abs(y - currentY)) { // check diagonal moves
            // positive x, positive y
            if ((x - currentX) > 0 && (y - currentY) > 0) {
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if ((x - currentX) > 0 && (y - currentY) < 0) { // positive x, negative y
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY - 1; j > y; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if ((x - currentX) < 0 && (y - currentY) < 0) { // negative x, negative y
                for (int i = currentX - 1; i > x; i--) {
                    for (int j = currentY - 1; j > y; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else { // negative x, positive y
                for (int i = currentX - 1; i > x; i--) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            }
        }
        if (board.getPiece(x, y) instanceof King) {
            boolean otherColor = board.getPiece(x, y).getColor();
            if (otherColor != this.color) {
                if (this.color) {
                    board.whiteInCheck = true;
                } else {
                    board.blackInCheck = true;
                }
            }
        }
        // cannot attack your own piece
        return this.color != board.getPiece(x, y).color;
    }

    /**
     * Moves a piece on the board to a new location
     * @param x - the new x location
     * @param y - the new y location
     * @param board - the board we are playing on
     * @return if the move was successful
     */
    public boolean move(int x, int y, Board board) {
        if (canMove(x, y, board)) {
            firstMove = false;
            board.setPiece(this, x, y);
            board.setPiece(null, currentX, currentY);
            currentX = x;
            currentY = y;
            if (this.color) {
                if (canMove(board.whiteKingLocation[0], board.whiteKingLocation[1], board)) {
                    board.whiteInCheck = true;
                }
            } else {
                if (canMove(board.blackKingLocation[0], board.blackKingLocation[1], board)) {
                    board.blackInCheck = true;
                }
            }
            return true;
        }
        return false;
    }
}
