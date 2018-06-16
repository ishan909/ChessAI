import java.math.*;

public class King extends GamePiece {

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
     * @return if king is moving within its reach
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }
        if (newX == currentX && newY == currentY) {
            // the piece does not move
            return false;
        }
        return Math.abs(newX - currentX) < 2 && Math.abs(newY - currentY) < 2;
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
