/**
 * 
 */
package engine;

import static engine.Pieces.*;

import java.util.ArrayList;


/**
 * @author shakir
 *
 */


public class Board {

	public int[] board;
	public boolean colour;

	public Board() {
		this.board = new int[] { 
				WROOK, WKNIGHT, WBISHOP, WQUEEN, WKING, WBISHOP, WKNIGHT, WROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,   EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				BROOK, BKNIGHT, BBISHOP, BQUEEN, BKING, BBISHOP, BKNIGHT, BROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY 
		};
		this.colour = WHITE;
	}

	public ArrayList<Integer> getKnightMoves(int position) {
		int knight_offsets[] = {33, 31, 18, 14, -33, -31, -18, -14};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {

				// if piece is black
				if (board[position] >= 7 && board[position] <= 12) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
					}
				}

				// if piece is white
				if (board[position] >= 1 && board[position] <= 6) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
					}
				}

				if (board[new_position] == 0) {
					moves.add(new_position);
				}
			}
		}			
		return moves;
	}

	public ArrayList<Integer> getKingMoves(int position) {
		int king_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : king_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {

				// if piece is black
				if (board[position] >= 7 && board[position] <= 12) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
					}
				}

				// if piece is white
				if (board[position] >= 1 && board[position] <= 6) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
					}
				}

				if (board[new_position] == 0) {
					moves.add(new_position);
				}
			}			
		}
		return moves;
	}


	public ArrayList<Integer> getBishopMoves(int position) {
		int bishop_offsets[] = {15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : bishop_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {

				// if piece is black and hits opps piece
				if (board[position] >= 7 && board[position] <= 12) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						break;
					}
				}

				// if piece is white and hits opps piece
				if (board[position] >= 1 && board[position] <= 6) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						break;
					}
				}

				// if empty square
				if (board[new_position] == 0) {
					moves.add(new_position);
				}
				new_position += i;
			}
		}			
		return moves;
	}

	public ArrayList<Integer> getQueenMoves(int position) {
		int queen_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : queen_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				// if piece is black and hits opps piece
				if (board[position] >= 7 && board[position] <= 12) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						break;
					}
				}

				// if piece is white and hits opps piece
				if (board[position] >= 1 && board[position] <= 6) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						break;
					}
				}

				// if empty square
				if (board[new_position] == 0) {
					moves.add(new_position);
				}
				new_position += i;
			}			
		}
		return moves;
	}

	public ArrayList<Integer> getRookMoves(int position) {
		int rook_offsets[] = {16, -16, 1, -1};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : rook_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {

				// if piece is black and hits opps piece or its own piece
				if (board[position] >= 7 && board[position] <= 12) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						break;
					}
				}
				// if piece is white and hits opps piece or its own piece
				if (board[position] >= 1 && board[position] <= 6) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						break;
					}
				}

				if (board[new_position] == 0) {
					moves.add(new_position);
				}
				new_position += i;
			}
		}			
		return moves;
	}

	public ArrayList<Integer> getWhitePawnMoves(int position) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int new_position = position + 16;
		// non capture moves
		if (onBoard(new_position) && board[new_position] == 0) {
			if (new_position >= 0 && new_position <= 7) {
				// handle pawn promotion
				moves.add(new_position);
			}
			else {
				// one square
				moves.add(new_position);
				// two squares
				new_position = position + 32;
				if ((position >= 16 && position <= 23) && board[new_position] == 0) {
					moves.add(new_position);
				}
			}
		}
		//capture moves
		int Wpawn_offsets[] = {15, 17};

		for (int i : Wpawn_offsets) {
			new_position = position + i;
			if (onBoard(new_position)) {
				if (
						(new_position >= 0 && new_position <= 7) &&
						(board[new_position] >= 7 && board[new_position] <= 12)
						) {
					// handle pawn promotion
					moves.add(new_position);
				}
				else {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						moves.add(new_position);
					}

					// add enpassant
				}
			}
		}
		return moves;
	}

	public ArrayList<Integer> getBlackPawnMoves(int position) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int new_position = position - 16;
		// non capture moves
		if (onBoard(new_position) && board[new_position] == 0) {
			if (new_position >= 0 && new_position <= 7) {
				// handle pawn promotion
				moves.add(new_position);
			}
			else {
				// one square
				moves.add(new_position);
				// two squares
				new_position = position - 32;
				if ((position >= 96 && position <= 103) && board[new_position] == 0) {
					moves.add(new_position);
				}
			}
		}
		//capture moves
		int Bpawn_offsets[] = {-15, -17};
		for (int i : Bpawn_offsets) {
			new_position = position + i;
			if (onBoard(new_position)) {
				if (
						(new_position >= 112 && new_position <= 119) &&
						(board[new_position] >= 1 && board[new_position] <= 6)
						) {
					// handle pawn promotion
					moves.add(new_position);
				}
				else {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						moves.add(new_position);
					}
					// add enpassant
				}
			}
		}
		return moves;
	}

	boolean isSquareAttacked(int position) {
		// pawns
		if (this.colour) {
			if (onBoard(position + 15) && (board[position + 15] == 7)) {
				return true;
			}

			if (onBoard(position + 17) && (board[position + 17] == 7)) {
				return true;
			}
		}
		else {
			if (onBoard(position - 15) && (board[position - 15] == 1)) {
				return true;
			}
			if (onBoard(position - 17) && (board[position - 17] == 1)) {
				return true;
			}	
		}
		// king
		int king_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		for (int i : king_offsets) {
			int new_position = position + i;

			if (onBoard(new_position)) {
				int piece = board[new_position];

				if (!this.colour ? piece == 6 : piece == 12) {
					return true;
				}

			}
		}			
		// knight
		int knight_offsets[] = {33, 31, 18, 14, -33, -31, -18, -14};
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {
				int piece = board[new_position];
				
				if(this.colour) {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						continue;
					}
				}
				else {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						continue;
					}
				}
				
				if (this.colour ? piece == 2 : piece == 8) {
					return true;
				}
			}
		}
		// bishop or queen diagonal attacks
		int bishop_offsets[] = {15, 17, -15, -17};
		for (int i : bishop_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = board[new_position];
				
				if (!this.colour ? (piece == 3 || piece == 5) : (piece == 9 || piece == 11)) {
					return true;
				}
				
				if (piece != 0) {
					break;
				}

				new_position += i;
			}
		}			
		// rook or queen up/down attacks
		int rook_offsets[] = {16, -16, 1, -1};
		for (int i : rook_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = board[new_position];
				
				if (!this.colour ? (piece == 4 || piece == 5) : (piece == 10 || piece == 11)) {
					return true;
				}
				
				if(this.colour) {
					if (board[new_position] >= 7 && board[new_position] <= 12) {
						break;
					}
				}
				else {
					if (board[new_position] >= 1 && board[new_position] <= 6) {
						break;
					}
				}

				new_position += i;
			}
		}				
		return false;
	}




	// return if position is on the board
	public static boolean onBoard(int position) {
		return (position & 0x88) == 0;
	}






	public boolean makeMove() {
		//
		return true;
	}


}
