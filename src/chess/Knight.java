import java.math.*;

public class Knight implements Piece {
    public Knight(int originalX, int OriginalY, boolean newColor) {
        currentX = originalX;
        currentY = originalY;
        color = newColor;
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
        return Math.square(newX) + Math.square(newY) == 5;

    }

    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
}
