/**
 * 
 */
package engine;

import static engine.Pieces.BBISHOP;
import static engine.Pieces.BKING;
import static engine.Pieces.BKNIGHT;
import static engine.Pieces.BPAWN;
import static engine.Pieces.BQUEEN;
import static engine.Pieces.BROOK;
import static engine.Pieces.EMPTY;
import static engine.Pieces.WBISHOP;
import static engine.Pieces.WKING;
import static engine.Pieces.WKNIGHT;
import static engine.Pieces.WPAWN;
import static engine.Pieces.WQUEEN;
import static engine.Pieces.WROOK;

/**
 * @author shakir
 *
 */

public class Pieces {
	public static final boolean WHITE = true;
	public static final boolean BLACK = false;
	public static final int EMPTY = 0;
	// WHITE PIECES
	public static final int WPAWN = 1;
	public static final int WKNIGHT = 2;
	public static final int WBISHOP = 3;
	public static final int WROOK = 4;
	public static final int WQUEEN = 5;
	public static final int WKING = 6;
	// BLACK PIECES
	public static final int BPAWN = 7;
	public static final int BKNIGHT = 8;
	public static final int BBISHOP = 9;
	public static final int BROOK = 10;
	public static final int BQUEEN = 11;
	public static final int BKING = 12;
	
	
	public static final int[] boardTest1 = new int[] { 
			EMPTY, BBISHOP, EMPTY, WQUEEN, WKING, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY,  EMPTY,  EMPTY,  EMPTY,  EMPTY,  EMPTY,  EMPTY,  EMPTY,   EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  BBISHOP, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, BKING, EMPTY, BQUEEN, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY 
	};
	
	public static final int[] boardTest2 = new int[] { 
			WROOK, WKNIGHT, WBISHOP, EMPTY, WKING, WBISHOP, WKNIGHT, WROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			WPAWN,  WPAWN,  WPAWN,  EMPTY,  WPAWN,  WPAWN,  WPAWN,  WPAWN,   EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, WQUEEN,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			BROOK, BKNIGHT, BBISHOP, BQUEEN, BKING, BBISHOP, BKNIGHT, BROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY 
	};

}
