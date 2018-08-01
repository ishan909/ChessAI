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
	
	private ArrayList<Integer[]> possibleMoves(GamePiece g) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		int r = g.getRow();
		int c = g.getCol();
		if (g != null && g.getColor()) {
			for (int newR = 0; newR < 8; newR++) {
				for (int newC = 0; newC < 8; newC++) {
					if (g.canMove(newR, newC, game.getBoard())) {
						Integer[] move = {r, c, newR, newC};
						moves.add(move);
					}
				}
			}
		}
		return moves;
	}
	
	public int[] minimax(int depth, int current_x, int current_y ) {
		int [] result = {current_x, current_y, -1, -1};
		if(depth == 0) {
			return result;
		}
		int bestX = -10000;
		int bestY = -10000;
		return result;
		
	}
//	public static void saveBoard(Game game) {
//		
//	}
//	
	
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
