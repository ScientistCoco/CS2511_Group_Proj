package visuals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameCompleteScreen {
	private Stage stage;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public GameCompleteScreen(Stage s) {
		this.stage = s;
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/game_complete_window.fxml"));
	}
	
	public void start() {
		GameCompleteController controller = new GameCompleteController(this.stage);
		fxmlLoader.setController(controller);
		
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 800, 1280);
			this.stage.setScene(sc);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
