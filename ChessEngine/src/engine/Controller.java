package engine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;


import java.util.List;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	@FXML
	private GridPane grid;
	@FXML
	private Button start;
	
	private Pane[][] paneArray;
	private List<Pane> paneList;
	
	private Board board;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneList = new ArrayList<>();
        paneArray = new Pane[8][8];
        this.board = new Board();
    }
	
    
	@FXML
	public void initBoard() {
		start.setVisible(false);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pane pane = new Pane();
                paneList.add(pane);
                paneArray[i][j] = pane;

                String light = "-fx-background-color: #e8ceab;";
                String dark = "-fx-background-color: #bc7944;";

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
                grid.add(pane, j, i);
            }
        }
		this.getPieces();
	}
	
	public void getPieces() {
		int[] board = this.board.board;
		
		for (int rank = 0; rank < 8; rank ++) {
			for (int file = 0; file < 16; file++)
			{
				int square = rank * 16 + file;
				if ((square & 0x88) == 0) {
					if (board[square] != 0) {
						int piece = board[square];
						int[] c = convert0x64(square);
						System.out.println("Piece: " + piece + " @ " + square + " / " + c[0] + "-" + c[1]);
					}
				}
			}
		}
	}

	
	
	public int convert0x88(int y, int x) {
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

	public int[] convert0x64(int index) {
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
