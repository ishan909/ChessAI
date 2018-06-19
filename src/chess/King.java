import java.math.*;

public class King extends GamePiece {

    /**
     * Constructor for a new King
     */
    public King(int originalX, int originalY, boolean newColor) {
        super(originalX, originalY, newColor);
    }


    /**
     * Returns the type of this piece
     * return the type of the piece
     */
    public String getType() {
        return "King";
    }

    /**
     * Checks if there is a clear path for the king to move given an (x,y) pairing
     * @param x - x location of new position
     * @param y - y location of new position
     * @param board - the board we are playing on
     * @return if king can move to and/or attack a piece
     */
    public boolean canMove(int x, int y, Board board) {
        if (currentX == x && currentY == y) {
            // makes sure it is not the same location
            return false;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        double distance = Math.sqrt((currentX - x) * (currentX - x)
                + (currentY - y) * (currentY - y));
        if (distance > Math.sqrt(2)) {
            return false;
        }
        // cannot attack your own piece
        return this.color != board.getPiece(x, y).color;
    }

    /**
     * Detemrines if a king is currently in check
     * @param board - the board we are playing on
     * @return if the king is currently in check
     */
    public boolean isInCheck(Board board) {
        if (isInCheckAt(currentX, currentY, board) {
            if (this.getColor) {
                board.blackInCheck = true;
            } else {
                board.whiteInCheck = true;
            }
            return true;
        } else {
            if (this.getColor) {
                board.blackInCheck = false;
            } else {
                board.whiteInCheck = false;
            }
            return false;
        }
    }

    /**
     * Determines if a king would be in check if it was at a given location
     * @param x - the x location to be checked
     * @param y - the y location to be checked
     * @param board - the board we are playing on
     * @return whether the king is in checkmate
     */
    public boolean isInCheckAt(int x, int y, Board board) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.getPiece(r, c);
                if (piece != null) {
                    if (piece.getColor() != this.color) {
                        if (piece.canMove(currentX, currentY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if a king is in checkmate by checking all possible moves
     * @param board - the board we are playing on
     * @return whether the king is in checkmate
     */
    public boolean isInCheckmate(Board board) {
        if (!isInCheckAt(currentX, currentY, board)) {
            return false;
        }
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (!isInCheck(currentX + r, currentY + y, board)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Moves a piece on the board to a new location
     * @param x - the new x location
     * @param y - the new y location
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
                board.blackKingLocation[0] = x;
                board.blackKingLocation[1] = y;
            } else {
                board.whiteKingLocation[0] = x;
                board.whiteKingLocation[1] = y;
            }
            return true;
        }
        return false;
    }
}
