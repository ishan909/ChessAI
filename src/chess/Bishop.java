import java.math.*;

public class Bishop implements Piece {
    public Bishop(int originalX, int OriginalY, boolean newColor) {
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

        // diagonal check
        return Math.abs(newX - currentX) == Math.abs(newY - currentY);
    }

    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
}
