/**
 * 
 */
package engine;

/**
 * @author shakir
 *
 */
public class Move {

	public int from;
	public int to;
	
	public Move( int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public void output() {
		System.out.println("FROM: " + this.from + "    " + "TO: "+ this.to);
		
	}

}
