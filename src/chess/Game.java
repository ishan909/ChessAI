package chess;

import java.util.*; // get scanner

public class Game {
    private int moveCount;

    public Game() {
        Board board = new Board();
        playGame(board);
    }

	public void playGame(Board board) {
        Scanner input = new Scanner(System.in);
        // Initialize move counter
        moveCount = 0;

        while (true) { // true for the black player
            if (!board.checkmate(true)) {
                board.printBoard();
                System.out.println("It is black's turn.");
                if (board.check(true)) { // true for the black player
                    System.out.println("Black, you are in check");
                }
                System.out.print("Black, which piece would you like to move? Enter horizonal first, then vertical: ");

                // TODO: make sure input is valid -- must make sure there is a piece there,
                //      that piece is that player's piece, and the string is formatted correctly
                int currentHorizontal = input.nextInt();
                int currentVertical = input.nextInt();
                System.out.print("Black, where would you like to move this piece? Enter horizonal first, then vertical: ");
                int new_horizontal = input.nextInt();
                int new_vertical = input.nextInt();

                // TODO: figure out how to work with bad input (throw exceptions later)
                // TODO: we need to re-add that "|| board.check(true)" condition, but we also need to update the check after each input
                while (!board.movePiece(currentHorizontal, currentVertical, new_horizontal, new_vertical, moveCount) /*|| board.check(true)*/) {
                    System.out.println("Invalid Move. ");
                    System.out.print("Black, which piece would you like to move? Enter horizonal first, then vertical: ");
                    currentHorizontal = input.nextInt();
                    currentVertical = input.nextInt();
                    System.out.print("Black, where would you like to move this piece? Enter horizonal first, then vertical: ");
                    new_horizontal = input.nextInt();
                    new_vertical = input.nextInt();
                }
                System.out.println("Black, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
            if (!board.checkmate(false)) { // false for the red player
                board.printBoard();
                System.out.println("It is red's turn.");
                if (board.check(false)) { // false for the red player
                    System.out.println("Red, you are in check");
                }
                System.out.print("Red, which piece would you like to move? Enter horizonal first, then vertical: ");
                int currentHorizontal = input.nextInt();
                int currentVertical = input.nextInt();
                System.out.print("Red, where would you like to move this piece? Enter horizonal first, then vertical: ");
                int new_horizontal = input.nextInt();
                int new_vertical = input.nextInt();
                
                // TODO: figure out how to work with bad input (throw exceptions later)
                // TODO: we need to re-add that "|| board.check(true)" condition, but we also need to update the check after each input
                while (!board.movePiece(currentHorizontal, currentVertical, new_horizontal, new_vertical, moveCount) /*|| board.check(true)*/) {
                    System.out.println("Invalid Move. ");
                    System.out.print("Red, which piece would you like to move? Enter horizonal first, then vertical: ");
                    currentHorizontal = input.nextInt();
                    currentVertical = input.nextInt();
                    System.out.print("Red, where would you like to move this piece? Enter horizonal first, then vertical: ");
                    new_horizontal = input.nextInt();
                    new_vertical = input.nextInt();
                }
                System.out.println("Red, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
        }
        System.out.println("Game Over! Player " + ((moveCount + 1) % 2) + " won!");
        input.close();
    }
}
