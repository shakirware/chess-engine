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
		//ArrayList<Move> moves = board.getMoves();

		//for (Move move : moves) {	
		//	move.output();
		//}

		board.board[20] = 11;

		board.board[35] = 7;

		board.board[18] = 0;

		Move move1 = new Move(5, 20);
		System.out.println(board.isLegal(move1));
		//move.output();
		//System.out.println(board.isCheckmate());
		
		ArrayList<Move> moves = board.getLegalMoves();

		for (Move move : moves) {	
			move.output();
		}
	}

}
