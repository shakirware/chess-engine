package engine;

import static engine.Pieces.random64;
import static engine.Pieces.coords;

import java.util.Arrays;
import java.util.List;

public class Book {

	public static boolean pawnForCapture(Board board) {

		int sqWithPawn = 0;
		int targetPce = board.colour ? 1 : 7;

		if (!board.FenEnPassant.equals("-")) {
			System.out.println(board.FenEnPassant);
			int index = coords.indexOf(board.FenEnPassant);
			System.out.println(index);
			if (board.colour) {
				sqWithPawn = index - 16;
			} else {
				sqWithPawn = index + 16;
			}

			if (board.board[sqWithPawn + 1] == targetPce) {
				return true;
			} else if (board.board[sqWithPawn - 1] == targetPce) {
				return true;
			}
		}
		return false;
	}

	public static long getKey(Board board) {
		long key = 0;

		int[] p = { -1, 1, 3, 5, 7, 9, 11, 0, 2, 4, 6, 8, 10 };

		for (int rank = 0; rank < 8; rank++) {
			// loop over board files
			for (int file = 0; file < 16; file++) {
				int square = rank * 16 + file;
				int piece = board.board[square];
				if (board.onBoard(square) && piece != 0) {
					key ^= random64[(64 * p[piece]) + (8 * rank) + file];
				}
			}

		}

		// castling

		int offset = 768;

		if (board.WHITE_CASTLING_SHORT) {
			System.out.println("test");
			key ^= random64[offset + 0];
		}
		if (board.WHITE_CASTLING_LONG) {
			key ^= random64[offset + 1];
		}
		if (board.BLACK_CASTLING_SHORT) {
			key ^= random64[offset + 2];
		}
		if (board.BLACK_CASTLING_LONG) {
			key ^= random64[offset + 3];
		}

		// en passant

		offset = 772;

		if (pawnForCapture(board)) {
			// get file of enpassant square
			int index = coords.indexOf(board.FenEnPassant);
			int indexFile = 0;

			for (int rank = 0; rank < 8; rank++) {
				for (int file = 0; file < 16; file++) {
					int square = rank * 16 + file;
					if (square == index) {
						indexFile = file;
					}
				}
			}
			System.out.println(indexFile);
			key ^= random64[offset + indexFile];
		}

		// side

		if (board.colour) {
			key ^= random64[780];
		}

		return key;
	}

}
