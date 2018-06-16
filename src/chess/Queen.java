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
        if (!super.insideBounds(newX, newY)) {
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
     * @param x,y
     * @return if knight can attack a piece
     */
    public boolean canAttack(int x, int y, Board board) {
      if (currentX == x && currentY == y) {
          return false;
      }

        if (currentX == x) { // same x, but different y
            // positive or negative y direction
            if (y > currentY) {
                // see if all the spots in between are empty
                // pass in Board
                for (int i = currentY + 1; i < y; i++) {
                    if (board.getCell(x, i).containsPiece) {
                        return false;
                    }
                }
            } else {
                for (int i = currentY - 1; i > y; i--) {
                    if (board.getCell(x, i).containsPiece) {
                        return false;
                    }
                }
            }
        } else if (currentY == y) { // same y, but different x
        // positive or negative x direction
            if (x > currentX) {
                for (int i = currentX + 1; i < x; i++) {
                    if (board.getCell(i, y).containsPiece) {
                        return false;
                    }
                }
            } else {
                for (int i = currentX - 1; i > x; i--) {
                    if (board.getCell(i, y).containsPiece) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        // check the 4 diagonals

        // it is in the bishop's path (potentially)
        if (Math.abs(x - currentX) == Math.abs(y - currentY)) {
            // positive x, positive y
            if ((x - currentX) > 0 && (y - currentY) > 0) {
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getCell(i, j).containsPiece) {
                            return false;
                        }
                    }
                }
            } else if ((x - currentX) > 0 && (y - currentY) < 0) { // positive x, negative y
                for (int i = currentX + 1; i < x; i++) {
                    for (int j = currentY - 1; j > y; j--) {
                        if (board.getCell(i, j).containsPiece) {
                            return false;
                        }
                    }
                }
            } else if ((x - currentX) < 0 && (y - currentY) < 0) { // negative x, negative y
                for (int i = currentX - 1; i > x; i--) {
                    for (int j = currentY - 1; j > y; j--) {
                        if (board.getCell(i, j).containsPiece) {
                            return false;
                        }
                    }
                }
            } else { // negative x, positive y
                for (int i = currentX - 1; i > x; i--) {
                    for (int j = currentY + 1; j < y; j++) {
                        if (board.getCell(i, j).containsPiece) {
                            return false;
                        }
                    }
                }
            }
        }
    }
}
