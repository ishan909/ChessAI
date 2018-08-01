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
	public int kingWeight = 1000;
	
	public Engine(Game game) {
		this.game = game;
	}
	
	// This method is the AI
	// We will later add the minimax, Alpha-Bata Pruning, and more
	public Integer[] calculateBestMove() { // {oldRow, oldCol, newRow, newCol}
		ArrayList<Integer[]> moves = possibleMoves();
		Integer[] best = moves.get((int) (Math.random() * moves.size()));
		for (int i = 0; i < moves.size(); i++) {
			if (getPoints(moves.get(i)) > getPoints(best)) {
				best = moves.get(i);
			}
		}
		return best;
	}
	
	private ArrayList<Integer[]> possibleMoves() {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		int count = 0;
		outLoop: for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (game.getBoard().getPiece(r, c) != null && game.getBoard().getPiece(r, c).getColor()) {
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
	
	private int getPoints(Integer[] list) {
		GamePiece p = game.getBoard().getPiece(list[2], list[3]);
		if (p == null) {
			return -1;
		} else {
			if (p instanceof Pawn) {
				return pawnWeight;
			} else if (p instanceof Knight) {
				return knightWeight;
			} else if (p instanceof Bishop) {
				return bishopWeight;
			} else if (p instanceof Rook) {
				return rookWeight;
			} else if (p instanceof Queen) {
				return queenWeight;
			} else {
				return kingWeight;
			}
		}
	}
}
