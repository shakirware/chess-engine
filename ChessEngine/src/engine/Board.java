/**
 * 
 */
package engine;

import static engine.Pieces.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a chess board and it's functionality.
 * @author Shakir
 */

public class Board {
	/**
	 * A 0x88 integer array representing pieces on a chess board.
	 */
	public int[] board;
	/**
	 * The colour of the current player. true : white false : black
	 */
	public boolean colour;
	/**
	 * The turn of the current player. true : white false : black
	 */
	public boolean turn;
	/**
	 * The position on the board where the white king resides.
	 */
	public int king_square_white = 4;
	/**
	 * The position on the board where the black king resides.
	 */
	public int king_square_black = 116;
	/**
	 * The last move performed.
	 */
	public Move lastMove;
	/**
	 * The last piece that was captured.
	 */
	public int lastMovetook;
	/**
	 * Castling rights for White
	 */
	public boolean WHITE_CASTLING = false;
	/**
	 * Castling rights for Black
	 */
	public boolean BLACK_CASTLING = false;
	
	public boolean WHITE_CASTLING_SHORT = false;
	public boolean WHITE_CASTLING_LONG = false;
	public boolean BLACK_CASTLING_SHORT = false;
	public boolean BLACK_CASTLING_LONG = false;
	
	/**
	 * Current enpassant move
	 */
	public Move enpassant = null;
	
	public String FenEnPassant = null;
	
	/**
	 * The constructor for class Board.
	 * 
	 * Initialises the board to the chess starting position and sets the 
	 * current player colour to white. Sets the castling rights to 
	 * true as the game starts with the default board.
	 */
	public Board() {
		this.board = new int[] {WROOK, WKNIGHT, WBISHOP, WQUEEN, WKING, WBISHOP, WKNIGHT, WROOK, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, WPAWN, WPAWN, WPAWN, WPAWN, WPAWN, WPAWN, WPAWN, WPAWN, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
				EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN,
				BPAWN, BPAWN, BPAWN, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BROOK, BKNIGHT, BBISHOP,
				BQUEEN, BKING, BBISHOP, BKNIGHT, BROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY };
		this.colour = WHITE;
		this.BLACK_CASTLING = true;
		this.WHITE_CASTLING = true;
		this.WHITE_CASTLING_SHORT = true;
		this.WHITE_CASTLING_LONG = true;
		this.BLACK_CASTLING_SHORT = true;
		this.BLACK_CASTLING_LONG = true;
	}

	/**
	 * Another constructor for class Board.
	 * 
	 * Initialises the board to the positions represented by then FEN string. 
	 * 
	 * @param fen forsyth-edwards notation string
	 */
	public Board(String fen) {
		this.board = new Fen().parseFenString(this, fen);
		this.getKingSquares();
		this.colour = WHITE;
	}

	/**
	 * Another constructor for class Board.
	 * 
	 * This constructor is used to perform a deep copy of a board class. The array
	 * and other variables are copied from the previous object.
	 * 
	 * @param board a Board object
	 */
	public Board(Board board) {
		this.board = Arrays.copyOf(board.board, board.board.length);
		this.colour = board.colour;
		this.king_square_white = board.king_square_white;
		this.king_square_black = board.king_square_black;
		this.enpassant = board.enpassant;
	}

	/**
	 * Returns a deep copy of the object's board array.
	 *
	 * @return board array copy
	 */
	public int[] getBoard() {
		return Arrays.copyOf(this.board, this.board.length);
	}

	/**
	 * Returns an array of the positions a knight can move to given the position of
	 * the knight on the board. The knight moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the knight on the
	 *                 board
	 * @return array of positions knight can move to
	 */
	public ArrayList<Integer> getKnightMoves(int position) {
		int knight_offsets[] = {33, 31, 18, 14, -33, -31, -18, -14};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
				}
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

	/**
	 * Returns an array of the positions a king can move to given the position of
	 * the king on the board. The king moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the king on the
	 *                 board
	 * @return array of positions king can move to
	 */
	public ArrayList<Integer> getKingMoves(int position) {
		int king_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : king_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
				}
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

	/**
	 * Returns an array of the positions a bishop can move to given the position of
	 * the bishop on the board. The bishop moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the bishop on the
	 *                 board
	 * @return array of positions bishop can move to
	 */
	public ArrayList<Integer> getBishopMoves(int position) {
		int bishop_offsets[] = {15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : bishop_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}
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

	/**
	 * Returns an array of the positions a queen can move to given the position of
	 * the queen on the board. The queen moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the queen on the
	 *                 board
	 * @return array of positions queen can move to
	 */
	public ArrayList<Integer> getQueenMoves(int position) {
		int queen_offsets[] = {16, -16, 1, -1, 15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : queen_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}
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

	/**
	 * Returns an array of the positions a rook can move to given the position of
	 * the rook on the board. The queen moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the rook on the
	 *                 board
	 * @return array of positions rook can move to
	 */
	public ArrayList<Integer> getRookMoves(int position) {
		int rook_offsets[] = {16, -16, 1, -1};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : rook_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				if (this.board[position] >= 7 && this.board[position] <= 12) {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
						break;
					}
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						break;
					}
				}
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

	/**
	 * Returns an array of the positions a white pawn can move to given the position of
	 * the white pawn on the board. The white pawn moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the white pawn on the
	 *                 board
	 * @return array of positions white pawn can move to
	 */
	public ArrayList<Integer> getWhitePawnMoves(int position) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int new_position = position + 16;
		if (onBoard(new_position) && this.board[new_position] == 0) {
			if (new_position >= 112 && new_position <= 119) {
				moves.add(new_position);
			} else {
				moves.add(new_position);
				new_position = position + 32;
				if ((position >= 16 && position <= 23) && this.board[new_position] == 0) {
					moves.add(new_position);
				}
			}
		}
		int Wpawn_offsets[] = {15, 17};
		for (int i : Wpawn_offsets) {
			new_position = position + i;
			if (onBoard(new_position)) {
				if ((new_position >= 112 && new_position <= 119)
						&& (this.board[new_position] >= 7 && this.board[new_position] <= 12)) {
					moves.add(new_position);
				} else {
					if (this.board[new_position] >= 7 && this.board[new_position] <= 12) {
						moves.add(new_position);
					}
				}
			}
		}
		return moves;
	}

	/**
	 * Returns an array of the positions a black pawn can move to given the position of
	 * the black pawn on the board. The black pawn moves are calculated using an offset.
	 * Potential captures and blocked positions are taken into consideration.
	 *
	 * @param position an integer representing the position of the black pawn on the
	 *                 board
	 * @return array of positions black pawn can move to
	 */
	public ArrayList<Integer> getBlackPawnMoves(int position) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int new_position = position - 16;
		if (onBoard(new_position) && this.board[new_position] == 0) {
			if (new_position >= 0 && new_position <= 7) {
				moves.add(new_position);
			} else {
				moves.add(new_position);
				new_position = position - 32;
				if ((position >= 96 && position <= 103) && this.board[new_position] == 0) {
					moves.add(new_position);
				}
			}
		}
		int Bpawn_offsets[] = { -15, -17 };
		for (int i : Bpawn_offsets) {
			new_position = position + i;

			if (onBoard(new_position)) {
				if ((new_position >= 0 && new_position <= 7)
						&& (this.board[new_position] >= 1 && this.board[new_position] <= 6)) {
					moves.add(new_position);
				} else {
					if (this.board[new_position] >= 1 && this.board[new_position] <= 6) {
						moves.add(new_position);
					}
				}
			}
		}
		return moves;
	}

	public boolean isSquareAttacked(int position, boolean colour) {
		// pawns
		if (colour) {
			if (onBoard(position + 15) && (this.board[position + 15] == 7)) {
				return true;
			}

			if (onBoard(position + 17) && (this.board[position + 17] == 7)) {
				return true;
			}
		} else {
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

				if (!colour ? piece == 6 : piece == 12) {
					return true;
				}

			}
		}
		// knight
		int knight_offsets[] = { 33, 31, 18, 14, -33, -31, -18, -14 };
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {
				int piece = this.board[new_position];
				if (!colour ? piece == 2 : piece == 8) {
					return true;
				}
			}
		}
		// bishop or queen diagonal attacks
		int bishop_offsets[] = { 15, 17, -15, -17 };
		for (int i : bishop_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = this.board[new_position];

				if (!colour ? (piece == 3 || piece == 5) : (piece == 9 || piece == 11)) {
					return true;
				}
				if (piece != 0) {
					break;
				}
				new_position += i;
			}
		}
		// rook or queen up/down attacks
		int rook_offsets[] = { 16, -16, 1, -1 };
		for (int i : rook_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = this.board[new_position];

				if (!colour ? (piece == 4 || piece == 5) : (piece == 10 || piece == 11)) {
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
	public ArrayList<Move> getMoves(boolean colour) {
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
						// if on 5th rank and square next to it has a black pawn
						if (square >= 61 && square <= 71) {
							if(board[square - 1] == 7) {
								Move move = new Move(square, square + 15);
								this.enpassant = move;
								moves.add(move);
							}
							if (board[square + 1] == 7) {
								Move move = new Move(square, square + 17);
								this.enpassant = move;
								moves.add(move);
							}
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

						if (this.WHITE_CASTLING) {
							// king side castling
							if ((board[5] == 0) && (board[6] == 0)) {
								if ((!this.isSquareAttacked(5, true)) && (!this.isSquareAttacked(6, true))) {
									this.WHITE_CASTLING_SHORT = true;
									Move move = new Move(this.king_square_white, 7);
									moves.add(move);
								}
							}
							// queen side castling
							if ((board[1] == 0) && (board[2] == 0) && (board[3] == 0)) {
								if ((!this.isSquareAttacked(4, true)) && (!this.isSquareAttacked(2, true))) {
									this.WHITE_CASTLING_LONG = true;
									Move move = new Move(this.king_square_white, 0);
									moves.add(move);
								}
							}
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
						if (square >= 48 && square <= 55) {
							if(board[square - 1] == 7) {
								Move move = new Move(square, square - 17);
								this.enpassant = move;
								moves.add(move);
							}
							if (board[square + 1] == 7) {
								Move move = new Move(square, square - 15);
								this.enpassant = move;
								moves.add(move);
							}
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
						if (this.BLACK_CASTLING) {
							// king side castling
							if ((board[117] == 0) && (board[118] == 0)) {
								if ((!this.isSquareAttacked(116, true)) && (!this.isSquareAttacked(117, true))) {
									this.BLACK_CASTLING_SHORT = true;
									Move move = new Move(this.king_square_black, 119);
									moves.add(move);
								}
							}
							// queen side castling
							if ((board[113] == 0) && (board[114] == 0) && (board[115] == 0)) {
								if ((!this.isSquareAttacked(116, true)) && (!this.isSquareAttacked(115, true))) {
									this.BLACK_CASTLING_LONG = true;
									Move move = new Move(this.king_square_black, 112);
									moves.add(move);
								}
							}
						}
					}

				}

			}

		}
		return moves;

	}

	public ArrayList<Move> getLegalMoves(boolean colour) {
		ArrayList<Move> legal_moves = new ArrayList<Move>();

		ArrayList<Move> moves = this.getMoves(colour);

		for (Move move : moves) {
			if (isLegal(move, colour)) {
				legal_moves.add(move);
			}
		}
		return legal_moves;
	}

	public boolean inCheckmate() {
		ArrayList<Move> movesWhite = this.getLegalMoves(true);
		ArrayList<Move> movesBlack = this.getLegalMoves(false);
		if (movesWhite.size() == 0 && this.inCheck(true)) {
			return true;
		}
		if ((movesBlack.size() == 0 && this.inCheck(false))) {
			return true;
		}
		return false;
	}

	public boolean isCheckmate(boolean colour) {
		ArrayList<Move> moves = this.getLegalMoves(colour);
		return (moves.size() == 0 && this.inCheck(colour));
	}

	public boolean isStalemate(boolean colour) {
		ArrayList<Move> moves = this.getLegalMoves(colour);
		return (moves.size() == 0 && !this.inCheck(colour));
	}

	public boolean inCheck(boolean colour) {
		return colour ? this.isSquareAttacked(this.king_square_white, colour)
				: this.isSquareAttacked(king_square_black, colour);
	}

	public boolean isLegal(Move move, boolean colour) {
		Board boardCopy = new Board(this);
		boardCopy.makeMove(move);
		boolean check = boardCopy.inCheck(colour);
		return !check;
	}

	public void makeMove(Move move) {
		this.lastMove = move;
		this.lastMovetook = this.board[move.to];
		
		if (move == this.enpassant) {
			if (this.colour) {
				this.board[move.to - 16] = 0;
				this.board[move.to] = this.board[move.from];
				this.board[move.from] = 0;
			} else {
				this.board[move.to + 16] = 0;
				this.board[move.to] = this.board[move.from];
				this.board[move.from] = 0;
			}
		}

		if (this.WHITE_CASTLING) {
			this.WHITE_CASTLING_LONG = false;
			this.WHITE_CASTLING_SHORT = false;
			// White castling
			if (move.from == 4) {
				// king side
				if (move.to == 7) {
					this.king_square_white = 6;
					// move king to g1
					this.board[6] = this.board[4];
					this.board[4] = 0;
					// move rook to f1
					this.board[5] = this.board[7];
					this.board[7] = 0;
				}
				// queen side
				if (move.to == 0) {
					this.king_square_white = 2;
					// move king to c1
					this.board[2] = this.board[4];
					this.board[4] = 0;
					// move rook to d1
					this.board[3] = this.board[0];
					this.board[0] = 0;
				}
				this.colour = !this.colour;
				return;
			}
		}
		
		if (this.BLACK_CASTLING) {
			this.BLACK_CASTLING_LONG = false;
			this.BLACK_CASTLING_SHORT = false;
			// Black castling
			if (move.from == 116) {
				// king side
				if (move.to == 119) {
					this.king_square_black = 118;
					// move king to g8
					this.board[116] = this.board[118];
					this.board[116] = 0;
					// move rook to f8
					this.board[119] = this.board[117];
					this.board[119] = 0;
				}
				// queen side
				if (move.to == 112) {
					this.king_square_black = 114;
					// move king to c8
					this.board[116] = this.board[114];
					this.board[116] = 0;
					// move rook to d8
					this.board[112] = this.board[115];
					this.board[112] = 0;

				}
				this.colour = !this.colour;
				return;
			}
		}

		if (this.board[move.from] == 6) {
			this.king_square_white = move.to;
			this.WHITE_CASTLING = false;
		}

		if (this.board[move.from] == 12) {
			this.king_square_black = move.to;
			this.BLACK_CASTLING = false;
		}

		this.board[move.to] = this.board[move.from];
		this.board[move.from] = 0;
		this.colour = !this.colour;
	}

	public void undoLastMove() {
		Move move = this.lastMove;
		int takenPiece = this.lastMovetook;

		int initialPiece = this.board[move.to];

		this.board[move.from] = initialPiece;
		this.board[move.to] = takenPiece;
	}

	public void getKingSquares() {
		for (int square = 0; square < 128; square++) {
			if (onBoard(square)) {
				if (this.board[square] == 6) {
					this.king_square_white = square;
				}
				if (this.board[square] == 12) {
					this.king_square_black = square;
				}
			}
		}
	}

	// return if position is on the board
	public boolean onBoard(int position) {
		return (position & 0x88) == 0;
	}

}
