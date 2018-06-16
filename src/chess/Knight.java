import java.math.*;

public class Knight implements GamePiece {

    /**
     * Constructor for a new Knight
     */
    public Knight(int originalX, int originalY, boolean newColor) {
      //  super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Knight";
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
    /**
     * Checks if the knight can attack a piece
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if knight can attack a piece
     */
    public boolean canAttack(int x, int y, Board board){
        if (!super.insideBounds(newX, newY)) {
            return false;
        }
        if (x == currentX && y == currentY) {
            return false;
        }
        // use the board
        if (Math.square(x) + Math.square(y) == 5) {
            return true;
        } else {
            return false;
        }

        // TODO
        // make sure the piece can't attack it's own set of pieces
    }
}
