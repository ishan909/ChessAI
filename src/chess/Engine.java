package chess;

import java.util.ArrayList;

/* This will be a test class for the eventual AI */

public class Engine {
	public Game game;
	public int pawnWeight = 5;
	public int knightWeight = 20;
	public int bishopWeight = 20;
	public int rookWeight = 50;
	public int queenWeight = 150;
	public int kingWight = 1000;
	
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
	
	ArrayList<Integer[]> possibleMoves() {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		int count = 0;
		outLoop: for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (game.getBoard().getPiece(r, c).getColor()) {
					for (int newR = 0; newR < 8; newR++) {
						for (int newC = 0; newC < 8; newC++) {
							if (game.getBoard().getPiece(r, c).canMove(newR, newC, game.getBoard())) {
								Integer[] move = {r, c, newR, newC};
								moves.add(move);
								count++;
								if (count == 16) {
									break outLoop;
								}
							}
						}
					}
				}
			}
		}
		return moves;
	}
}
