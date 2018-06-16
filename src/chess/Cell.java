// Create a cell class or square class

// do not need cell clas


class Cell {
    private int x, y; // location of this cell
    private boolean containsPiece; // whether this cell is currently holding a piece
    private Piece currentPiece; // the peice that is currently in this cell
    private boolean color; // color of this cell; true is black, false is red

    /**
     * Cell constructor
     */
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

    /**
     * Returns the x position of the Cell
     * @return x position of Cell
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Cell
     * @return y position of Cell
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns the color of the Cell
     * true is black, false is red
     * @return color of Cell as a boolean
     */
    public boolean getCellColor() {
      return color;
    }

    /**
     * Returns if there is a piece at the cell
     * @return if there is a piece
     */
    public boolean containsPiece() {
        return this.containsPiece;
    }

    /**
     * Returns the piece at the cell
     * @return the piece at the cell
     */
    public Piece getPiece() {
        return this.currentPiece;
    }

    /**
     * Sets whether this cell is holding a piece
     * @param newBoolean - if there is a peice at this location
     */
    public void setContainsPiece(boolean newBoolean) {
        this.containsPiece = newBoolean;
    }

    /**
     * Set the piece at a Cell
     * @param moving - the piece that will be set at this cell
     */
    public void setPiece(Piece moving) {
        // use to initialize a new board as well
        this.currentPiece = moving;
    }

}
