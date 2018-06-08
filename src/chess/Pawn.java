import java.math.*;

public class Pawn implements Piece {
    public Pawn(int originalX, int OriginalY, boolean newColor) {
        currentX = originalX;
        currentY = originalY;
        color = newColor;
    }


    // Issues (Pawn reaching end, color means different, moving one or two spaces based on first move check or not)


    // implement valid attack elsewhere (most likely in the game class)

    public boolean validMove(int newX, int newY) {
        // bound checks
        if (!insideBounds(newX, newY)){
            return false;
        }
        if (newX == currentX && newY == currentY) {
            return false;
        }

    }

    public boolean insideBounds(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
}
