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
		
		//search.miniMax();
		
		Move move1 = new Move(3, 5);
		
		Move move = new Move(3, 5);
		
		if (move.equals(move1)) {
			System.out.println("test");
		}
		//System.out.println(board.getKingMoves(35));
		//board.colour = false;
		//ArrayList<Move> moves = board.getMoves();

		//for (Move move : moves) {	
		//	move.output();
		//}

		
	}

}
