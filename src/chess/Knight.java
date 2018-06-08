import java.math.*;

public class Knight extends Piece {

    /**
     * Constructor for a new Knight
     */
    public Knight(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the knight can move to a new location
     * @return if knight can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!super.insideBounds(newX, newY)) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }
        return Math.square(newX) + Math.square(newY) == 5;

    }
}
