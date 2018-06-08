
/* This interface will contain the foundations for all the pieces in the Chess Game */
public interface Piece{

  // take care of bound check, check if person puts piece back in new spot as well
  boolean validMove(int newX, int newY);
  // check if there is a piece there or not (opponent or same side) + is it valid?
  boolean validAttack(int newX, int newY);
  // black is true, white is false
  boolean color;
  // hold current x values
  int currentX;
  // hold current y value
  int currentY;

}
