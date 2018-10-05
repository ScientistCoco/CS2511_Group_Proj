package visuals;

/**
 * Notes: The Game will be 1280 x 800 in dimensions. In that case we should make the maximum size for the map to be 704 x 704.
 * That means a 22 x 22 matrix is the biggest we can make
 */
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import javafx.stage.Stage;
import levels.level1;
import levels.level2;
import other.Board;

public class DungeonController {
	@FXML
	private StackPane background;	// This is used as empty space... visual purposes so that we can resize the playable map
	@FXML
	private GridPane baseMap;
	@FXML
	private VBox objectivesList;
	
	private Stage currStage;
	private boolean invOpen = false;	// True/false depending on whether the window is open or not. Default = false.
	private Popup pu;
	
	public DungeonController(Stage s) {
		currStage = s;
	}
	
	private Board board;
	
	@FXML
	public void initialize() {
		board = new level2().getBoard();
		
		//baseMap.getRowConstraints().add(new RowConstraints(10));	// To get it to display with no cropping we set the constraints to be the size of the grid
		//baseMap.getColumnConstraints().add(new ColumnConstraints(10));
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j ++ ) {
				baseMap.add(board.getFloor(i, j), i, j);		
			}
		}
	
		objectivesList.getChildren().clear();
		objectivesList.getChildren().addAll(board.getObjectivesOnThisBoard().getObjectives());
		
		InventoryController inv = new InventoryController();
		pu = new Popup();
		pu.getContent().add(inv);
	}
	
	
	// This method handles the keyboard events - right,left,up and down arrow keys
	@FXML
	public void onKeyPressed(KeyEvent key) {
		//System.out.println(key.getCode());
		if (key.getCode().toString().equals("I")) {
			openInventory();
		} else {
			board.getPlayerObject().moveSelf(key.getCode().toString());
		}
	}
	
	@FXML
	public void openInventory() {
		if (invOpen == false) {
			pu.show(currStage.getScene().getWindow(), currStage.getWidth()-150, currStage.getHeight()-600);
			invOpen = true;
		} else {
			pu.hide();
			invOpen = false;
		}
	}
}
