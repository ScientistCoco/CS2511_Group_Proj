package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private Button DesignMode;
	@FXML
	private Button PlayMode;

	private Stage currStage;
	
	public MenuController(Stage stage) {
		currStage = stage;
	}
	/*
	@FXML
	public void initialize() {
		
	}
	*/
	@FXML
	public void handlePlayMode() {
		DungeonScreen dungeonScreen = new DungeonScreen(currStage);
		dungeonScreen.start();
	}
	
	@FXML
	public void handleDesignMode() {
		DesignScreen designScreen = new DesignScreen(this.currStage);
		designScreen.start();
		
	}
}
