package visuals;

/**
 * Notes: The Game will be 1280 x 800 in dimensions. In that case we should make the maximum size for the map to be 704 x 704.
 * That means a 22 x 22 matrix is the biggest we can make
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import levels.level1;
import levels.level2;
import other.Board;

public class DungeonController {
	@FXML private AnchorPane base;
	@FXML private GridPane baseMap;
	@FXML private VBox objectivesList;
	
	private InventoryController inv;
	@FXML private AnchorPane inventoryPane;
	
	private Stage currStage;
	private Board board;
	
	public DungeonController(Stage s, FXMLLoader loader) {
		currStage = s;
	}
	
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
		inv = new InventoryController();
		inv.setInventory(board.getPlayerObject().getInventory());
		inventoryPane.getChildren().add(inv);
	}
	
	
	// This method handles the keyboard events - right,left,up and down arrow keys
	@FXML
	public void onKeyPressed(KeyEvent key) {
		// TODO: If player has inventory open and they pick up an item, it should automatically show up in the inventory.
		if (key.getCode().toString().equals("I")) {
			inv.onActionInv();		
		} else {
			board.getPlayerObject().moveSelf(key.getCode().toString());
		}
	}
	
}
