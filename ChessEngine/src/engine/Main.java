/**
 * 
 */
package engine;

/**
 * @author shakir
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		Board board = new Board("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
		board.colour = false;
		long key = Book.getKey(board);
		System.out.println(String.format("%11X", key));
		
		
	}

}
