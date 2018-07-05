package chess;

abstract public class GamePiece {
    // location of the piece (row,col)
    public int currentX, currentY;
    // color of the piece: black = true, white = false;
    public boolean color;
    // if this is the first move of the piece
    public boolean firstMove;
    // if the piece is alive
    public boolean isAlive;

    /**
     * Constructor for a GamePiece
     * @param row - current row location
     * @param col - current column location
     * @param color - current color of piece
     */
    public GamePiece(int row, int col, boolean color) {
        this.currentX = row;
        this.currentY = col;
        this.color = color;
        this.firstMove = true;
        this.isAlive = true;
    }

    /**
     * Returns type of piece
     */
    abstract public String getType();

    /**
     * Checks if an arbitrary piece can move to a different location
     * @param row - current row location of a piece
     * @param col - current column location of a piece
     * @param board - instance of a board
     * @return if the piece can be moved
     */
    abstract public boolean canMove(int row, int col, Board board);

    /**
     * Moves a piece on the board
     * @param row - current row location of a piece
     * @param col - current column location of a piece
     * @param board - instance of a board
     * @return if the move was successful
     */
    abstract public boolean move(int row, int col, Board board);

    /**
     * Checks if a player is in check
     * @param board - instance of a board
     * @return if the piece is in check
     */
    public boolean isInCheck(Board board) {
    	// default return to be false
    	// this method is overwritten in the king class
    	return false;
    }

    /**
     * Checks if a player is in checkmate
     * @param board - instance of a board
     * @return if the piece is in checkmate
     */
    public boolean isInCheckmate(Board board) {
    	// default return to be false
    	// this method is overwritten in the king class
		return false;
    }

    /**
     * Returns of a piece is still alive
     * @return if a piece is still alive
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Returns the color of a piece
     * @return the color of the game piece
     */
    public boolean getColor() {
        return this.color;
    }
}
