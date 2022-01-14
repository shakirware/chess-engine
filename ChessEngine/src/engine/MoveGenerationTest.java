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
		
		//System.out.println(moves);
		//System.out.println(array);
		
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
		
		//System.out.println(moves);
		//System.out.println(array);
		
		assertEquals(array.size(), moves.size());
		assertTrue(array.containsAll(moves));
		assertTrue(moves.containsAll(array));
	}

}
