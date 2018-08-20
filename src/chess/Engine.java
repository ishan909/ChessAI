package chess;
import java.util.ArrayList;

public class Engine {

	private Game game;

	/**
	 * Constructor for a new Bishop
	 * @param game - instance of internal game
	 */
	public Engine(Game game) {
		this.game = game;
	}

	/**
	 * @return returns an instance of internal game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @return returns an integer array of the best move
	 */
	public Integer[] calculateBestMove() {
		return findBestMoveMinimaxRecursive(game.getBoard(), 4);
	}

	/**
	 * @return returns the find best possible move
	 */
	private ArrayList<Integer[]> possibleMoves(Board b, boolean color) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (b.getPiece(r, c) != null && b.getPiece(r, c).getColor() == color) {
					for (int newR = 0; newR < 8; newR++) {
						for (int newC = 0; newC < 8; newC++) {
							if (b.getPiece(r, c).canMove(newR, newC, game.getBoard())) {
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
	/**
	 * @param currentBoard
	 * @param recursiveLevel
	 * @return returns the best move 
	 */
	public Integer[] findBestMoveMinimaxRecursive(Board currentBoard, int recursiveLevel) {
		if (recursiveLevel == 0) {
			int highest = Integer.MIN_VALUE;
			ArrayList<Integer[]> possible_moves = possibleMoves(currentBoard, recursiveLevel % 2 == 0 /*black*/);
			ArrayList<Integer> highestTies = new ArrayList<Integer>();
			for (int i = 0; i < possible_moves.size(); i++) {
				int current = evaluateBoard(game.getBoard(), possible_moves.get(i)[0], possible_moves.get(i)[1], possible_moves.get(i)[2], possible_moves.get(i)[3]);
				if (highest <= current) {
					if (highest == current) {
						highestTies.add(i);
					} else {
						highestTies = new ArrayList<Integer>();
						highestTies.add(i);
						highest = current;
					}
				}
			}
			return possible_moves.get(highestTies.get((int) (Math.random() * highestTies.size())));
		} else {
			int highest = Integer.MIN_VALUE;
			ArrayList<Integer> highestTies = new ArrayList<Integer>();
			ArrayList<Integer[]> moves1 = possibleMoves(game.getBoard(), recursiveLevel % 2 == 0 /*black*/);
			for (int i1 = 0; i1 < moves1.size(); i1++) {
				Board res1 = new Board();
				for (int r1 = 0; r1 < 8; r1++) {
					for (int c1 = 0; c1 < 8; c1++) {
						res1.matrix[r1][c1] = game.getBoard().matrix[r1][c1];
						GamePiece p = game.getBoard().matrix[r1][c1];
						if (p == null) {
							continue;
						} else if (p instanceof Pawn) {
							res1.matrix[r1][c1] = new Pawn(r1, c1, p.color);
						} else if (p instanceof Rook) {
							res1.matrix[r1][c1] = new Rook(r1, c1, p.color);
						} else if (p instanceof Knight) {
							res1.matrix[r1][c1] = new Knight(r1, c1, p.color);
						} else if (p instanceof Bishop) {
							res1.matrix[r1][c1] = new Bishop(r1, c1, p.color);
						} else if (p instanceof Queen) {
							res1.matrix[r1][c1] = new Queen(r1, c1, p.color);
						} else if (p instanceof King) {
							res1.matrix[r1][c1] = new King(r1, c1, p.color);
						}
					}
				}
				for (int i = 0; i < 2; i++) {
					res1.blackKingLocation[i] = game.getBoard().blackKingLocation[i];
					res1.whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
				}
				// need to make the move
				GamePiece eval1 = res1.getPiece(moves1.get(i1)[0], moves1.get(i1)[1]);
				if (eval1 != null) {
					eval1.move(moves1.get(i1)[2], moves1.get(i1)[3], res1);
					res1.matrix[moves1.get(i1)[0]][moves1.get(i1)[1]] = null;
				}

				Integer[] temp = findBestMoveMinimaxRecursive(res1, recursiveLevel - 1);
				int current = evaluateBoard(res1, temp[0], temp[1], temp[2], temp[3]);
				if (current >= highest) {
					if (current == highest) {
						highestTies.add(i1);
					} else {
						highestTies = new ArrayList<Integer>();
						highestTies.add(i1);
						highest = current;
					}
				}
			}
			return moves1.get(highestTies.get((int) (Math.random() * highestTies.size())));
		}
	}
	/**
	 * @return returns the best 1 move (Greedy Optimization)
	 */
	public Integer[] findBestMoveMinimax() {
		int highest = Integer.MIN_VALUE;
		ArrayList<Integer> highestTies = new ArrayList<Integer>();
		ArrayList<Integer[]> moves1 = possibleMoves(game.getBoard(), true);
		for (int i1 = 0; i1 < moves1.size(); i1++) {
			Board res1 = new Board();
			for (int r1 = 0; r1 < 8; r1++) {
				for (int c1 = 0; c1 < 8; c1++) {
					res1.matrix[r1][c1] = game.getBoard().matrix[r1][c1];
					GamePiece p = game.getBoard().matrix[r1][c1];
					if (p == null) {
						continue;
					} else if (p instanceof Pawn) {
						res1.matrix[r1][c1] = new Pawn(r1, c1, p.color);
					} else if (p instanceof Rook) {
						res1.matrix[r1][c1] = new Rook(r1, c1, p.color);
					} else if (p instanceof Knight) {
						res1.matrix[r1][c1] = new Knight(r1, c1, p.color);
					} else if (p instanceof Bishop) {
						res1.matrix[r1][c1] = new Bishop(r1, c1, p.color);
					} else if (p instanceof Queen) {
						res1.matrix[r1][c1] = new Queen(r1, c1, p.color);
					} else if (p instanceof King) {
						res1.matrix[r1][c1] = new King(r1, c1, p.color);
					}
				}
			}
			for (int i = 0; i < 2; i++) {
				res1.blackKingLocation[i] = game.getBoard().blackKingLocation[i];
				res1.whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
			}
			GamePiece eval1 = res1.getPiece(moves1.get(i1)[0], moves1.get(i1)[1]);
			if (eval1 != null) {
				eval1.move(moves1.get(i1)[2], moves1.get(i1)[3], res1);
				res1.matrix[moves1.get(i1)[0]][moves1.get(i1)[1]] = null;
			}

			int current = res1.currentBoardScore();
			if (highest <= current) {
				if (highest == current) {
					highestTies.add(i1);
				} else {
					highestTies = new ArrayList<Integer>();
					highestTies.add(i1);
					highest = current;
				}
			}
		}
		return moves1.get(highestTies.get((int) (Math.random() * highestTies.size())));
	}

	/**
	 * @return returns the find best possible move
	 */
	public Integer[] findBestMove() {
		int highest = Integer.MIN_VALUE;
		ArrayList<Integer[]> possible_moves = possibleMoves(game.getBoard(), true /*black*/);
		ArrayList<Integer> highestTies = new ArrayList<Integer>();
		for (int i = 0; i < possible_moves.size(); i++) {
			int current = evaluateBoard(game.getBoard(), possible_moves.get(i)[0], possible_moves.get(i)[1], possible_moves.get(i)[2], possible_moves.get(i)[3]);
			if (highest <= current) {
				if (highest == current) {
					highestTies.add(i);
				} else {
					highestTies = new ArrayList<Integer>();
					highestTies.add(i);
					highest = current;
				}
			}
		}
		return possible_moves.get(highestTies.get((int) (Math.random() * highestTies.size())));
	}

	/**
	 * evaluateBoard
	 * @param current_row
	 * @param current_col
	 * @param new_row
	 * @param new_col
	 * @return returns the current evaluation of the board for black in terms of a quantitative value
	 */
	public int evaluateBoard(Board b, int current_row, int current_col, int new_row, int new_col) {
		Board res = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				res.matrix[i][j] = b.matrix[i][j];
				GamePiece p = game.getBoard().matrix[i][j];
				if (p == null) {
					continue;
				} else if (p instanceof Pawn) {
					res.matrix[i][j] = new Pawn(i, j, p.color);
				} else if (p instanceof Rook) {
					res.matrix[i][j] = new Rook(i, j, p.color);
				} else if (p instanceof Knight) {
					res.matrix[i][j] = new Knight(i, j, p.color);
				} else if (p instanceof Bishop) {
					res.matrix[i][j] = new Bishop(i, j, p.color);
				} else if (p instanceof Queen) {
					res.matrix[i][j] = new Queen(i, j, p.color);
				} else if (p instanceof King) {
					res.matrix[i][j] = new King(i, j, p.color);
				}
			}
		}
		for (int i = 0; i < 2; i++) {
			res.blackKingLocation[i] = b.blackKingLocation[i];
			res.whiteKingLocation[i] = b.whiteKingLocation[i];
		}
		GamePiece eval = res.getPiece(current_row, current_col);
		if (eval != null) {
			eval.move(new_row, new_col, res);
			res.matrix[current_row][current_col] = null;
			return res.currentBoardScore();
		} else {
			return Integer.MIN_VALUE;
		}

	}
}
