package chess;

abstract public class GamePiece {
    // location of the piece
    public int currentX, currentY;
    // color of the piece: black = true, white = false;
    public boolean color;
    // if this is the first move of the piece
    public boolean firstMove;
    // if the piece is alive
    public boolean isAlive;

    public GamePiece(int row, int col, boolean color) {
        this.currentX = row;
        this.currentY = col;
        this.color = color;
        this.firstMove = true;
        this.isAlive = true;
    }

    // returns type of piece as String
    abstract public String getType();
    // can't take your own piece, valid square to move on
    abstract public boolean canMove(int row, int col, Board board);
    // moves a piece on the board
    abstract public boolean move(int row, int col, Board board);
    // alive
    public boolean isInCheck(Board board) {
    		return false;
    }
    public boolean isInCheckmate(Board board) {
		return false;
    }
    public boolean isAlive() {
        return this.isAlive;
    }
    // returns the color of the piece
    public boolean getColor() {
        return this.color;
    }
    public int getX() {
    	return currentX;
    }
    public int getY() {
    	return currentY;
    }
}
