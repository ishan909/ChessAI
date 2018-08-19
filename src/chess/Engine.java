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
		/* Use for testing to make sure possible moves gives out correct values
		for(Integer[] a : moves) {
			for(int b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
		*/
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
		ArrayList<Integer[]> possible_moves = possibleMoves();
		int highest = Integer.MIN_VALUE;
		int index = -1;
		// iterate through the arraylist of possible moves and find the best move
		for(int i = 0; i < possible_moves.size(); i++) {
			/* Algorithm:
			 * Evaluate the array at each index of the arraylist using a helper evaluate function that creates a copy of the board and evaluates it
			 * Use the normal max algorithm to find the best scoring one
			 * Return the index of the best one (if tie-breaker, just return 1st --> should be done automatically)
			 */
			
			// Skeleton of Algorithm below
			int current = evaluateBoard(possible_moves.get(i)[0], possible_moves.get(i)[1], possible_moves.get(i)[2], possible_moves.get(i)[3]);
			if (highest <= current) {
				if (highest == current) {
					if (Math.random() < 0.4) {
						highest = current;
						index = i;
					}
				} else {
					highest = current;
					index = i;
				}
			}
		}
		return possible_moves.get(index);
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
	
	
	
	
	
	
	
	
	
	

	
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
//	public Integer[] findBestMove() {
//		// first get all the possible moves at a current level
//		ArrayList<Integer[]> possible = possibleMoves();
//		int highest = -9999;
//		int index = -1;
//		for (int i = 0; i < possible.size(); i++) {
//			Board[] tmp = generate(possible.get(i)[0], possible.get(i)[1], possible.get(i)[2], possible.get(i)[3]);
//			int score = currentBlackScore(tmp[1]);
//			if (highest < score) {
//				index = i;
//			}
//		}
//		return possible.get(index);
//	}
	
//	// convert array of boards to stack of boards if it works, do null checks
//	public Board[] generate(int current_row, int current_col, int new_row, int new_col) {
//		Board[] boards = new Board[2];
//		boards[0] = new Board();
//		boards[1] = new Board();
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				boards[0].matrix[i][j] = game.getBoard().matrix[i][j];
//				boards[1].matrix[i][j] = game.getBoard().matrix[i][j];
//				
//				GamePiece p = game.getBoard().matrix[i][j];
//				if (p == null) {
//					continue;
//				} else if (p instanceof Pawn) {
//					boards[0].matrix[i][j] = new Pawn(i, j, p.color);
//					boards[1].matrix[i][j] = new Pawn(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				} else if (p instanceof Rook) {
//					boards[0].matrix[i][j] = new Rook(i, j, p.color);
//					boards[1].matrix[i][j] = new Rook(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				} else if (p instanceof Knight) {
//					boards[0].matrix[i][j] = new Knight(i, j, p.color);
//					boards[1].matrix[i][j] = new Knight(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				} else if (p instanceof Bishop) {
//					boards[0].matrix[i][j] = new Bishop(i, j, p.color);
//					boards[1].matrix[i][j] = new Bishop(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				} else if (p instanceof Queen) {
//					boards[0].matrix[i][j] = new Queen(i, j, p.color);
//					boards[1].matrix[i][j] = new Queen(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				} else if (p instanceof King) { // instanceof King
//					boards[0].matrix[i][j] = new King(i, j, p.color);
//					boards[1].matrix[i][j] = new King(i, j, p.color);
////					boards[0].matrix[i][j].firstMove = p.firstMove;
////					boards[1].matrix[i][j].firstMove = p.firstMove;
//				}
//			}
//		}
//		for (int i = 0; i < 2; i++) {
//			boards[0].blackKingLocation[i] = game.getBoard().blackKingLocation[i];
//			boards[0].whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
//			boards[1].blackKingLocation[i] = game.getBoard().blackKingLocation[i];
//			boards[1].whiteKingLocation[i] = game.getBoard().whiteKingLocation[i];
//		}
//		GamePiece tmp =	boards[1].getPiece(current_row, current_col); // index 0 is original and 1 is the one we are changing
//		if (tmp != null) {
//			// Does it actually move it on boards[1]?
//			
//			// Before
//			System.out.println("This is before");
//			//printBoard(boards[1]);
//			if (tmp.canMove(new_row, new_col, boards[1])) {
//				System.out.println("This is after");
//				tmp.move(new_row, new_col, boards[1]);
//			//	printBoard(boards[1]);
//				
//			}
//		}
//		
//		return boards;
//	}
	
//	public void printBoard(Board board) {
//		 System.out.println(" - 0- 1- 2- 3- 4- 5- 6- 7-");
//	        System.out.println("---------------------------");
//	        for (int row = 0; row < 8; row++) {
//	            System.out.print("" + row + "|");
//	            for (int col = 0; col < 8; col++) {
//	                if (board.matrix[row][col] == null) {
//	                    System.out.print("  ");
//	                } else {
//	                    GamePiece p = board.matrix[row][col];
//	                    if (p == null) {
//	            			System.out.print("  |");
//	            			continue;
//	                    }
//	                    if (p.getColor()) {
//	                        System.out.print("B");
//	                    } else {
//	                        System.out.print("W");
//	                    }
//	                    if (p instanceof Rook) {
//	                        System.out.print("R");
//	                    } else if (p instanceof Knight) {
//	                        System.out.print("k");
//	                    } else if (p instanceof Bishop) {
//	                        System.out.print("B");
//	                    } else if (p instanceof King) {
//	                        System.out.print("K");
//	                    } else if (p instanceof Queen) {
//	                        System.out.print("Q");
//	                    } else if (p instanceof Pawn) {
//	                        System.out.print("P");
//	                    }
//	                }
//	                System.out.print("|");
//	            }
//	            System.out.println();
//	        }
//	        System.out.println("---------------------------");
//	}
	
//	/* Not needed but here in case we revert back */
//	private int getPoints(Integer[] list) {
//		GamePiece p = game.getBoard().getPiece(list[2], list[3]);
//		if (p == null) {
//			return -1;
//		} else {
//			if (p instanceof Pawn) {
//				return pawnWeight;
//			} else if (p instanceof Knight) {
//				return knightWeight;
//			} else if (p instanceof Bishop) {
//				return bishopWeight;
//			} else if (p instanceof Rook) {
//				return rookWeight;
//			} else if (p instanceof Queen) {
//				return queenWeight;
//			} else {
//				return kingWeight;
//			}
//		}
//	}
}
