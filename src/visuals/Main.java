package visuals;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(800);
		primaryStage.setWidth(1280);
		
		MenuScreen menuScreen = new MenuScreen(primaryStage);
		menuScreen.start();
		
		//GameCompleteScreen screen = new GameCompleteScreen(primaryStage);
		//screen.start();
		//DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		//dungeonScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
