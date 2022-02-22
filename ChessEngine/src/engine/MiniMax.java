package engine;

import java.util.ArrayList;

import static engine.Pieces.*;

public class MiniMax {
	
	static int winVal = Integer.MAX_VALUE;
    static int loseVal = Integer.MIN_VALUE;
	
	static Move nextMove;
	static int depth;
	static int tempDepth;
	static int counter;

	protected static int[] pawnTable = { 0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 10, 10, 20, 30, 30, 20,
			10, 10, 5, 5, 10, 25, 25, 10, 5, 5, 0, 0, 0, 20, 20, 0, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5, 5, 10, 10, -20,
			-20, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0 };

	// Placement Precedence for all Knights
	protected static int[] knightTable = { -50, -40, -30, -30, -30, -30, -40, -50, -40, -20, 0, 0, 0, 0, -20, -40, -30,
			0, 10, 15, 15, 10, 0, -30, -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 15, 20, 20, 15, 0, -30, -30, 5, 10, 15,
			15, 10, 5, -30, -40, -20, 0, 5, 5, 0, -20, -40, -50, -40, -30, -30, -30, -30, -40, -50 };

	protected static int[] bishopPrecedence = { -20, -10, -10, -10, -10, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10,
			0, 5, 10, 10, 5, 0, -10, -10, 5, 5, 10, 10, 5, 5, -10, -10, 0, 10, 10, 10, 10, 0, -10, -10, 10, 10, 10, 10,
			10, 10, -10, -10, 5, 0, 0, 0, 0, 5, -10, -20, -10, -10, -10, -10, -10, -10, -20 };

	protected static int[] rookTable = { 0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, -5, 0, 0, 0, 0, 0, 0, -5,
			-5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, 0,
			0, 0, 5, 5, 0, 0, 0 };

	protected static int[] queenTable = { -20, -10, -10, -5, -5, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10, 0, 5,
			5, 5, 5, 0, -10, -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, 5, 5, 5, 5, 0, -5, -10, 5, 5, 5, 5, 5, 0, -10, -10, 0, 5,
			0, 0, 0, 0, -10, -20, -10, -10, -5, -5, -10, -10, -20 };

	protected static int[] kingTable = { -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30,
			-30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -20, -30, -30, -40, -40,
			-30, -30, -20, -10, -20, -20, -20, -20, -20, -20, -10, 20, 20, 0, 0, 0, 0, 20, 20, 20, 30, 10, 0, 0, 10, 30,
			20 };

	public static Move getNextMove(Board board, boolean side) {
		counter = 0;
		Move move = minimax(board, BLACK, 2);
		System.out.println("Boards evaluated: " + counter);
		return move;
	}

	private static int max(Board board, boolean colour, int d) {
		counter++;
		if (board.isStalemate(BLACK) || board.isStalemate(WHITE) || board.inCheckmate() || d > MiniMax.tempDepth) {
			return eval(board, BLACK, d);
		}
		int best = loseVal;
		int current;
		ArrayList<Move> moves = board.getLegalMoves(BLACK);
		for (Move move : moves) {
			Board b = new Board(board);
			b.makeMove(move);	
			current = min(b, WHITE, d + 1);
			b.undoLastMove();
			if (current > best) {
				best = current;
			}
		}
		return best;
	}

	private static int min(Board board, boolean colour, int d) {
		counter++;
		if (board.isStalemate(BLACK) || board.isStalemate(WHITE) || board.inCheckmate() || d > MiniMax.tempDepth) {
			return eval(board, WHITE, d);
		}

		int best = winVal;
		int current;
		ArrayList<Move> moves = board.getLegalMoves(WHITE);
		for (Move move : moves) {
			Board b = new Board(board);
			b.makeMove(move);
			current = max(b, BLACK, d);
			b.undoLastMove();
			if (current < best) {
				best = current;
			}
		}
		return best;
	}

	private static Move minimax(Board board, boolean colour, int maxDepth) {
		counter++;
		for (MiniMax.depth = 1; MiniMax.depth < maxDepth; MiniMax.depth++) {
			int current = loseVal;
			int best = loseVal;
			int index = 0;
			int bestIndex = 0;

			MiniMax.tempDepth = MiniMax.depth;

			ArrayList<Move> moves = board.getLegalMoves(BLACK);
			for (Move move : moves) {
				Board b = new Board(board);
				b.makeMove(move);
				current = min(b, WHITE, 1);
				b.undoLastMove();
				
				if (best <= current) {
					best = current;
					//moves.get(bestIndex).output();;
					bestIndex = index;
				}
				index++;
			}
			
			
			if (best == winVal) {
				return moves.get(bestIndex);
			}

			if ((MiniMax.depth + 1) == maxDepth) {
				//System.out.println(depth);
				return moves.get(bestIndex);
			}

		}

		return new Move(1, 1);
	}


	public static int eval(Board board, boolean colour, int d) {
		if (board.isCheckmate(WHITE)) {
			if (d <= MiniMax.tempDepth) {
				System.out.println("Found a win - DepthLimit = " + d + " " + MiniMax.tempDepth);
				MiniMax.tempDepth = d;
				//System.out.println("Found a win - DepthLimit = " + depth);
				return winVal;
			} else {
				return loseVal;
			}
		}

		if (board.isStalemate(colour) || board.isStalemate(!colour) || board.isCheckmate(colour)) {
			return loseVal;
		}
		

		return getMaterials(board, colour);
	}

	public static int getMaterials(Board board, boolean colour) {
		int val = 0;
		int wPawns = 0, wBishops = 0;
		int bPawns = 0, bBishops = 0;
		for (int square = 0; square < 128; square++) {
			if (board.onBoard(square)) {

				int piece = board.board[square];
				int[] c = convert0x64(square);
				int row = c[0];
				int col = c[1];
				switch (piece) {
				case 1:
					wPawns++;
					val += (100 + (pawnTable[8 * (7 - row) + col]));
					break;

				case 2:
					val += (320 + (knightTable[8 * (7 - row) + col]));
					break;

				case 3:
					wBishops++;
					val += (330 + (rookTable[8 * (7 - row) + col]));
					break;
				case 4:
					val += (500 + (rookTable[8 * (7 - row) + col]));
					break;
				case 5:
					val += (900 + (queenTable[8 * (7 - row) + col]));
					break;
				case 6:
					val += (20000 + (kingTable[8 * (7 - row) + col]));
					break;
				case 7:
					bPawns++;
					val -= (100 + (pawnTable[8 + (8 * row) - (1 + col)]));
					break;
				case 8:
					val -= (320 + (knightTable[8 + (8 * row) - (1 + col)]));
					break;
				case 9:
					bBishops++;
					val -= (330 + (rookTable[8 + (8 * row) - (1 + col)]));
					break;
				case 10:
					val -= (500 + (rookTable[8 + (8 * row) - (1 + col)]));
					break;
				case 11:
					val -= (900 + (queenTable[8 + (8 * row) - (1 + col)]));
					break;
				case 12:
					val -= (20000 + (kingTable[8 + (8 * row) - (1 + col)]));
					break;
				}

				if (wPawns == 0) {
					val -= 20;
				}

				if (bPawns == 0) {
					val += 20;
				}

				// Bonus for bishop pair
				if (wBishops == 2) {
					val += 40;
				}

				if (bBishops == 2) {
					val -= 40;
				}
			}
		}
		return (colour ? val : val * -1);

	}

	public static int[] convert0x64(int index) {
		int x = 0;
		int y = 0;

		if (index <= 7) {
			y = 7;
			x = index;
		} else if ((index >= 16) && (index <= 32)) {
			y = 6;
			x = index - 8;
			x = index % 8;
		} else if ((index >= 32) && (index <= 39)) {
			y = 5;
			x = index - 16;
			x = index % 8;
		} else if ((index >= 48) && (index <= 55)) {
			y = 4;
			x = index - 24;
			x = index % 8;
		} else if ((index >= 64) && (index <= 71)) {
			y = 3;
			x = index - 32;
			x = index % 8;
		} else if ((index >= 80) && (index <= 87)) {
			y = 2;
			x = index - 40;
			x = index % 8;
		} else if ((index >= 96) && (index <= 103)) {
			y = 1;
			x = index - 48;
			x = index % 8;
		} else if ((index >= 112) && (index <= 119)) {
			y = 0;
			x = index - 56;
			x = index % 8;
		}
		int[] coords = { x, y };
		return coords;
	}
}