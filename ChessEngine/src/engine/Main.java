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
		
		
		// FROM: 115 TO: 99
		
		Board board = new Board();

		Move playerMove = new Move(51, 99);
		board.makeMove(playerMove);
		
		System.out.println(board.isSquareAttacked(99, false));
		
		ArrayList<Move> moves = board.getLegalMoves(false);

		for (Move move : moves) {
			move.output();
		}
		
	}

}
