import java.math.*;

public class Pawn extends GamePiece {

    /**
     * Constructor for a new Pawn
     */
    public Pawn(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Pawn";
    }

    // TODO: Issues (Pawn reaching end, color means different, moving one or two spaces based on first move check or not)

    /**
     * Checks if the pawn can move to a new location
     * @return if pawn can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (newX == currentX && newY == currentY) {
            return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        // TODO finish implementing this
    }

    /**
     * Checks if the piece can attack a piece
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if piece can attack a piece
     */
    public boolean canAttack(int x, int y, Board board) {
        // make sure you can not attack your own piece
        if (x == currentX && y == currentY) {
            return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        // attack
        // TODO finish implementing attack
    }
}
