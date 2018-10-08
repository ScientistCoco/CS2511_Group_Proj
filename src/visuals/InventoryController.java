package visuals;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import other.Inventory;

public class InventoryController extends Pane {
		@FXML private GridPane inventoryGrid;
		@FXML private Text inventoryText;
		@FXML private StackPane[][] inventoryStackPane;	// We use a stackpane to hold the items. The gridpane is only to help with visually laying out the inventory.
		private int invColSize = 5;
		private int invRowSize = 7;
		private boolean invOpened = false; // True/false depending on whether the window is open or not. Default = false.
		private Inventory inv;
		
		public InventoryController() {
			this.setVisible(false);
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
		
		public void setInventory(Inventory inv) {
			this.inv = inv;
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

		}
		
		/**
		 * This method will show the description of the item to the user if the an item exists
		 * in the given row and col
		 * @param col
		 * @param row
		 */
		public void showDescription(int col, int row) {
			if (inventoryStackPane[col][row].getChildren().size() != 0) {
				System.out.println(inv.findItemByImage((ImageView) inventoryStackPane[col][row].getChildren().get(0)).getDescription());
			}
		}
		
		/**
		 * Receives an arraylist of imageview items and populates the inventory to show an image.
		 * @param items, an arraylist of imageview nodes
		 */
		public void showItems() {
			ArrayList<ImageView> items = inv.getInventoryItems();
			int curRow = 0;
		    int curCol = 0;
			for (ImageView item : items){
				if (curCol == 5) {
					curCol = 0;
					curRow++;
				} 					
				inventoryStackPane[curCol][curRow].getChildren().clear();
				inventoryStackPane[curCol][curRow].getChildren().add(0, item);
				curCol++;
			}
		}
		
		@FXML
		public void onCloseBtnClicked() {
			onActionInv();
		}
		
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
}
