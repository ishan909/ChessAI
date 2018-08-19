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
	 * @return instance of internal game
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * @return Returns an integer array of the best move
	 */
	public Integer[] calculateBestMove() {
		return findBestMove();
	}
	/**
	 * @return Returns the find best possible move
	 */
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
	/**
	 * @return Returns the find best possible move
	 */
	public Integer[] findBestMove() {
		int highest = Integer.MIN_VALUE;
		ArrayList<Integer[]> possible_moves = possibleMoves();
		ArrayList<Integer> highestTies = new ArrayList<Integer>();
		for (int i = 0; i < possible_moves.size(); i++) {
			int current = evaluateBoard(possible_moves.get(i)[0], possible_moves.get(i)[1], possible_moves.get(i)[2], possible_moves.get(i)[3]);
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
	public int evaluateBoard(int current_row, int current_col, int new_row, int new_col) {
		Board res = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				res.matrix[i][j] = game.getBoard().matrix[i][j];
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
			res.blackKingLocation[i] = game.getBoard().blackKingLocation[i];
			res.whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
		}
		GamePiece eval = res.getPiece(current_row, current_col);
		if (eval != null) {
			eval.move(new_row, new_col, res);
			return res.currentBlackScore();
		} else {
			return -1;
		}

	}
}
