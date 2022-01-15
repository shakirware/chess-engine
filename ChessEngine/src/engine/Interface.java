/**
 * 
 */
package engine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * @author shakir
 *
 */

public class Interface {

	private Board board;
	private Search search;
	private int from;
	private int to;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private JPanel chessBoard;
	private final JLabel message = new JLabel(
			"Chess Engine is ready to play!");
	private static final String COLS = "ABCDEFGH";
	public static final int QUEEN = 0, KING = 1,
			ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = {
			ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
	};
	public static final int BLACK = 0, WHITE = 1;
	public JButton pieceToMoveButton = null;   

	public Interface() {
		initializeGui();
		this.board = new Board();
		this.search = new Search(this.board, 3);
	}

	public final void initializeGui() {
		// create the images for the chess pieces
		createImages();

		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				setupNewGame();
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("Save")); // TODO - add functionality!
		tools.add(new JButton("Restore")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9)) {

			/**
			 * Override the preferred size to return the largest it can, in
			 * a square shape.  Must (must, must) be added to a GridBagLayout
			 * as the only component (it uses the parent as a guide to size)
			 * with no GridBagConstaint (so it is centered).
			 */
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null) {
					prefSize = new Dimension(
							(int)d.getWidth(),(int)d.getHeight());
				} else if (c!=null &&
						c.getWidth()>d.getWidth() &&
						c.getHeight()>d.getHeight()) {
					prefSize = c.getSize();
				} else {
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w>h ? h : w);
				return new Dimension(s,s);
			}
		};
		chessBoard.setBorder(new CompoundBorder(
				new EmptyBorder(8,8,8,8),
				new LineBorder(Color.BLACK)
				));
		// Set the BG to be ochre
		Color ochre = new Color(204,119,34);
		chessBoard.setBackground(ochre);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				ImageIcon icon = new ImageIcon(
						new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						//) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[jj][ii] = b;
				int x = jj;
				int y = ii;
				//Add event listener
				ActionListener buttonListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedButton(x, y);
					}
				};
				chessBoardSquares[jj][ii].addActionListener(buttonListener);


			};
		}


		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(
					new JLabel(COLS.substring(ii, ii + 1),
							SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					chessBoard.add(new JLabel("" + (9-(ii + 1)),
							SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
		}

		setupNewGame();
	}

	public final JComponent getGui() {
		return gui;
	}

	private final void createImages() {
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(
							jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	private final void setupNewGame() {
		message.setText("Make your move!");
		// set up the black pieces
		for (int ii = 0; ii < STARTING_ROW.length; ii++) {
			chessBoardSquares[ii][0].setIcon(new ImageIcon(
					chessPieceImages[BLACK][STARTING_ROW[ii]]));
		}
		for (int ii = 0; ii < STARTING_ROW.length; ii++) {
			chessBoardSquares[ii][1].setIcon(new ImageIcon(
					chessPieceImages[BLACK][PAWN]));
		}
		// set up the white pieces
		for (int ii = 0; ii < STARTING_ROW.length; ii++) {
			chessBoardSquares[ii][6].setIcon(new ImageIcon(
					chessPieceImages[WHITE][PAWN]));
		}
		for (int ii = 0; ii < STARTING_ROW.length; ii++) {
			chessBoardSquares[ii][7].setIcon(new ImageIcon(
					chessPieceImages[WHITE][STARTING_ROW[ii]]));
		}
	}


	private int convert0x88(int y, int x) {
		int index = 0;
		switch (y+1) {
		case 1:
			index = ((y*16) +x) + 112;
			break;
		case 2:
			index = ((y*16) +x) + 80;
			break;
		case 3:
			index = ((y*16) +x) + 48;
			break;
		case 4:
			index = ((y*16) +x) + 16;
			break;
		case 5:
			index = ((y*16) +x) - 16;
			break;
		case 6:
			index = ((y*16) +x) - 48;
			break;
		case 7:
			index = ((y*16) +x) - 80;
			break;
		case 8:
			index = ((y*16) +x) - 112;
			break;
		}
		return index;
	}

	private int[] convert0x64(int index) {
		int x = 0;
		int y = 0;

		if (index <= 7) {
			y = 7;
			x = index;
		}
		else if ((index >= 16) && (index <= 32)) {
			y = 6;
			x = index - 8;
			x = index % 8;
		}
		else if ((index >= 32) && (index <= 39)) {
			y = 5;
			x = index - 16;
			x = index % 8;
		}
		else if ((index >= 48) && (index <= 55)) {
			y = 4;
			x = index - 24;
			x = index % 8;
		}
		else if ((index >= 64) && (index <= 71)) {
			y = 3;
			x = index - 32;
			x = index % 8;
		}
		else if ((index >= 80) && (index <= 87)) {
			y = 2;
			x = index - 40;
			x = index % 8;
		}
		else if ((index >= 96) && (index <= 103)) {
			y = 1;
			x = index - 48;
			x = index % 8;
		}
		else if ((index >= 112) && (index <= 119)) {
			y = 0;
			x = index - 56;
			x = index % 8;
		}
		int[] coords = {x,y};
		return coords;
	}




	private void pressedButton(int x, int y) {
		JButton button = (JButton)chessBoardSquares[x][y];

		if (pieceToMoveButton == null)   
		{
			from = convert0x88(y, x);
			pieceToMoveButton = button;
		}
		else    
		{
			to = convert0x88(y, x);
			//int index = convert0x88(y, x);

			ArrayList<Move> moves = this.board.getLegalMoves();
			Move move1 = new Move(from, to);
			for (Move move : moves) {
				if (move.equals(move1)) {
					//move.output();

					this.makeMove(pieceToMoveButton, button);
					this.board.makeMove(move1);
					//System.out.print(this.board.colour);

					Move ai_move = search.miniMax();
					ai_move.output();
					int[] from = convert0x64(ai_move.from);
					
					System.out.println(ai_move.from);
					System.out.println(from[0]);
					System.out.println(from[1]);
					
					
					int[] to = convert0x64(ai_move.to);
					
					
					
					JButton from_button = (JButton)chessBoardSquares[from[0]][from[1]];
					JButton to_button = (JButton)chessBoardSquares[to[0]][to[1]];
					this.board.makeMove(ai_move);
					this.makeMove(from_button, to_button);
				}
			}
			pieceToMoveButton = null; 
		}
	}

	public void makeMove(JButton from_button, JButton to_button) {
		ImageIcon icon = (ImageIcon) from_button.getIcon();
		from_button.setIcon(null);
		to_button.setIcon(icon);
	}


	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Interface cg = new Interface();

				JFrame f = new JFrame("Chess Engine");
				f.add(cg.getGui());
				// Ensures JVM closes after frame(s) closed and
				// all non-daemon threads are finished
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// See https://stackoverflow.com/a/7143398/418556 for demo.
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		// Swing GUIs should be created and updated on the EDT
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
	}
}