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
