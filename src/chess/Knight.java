package chess;

import java.math.*;

public class Knight extends GamePiece {
    /**
     * Constructor for a new Knight
     */
    public Knight(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }

    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "Knight";
    }

    /**
     * Checks if there is a clear path for the knight to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if knight can move to and/or attack a piece
     */
    public boolean canMove(int x, int y, Board board){
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        if (x == currentX && y == currentY) {
            return false;
        }

        // use the board
        if (Math.square(x) + Math.square(y) != 5) {
            return false;
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
