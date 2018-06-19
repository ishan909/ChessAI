import java.math.*;

public class Bishop extends GamePiece {

    /**
     * Constructor for a new Bishop
     */
    public Bishop(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }


    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Bishop";
    }


    /**
     * Checks if there is a clear path for the Bishop to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if bishop can move to and/or attack a piece
     */
    public boolean canMove(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
            // makes sure it is not the same location
            return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        // check the 4 diagonals
        // it is in the bishop's path (potentially)
        if (Math.abs(x - currentX) == Math.abs(y - currentY)) {
            // positive x, positive y
            if ((x - currentX) > 0 &&  (y - currentY) > 0) {
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
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
}
