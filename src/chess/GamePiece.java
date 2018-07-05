package chess;

abstract public class GamePiece {
    // location of the piece (row, col)
    protected int currentX, currentY;
    // color of the piece: black = true, white = false;
    protected boolean color;
    // if this is the first move of the piece
    protected boolean firstMove;

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
    }

    /**
     * Returns type of piece
     */
    abstract public String getType();

    /**
     * Returns the color of a piece
     * @return the color of the game piece
     */
    public boolean getColor() {
        return this.color;
    }

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
}
