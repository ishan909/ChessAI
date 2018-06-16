import java.math.*;

public class Knight extends GamePiece {

    /**
     * Constructor for a new Knight
     */
    public Knight(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }


    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Knight";
    }


    /**
     * Checks if the knight can move to a new location
     * @return if knight can move to the new location
     */
    public boolean validMove(int newX, int newY) {
        // bound checks
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
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
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        if (x == currentX && y == currentY) {
            return false;
        }

        // use the board
        if (Math.square(x) + Math.square(y) != 5) {
            return false;
        }

        if (board.getPiece(x, y) instanceof King) {
            boolean otherColor = board.getPiece(x, y).getColor();
            if (otherColor != this.color) {
                if (this.color) {
                    board.whiteInCheck = true;
                } else {
                    board.blackInCheck = true;
                }
            }
        }
        // cannot attack your own piece
        return this.color != board.getPiece(x, y).color;
    }
}
