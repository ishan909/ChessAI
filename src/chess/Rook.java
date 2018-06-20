import java.math.*;

public class Rook extends GamePiece {
    /**
     * Constructor for a new Rook
     */
    public Rook(int originalX, int OriginalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Rook";
    }

    /**
     * Checks if the rook can attack a piece
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if rook can move to and/or attack a piece
     */
    public boolean canMove(int x, int y, Board board) {
        // TODO
        // make sure the piece can't attack it's own set of pieces
        if (currentX == x && currentY == y) {
            return false;
        }
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }

        // same x, but different y
        if (currentX == x) {
            // positive or negative y direction
            if (y > currentY) {
                // see if all the spots in between are empty
                // pass in Board
                for (int i = currentY + 1; i < y; i++) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentY - 1; i > y; i--) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            }
        } else if (currentY == y) { // same y, but different x
            // positive or negative x direction
            if (x > currentX) {
                for (int i = currentX + 1; i < x; i++) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = currentX - 1; i > x; i--) {
                    if (board.getPiece(i, j) != null) {
                        return false;
                    }
                }
            }
        }
        if (board.getPiece(x, y) instanceof King) {
            boolean otherColor = board.getPiece(x, y).getColor();
            if (otherColor != this.color) {
                if (this.color) {
                    board.whiteInCheck = true;
                } else {
                    board.blackInCheck = true;
                }
            }
        }
        // cannot attack your own piece
        return this.color != board.getPiece(x, y).color;
    }

    /**
     * Moves a piece on the board to a new location
     * @param x - the new x location
     * @param y - the new y location
     * @param board - the board we are playing on
     * @return if the move was successful
     */
    public boolean move(int x, int y, Board board) {
        if (canMove(x, y, board)) {
            firstMove = false;
            board.setPiece(this, x, y);
            board.setPiece(null, currentX, currentY);
            currentX = x;
            currentY = y;
            if (this.color) {
                if (canMove(whiteKingLocation[0], whiteKingLocation[1], board)) {
                    board.whiteInCheck = true;
                    if (board.getPiece(whiteKingLocation[0], whiteKingLocation[1]).isInCheckmate(board)) {
                        // TODO terminate game
                    }
                }
            } else {
                if (canMove(blackKingLocation[0], blackKingLocation[1], board)) {
                    board.blackInCheck = true;
                    if (board.getPiece(blackKingLocation[0], blackKingLocation[1]).isInCheckmate(board)) {
                        // TODO terminate game
                    }
                }
            }
            return true;
        }
        return false;
    }
}
