package chess;

/* This will be a test class for the eventual AI */

public class Engine {
	public Game game;
	
	public Engine(Game game) {
		this.game = game;
	}
	
	// This method is the AI
	// We will later add the minimax, Alpha-Bata Pruning, and more
	public int[] calculateBestMove() {
		int[] bestMove = {-1, -1, -1, -1}; // {oldRow, oldCol, newRow, newCol}
		Board currentBoard = game.getBoard();
		// Use the currentBoard to determine what the best next move would be for black to make. 
		// Then return the best move by its coordinates.
		
		
		// TODO
		/*
		 * 1. loop through the board and find every black piece
		 * 2. for each black piece that was found, loop through the board again and see if it can move to every location
		 * 	  - if it can move to that location add it to an List of valid moves which also contains the points gained for that move
		 * 3. have the AI make the move that results in the most points gained
		 * 	  - if there is a tie for most points gained, randomly select which move should be made
		 */
		
		do {
			int randOldRow = (int) (Math.random() * 8);
			int randOldCol = (int) (Math.random() * 8);
			GamePiece current = currentBoard.getPiece(randOldRow, randOldCol);
			if (current != null && current.getColor()) {
				int randNewRow = (int) (Math.random() * 8);
				int randNewCol = (int) (Math.random() * 8);
				if (current.canMove(randNewRow, randNewCol, currentBoard)) {
					bestMove[0] = randOldRow;
					bestMove[1] = randOldCol;
					bestMove[2] = randNewRow;
					bestMove[3] = randNewCol;
					break;
				}
			}
		} while (true);
		
		return bestMove;
	}
}
