import java.math.*;

public class Bishop extends Piece {

    /**
     * Constructor for a new Bishop
     */
    public Bishop(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
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
}
