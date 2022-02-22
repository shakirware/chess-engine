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
		Board board = new Board("4k2r/4r3/8/8/8/8/2R5/2K1R3 w - - 0 1");
		
		
		//System.out.println(board.board[0]);
		//Move playerMove = new Move(0, 1);
		//board.makeMove(playerMove);
		//System.out.println(board.board[0]);
		//board.undoLastMove();
		//System.out.println(board.board[0]);
		//board.lastMove.output();
		//System.out.println(board.lastMovetook);
		
		
		System.out.println(board.inCheckmate());
		
	}

}
