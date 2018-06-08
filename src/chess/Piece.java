/* This class will contain the foundations for all the pieces in the Chess Game */
public class Piece {

    // black is true, white is false
    boolean color;

    // hold x and y values of the piece
    int currentX, currentY;

    /**
     * Constructor for a new Piece without a color
     */
    public Piece(int originalX, int originalY) {
      this.currentX = originalX;
      this.currentY = originalY;
    }

    /**
     * Constructor for a new Piece with a color
     */
    public Piece(int originalX, int originalY, boolean color) {
        this.currentX = originalX;
        this.currentY = originalY;
        this.color = color;
    }

    // implement valid attack elsewhere (most likely in the game class)

    // TODO check if there is a piece there or not (opponent or same side) + is it valid?
    boolean validAttack(int newX, int newY) {
        return;
    }

    /**
     * Checks if a location is inside the bounds of the Board
     * @return if an x and a y is inside a Board
     */
    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
}
