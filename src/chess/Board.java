// This class will contain what is at each location of the board

public class Board {

    Cell[][] matrix = new Cell[8][8];

    public Board(){
      initializeBoard();
    }

    public void initializeBoard(){
      // initialization of Chess Board -- (w/ Colors -- set as different colors than red/black)
      for (int r = 0; r < 8; r++) {
          for (int c = 0; c < 8; c++) {
            int colorChoice = r*8 + c;
            if(colorChoice%2 == 0){
              matrix[r][c] = new Cell(r,c,false,new Piece(r,c),false);
            }
            else{
              matrix[r][c] = new Cell(r,c,false,new Piece(r,c),true);
            }
          }
      }
      // // only the pawns first
      // for (int r = 0; r < 8; r++) {
      //     for (int c = 0; c < 8; c++) {
      //       if(r == 1){
      //         // true color == black
      //         matrix[r][c].setPiece(new Pawn(r,c,true));
      //       }
      //       if(r == 6){
      //         matrix[r][c].setPiece(new Pawn(r,c,false));
      //       }
      //     }
      // }
      for(int c = 0; c < 8; c++){
        matrix[1][c].setPiece(new Pawn(1,c,true));
      }
      for(int c = 0; c < 8; c++){
        matrix[6][c].setPiece(new Pawn(6,c,false));
      }

      // hard code the pieces

      // all the rooks
      matrix[0][0].setPiece(new Rook(0,0,true));
      matrix[0][7].setPiece(new Rook(0,7,true));
      // other half
      matrix[7][0].setPiece(new Rook(7,0,false));
      matrix[7][7].setPiece(new Rook(7,7,false));

      // knights
      matrix[1][0].setPiece(new Knight(1,0,true));
      matrix[6][0].setPiece(new Knight(6,0,true));
      // other half
      matrix[1][7].setPiece(new Knight(1,7,false));
      matrix[6][7].setPiece(new Knight(6,7,false));

      // bishops
      matrix[2][0].setPiece(new Bishop(2,0,true));
      matrix[5][0].setPiece(new Bishop(5,0,true));
      // other half
      matrix[2][7].setPiece(new Bishop(2,7,false));
      matrix[5][7].setPiece(new Knight(1,7,false));

      //Queens
      matrix[3][0].setPiece(new Queen(3,0,true));
      matrix[3][7].setPiece(new Queen(3,7,false));

      // Kings
      matrix[4][0].setPiece(new King(4,0,true));
      matrix[4][7].setPiece(new King(4,7,false));



    }



}
