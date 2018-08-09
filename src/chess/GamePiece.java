package chess;

abstract public class GamePiece {
    public int currentRow, currentCol;
    public boolean color;
    public boolean firstMove;

    /**
     * Constructor for a GamePiece
     * @param row - current row location
     * @param col - current column location
     * @param color - current color of piece
     */
    public GamePiece(int row, int col, boolean color) {
        this.currentRow = row;
        this.currentCol = col;
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
     * Gets the row number of the piece
     * @return the row number of the piece
     */
    public int getRow() {
    	return this.currentRow;
    }

    /**
     * Sets the row location of the piece
     * @param newRow - the new row location
     * @return if the row was set to the new location
     */
    public boolean setRow(int newRow) {
    	if (newRow < 0 || newRow > 7) {
    		return false;
    	}
    	this.currentRow = newRow;
    	return true;
    }

    /**
     * Gets the column number of the piece
     * @return the column number of the piece
     */
    public int getCol() {
    	return this.currentCol;
    }

    /**
     * Sets the column location of the piece
     * @param newCol - the new column location
     * @return if the column was set to the new location
     */
    public boolean setCol(int newCol) {
    	if (newCol < 0 || newCol > 7) {
    		return false;
    	}
    	this.currentCol = newCol;
    	return true;
    }

    /**
     * Returns if this is the piece's first move
     * @return if this is the piece's first move
     */
    public boolean getFirstMove() {
    	return this.firstMove;
    }

    /**
     * Sets the first move to be false
     * A piece's first move will be set to false after one successful move
     */
    public void pieceMoved() {
    	this.firstMove = false;
    }

    /**
     * Checks if a piece can move to a different location
     * @param row - new row location of a piece
     * @param col - new column location of a piece
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
