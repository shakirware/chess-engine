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

		//Move move = new Move(115, 99);
		
		System.out.println(board.isCheckmate(false));
		
	}

}
