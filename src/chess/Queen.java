import java.math.*;

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

    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the queen can move to a new location
     * @return if queen can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }

        // so a queen can go straight in one direction or diagonally
        if (currentX == newX || currentY == newY) {
            return true;
        }
        // diagonal check
        return Math.abs(newX - currentX) == Math.abs(newY - currentY);
    }

    /**
     * Checks if the knight can attack a piece
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if knight can attack a piece
     */
    public boolean canAttack(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
          return false;
        }
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }

        // check vertical and horizontal moves
        if (currentX == x) { // same x, but different y
            // positive or negative y direction
            if (y > currentY) {
                // see if all the spots in between are empty
                for (int i = currentY + 1; i < y; i++) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentY - 1; i > y; i--) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            }
        } else if (currentY == y) { // same y, but different x
            // positive or negative x direction
            if (x > currentX) {
                for (int i = currentX + 1; i < x; i++) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentX - 1; i > x; i--) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            }
        } else if (Math.abs(x - currentX) == Math.abs(y - currentY)) { // check diagongal moves
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
}
