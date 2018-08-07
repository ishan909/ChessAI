package chess;

import java.util.ArrayList;

/* This will be a test class for the eventual AI */

public class Engine {
	private Game game;
	private int pawnWeight = 5;
	private int knightWeight = 20;
	private int bishopWeight = 20;
	private int rookWeight = 50;
	private int queenWeight = 150;
	private int kingWeight = 1000;
	
	
	public Engine(Game game) {
		this.game = game;
	}
	
	// This method is the AI, 
	// We will later add the minimax, Alpha-Bata Pruning, and more
	public Integer[] calculateBestMove() { // {oldRow, oldCol, newRow, newCol}
		return findBestMove();
//		ArrayList<Integer[]> moves = possibleMoves();
//		Integer[] best = moves.get((int) (Math.random() * moves.size()));
//		for (int i = 0; i < moves.size(); i++) {
//			if (getPoints(moves.get(i)) > getPoints(best)) {
//				best = moves.get(i);
//			}
//		}
//		return best;
	}
	
	public int currentBoardScore(Board b) {
		return currentBlackScore(b) + currentWhiteScore(b);
	}
	
	public int currentWhiteScore(Board b) {
		int current_score = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				GamePiece tmp = b.getPiece(i, j);
				if (tmp!= null) {
					if (!tmp.getColor()) {
						if (tmp instanceof King) {
							current_score -= kingWeight;
						} else if (tmp instanceof Queen) {
							current_score -= queenWeight;
						} else if (tmp instanceof Rook) {
							current_score -= rookWeight;
						} else if (tmp instanceof Bishop) {
							current_score -= bishopWeight;
						} else if (tmp instanceof Knight) {
							current_score -= knightWeight;
						} else {
							current_score -= pawnWeight;
						}
					}
				}
			}
		}
		return current_score;
	}
	
	public int currentBlackScore(Board b) {
		int current_score = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				GamePiece tmp = b.getPiece(i, j);
				if (tmp!= null) {
					if (tmp.getColor()) {
						if (tmp instanceof King) {
							current_score += kingWeight;
						} else if (tmp instanceof Queen) {
							current_score += queenWeight;
						} else if (tmp instanceof Rook) {
							current_score += rookWeight;
						} else if (tmp instanceof Bishop) {
							current_score += bishopWeight;
						} else if (tmp instanceof Knight) {
							current_score += knightWeight;
						} else {
							current_score += pawnWeight;
						}
					}
				}
			}
		}
		return current_score;
	}
	
	private ArrayList<Integer[]> possibleMoves() {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (game.getBoard().getPiece(r, c) != null && game.getBoard().getPiece(r, c).getColor()) {
					for (int newR = 0; newR < 8; newR++) {
						for (int newC = 0; newC < 8; newC++) {
							if (game.getBoard().getPiece(r, c).canMove(newR, newC, game.getBoard())) {
								Integer[] move = {r, c, newR, newC};
								moves.add(move);
							}
						}
					}
				}
			}
		}
		return moves;
	}
	
	public Integer[] findBestMove() {
		System.out.println("Called");
		Integer[] move = possibleMoves().get(0);
		int maxBoardScore = 0;
//		for (int i = 0; i < 1; i++) { // looking one move ahead
			ArrayList<Integer[]> movesList = possibleMoves();
			for (int j = 0; j < movesList.size(); j++) {
				System.out.println("Hello " + j);
				Board tempBoard = new Board();
				// deep copy
				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						tempBoard.matrix[r][c] = game.getBoard().getPiece(r, c);
					}
				}
				for (int n = 0; n < 2; n++) {
					tempBoard.whiteKingLocation[n] = game.getBoard().whiteKingLocation[n];
					tempBoard.blackKingLocation[n] = game.getBoard().blackKingLocation[n];
				}
				
				tempBoard.movePiece(movesList.get(j)[0], movesList.get(j)[1], movesList.get(j)[2], movesList.get(j)[3]);
				if (currentBlackScore(tempBoard) > maxBoardScore) {
					maxBoardScore = currentBlackScore(tempBoard);
					move = movesList.get(j);
				}
				
			}
//		}
		for (int i = 0; i < 4; i++) {
			System.out.print(move[i] + " ");
		}
		System.out.println();
		game.getBoard().printBoard();
		
//		if (game.getBoard().getPiece(move[2], move[3]) != null) {
//			System.out.println(game.getBoard().getPiece(move[2], move[3]).getColor());
//		}
		return move;
	}
	
	/* TODO: Write a save board/undo function for later */
}
