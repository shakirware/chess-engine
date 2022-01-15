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

	public Board(Board board) {
		this.board = board.getBoard();
		this.colour = WHITE;
	}

	public int[] getBoard() {
		return this.board.clone();
	}

	public ArrayList<Integer> getKnightMoves(int position) {
		int knight_offsets[] = {33, 31, 18, 14, -33, -31, -18, -14};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {

				// if piece is black
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
				}

				// if piece is white
				if (this.board[position] >= 1 && this.board[position] <= 6) {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
					}
				}

				if (this.board[new_position] == 0) {
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
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
				}

				// if piece is white
				if (this.board[position] >= 1 && this.board[position] <= 6) {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
					}
				}

				if (this.board[new_position] == 0) {
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
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}

				// if piece is white and hits opps piece
				if (this.board[position] >= 1 && this.board[position] <= 6) {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						break;
					}
				}

				// if empty square
				if (this.board[new_position] == 0) {
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
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}

				// if piece is white and hits opps piece
				if (this.board[position] >= 1 && this.board[position] <= 6) {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						break;
					}
				}

				// if empty square
				if (this.board[new_position] == 0) {
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
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}
				// if piece is white and hits opps piece or its own piece
				if (this.board[position] >= 1 && this.board[position] <= 6) {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						break;
					}
				}

				if (this.board[new_position] == 0) {
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
		if (onBoard(new_position) && this.board[new_position] == 0) {
			if (new_position >= 0 && new_position <= 7) {
				// handle pawn promotion
				moves.add(new_position);
			}
			else {
				// one square
				moves.add(new_position);
				// two squares
				new_position = position + 32;
				if ((position >= 16 && position <= 23) && this.board[new_position] == 0) {
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
						(this.board[new_position] >= 7 && this.board[new_position] <= 12)
						) {
					// handle pawn promotion
					moves.add(new_position);
				}
				else {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
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
		if (onBoard(new_position) && this.board[new_position] == 0) {
			if (new_position >= 0 && new_position <= 7) {
				// handle pawn promotion
				moves.add(new_position);
			}
			else {
				// one square
				moves.add(new_position);
				// two squares
				new_position = position - 32;
				if ((position >= 96 && position <= 103) && this.board[new_position] == 0) {
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
						(this.board[new_position] >= 1 && this.board[new_position] <= 6)
						) {
					// handle pawn promotion
					moves.add(new_position);
				}
				else {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
					// add enpassant
				}
			}
		}
		return moves;
	}


	public boolean isCheck() {
		int king_square = 0;

		for (int square = 0; square < 128; square++) {
			if (onBoard(square)) {
				if (this.colour) {
					if (this.board[square] == 6) {
						king_square = square;
					}
				}
				else {
					if (this.board[square] == 12) {
						king_square = square;
					}
				}
			}
		}
		return isSquareAttacked(king_square);
	}



	public boolean isSquareAttacked(int position) {
		// pawns
		if (this.colour) {
			if (onBoard(position + 15) && (this.board[position + 15] == 7)) {
				return true;
			}

			if (onBoard(position + 17) && (this.board[position + 17] == 7)) {
				return true;
			}
		}
		else {
			if (onBoard(position - 15) && (this.board[position - 15] == 1)) {
				return true;
			}
			if (onBoard(position - 17) && (this.board[position - 17] == 1)) {
				return true;
			}	
		}
		// king
		int king_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		for (int i : king_offsets) {
			int new_position = position + i;

			if (onBoard(new_position)) {
				int piece = this.board[new_position];

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
				int piece = this.board[new_position];

				if(this.colour) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						continue;
					}
				}
				else {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
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
				int piece = this.board[new_position];

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
				int piece = this.board[new_position];

				if (!this.colour ? (piece == 4 || piece == 5) : (piece == 10 || piece == 11)) {
					return true;
				}

				if (piece != 0) {
					break;
				}

				new_position += i;
			}
		}				
		return false;
	}

	// add castling
	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int square = 0; square < 128; square++) {
			if (onBoard(square)) {
				// White
				if (colour) {
					// White Pawn
					if (board[square] == 1) {
						ArrayList<Integer> destinations = this.getWhitePawnMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// White Knight
					if (board[square] == 2) {
						ArrayList<Integer> destinations = this.getKnightMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// White Bishop
					if (board[square] == 3) {
						ArrayList<Integer> destinations = this.getBishopMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// White Rook
					if (board[square] == 4) {
						ArrayList<Integer> destinations = this.getRookMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// White Queen
					if (board[square] == 5) {
						ArrayList<Integer> destinations = this.getQueenMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// White King
					if (board[square] == 6) {
						ArrayList<Integer> destinations = this.getKingMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}

				} 
				// Black
				else {
					// Black Pawn
					if (board[square] == 7) {
						ArrayList<Integer> destinations = this.getBlackPawnMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// Black Knight
					if (board[square] == 8) {
						ArrayList<Integer> destinations = this.getKnightMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// Black Bishop
					if (board[square] == 9) {
						ArrayList<Integer> destinations = this.getBishopMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// Black Rook
					if (board[square] == 10) {
						ArrayList<Integer> destinations = this.getRookMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// Black Queen
					if (board[square] == 11) {
						ArrayList<Integer> destinations = this.getQueenMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}
					// Black King
					if (board[square] == 12) {
						ArrayList<Integer> destinations = this.getKingMoves(square);
						for (int destination : destinations) {
							Move move = new Move(square, destination);
							moves.add(move);
						}
					}

				}


			}

		}
		return moves;
	}


	public ArrayList<Move> getLegalMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int square = 0; square < 128; square++) {
			if (onBoard(square)) {

				// if king in check
				if (isCheck()) {
					// you can only move king
				}
				else {



				}


			}

		}




		return moves;
	}

	public boolean isLegal(Move move) {
		if(this.colour) {
			if (board[move.from] == 6) {
				Board boardCopy = new Board(this);
				boardCopy.makeMove(move);
				return !boardCopy.isCheck();
			}
		}
		else {
			if (board[move.from] == 12) {
				Board boardCopy = new Board(this);
				boardCopy.makeMove(move);
				return !boardCopy.isCheck();
			}
		}
		return false;
	}

	public void makeMove(Move move) {
		this.board[move.to] = this.board[move.from];
		this.board[move.from] = 0;
	}


	// return if position is on the board
	public  boolean onBoard(int position) {
		return (position & 0x88) == 0;
	}




}
