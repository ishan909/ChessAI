import java.math.*;

public class Pawn extends Piece {

    /**
     * Constructor for a new Pawn
     */
    public Pawn(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }


    // Issues (Pawn reaching end, color means different, moving one or two spaces based on first move check or not)


    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the pawn can move to a new location
     * @return if pawn can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!super.insideBounds(newX, newY)) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }
    }
}
