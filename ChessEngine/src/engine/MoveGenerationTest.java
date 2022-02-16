package engine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MoveGenerationTest {

	@Test
	public void testBishop() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(66);
		array.add(81);
		array.add(36);
		array.add(102);
		array.add(34);
		array.add(96);
		array.add(68);
		array.add(85);
		
		Board board = new Board();
		
		// Place a white bishop
		board.board[51] = 3;
		
		ArrayList<Integer> moves =  board.getBishopMoves(51);
		
		//System.out.println(moves);
		//System.out.println(array);
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testKnight() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(4);
		array.add(6);
		array.add(51);
		array.add(19);
		array.add(23);
		array.add(55);
		array.add(68);
		array.add(70);
		
		Board board = new Board();
		
		// Place a black knight
		board.board[37] = 8;
		
		ArrayList<Integer> moves =  board.getKnightMoves(37);

		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testKnight1() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(66);
		array.add(49);
		
		Board board = new Board();
		
		// Place a black knight
		board.board[80] = 8;
		
		ArrayList<Integer> moves =  board.getKnightMoves(80);
		

		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testRook() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(87);
		array.add(55);
		array.add(39);
		array.add(23);
		array.add(70);
		array.add(69);
		array.add(68);
		array.add(67);
		array.add(66);
		array.add(65);
		array.add(64);
		
		Board board = new Board();
		
		// Place a black rook
		board.board[71] = 10;
		
		ArrayList<Integer> moves =  board.getRookMoves(71);
		

		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testWPawn() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(32);
		array.add(48);
		
		Board board = new Board();
		
		// Place a white pawn
		board.board[16] = 1;
		
		ArrayList<Integer> moves =  board.getWhitePawnMoves(16);
		

		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testWPawn1() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(96);
		array.add(98);
		
		Board board = new Board();
		
		// Place a white pawn
		board.board[81] = 1;
		
		ArrayList<Integer> moves =  board.getWhitePawnMoves(81);
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testBPawn() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(81);
		array.add(65);
		
		Board board = new Board();
		
		// Place a black pawn
		board.board[97] = 7;
		
		ArrayList<Integer> moves =  board.getBlackPawnMoves(97);
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testBPawn1() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(16);
		array.add(18);
		
		Board board = new Board();
		
		// Place a black pawn
		board.board[33] = 7;
		
		ArrayList<Integer> moves =  board.getBlackPawnMoves(33);
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testKing() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		//array.add(16);
		//array.add(18);
		
		Board board = new Board();
		
		// Place a white king
		board.board[3] = 6;
		
		ArrayList<Integer> moves =  board.getKingMoves(3);
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testKing1() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(66);
		array.add(67);
		array.add(68);
		array.add(52);
		array.add(36);
		array.add(35);
		array.add(34);
		array.add(50);
		
		Board board = new Board();
		
		// Place a black king
		board.board[51] = 12;
		
		ArrayList<Integer> moves =  board.getKingMoves(51);
		
		
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testQueen() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(48);
		array.add(64);
		array.add(80);
		array.add(96);
		array.add(49);
		array.add(66);
		array.add(83);
		array.add(100);
		array.add(33);
		array.add(34);
		array.add(35);
		array.add(36);
		array.add(37);
		array.add(38);
		array.add(39);
		
		Board board = new Board();
		
		// Place a white queen
		board.board[32] = 5;
		
		ArrayList<Integer> moves =  board.getQueenMoves(32);
		
		
		
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}
	
	@Test
	public void testSquareAttacked() {		
		Board board = new Board();
		
		// Place a black queen
		board.board[51] = 11;
		
		
		boolean result = board.isSquareAttacked(67, true);
		assertTrue(result);
	}
	
	@Test
	public void testCheck() {		
		Board board = new Board();
		
		// Place a black queen
		board.board[52] = 11;
		board.board[20] = 0;
		
		boolean check = board.inCheck(board.colour);
		assertTrue(check);
	}
	
	@Test
	public void testLegalMoves() {		
		Board board = new Board();
		
		// set a black queen
		board.board[20] = 11;
		// set a black bishop
		board.board[35] = 9;
		// set a white king
		board.board[4] = 6;
		
		// king cannot take queen due to bishop protecting queen
		Move move1 = new Move(4, 20);
		boolean check = board.isLegal(move1, true);
		assertFalse(check);
	}
	
	@Test
	public void testisLegal() {		
		Board board = new Board();
		// Place a black queen
		board.board[20] = 11;
		// Place a white king
		board.board[4] = 6;
		
		// king takes queen
		Move move = new Move(4, 20);
		boolean check = board.isLegal(move, true);
		assertTrue(check);
	}
	
	
	@Test
	public void testCheckmateWhite() {		
		Board board = new Board();
		
		// place a black queen
		board.board[20] = 11;

		// place a black bishop
		board.board[35] = 9;

		board.board[4] = 6;
		
		board.board[1] = 0;
		board.board[6] = 0;
		board.board[3] = 0;
		board.board[5] = 0;
		board.board[2] = 0;
		
		boolean check = board.isCheckmate(board.colour);
		
		
		assertTrue(check);
	}
	
	@Test
	public void testCheckmateBlack() {		
		Board board = new Board();
		
		// place a white queen
		board.board[100] = 5;

		// place a white bishop
		board.board[83] = 3;

		board.board[115] = 0;
		board.board[99] = 0;
		board.board[117] = 0;
		board.board[101] = 0;
		board.board[114] = 0;
		board.board[118] = 0;
		
		boolean check = board.inCheckmate();	
		
		assertTrue(check);
	}
	
	@Test
	public void boardCopyTest() {
		Board board = new Board();
		
		
		Board boardCopy = new Board(board);
		boardCopy.board[20] = 11;
		Move move = new Move(4, 20);
		boardCopy.makeMove(move);
		
		
		assertNotEquals(board.board[4],boardCopy.board[4]);
	}

}
