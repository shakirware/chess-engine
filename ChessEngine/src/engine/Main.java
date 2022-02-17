/**
 * 
 */
package engine;

import java.util.ArrayList;

/**
 * @author shakir
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		
		int[] board = Fen.parseFenString("4k2r/6r1/8/8/8/8/3R4/R3K3");
		
		for (int rank = 0; rank < 8; rank ++) {
			for (int file = 0; file < 16; file++)
			{
				int square = rank * 16 + file;
				if ((square & 0x88) == 0) {
					if (board[square] != 0) {
						int piece = board[square];
						System.out.println("Piece: " + piece + " @square " + square);
					}
				}
			}
		}
		
		
	}

}
