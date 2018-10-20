package visuals;
import java.util.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import items.Item;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Board;
import other.Inventory;
import player.Player;

public class InventoryController extends Pane {
		@FXML private GridPane inventoryGrid;
		@FXML private Text inventoryText;
		@FXML private StackPane[][] inventoryStackPane;	// We use a stackpane to hold the items. The gridpane is only to help with visually laying out the inventory.
		private int invColSize = 5;
		private int invRowSize = 7;
		private boolean invOpened = false; // True/false depending on whether the window is open or not. Default = false.
		private Board board;
		private Player player;
		private Inventory inv;
		private ArrayList<ImageView> items;
		@FXML private TextArea systemTextUpdates;
		@FXML private AnchorPane itemDescriptionPane;
		@FXML private Text itemDescription;
		@FXML private Label itemName;
		private Stage currStage;
		
		public InventoryController(Stage s, Board board) {
			this.setVisible(false);
			this.board = board;
			this.currStage = s;
			inventoryStackPane = new StackPane[5][7];
			try {
				FXMLLoader l = new FXMLLoader(getClass().getResource("/visuals/inventory_window.fxml"));
				l.setRoot(this);
				l.setController(this);
				l.load();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void setPlayer(Player player) {
			this.player = player;
			this.inv = this.player.getInventory();
		}
		
		public void setSystemTextUpdates(TextArea stext) {
			this.systemTextUpdates = stext;
		}
		
		@FXML
		private void initialize() {
			// We want to initialize each cell in the grid with a darker background colour to make the grid more distinct
			// The grid size is 5 * 7
			for (int i = 0; i < invColSize; i ++) {
				for (int j = 0; j < invRowSize; j ++) {
					inventoryStackPane[i][j] = new StackPane();
					inventoryStackPane[i][j].getStylesheets().add("/visuals/application.css");	// Preload stylesheet
					inventoryStackPane[i][j].getStyleClass().add("inventory-cells");	
					inventoryGrid.add(inventoryStackPane[i][j], i, j);
				}
			}	
			inventoryGrid.getChildren().forEach((item) -> {
				item.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
					int row = inventoryGrid.getRowIndex(item);
					int col = inventoryGrid.getColumnIndex(item);
					showDescription(col, row);
				});
			});
			inventoryGrid.getChildren().forEach((item) -> {
				item.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
					int row = inventoryGrid.getRowIndex(item);
					int col = inventoryGrid.getColumnIndex(item);
					hideDescription();
				});
			});
			inventoryGrid.getChildren().forEach((item) -> {
				item.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
					int row = inventoryGrid.getRowIndex(item);
					int col = inventoryGrid.getColumnIndex(item);
					useInventoryItem(col, row);
				});
			});

		}
		
		/**
		 * This method uses the item in the given col and row if there is one
		 * @param col
		 * @param row
		 */
		public void useInventoryItem(int col, int row) {
			if (inventoryStackPane[col][row].getChildren().size() != 0) {
				Item item = inv.findItemByImage((ImageView) inventoryStackPane[col][row].getChildren().get(0));
				systemTextUpdates.appendText(inv.useItem(this.player, item.getItemName()) + "\n");
				
				// player could be killed by using bomb within one square distance
				if (item.getItemName().equals("Bomb")) {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									checkPlayerDeath();
								}					
							});
							
						}
					}, 1201);
				}
				
				showItems();
			}
		}
		
		/**
		 * This method will show the description of the item to the user if the an item exists
		 * in the given row and col
		 * @param col
		 * @param row
		 */
		public void showDescription(int col, int row) {
			if (inventoryStackPane[col][row].getChildren().size() != 0) {
				//System.out.println(inv.findItemByImage((ImageView) inventoryStackPane[col][row].getChildren().get(0)).getDescription());			
				Item it = inv.findItemByImage((ImageView) inventoryStackPane[col][row].getChildren().get(0));
				itemName.setText(it.getItemName());
				itemDescription.setText(it.getDescription());
				itemDescriptionPane.setOpacity(1);
				
			}
		}
		
		/** This method hides the description of the item from the user
		 * 
		 */
		public void hideDescription() {
			itemDescriptionPane.setOpacity(0);
		}
		
		/**
		 * Receives an arraylist of imageview items and populates the inventory to show an image.
		 * @param items, an arraylist of imageview nodes
		 */
		public void showItems() {
			this.items = inv.getInventoryItems();
			int curRow = 0;
		    int curCol = 0;
		    
		    // First clear the inventoryStackPane;
		    for (int i = 0; i < invColSize; i ++) {
		    	for (int j = 0; j < invRowSize; j++) {
		    		inventoryStackPane[i][j].getChildren().clear();
		    	}
		    }
		    
			for (ImageView item : this.items){
				if (curCol == 5) {
					curCol = 0;
					curRow++;
				} 					
				inventoryStackPane[curCol][curRow].getChildren().add(0, item);
				curCol++;
			}			
		}
		
		/**
		 * This is the event that happens when the user pressed on the 'x' button on the inventory
		 */
		@FXML
		public void onCloseBtnClicked() {
			onActionInv();
		}
		
		/**
		 * This method determines whether to close the inventory or to open the inventory
		 */
		public void onActionInv() {
			if (this.invOpened == false) {
				this.invOpened = true;
				showItems();
				this.setVisible(true);

			} else {
				this.invOpened = false;
				this.setVisible(false);
			}
		}
		public void checkPlayerDeath() {
			if (board.getPlayerObject() == null) {
				LevelFailScreen sc = new LevelFailScreen(currStage);
				sc.start();
			}
		}
}
