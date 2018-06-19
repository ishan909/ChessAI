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

    // TODO: Issues: Pawn reaching end
    // TODO: update, in another function

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

        if (!firstMove) { // can only move up once, do by colors
            if (!color) { // white color
                // make sure you can only go up
                if (y - currentY != 1) {
                    return false;
                }
                // check for dx
                if (x - currentX == 1 || x - currentX == -1) {
                    if (board.getPiece(x, y) != null) {
                        return this.color != board.getPiece(x, y).color;
                    } else {
                        return false;
                    }
                }
            } else { // black color
                if (y - currentY != -1) {
                    return false;
                }
                // check for dx
                if (x - currentX == 1 || x - currentX == -1) {
                    if (board.getPiece(x, y) != null) {
                        return this.color != board.getPiece(x, y).color;
                    } else {
                        return false;
                    }
                }
            }
        } else { // move up 2 or 1
            if (!color) { // white color
                // make sure you can only go up
                if (y - currentY != 1 || y - currentY != 2) {
                    return false;
                }
                // check for dx
                if (x - currentX == 1 || x - currentX == -1) {
                    if (y - currentY == 1) {
                        if (board.getPiece(x, y) != null) {
                            return this.color != board.getPiece(x, y).color;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                if (y - currentY == 2 && board.getPiece(x, currentY + 1) != null) {
                    // there is a piece in the way of the move
                    return false;
                }
            } else { // black color
                if (y - currentY != -1 || y - currentY != -2) {
                    return false;
                }
                // check for dx
                if (x - currentX == 1 || x - currentX == -1) {
                    if (y - currentY == -1) {
                        if (board.getPiece(x, y) != null) {
                            return this.color != board.getPiece(x, y).color;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            if (y - currentY == -2 && board.getPiece(x, currentY - 1) != null) {
                // there is a piece in the way of the move
                return false;
            }
        }
        return board.getPiece(x, y) == null;
    }

}
