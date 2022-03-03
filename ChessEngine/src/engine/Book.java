package engine;

import static engine.Pieces.random64;
import static engine.Pieces.coords;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Book {
	
	private static String bookName = "Human.bin";
	
	private static String int2MoveString(short move) {
		StringBuilder sb = new StringBuilder();
		sb.append((char) ('a' + ((move >> 6) & 0x7)));
		sb.append(((move >> 9) & 0x7) + 1);
		sb.append((char) ('a' + (move & 0x7)));
		sb.append(((move >> 3) & 0x7) + 1);
		if (((move >> 12) & 0x7) != 0) sb.append("NBRQ".charAt(((move >> 12) & 0x7) - 1));
		return sb.toString();
	}
	
	
	public static ArrayList<Move> getMoves(Board board, boolean colour) {
		Move move = null;
		ArrayList<Move> moves = new ArrayList<Move>();
		long BoardKey = Book.getKey(board, colour);
		//System.out.println(String.format("%11X", BoardKey));
		
		try {
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(bookName))));
			
			while (true) {
				long key = dataInputStream.readLong();
				if (key == BoardKey) {
					short moveInt = dataInputStream.readShort();
					dataInputStream.readShort(); // weight
					dataInputStream.readInt();
					
					String moveString = int2MoveString(moveInt);
					
					int fromIndex = coords.indexOf(moveString.substring(0, 2));
					int toIndex = coords.indexOf(moveString.substring(2, 4));
					
					move = new Move(fromIndex, toIndex);
					
					if (board.isLegal(move, colour)) {
						moves.add(move);
					}
					

				} else {
					dataInputStream.skipBytes(8);
				}
			}
			
		} catch (Exception ignored) {
			//e.printStackTrace();
		}
		
		return moves;
	}
	
	
	

	public static boolean pawnForCapture(Board board) {

		int sqWithPawn = 0;
		int targetPce = board.colour ? 1 : 7;

		if (!board.FenEnPassant.equals("-")) {
			int index = coords.indexOf(board.FenEnPassant);
			if (board.colour) {
				sqWithPawn = index - 16;
			} else {
				sqWithPawn = index + 16;
			}

			if (board.board[sqWithPawn + 1] == targetPce) {
				return true;
			} else if (board.board[sqWithPawn - 1] == targetPce) {
				return true;
			}
		}
		return false;
	}

	public static long getKey(Board board, boolean colour) {
		long key = 0;

		int[] p = { -1, 1, 3, 5, 7, 9, 11, 0, 2, 4, 6, 8, 10 };

		for (int rank = 0; rank < 8; rank++) {
			// loop over board files
			for (int file = 0; file < 16; file++) {
				int square = rank * 16 + file;
				int piece = board.board[square];
				if (board.onBoard(square) && piece != 0) {
					key ^= random64[(64 * p[piece]) + (8 * rank) + file];
				}
			}

		}

		// castling

		int offset = 768;

		if (board.WHITE_CASTLING_SHORT) {
			key ^= random64[offset + 0];
		}
		if (board.WHITE_CASTLING_LONG) {
			key ^= random64[offset + 1];
		}
		if (board.BLACK_CASTLING_SHORT) {
			key ^= random64[offset + 2];
		}
		if (board.BLACK_CASTLING_LONG) {
			key ^= random64[offset + 3];
		}

		// en passant

		offset = 772;

		if (pawnForCapture(board)) {
			// get file of enpassant square
			int index = coords.indexOf(board.FenEnPassant);
			int indexFile = 0;

			for (int rank = 0; rank < 8; rank++) {
				for (int file = 0; file < 16; file++) {
					int square = rank * 16 + file;
					if (square == index) {
						indexFile = file;
					}
				}
			}
			System.out.println(indexFile);
			key ^= random64[offset + indexFile];
		}

		// side

		if (colour) {
			key ^= random64[780];
		}

		return key;
	}

}
