import java.math.*;

public class Rook extends Piece {

    public Rook(int originalX, int OriginalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    // implement valid attack elsewhere (most likely in the game class)

    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!insideBounds(newX, newY)){
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }

        // so a queen can go straight in one direction or diagonally
        return(currentX == newX || currentY == newY);
    }

    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
}
