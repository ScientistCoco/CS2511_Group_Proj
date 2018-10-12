package visuals;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Board;

public class DesignController {

	private Stage currStage;
	private int rowSize = 10;
	private int colSize = 10;
	private Board board;
	@FXML private GridPane grid;
	
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
	}

}
