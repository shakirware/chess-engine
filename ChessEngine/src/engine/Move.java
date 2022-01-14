/**
 * 
 */
package engine;

/**
 * @author shakir
 *
 */
public class Move {

	private int from;
	private int to;
	
	public Move( int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public void output() {
		System.out.println(this.from + this.to);
	}

}
