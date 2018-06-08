import java.math.*;

public class King extends Piece {

    public King(int originalX, int originalY, boolean newColor) {
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
        return Math.abs(newX - currentX) < 2 && Math.abs(newY - currentY) < 2;
    }

    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

}
