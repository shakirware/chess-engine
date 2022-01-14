/**
 * 
 */
package engine;

import static engine.Pieces.*;

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
		System.out.println(board.getRookMoves(86));
		board.colour = BLACK;
		System.out.println(board.getRookMoves(86));

	}

}
