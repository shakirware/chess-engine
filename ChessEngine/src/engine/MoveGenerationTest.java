package engine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class MoveGenerationTest {

	@Test
	public void testBishop() {
		Board board = new Board("k2P4/6P1/5b2/6B1/3Q3P/8/8/K2R4");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(100);
		array.add(115);
		array.add(102);
		array.add(70);
		array.add(68);
		array.add(51);

		ArrayList<Integer> moves = board.getBishopMoves(85);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testKnight() {
		Board board = new Board("2k1q3/8/8/8/8/4B3/8/3K1N2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(23);
		array.add(38);
		array.add(19);

		ArrayList<Integer> moves = board.getKnightMoves(5);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testRook() {
		Board board = new Board("2k1q3/8/8/8/n7/4B3/8/R2K1N2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(1);
		array.add(2);
		array.add(16);
		array.add(32);
		array.add(48);

		ArrayList<Integer> moves = board.getRookMoves(0);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testWhitePawn() {
		Board board = new Board("2k1q3/8/8/5r1r/n5P1/4B3/8/R2K1N2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(69);
		array.add(70);
		array.add(71);

		ArrayList<Integer> moves = board.getWhitePawnMoves(54);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testBlackPawn() {
		Board board = new Board("3kq3/8/1p6/Q1R2r1r/n5P1/4B3/8/R2K1N2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(64);
		array.add(65);
		array.add(66);

		ArrayList<Integer> moves = board.getBlackPawnMoves(81);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testKing() {
		Board board = new Board("3kq3/8/1p6/Q1R2r1r/n5P1/4B3/2p1p3/R2K1N2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(2);
		array.add(18);
		array.add(19);
		array.add(20);
		array.add(4);

		ArrayList<Integer> moves = board.getKingMoves(3);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testQueen() {
		Board board = new Board("3kq3/N7/1p6/Q1R2r1r/n5P1/4B3/2p1p3/R2KnN2");

		ArrayList<Integer> array = new ArrayList<Integer>();
		
		array.add(81);
		array.add(80);
		array.add(65);
		array.add(49);
		array.add(48);
		array.add(34);
		array.add(19);
		array.add(4);

		ArrayList<Integer> moves = board.getQueenMoves(64);

		Collections.sort(array);
		Collections.sort(moves);

		assertArrayEquals(array.toArray(), moves.toArray());
	}

	@Test
	public void testSquareAttacked() {
		Board board = new Board("3kq3/N7/1p6/Q1R2r1r/n5P1/4B3/2p1p3/R2KnN2");
		boolean result = board.isSquareAttacked(3, true);
		assertTrue(result);
	}

	@Test
	public void testCheck() {
		Board board = new Board("3kq3/N7/1p6/Q1R2r1r/n5P1/4B3/2p1p3/R2KnN2");
		boolean check = board.inCheck(true);
		assertTrue(check);
	}

	@Test
	public void testLegal() {
		Board board = new Board("3k4/8/8/8/6q1/8/4b3/3K4");
		Move move1 = new Move(3, 20);
		boolean check = board.isLegal(move1, true);
		assertFalse(check);
	}

	@Test
	public void testCheckmate() {
		Board board = new Board("3k4/8/8/3q3b/b7/8/2ppp3/3K4");
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

		assertNotEquals(board.board[4], boardCopy.board[4]);
	}

}
