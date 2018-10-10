package visuals;

import java.util.ArrayList;

import javafx.event.EventHandler;
/**
 * Notes: The Game will be 1280 x 800 in dimensions. In that case we should make the maximum size for the map to be 704 x 704.
 * That means a 22 x 22 matrix is the biggest we can make
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
	@FXML private TextArea systemTextUpdates;
	@FXML private HBox buffBar;	// Size is 185 (W) * 42 (H)
	private ArrayList<ImageView> buffs;
	private InventoryController inv;
	@FXML private AnchorPane inventoryPane;
	
	private Stage currStage;
	private Board board;
	private int rowSize = 10;
	private int colSize = 10;
	
	public DungeonController(Stage s, FXMLLoader loader) {
		currStage = s;
	}
	
	@FXML
	public void initialize() {
		board = new level2().getBoard();
		this.buffs = new ArrayList<ImageView>();
		
		// Now the gridpane will observe the board:
		for (int i = 0; i < colSize; i ++) {
			for (int j = 0; j < rowSize; j ++ ) {
				baseMap.add(board.getFloor(i, j), i, j);		
			}
		}
		
		objectivesList.getChildren().clear();
		objectivesList.getChildren().addAll(board.getObjectivesOnThisBoard().getObjectives());
		inv = new InventoryController();
		inv.setPlayer(board.getPlayerObject());
		inv.setSystemTextUpdates(systemTextUpdates);
		inventoryPane.getChildren().add(inv);
		systemTextUpdates.setEditable(false);
		
		// We add a key listener to the base map so we can determine whether the player wants to move or just
		// change directions. We do this by measuring how long the key was pressed down for.
		// If it was pressed for < 100 ms then we assume the player just wants to change direction.
		// If it was pressed for > 100 ms then we assume the player wants to move.
		base.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
			long startTime;
			
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().isArrowKey()) {
					if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
						startTime = System.currentTimeMillis();
					} else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
						long length = System.currentTimeMillis() - startTime;
						if (length < 50) {
							board.getPlayerObject().changeDirection(event.getCode().toString());
						} else {
							board.getPlayerObject().moveSelf(event.getCode().toString());
							inv.showItems();
						}
					}
				}
			}
		});
	}
	
	/**
	 * This method updates the buffs on the buffBar view
	 */
	public void updateBuffs() {		
		this.buffs = board.getPlayerObject().getBuffs();
		buffBar.getChildren().clear();
		for (ImageView b : this.buffs) {
			buffBar.getChildren().add(b);
		}
	}
	
	// This method handles events that are not arrow keys
	@FXML
	public void onKeyPressed(KeyEvent key) {
		updateBuffs();
		if (key.getCode().toString().equals("I")) {
			inv.onActionInv();		
		} 
		inv.showItems();	// Gets the inventoryController to check if there has been new additions. So that if the player has the inv open and they pick up an item, it will show up in their inv.
	}
	
}
