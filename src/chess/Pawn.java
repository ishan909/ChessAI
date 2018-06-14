import java.math.*;

public class Pawn extends Piece {

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
    /**
     * Checks if the piece can attack a piece
     * @param x,y
     * @return if piece can attack a piece
     */

     // TODO
     // make sure you can not attack your own piece
    public boolean canAttack(int x, int y, Board board) {
        if(x == currentX && y == currentY){
          return false;
        }
        // attack 
    }
}
