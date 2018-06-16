

public abstract class GamePiece {
    // location of the piece
    public int x, y;
    // color of the piece: black = true, white = false;
    public boolean color;
    // if this is the first move of the piece
    public boolean firstMove;
    // if the piece is alive
    public boolean isAlive;

    public GamePiece(int x, int y, boolean color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.firstMove = true;
        this.isAlive = true;
    }

    // check (king only)
    public boolean isInCheck();
    // returns type of piece as String
    public String getType();
    // checks validity
    public boolean validMove();
    // can't take your own piece, valid square to move on
    public boolean canAttack();
    // alive
    public boolean isAlive() {
        return this.isAlive;
    }
    // returns the color of the piece
    public boolean getColor() {
        return this.color;
    }
}

// alive parameter, int x, int y, getType
