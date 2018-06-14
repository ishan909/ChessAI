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
    /**
     * Checks if the rook can attack a piece
     * @param x,y
     * @return if rook can attack a piece
     */
    public boolean canAttack(int x, int y, Board board){
      if(currentX == x && currentY == y){
        return false;
      }
      // same x, but different y
      if(currentX == x){
          // positive or negative y direction
          if( y > currentY){
            // see if all the spots in between are empty
            // pass in Board
            for(int i = currentY+1; i < y; i++){
              if(board.getCell(x,i).containsPiece){
                return false;
              }
            }
          }
          else{
            for(int i = currentY-1; i > y; i--){
              if(board.getCell(x,i).containsPiece){
                return false;
              }
            }

          }
      }
      // same y, but different x
      else if(currentY == y){
          // positive or negative x direction
          if(x > currentX){
            for(int i = currentX+1; i < x; i++){
              if(board.getCell(i,y).containsPiece){
                return false;
              }
            }
          }
          else{
            for(int i = currentX-1; i > x; i--){
              if(board.getCell(i,y).containsPiece){
                return false;
              }
            }
          }
      }
      else{
        return false;
      }
      return true;


    }
}
