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
		
		//System.out.println(board.getKingMoves(35));
		//board.colour = false;
		ArrayList<Move> moves = board.getMoves();
		
		for (Move move : moves) {	
			move.output();
		}
	}

}
