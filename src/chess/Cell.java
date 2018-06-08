// Create a cell class or square class

class Cell {
    private int x, y;
    private boolean containsPiece;
    private Piece currentPiece;
    private boolean color; // true is black, false is red

    public Cell(int x, int y, boolean addPiece, Piece piece, boolean aColor) {
        this.x = x;
        this.y = y;
        this.containsPiece = addPiece;
        this.color = aColor;
        if (addPiece) {
            this.currentPiece = piece;
        } else {
            currentPiece = new Piece();
        }
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean getColor(){
      return color;
    }

    public boolean containsPiece() {
        return this.containsPiece;
    }

    public Piece getPiece() {
        return this.currentPiece;
    }
    // use to initialize a new board as well 
    public void setPiece(Piece attacking){
      this.currentPiece = attacking;
    }

}
