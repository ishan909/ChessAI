import java.math.*;


public class King implements Piece {


    public King(int originalX, int OriginalY, boolean newColor) {
        currentX = originalX;
        currentY = originalY;
        color = newColor;
    }

    // implement valid attack elsewhere (most likely in the game class)

    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!insideBounds(newX,newY)){
            return false;
        }
        return Math.abs(newX - currentX) < 2 && Math.abs(newY - currentY) < 2;
    }
    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }












}
