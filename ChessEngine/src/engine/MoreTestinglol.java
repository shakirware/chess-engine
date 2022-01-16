package engine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MoreTestinglol {
	/*
	@Test
	public void testCheckmate1() {		
		Board board = new Board();
		
		//boolean check = board.isLegal(move);
		//System.out.println(board.inCheck(!board.colour));
		//System.out.println(board.isSquareAttacked(20));
		ArrayList<Move> moves = board.getLegalMoves(!board.colour);
		
		for (Move move : moves) {
			//move.output();
		}
		//.isCheckmate(false)
		System.out.println(board.isCheckmate(false));
		
		//assertTrue(check);
	}
	*/
	@Test
	public void testLegalMoves() {		
		Board board = new Board();
		
		//boolean check = board.isLegal(move);
		//System.out.println(board.inCheck(!board.colour));
		//System.out.println(board.isSquareAttacked(20));
		ArrayList<Move> moves = board.getLegalMoves(!board.colour);
		
		for (Move move : moves) {
			move.output();
		}
		//.isCheckmate(false)
		//System.out.println(board.isCheckmate(false));
		
		//assertTrue(check);
	}

}
