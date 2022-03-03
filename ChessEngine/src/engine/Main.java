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
		

		Board board = new Board("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq - 0 1");
		ArrayList<Move> moves = Book.getMoves(board, false);
		
		for (Move move : moves) {
			move.output();
		}
		
		
		//long key = Book.getKey(board);
		//System.out.println(String.format("%11X", key));
		
		
	}

}
