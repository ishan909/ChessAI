// This class will contain what is at each location of the board

public class Board {
    Cell[][] matrix = new Cell[8][8];

    // initialization
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            matrix[r][c] = new Cell();
        }
    }

    // update the bottom 2 and top two rows in board to have default peices
}
