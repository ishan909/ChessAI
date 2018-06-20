import java.math.*;

public class Pawn extends GamePiece {
    /**
     * Constructor for a new Pawn
     */
    public Pawn(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of th
     is piece
     * return the type of the piece
     */
    public String getType() {
        return "Pawn";
    }

    // TODO: Issues: Pawn reaching end
    //      update, in another function

    /**
     * Checks if there is a clear path for the pawn to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if the pawn can move to and/or attack a piece
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
