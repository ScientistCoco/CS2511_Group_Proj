package visuals;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Board;

public class DesignController {

	private Stage currStage;
	private int rowSize = 10;
	private int colSize = 10;
	private Board board;
	@FXML private GridPane grid;
	@FXML private AnchorPane itemsPane;
	
	public DesignController(Stage s) {
		this.board = new Board();
		this.currStage = s;
	}
	
	@FXML
	public void initialize() {
		for (int i = 0; i < colSize; i ++) {
			for (int j = 0; j < rowSize; j ++ ) {
				grid.add(board.getFloor(i, j), i, j);		
			}
		}
		ItemsController its = new ItemsController(this.currStage);
		itemsPane.getChildren().add(its);
	}

}
