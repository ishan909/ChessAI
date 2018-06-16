import java.math.*;

public class Bishop  {

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

    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the bishop can move to a new location
     * @return if bishop can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!super.insideBounds(newX, newY)) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }

        // diagonal check
        return Math.abs(newX - currentX) == Math.abs(newY - currentY);
    }

    // TODO
    // make sure the piece can't attack it's own set of pieces
    /**
     * Checks if the bishop can attack a piece
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if bishop can attack a piece
     */
    public boolean canAttack(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
            // makes sure it is not the same location
            return false;
        }

        // check the 4 diagonals
        // it is in the bishop's path (potentially)
        if (Math.abs(x - currentX) == Math.abs(y - currentY)) {
            // positive x, positive y
            if ((x - currentX) > 0 &&  (y - currentY) > 0) {
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getCell(i, j).containsPiece) {
                            return false;
                        }
                    }
                }
            }
        } else if ((x - currentX) > 0 && (y - currentY) < 0) { // positive x, negative y
            for (int i = currentX + 1; i < x; i++) {
                for (int j = currentY-1; j > y; j--) {
                    if (board.getCell(i, j).containsPiece) {
                        return false;
                    }
                }
            }
        } else if ((x - currentX) < 0 && (y - currentY) < 0) { // negative x, negative y
            for (int i = currentX - 1; i > x; i--) {
                for (int j = currentY - 1; j > y; j--) {
                    if (board.getCell(i,j).containsPiece) {
                        return false;
                    }
                }
            }
        } else { // negative x, positive y
            for (int i = currentX - 1; i > x; i--) {
                for (int j = currentY + 1; j < y; j++) {
                    if (board.getCell(i,j).containsPiece) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }
}
