package chess;

import java.util.ArrayList;

/* This will be a test class for the eventual AI */

public class Engine {
	private Game game;
	
	public Engine(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	// This method is the AI, 
	// We will later add the minimax, Alpha-Bata Pruning, and more
	public Integer[] calculateBestMove() { // {oldRow, oldCol, newRow, newCol}
		return findBestMove();
	}
	
	/* Generate all the possible moves */
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
	
	//  Continue onto findBestMove(), assuming possibleMoves() has correctly generated all the possible moves
	
	/* AI still has problem recognizing the checks/check-mate (This has to be fixed first or it will be impossible).
	 * Will continue to write the function slowly
	 */
	
	// So if a specific index is chosen, the move will repeat BUT the pieces other than the pawn will move (which is good but not what we want)
	// With previously written best move, the pawn on the right hand side for black would always move and the moves were predictable. 
	
	// This function will have to calculate the best move (hopefully based on 1 move forward)
	// In order to repeat for more moves, we need to apply or create our own recursion stack or iteration of some sort
	public Integer[] findBestMove() {
		int highest = Integer.MIN_VALUE;
		ArrayList<Integer[]> possible_moves = possibleMoves();
		ArrayList<Integer> highestTies = new ArrayList<Integer>();
		// iterate through the ArrayList of possible moves and find the best move
		for (int i = 0; i < possible_moves.size(); i++) {
			/* Algorithm:
			 * Evaluate the array at each index of the ArrayList using a helper evaluate function that creates a copy of the board and evaluates it
			 * Use the normal max algorithm to find the best scoring one
			 * Return the index of the best one (if tie-breaker, just return 1st --> should be done automatically)
			 */
			
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
	
	/* Big Idea
	 * The idea is that if the evaluate board works correctly for one move, the next move is to be able to recursively be able to call it onto some sort of stack
	 * or to just be able to simply pass in the inputs of the best move more than once, OR be able to call the function again once we determined what the first best moves
	 * are, or be able to call possible moves or something after to make the AI better.
	 */
	
	// Use the current_black score as what you return and will process the board
	/**
	 * evaluateBoard
	 * @param current_row
	 * @param current_col
	 * @param new_row
	 * @param new_col
	 * @return returns the current evaluation of the board for black in terms of a quantitative value
	 */
	public int evaluateBoard(int current_row, int current_col, int new_row, int new_col) {
		/* Algorithm: 
		 * Instantiate a board that will have the CURRENT state (internal)
		 * Perform the move on the board
		 * Return black score
		 * When function is called again, it will be able to score a different move and this will help simplify the code 
		 */
		
		 // Screw it, deep copy for just 1 board every time , Have to reset the board or clean the board pieces
		
		 // WE HAVE to access the internal definition of the board everytime
		
		// we can also make the board update or a helper function that returns a deep copy of the board to make the code cleaner and easier to debug
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
