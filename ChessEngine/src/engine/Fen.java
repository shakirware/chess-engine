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
	
	public static int[] parseFenString(String fen) {
		int[] board = boardEmpty;
		int count = 0;
		
		for (int rank = 7; rank >= 0; rank --) {
			for (int file = 0; file < 16; file++)
			{
				int square = rank * 16 + file;
				 if ((square & 0x88) == 0) {
					 char fenChar = fen.charAt(count);
					 if (Character.isLetter(fenChar)) {
						 board[square] = pieces.indexOf(fenChar);
						 //System.out.println("Setting " + square + " to " + pieces.indexOf(fenChar));
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
		return board;
	}
}
