package engine;

import static engine.Pieces.random64;;

public class Book {

	public static long getKey(Board board) {
		long key = 0;
		
		int[] p = {-1, 1, 3, 5, 7, 9, 11, 0, 2, 4, 6, 8, 10};
		
		for (int rank = 0; rank < 8; rank++) {
			// loop over board files
			for (int file = 0; file < 16; file++) {
				int square = rank * 16 + file;
				int piece = board.board[square];
				if (board.onBoard(square) && piece != 0) {
					System.out.println(p[piece]);
					key ^= random64[(64 * p[piece]) + (8 * rank) + file];
				}
			}

		}
		return key;
	}
}
