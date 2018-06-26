package chess;

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

    /**
     * Checks if there is a clear path for the pawn to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if the pawn can move to and/or attack a piece
     */
    public boolean canMove(int row, int col, Board board) {
        // make sure you can not attack your own piece
        if (row == currentX && col == currentY) {
            return false;
        }
        // valid position
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }

        if (!firstMove) { // can only move up once, do by colors
            if (color) { // black color
                // make sure you can only go up
                if (row - currentX != 1) {
                    return false;
                }
                // check for dx
                if (col - currentY == 1 || col - currentY == -1) {
                    if (board.getPiece(row, col) != null) {
                        return this.color != board.getPiece(row, col).color;
                    } else {
                        return false;
                    }
                }
            } else { // black color
                if (row - currentX != -1) {
                    return false;
                }
                // check for dx
                if (col - currentY == 1 || col - currentY == -1) {
                    if (board.getPiece(row, col) != null) {
                        return this.color != board.getPiece(row, col).color;
                    } else {
                        return false;
                    }
                }
            }
        } else { // move up 1 or 2
            if (color) { // black color
                // make sure you can only go up
                if (row - currentX != 1 && row - currentX != 2) {
                    return false;
                }
                // check for col value change
                if (col - currentY == 1 || col - currentY == -1) {
                    if (col - currentY == 1) {
                        if (board.getPiece(row, col) != null) {
                            return this.color != board.getPiece(row, col).color;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                
            } else { // black color
                if (row - currentX != -1 && row - currentX != -2) {
                    return false;
                }
                // check for dx
                if (col - currentY == 1 || col - currentY == -1) {
                    if (row - currentX == -1) {
                        if (board.getPiece(row, col) != null) {
                            return this.color != board.getPiece(row, col).color;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            if (row - currentX == 2 && board.getPiece(row + 1, col) != null) {
                // there is a piece in the way of the move
                return false;
            }
        }
        return board.getPiece(row, col) == null;
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
            return true;
        }
        return false;
    }
}
