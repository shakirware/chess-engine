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
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneList = new ArrayList<>();
        paneArray = new Pane[8][8];
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
		
	}

}
