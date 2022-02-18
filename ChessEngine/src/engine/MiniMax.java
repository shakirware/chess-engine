package engine;

import java.util.ArrayList;

public class MiniMax {
	static Move nextMove;
	static int depth;
	static int counter;
	static int tempDepth;
	static int[] material_score = { 0, 100, 300, 350, 500, 1000, 10000, 100, 300, 350, 500, 1000, 10000 };
	
    protected static int [] pawnTable = {
            0,  0,  0,  0,  0,  0,  0,  0,
            50, 50, 50, 50, 50, 50, 50, 50,
            10, 10, 20, 30, 30, 20, 10, 10,
            5,  5, 10, 25, 25, 10,  5,  5,
            0,  0,  0, 20, 20,  0,  0,  0,
            5, -5,-10,  0,  0,-10, -5,  5,
            5, 10, 10,-20,-20, 10, 10,  5,
            0,  0,  0,  0,  0,  0,  0,  0 };

          // Placement Precedence for all Knights
        protected static int [] knightTable = {
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50 };

        protected static int [] bishopPrecedence = {
            -20,-10,-10,-10,-10,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5, 10, 10,  5,  0,-10,
            -10,  5,  5, 10, 10,  5,  5,-10,
            -10,  0, 10, 10, 10, 10,  0,-10,
            -10, 10, 10, 10, 10, 10, 10,-10,
            -10,  5,  0,  0,  0,  0,  5,-10,
            -20,-10,-10,-10,-10,-10,-10,-20 };

        protected static int [] rookTable = {
              0,  0,  0,  0,  0,  0,  0,  0,
              5, 10, 10, 10, 10, 10, 10,  5,
             -5,  0,  0,  0,  0,  0,  0, -5,
             -5,  0,  0,  0,  0,  0,  0, -5,
             -5,  0,  0,  0,  0,  0,  0, -5,
             -5,  0,  0,  0,  0,  0,  0, -5,
             -5,  0,  0,  0,  0,  0,  0, -5,
              0,  0,  0,  5,  5,  0,  0,  0 };
          
        protected static int [] queenTable = {
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5,  5,  5,  5,  0,-10,
             -5,  0,  5,  5,  5,  5,  0, -5,
              0,  0,  5,  5,  5,  5,  0, -5,
            -10,  5,  5,  5,  5,  5,  0,-10,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20 };

        protected static int [] kingTable = {
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -20,-30,-30,-40,-40,-30,-30,-20,
            -10,-20,-20,-20,-20,-20,-20,-10,
             20, 20,  0,  0,  0,  0, 20, 20,
             20, 30, 10,  0,  0, 10, 30, 20 };

	public static void setDepth(int depth) {
		MiniMax.depth = depth;
	}

	public static Move getNextMove(Board board, boolean side) {
		counter = 0;
		minimax(board, side, true, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
		System.out.println("Boards evaluated: " + counter);
		return nextMove;
	}

	private static int minimax(Board board, boolean colour, boolean maximize, int alpha, int beta, int d) {
		counter++;

		if (d == 0 || board.isStalemate(colour) || board.isStalemate(!colour) || board.isCheckmate(!colour)) {
			return eval(board, colour);
		}

		//if (d == 0) {
		// eval(board, colour);
		//}
		
		int bestv = 0;
		int newv = 0;
			
		if (maximize) {
			bestv = Integer.MIN_VALUE;
			// Check all next possible boards
			for (Board nextBoard : getNextBoards(board, colour)) {
				// Recursive call
				newv = minimax(nextBoard, colour, false, alpha, beta, d - 1);
				if (newv >= bestv) { // Check if we have new max
					bestv = newv;
					if (d == depth) {
						nextMove = nextBoard.lastMove;
					}
				}
				alpha = Math.max(alpha, bestv);
		          if (beta <= alpha) { 
		              return bestv;
		          }
			}
			return bestv;	
		} else {
			bestv = Integer.MAX_VALUE;
			// Check all next possible boards
			for (Board nextBoard : getNextBoards(board, !colour)) {
				// Recursive call
				newv = minimax(nextBoard, colour, true, alpha, beta, d - 1);
				if (newv <= bestv) { // Check if we have a new min
					bestv = newv;
				}
			}
          beta = Math.min(beta, bestv);
          if (beta <= alpha) { //Useless branch - don't explore
              return bestv;
          }
		}
		return bestv;
	}

	public static ArrayList<Board> getNextBoards(Board board, boolean colour) {
		ArrayList<Board> nextBoards = new ArrayList<>();

		ArrayList<Move> moves = board.getLegalMoves(colour);
		for (Move move : moves) {
			Board b = new Board(board);
			b.makeMove(move);
			nextBoards.add(b);
		}
		return nextBoards;
	}

	public static int eval(Board board, boolean colour, int depth) {
		
		
		if (board.isCheckmate(!colour)) {
			if (depth <= tempDepth) {
				tempDepth = depth;
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}
		
		if (board.isStalemate(colour) || board.isStalemate(!colour) || board.isCheckmate(colour)) {
			return Integer.MIN_VALUE;
		}
		
		return getMaterials(board);
	}
	
	public static int getMaterials(Board board) {
		int val = 0;
        int wPawns = 0, wRooks = 0, wBishops = 0, wQueens = 0, wKnights = 0, wKings = 0;
        int bPawns = 0, bRooks = 0, bBishops = 0, bQueens = 0, bKnights = 0, bKings = 0;
        
        
        for (int square = 0; square < 128; square++) {
			if (board.onBoard(square)) {
				
				int piece = board.board[square];
				int[] c = convert0x64(square);
				switch (piece) {
				case 1:
					wPawns++;
					val += (100 + (pawnTable[8*(7 - row) + col])); 
				
				}
				
				
				if (piece >= 1 && piece <= 6) {
					whitePiecesValue += material_score[piece];
				}
				if (piece > 6 && piece < 12) {
					blackPiecesValue += material_score[piece];
				}
			}
		}
        
        
		
		int blackPiecesValue = 0;
		int whitePiecesValue = 0;
		for (int square = 0; square < 128; square++) {
			if (board.onBoard(square)) {
				int piece = board.board[square];
				if (piece >= 1 && piece <= 6) {
					whitePiecesValue += material_score[piece];
				}
				if (piece > 6 && piece < 12) {
					blackPiecesValue += material_score[piece];
				}
			}
		}
		return blackPiecesValue - whitePiecesValue;

	}
	
	public static int[] convert0x64(int index) {
		int x = 0;
		int y = 0;

		if (index <= 7) {
			y = 7;
			x = index;
		} else if ((index >= 16) && (index <= 32)) {
			y = 6;
			x = index - 8;
			x = index % 8;
		} else if ((index >= 32) && (index <= 39)) {
			y = 5;
			x = index - 16;
			x = index % 8;
		} else if ((index >= 48) && (index <= 55)) {
			y = 4;
			x = index - 24;
			x = index % 8;
		} else if ((index >= 64) && (index <= 71)) {
			y = 3;
			x = index - 32;
			x = index % 8;
		} else if ((index >= 80) && (index <= 87)) {
			y = 2;
			x = index - 40;
			x = index % 8;
		} else if ((index >= 96) && (index <= 103)) {
			y = 1;
			x = index - 48;
			x = index % 8;
		} else if ((index >= 112) && (index <= 119)) {
			y = 0;
			x = index - 56;
			x = index % 8;
		}
		int[] coords = { x, y };
		return coords;
	}
}