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

    /**
     * Checks if there is a clear path for the king to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if king can attack the opponent King
     */
    public boolean canMove(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
            // makes sure it is not the same location
            return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        double distance = Math.sqrt((currentX - x) * (currentX - x)
                + (currentY - y) * (currentY - y));
        if (distance > Math.sqrt(2)) {
            return false;
        }
        // cannot attack your own piece
        return this.color != board.getPiece(x, y).color;
    }
}
