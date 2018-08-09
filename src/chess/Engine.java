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
	
	// first store the state of the current board
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
		// first get all the possible moves at a current level
		ArrayList<Integer[]> possible = possibleMoves();
		int highest = -9999;
		int index = -1;
		for (int i = 0; i < possible.size(); i++) {
			Board[] tmp = reverse(possible.get(i)[0], possible.get(i)[1], possible.get(i)[2], possible.get(i)[3]);
			int score = currentBlackScore(tmp[1]);
			if (highest < score) {
				index = i;
			}
		}
		return possible.get(index);
	}
	
	// convert array of boards to stack of boards if it works, do null checks
	public Board[] reverse(int current_row, int current_col, int new_row, int new_col) {
		Board[] boards = new Board[2];
		boards[0] = new Board();
		boards[1] = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boards[0].matrix[i][j] = game.getBoard().matrix[i][j];
				boards[1].matrix[i][j] = game.getBoard().matrix[i][j];
				
				GamePiece p = game.getBoard().matrix[i][j];
				if (p == null) {
					continue;
				} else if (p instanceof Pawn) {
					boards[0].matrix[i][j] = new Pawn(i, j, p.color);
					boards[1].matrix[i][j] = new Pawn(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				} else if (p instanceof Rook) {
					boards[0].matrix[i][j] = new Rook(i, j, p.color);
					boards[1].matrix[i][j] = new Rook(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				} else if (p instanceof Knight) {
					boards[0].matrix[i][j] = new Knight(i, j, p.color);
					boards[1].matrix[i][j] = new Knight(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				} else if (p instanceof Bishop) {
					boards[0].matrix[i][j] = new Bishop(i, j, p.color);
					boards[1].matrix[i][j] = new Bishop(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				} else if (p instanceof Queen) {
					boards[0].matrix[i][j] = new Queen(i, j, p.color);
					boards[1].matrix[i][j] = new Queen(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				} else if (p instanceof King) { // instanceof King
					boards[0].matrix[i][j] = new King(i, j, p.color);
					boards[1].matrix[i][j] = new King(i, j, p.color);
					boards[0].matrix[i][j].firstMove = p.firstMove;
					boards[1].matrix[i][j].firstMove = p.firstMove;
				}
			}
		}
		for (int i = 0; i < 2; i++) {
			boards[0].blackKingLocation[i] = game.getBoard().blackKingLocation[i];
			boards[0].whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
			boards[1].blackKingLocation[i] = game.getBoard().blackKingLocation[i];
			boards[1].whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
		}
		
		GamePiece tmp =	boards[1].getPiece(current_row, current_col); // index 0 is original and 1 is the one we are changing
		if (tmp != null) {
			if (tmp.canMove(new_row, new_col, boards[1])) {
				tmp.move(new_row, new_col, boards[1]);
			}
		}
		
		return boards;
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
