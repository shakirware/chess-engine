package engine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;


import java.net.URL;

import static engine.Pieces.*;

public class Controller implements Initializable {

	@FXML
	private GridPane grid;
	@FXML
	private Button start;

	private Pane[][] paneArray;
	private List<Pane> paneList;

	private Pane paneToMove;
	private int from;
	private int to;

	private Board board;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		paneList = new ArrayList<>();
		paneArray = new Pane[8][8];
		this.board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
	}

	@FXML
	public void initBoard() {
		start.setVisible(false);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int[] board = this.board.board;
				int square = convert0x88(i, j);
				int piece = board[square];

				Pane pane = new Pane();
				paneList.add(pane);
				paneArray[i][j] = pane;

				pane.setOnMousePressed((MouseEvent event) -> {

					if (this.board.inCheckmate()) {
						mateAlert();
					} else {

						if (paneToMove == null) {
							pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
									CornerRadii.EMPTY, new BorderWidths(3))));
							paneToMove = pane;
							from = square;
						} else {
							System.out.println("FROM: " + from + " TO: " + to);
							to = square;
							
							Move playerMove = new Move(from, to);
							
							ArrayList<Move> moves = this.board.getLegalMoves(true);
							
							for (Move LegalMove : moves) {
								if (LegalMove.equals(playerMove)) {
									this.board.makeMove(playerMove);
									initBoard();
									
									if (this.board.inCheckmate()) {
										mateAlert();
										return;
									}

									Move ai_move = MiniMax.getNextMove(this.board, BLACK);
									ai_move.output();
									this.board.makeMove(ai_move);
									initBoard();
								}
							}
							paneToMove.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
									CornerRadii.EMPTY, new BorderWidths(0))));
							paneToMove = null;
						}
					}
				});

				String light = "-fx-background-color: #e8ceab;";
				String dark = "-fx-background-color: #bc7944;";
				String bc = "-fx-background-size: 80 80;" + "-fx-background-position: center;";

				if (piece != 0) {
					if (i % 2 == 0) {
						if (j % 2 == 0) {
							// light
							pane.setStyle(light + "-fx-background-image: url('" + pieceImages[piece] + "');"
									+ bc);

						} else {
							// dark
							pane.setStyle(dark + "-fx-background-image: url('" + pieceImages[piece] + "');"
									+ bc);
						}
					} else {
						if (j % 2 != 0) {
							pane.setStyle(light + "-fx-background-image: url('" + pieceImages[piece] + "');"
									+ bc);
						} else {
							pane.setStyle(dark + "-fx-background-image: url('" + pieceImages[piece] + "');"
									+ bc);
						}
					}
				} else {
					if (i % 2 == 0) {
						if (j % 2 == 0) {
							pane.setStyle(light);
						} else {
							pane.setStyle(dark);
						}
					} else {
						if (j % 2 != 0) {
							pane.setStyle(light);
						} else {
							pane.setStyle(dark);
						}
					}

				}

				grid.add(pane, j, i);
			}
		}
	}
	
	public void mateAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Checkmate sir!");
		alert.showAndWait();
	}

	public int convert0x88(int y, int x) {
		int index = 0;
		switch (y + 1) {
		case 1:
			index = ((y * 16) + x) + 112;
			break;
		case 2:
			index = ((y * 16) + x) + 80;
			break;
		case 3:
			index = ((y * 16) + x) + 48;
			break;
		case 4:
			index = ((y * 16) + x) + 16;
			break;
		case 5:
			index = ((y * 16) + x) - 16;
			break;
		case 6:
			index = ((y * 16) + x) - 48;
			break;
		case 7:
			index = ((y * 16) + x) - 80;
			break;
		case 8:
			index = ((y * 16) + x) - 112;
			break;
		}
		return index;
	}

	public int[] convert0x64(int index) {
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
