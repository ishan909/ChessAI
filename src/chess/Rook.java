import java.math.*;

public class Rook extends Piece {

    /**
     * Constructor for a new Rook
     */
    public Rook(int originalX, int OriginalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Rook";
    }

    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the rook can move to a new location
     * @return if rook can move to the new location
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
        return(currentX == newX || currentY == newY);
    }
}
