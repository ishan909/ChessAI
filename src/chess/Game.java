
public class Game {
    // TODO:
    // will accept inputs
    // will keep track of whose turn it is
    // will check if moves are valid
    //      as in making sure a peice is moving to an open spot
    //      or making sure it doesn't move to the same location it
    //      already is in
    // will determine if player is in check or checkmate
    // will determine if game has been won

    // TODO: move counter: can tell us whose turn it is (even or odd). Implement later as needed
    // TODO: if a king is in check, that player's next move must remove them from check
    // ----> lead into making a moves method

    private int moveCount;

    public Game() {
        Board board = new Board();
        player = false;
        playGame(board);
    }

    public playGame(Board board) {
        Scanner input = new Scanner(System.in);
        while (true) { // true for the black player
            if (!board.checkmate()) {
                System.out.println("It is black's turn.");
                if (board.check(true)) { // true for the black player
                    System.out.println("Black, you are in check");
                }
                System.out.print("Black, which piece would you like to move? ");
                // TODO: make sure input is valid -- must make sure there is a piece there,
                // that piece is that player's piece, and the string is formatted correctly (letter + num)
                String move_from = input.nextLine(); // assume they will enter letter first then number (fix later)
                System.out.print("Black, where would you like to move this piece? ");
                String move_to = input.nextLine();
                // TODO: Update the board with the user's move (added to the board class)
                // keep in mind that this returns a boolean to tell you if the move was successful
                System.out.println("Black, your peice has been moved");
            }
            if (!board.checkmate(false)) { // false for the red player
                System.out.println("It is red's turn.");
                if (board.check(false)) { // false for the red player
                    System.out.println("Red, you are in check");
                }
                System.out.print("Red, which piece would you like to move? ");
                // TODO: make sure input is valid -- must make sure there is a piece there,
                // that piece is that player's piece, and the string is formatted correctly (letter + num)
                String move_from = input.nextLine(); // assume they will enter letter first then number (fix later)
                System.out.print("Red, where would you like to move this piece? ");
                String move_to = input.nextLine();
                // TODO: Update the board with the user's move (added to the board class)
                // keep in mind that this returns a boolean to tell you if the move was successful
                System.out.println("Red, your peice has been moved");
            }
        }
    }
    
    public int turn() {
        moveCount = 1;
    }

}
