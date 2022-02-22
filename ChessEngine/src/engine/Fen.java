/**
 * 
 */
package engine;

import static engine.Pieces.*;

/**
 * @author shakir
 *
 */
public class Fen {
	
	public int[] board;
	
	public int[] parseFenString(Board board, String fenString) {
		this.board = boardEmpty;
		String[] split = fenString.split("\\s+");
		String fen = split[0];
		String turn = split[1];
		String castling = split[2];
		// String enpassant = split[3];
		int count = 0;

		for (int rank = 7; rank >= 0; rank--) {
			for (int file = 0; file < 16; file++) {
				int square = rank * 16 + file;
				if ((square & 0x88) == 0) {
					char fenChar = fen.charAt(count);
					if (Character.isLetter(fenChar)) {
						this.board[square] = pieces.indexOf(fenChar);
						count++;
					}
					if (Character.isDigit(fenChar)) {
						int offset = fenChar - '0';

						if ((square & 0x88) == 0) {
							file--;
						}

						file += offset;
						count++;
					}
					if (fenChar == '/') {
						file--;
						count++;
					}
				}
			}
		}
		
		if (castling.contains("Q") || castling.contains("K")) {
		    board.WHITE_CASTLING = true;
		} else if (castling.contains("q") || castling.contains("k")) {
			board.BLACK_CASTLING = true;
		} else {
			board.WHITE_CASTLING = false;
			board.WHITE_CASTLING = false;
		}
		
		return this.board;
	}
}
