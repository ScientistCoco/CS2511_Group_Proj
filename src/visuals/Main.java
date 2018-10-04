package visuals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(800);
		primaryStage.setWidth(1280);
		
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		dungeonScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
