package visuals;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the screen that will be shown when the player has completed the level
 * @author court
 *
 */
public class GameCompleteScreen {
	private Stage stage;
	private FXMLLoader fxmlLoader;
	
	public GameCompleteScreen(Stage s) {
		this.stage = s;
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/level_complete_window.fxml"));
	}
	
	public void start() {
		GameCompleteController comController = new GameCompleteController(stage);
		fxmlLoader.setController(comController);
		
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 800, 1280);
			this.stage.setScene(sc);			
			Timeline fadeIn = new Timeline();
			root.setOpacity(0.10);
			// Screen will fade in
			fadeIn.setCycleCount(Timeline.INDEFINITE);
			fadeIn.getKeyFrames().add(
					new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {						
						public void handle(ActionEvent t) {
							root.setOpacity(root.getOpacity()+0.10);
							if (root.getOpacity() > 0.9) {
								showStage();
								fadeIn.stop();
							}
						}
					}
			));
			fadeIn.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showStage() {
		this.stage.show();
	}
}
