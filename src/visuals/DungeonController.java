package visuals;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
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
	private InventoryController inv;
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
		
		inv = new InventoryController();
		pu = new Popup();
		pu.getContent().add(inv);
		
		// With the popup it seems to have this problem of sticking to the screen when the game window is minimized
		// so we need to define a listener to the stage so we can close the popup as required. 
		currStage.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				// When the window is opened and the invOpen value is also true we want to show the inventory.
				// This might be the case when the user navigates away from the window but forgot to close the inv, they should
				// expect the inv to still be there when they come back
				if (newValue == true && invOpen == true) {
					invOpen = false;
					openInventory();
				} else {
					pu.hide();
				}
			}
			
		});
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
			// TODO: There seems to be a bug where if the player picks up a key first, any items picked up afterwards do not show
			// up in the inventory. 
			inv.showItems(board.getPlayerObject().getInventory().getInventoryItems());
			pu.show(currStage.getScene().getWindow(), currStage.getWidth()-150, currStage.getHeight()-600);
			invOpen = true;
		} else {
			pu.hide();
			invOpen = false;
		}
	}
}
