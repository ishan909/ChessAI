package chess;

import java.util.Scanner;

public class Game {
	// keeps count of how many successful moves have been made
    private int moveCount;
    
    /**
     * Constructor for a new Game
     */
    public Game() {
        Board board = new Board();
        ChessGraphics gui = new ChessGraphics(board);
        playGame(board, gui);
    }
    
    /**
     * User interface for playing game
     * @param board - an instance of the chess board
     * @param gui - an instance of the Chess Graphics
     */
  	public void playGame(Board board, ChessGraphics gui) {
        Scanner input = new Scanner(System.in);
        moveCount = 0; // Initialize move counter
        gui.update(board);

        while (true) { // true for the black player
        	board.printBoard();
            if (!board.checkmate(true)) {
                System.out.println("It is black's turn.");
                if (board.check(true)) { // true for the black player
                    System.out.println("Black, you are in check");
                }

                boolean first_move = true;

                int current_col, current_row, new_col, new_row;
                do {
	                if (!first_move) {
	                		System.out.println("Invalid Move.");
	                }
                    System.out.print("Black, which piece would you like to move?\nEnter row number: ");
                    current_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    current_col = Integer.parseInt(input.nextLine());
                    System.out.print("Black, where would you like to move this piece?\nEnter row number: ");
                    new_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    new_col = Integer.parseInt(input.nextLine());
                    first_move = false;
                } while (!board.movePiece(current_row, current_col, new_row, new_col, moveCount) || board.check(true));
                if (new_row == 7 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
            		board.setPawnToPiece(new_row, new_col, input);
                }
                gui.update(board);
                System.out.println("Black, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
            board.printBoard();
            if (!board.checkmate(false)) { // false for the white player
                System.out.println("It is white's turn.");
                if (board.check(false)) { // false for the white player
                    System.out.println("White, you are in check");
                }

                boolean first_move = true;
                int current_col, current_row, new_col, new_row;
                do {
	                if (!first_move) {
	                		System.out.println("Invalid Move.");
	                }
	                System.out.print("White, which piece would you like to move?\nEnter row number: ");
                    current_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    current_col = Integer.parseInt(input.nextLine());
                    System.out.print("White, where would you like to move this piece?\nEnter row number: ");
                    new_row = Integer.parseInt(input.nextLine());
                    System.out.print("Enter col number: ");
                    new_col = Integer.parseInt(input.nextLine());
                    first_move = false;
                } while (!board.movePiece(current_row, current_col, new_row, new_col, moveCount) || board.check(false));
                if (new_row == 0 && board.getPiece(new_row, new_col) != null && board.getPiece(new_row, new_col) instanceof Pawn) {
        			board.setPawnToPiece(new_row, new_col, input);
                }
                gui.update(board);
                System.out.println("White, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
        }

        System.out.println("Game Over! " + ((moveCount + 1) % 2 == 0 ? "Black" : "White") + " won!");
        input.close();
    }
}
