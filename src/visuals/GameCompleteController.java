package visuals;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GameCompleteController {
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	
	public GameCompleteController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		
	}
	
}
