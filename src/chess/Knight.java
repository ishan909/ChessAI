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
     * Checks if there is a clear path for the knight to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if knight can attack a piece
     */
    public boolean canMove(int x, int y, Board board){
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
