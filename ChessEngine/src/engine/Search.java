package engine;

import java.util.ArrayList;

public class Search {

	public Board board;
	public int max_depth;
	private int temp_depth;
	private int[] material_score = {0, 100, 300, 350, 500, 1000, 10000, -100, -300, -350, -500, -1000, -10000};
	private final int winVal = 1000000;
	private final int loseVal = -1000000;
	
	
	public Search(Board board, int max_depth) {
		this.board = board;
		this.max_depth = max_depth;
	}

	public Move miniMax() {
		Move bestmove = null;
		for (int depth = 1; depth < this.max_depth; depth++) {
			ArrayList<Move> moves = this.board.getLegalMoves(false);
			int currUtil = loseVal;
			int bestUtil = loseVal;
			this.temp_depth = depth;
			for(Move move: moves) {
				Board boardCopy = new Board(this.board);
				boardCopy.makeMove(move);
				currUtil = min(boardCopy, 1);
				if (bestUtil <= currUtil) {
					bestUtil = currUtil;
					bestmove = move;
					if(bestUtil == winVal) {
						bestmove = move;
					}
					if(depth+1 == this.max_depth) {
						bestmove = move;
					}
				}
			}
		}
		return bestmove;
	}
	
	public int min(Board board, int depth) {
		if (depth > this.temp_depth) {
			return getScore(board, true);
		}
		int min = winVal;
		int curr;
		ArrayList<Move> moves = board.getLegalMoves(true);
		for(Move move: moves) {
			Board boardCopy = new Board(board);
			boardCopy.makeMove(move);
			curr = max(boardCopy, depth + 1);
			if (curr < min) {
				min = curr;
			}
		}
		return min;
	}
	
	public int max(Board board, int depth) {
		if (depth > this.temp_depth) {
			return getScore(board, false);
		}
		int max = loseVal;
		int curr;
		ArrayList<Move> moves = board.getLegalMoves(false);
		for(Move move: moves) {
			Board boardCopy = new Board(board);
			boardCopy.makeMove(move);
			curr = min(boardCopy, depth + 1);
			if (curr > max) {
				max = curr;
			}
		}
		return max;
	}
	
	public int getScore(Board board, boolean colour) {
		// check mate for player
		if (board.isCheckmate(!colour)) {
			return loseVal;
		}
		if (board.isCheckmate(colour)) {
			return winVal;
		}
		else {
			return getMaterial(board, colour);
		}
	}
	
	
	public int getMaterial(Board board, boolean colour) {
		int score = 0;
		for (int square = 0; square < 128; square++)
		{
			if (board.onBoard(square)) {
				int piece = board.board[square];
				if (piece >= 1 && piece <=6) {
				score += material_score[piece];
				}
				if (piece > 6 && piece < 12) {
					score -= material_score[piece];
				}
			}
		}
		return colour ? score : -score;
	}
}
