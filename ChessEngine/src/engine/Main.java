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
		//Board board = new Board("3k4/8/5q2/8/8/8/8/Kb6");
		//System.out.println(board.board[0]);
		//Move playerMove = new Move(0, 1);
		//board.makeMove(playerMove);
		//System.out.println(board.board[0]);
		//board.undoLastMove();
		//System.out.println(board.board[0]);
		//board.lastMove.output();
		//System.out.println(board.lastMovetook);
		
		Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		
		System.out.println(Book.getKey(board));
		
	}

}
