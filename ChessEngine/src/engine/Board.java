/**
 * 
 */
package engine;

import static engine.Pieces.*;

import java.util.ArrayList;


/**
 * @author shakir
 *
 */


public class Board {
	
	public int[] board;
	public boolean colour;
	
	public Board() {
		this.board = new int[] { 
				WROOK, WKNIGHT, WBISHOP, WQUEEN, WKING, WBISHOP, WKNIGHT, WROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,  WPAWN,   EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN, BPAWN,  EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                BROOK, BKNIGHT, BBISHOP, BQUEEN, BKING, BBISHOP, BKNIGHT, BROOK, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY 
                };
		this.colour = WHITE;
	}
	
	public ArrayList<Integer> getKnightMoves(int position) {
		int knight_offsets[] = {33, 31, 18, 14, -33, -31, -18, -14};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : knight_offsets) {
			int new_position = position + i;
			if (onBoard(new_position)) {
				int piece = board[new_position];
				if (colour ?
                        (piece == 0 || (piece >= 7 && piece <= 12)) : 
                        (piece == 0 || (piece >= 1 && piece <= 6))
                      ) {
					moves.add(new_position);
				}
			}			
		}
		return moves;
	}
	
	
	public ArrayList<Integer> getBishopMoves(int position) {
		int bishop_offsets[] = {15, 17, -15, -17};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : bishop_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = this.board[new_position];
				// if it hits own piece
				if (colour ? (piece >= 1 && piece <= 6) : ((piece >= 7 && piece <= 12))) {
						break;
					}
				// if it hits opps piece
				if (colour ? (piece >= 7 && piece <= 12) : ((piece >= 1 && piece <= 6))) {
					moves.add(new_position);
					break;
				}
				// if empty square
				if (piece == 0) {
					moves.add(new_position);
				}
				new_position += i;
			}
		}			
		return moves;
	}
	
	public ArrayList<Integer> getRookMoves(int position) {
		int rook_offsets[] = {16, -16, 1, -1};
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i : rook_offsets) {
			int new_position = position + i;
			while (onBoard(new_position)) {
				int piece = this.board[new_position];
				if (colour ? (piece >= 1 && piece <= 6) : ((piece >= 7 && piece <= 12))) {
						break;
					}
				if (colour ? (piece >= 7 && piece <= 12) : ((piece >= 1 && piece <= 6))) {
					moves.add(new_position);
					break;
				}
				if (piece == 0) {
					moves.add(new_position);
				}
				new_position += i;
			}
		}			
		return moves;
	}
	
	
	
	// return if position is on the board
	public static boolean onBoard(int position) {
		return (position & 0x88) == 0;
	}
	
	
	
	
	

	public boolean makeMove() {
		//
		return true;
	}
	
	
}
