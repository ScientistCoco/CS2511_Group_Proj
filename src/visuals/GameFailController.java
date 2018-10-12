package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameFailController{
	
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	@FXML Button yesBtn;
	@FXML Button noBtn;

	
	public GameFailController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		
	}
	
	@FXML
	public void clickYes() {
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
	
	@FXML
	public void clickNo() {
		MenuScreen ms = new MenuScreen(stage);
		ms.start();
	}
}