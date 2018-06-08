import java.math.*;

public class Queen extends Piece {

    /**
     * Constructor for a new Queen
     */
    public Queen(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
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
}
