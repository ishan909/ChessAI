package chess;

/* This will be a test class for the eventual AI */

public class ChessEngine {
	public Game game;
	
	public ChessEngine(Game game) {
		this.game = game;
	}
	
	// This method is the AI
	// We will later add the minimax, Alpha-Bata Pruning, and more
	public int[] calculateBestMove() {
		int[] bestMove = {-1, -1, -1, -1}; // {oldRow, oldCol, newRow, newCol}
		Board currentBoard = game.getBoard();
		// Use the currentBoard to determine what the best next move would be for black to make. 
		// Then return the best move by its coordinates.
		return bestMove;
	}
}
