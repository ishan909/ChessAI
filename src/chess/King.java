import java.math.*;

public class King implements GamePiece {

    /**
     * Constructor for a new King
     */
    public King(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "King";
    }

    // implement valid attack elsewhere (most likely in the game class)

    /**
     * Checks if the king can move to a new location
     * @return if king can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!super.insideBounds(newX, newY)) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }
        return Math.abs(newX - currentX) < 2 && Math.abs(newY - currentY) < 2;
    }

    public boolean KingLife() {
        return true;
    }

    /**
     * Checks if the king can attack an opposing King
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if king can attack the opponent King
     */
    public boolean canAttack(int x, int y, Board board) {
        // king cannot attack an opponet king
        return false;
    }
}
