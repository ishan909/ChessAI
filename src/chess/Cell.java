// Create a cell class or square class

class Cell {
    private int x, y;
    private boolean containsPiece;
    private Piece currentPiece;

    public Cell(int x, int y, boolean addPiece, Piece piece) {
        this.x = x;
        this.y = y;
        this.containsPiece = addPiece;
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

    public boolean containsPiece() {
        return this.containsPiece;
    }

    public Piece getPiece() {
        return this.currentPiece;
    }

}
