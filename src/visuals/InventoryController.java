package visuals;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class InventoryController extends Pane {
		@FXML
		private GridPane inventoryGrid;
		
		public InventoryController() {
			super();
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
		
		@FXML
		private void initialize() {
			// We want to initialize each cell in the grid with a darker background colour to make the grid more distinct
			// The grid size is 5 * 7
			/*for (int i = 0; i < 5; i ++) {
				for (int j = 0; j < 7; j ++) {
					inventoryGrid.
				}
			}*/
		}

}
