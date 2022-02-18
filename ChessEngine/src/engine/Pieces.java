/**
 * 
 */
package engine;

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

	public static final String[] pieceImages = { ".", "wP.png", "wN.png", "wB.png", "wR.png", "wQ.png", "wK.png",
			"bP.png", "bN.png", "bB.png", "bR.png", "bQ.png", "bK.png" };

	public static final String pieces = ".PNBRQKpnbrqk";

	public static final int[] boardEmpty = new int[] { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
			EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY };

}
