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

                // TODO: make sure input is valid -- must make sure there is a piece there,
                //      that piece is that player's piece, and the string is formatted correctly
                System.out.print("Black, which piece would you like to move?\nEnter horizontal: ");
                int currentHorizontal = input.nextInt();
                System.out.print("Enter vertical: ");
                int currentVertical = input.nextInt();
                System.out.print("Black, where would you like to move this piece?\nEnter horizontal: ");
                int newHorizontal = input.nextInt();
                System.out.print("Enter vertical: ");
                int newVertical = input.nextInt();

                // TODO: figure out how to work with bad input (throw exceptions later)
                // TODO: we need to re-add that "|| board.check(true)" condition, but we also need to update the check after each input
                while (!board.movePiece(currentHorizontal, currentVertical, newHorizontal, newVertical, moveCount) /*|| board.check(true)*/) {
                    System.out.println("Invalid Move.");
                    System.out.print("Black, which piece would you like to move?\nEnter horizontal: ");
                    currentHorizontal = input.nextInt();
                    System.out.print("Enter vertical: ");
                    currentVertical = input.nextInt();
                    System.out.print("Black, where would you like to move this piece?\nEnter horizontal: ");
                    newHorizontal = input.nextInt();
                    System.out.print("Enter vertical: ");
                    newVertical = input.nextInt();
                }
                System.out.println("Black, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
            if (!board.checkmate(false)) { // false for the red player
                board.printBoard();
                System.out.println("It is white turn.");
                if (board.check(false)) { // false for the red player
                    System.out.println("White, you are in check");
                }
                System.out.print("White, which piece would you like to move?\nEnter horizontal: ");
                int currentHorizontal = input.nextInt();
                System.out.print("Enter vertical: ");
                int currentVertical = input.nextInt();
                System.out.print("White, where would you like to move this piece?\nEnter horizontal: ");
                int newHorizontal = input.nextInt();
                System.out.print("Enter vertical: ");
                int newVertical = input.nextInt();
                
                // TODO: figure out how to work with bad input (throw exceptions later)
                // TODO: we need to re-add that "|| board.check(false)" condition, but we also need to update the check after each input
                while (!board.movePiece(currentHorizontal, currentVertical, newHorizontal, newVertical, moveCount) /*|| board.check(false)*/) {
                    System.out.println("Invalid Move.");
                    System.out.print("White, which piece would you like to move?\nEnter horizontal: ");
                    currentHorizontal = input.nextInt();
                    System.out.print("Enter vertical: ");
                    currentVertical = input.nextInt();
                    System.out.print("White, where would you like to move this piece?\nEnter horizontal: ");
                    newHorizontal = input.nextInt();
                    System.out.print("Enter vertical: ");
                    newVertical = input.nextInt();
                }
                System.out.println("White, your piece has been moved.");
                moveCount += 1;
            } else {
                break;
            }
        }
        System.out.println("Game Over! Player " + (((moveCount + 1) % 2) + 1) + " won!");
        input.close();
    }
}
