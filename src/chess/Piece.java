/* This class will contain the foundations for all the pieces in the Chess Game */
public class Piece {

    // black is true, white is false
    boolean color;

    // hold current x and y values
    int currentX, currentY;

    public Piece(int originalX, int originalY, boolean color) {
        this.currentX = originalX;
        this.currentY = originalY;
        this.color = color;
    }

    // implement valid attack elsewhere (most likely in the game class)

    // check if there is a piece there or not (opponent or same side) + is it valid?
    boolean validAttack(int newX, int newY) {
        return;
    }
}
