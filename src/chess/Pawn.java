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
     * Checks if there is a clear path for the queen to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if piece can attack a piece
     */
    public boolean canMove(int x, int y, Board board) {

        // make sure you can not attack your own piece
        if (x == currentX && y == currentY) {
            return false;
        }
        // valid position
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        // attack
        // TODO finish implementing attack

        if(!firstMove) {
          // can only move up once, do by colors

          // white color
          if(!color) {
            // make sure you can only go up
            if(y - currentY != 1) {
              return false;
            }
            // check for dx
            if(x-currentX == 1 || x-currentX == -1) {
              if(y - currentY == 1) {
                return this.color != board.getPiece(x, y).color;
              }
            }

          }
          // black color
          else {
            if(y - currentY != -1) {
              return false;
            }

            // check for dx
            if(x - currentX == 1 || x-currentX == -1) {
              if(y - currentY == -1) {
                return this.color != board.getPiece(x, y).color;
              }
            }

          }
        }
        else {
          // move up 2 or 1

          // update, in another function

          // white color
          if(!color) {
            // make sure you can only go up
            if(y - currentY != 1 || y-currentY != 2) {
              return false;
            }
            // check for dx
            if(x-currentX == 1 || x-currentX == -1) {
              if(y - currentY == 1) {
                return this.color != board.getPiece(x, y).color;
              }
              else {
                return false;
              }
            }

          }
          // black color
          else {
            if(y - currentY != -1 || y - currentY != -2) {
              return false;
            }

            // check for dx
            if(x - currentX == 1 || x-currentX == -1) {
              if(y - currentY == -1) {
                return this.color != board.getPiece(x, y).color;
              }
              else {
                return false;
              }
            }

          }


        }



    }

}
