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
		Board board = new Board();
		Search search = new Search(board, 5);
		
		search.miniMax();
		//System.out.println(board.getKingMoves(35));
		//board.colour = false;
		//ArrayList<Move> moves = board.getMoves();

		//for (Move move : moves) {	
		//	move.output();
		//}

		
	}

}
