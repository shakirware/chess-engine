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

}
