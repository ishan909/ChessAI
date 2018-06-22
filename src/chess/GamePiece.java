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

    public GamePiece(int x, int y, boolean color) {
        this.currentX = x;
        this.currentY = y;
        this.color = color;
        this.firstMove = true;
        this.isAlive = true;
    }

    // returns type of piece as String
    abstract public String getType();
    // can't take your own piece, valid square to move on
    abstract public boolean canMove(int x, int y, Board board);
    // moves a piece on the board
    abstract public boolean move(int x, int y, Board board);
    // alive
    public boolean isAlive() {
        return this.isAlive;
    }
    // returns the color of the piece
    public boolean getColor() {
        return this.color;
    }
}
