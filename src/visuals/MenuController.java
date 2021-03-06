package visuals;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import media.MusicPlayer;

public class MenuController {
	@FXML private Button DesignMode;
	@FXML private Button PlayMode;
	@FXML ImageView char1;
	@FXML ImageView char2;
	@FXML ImageView char3;
	private String musicFile = "Winds Of Stories.mp3";
	private Stage currStage;
	private MusicPlayer musicPlayer;
	
	public MenuController(Stage stage) {
		currStage = stage;
	}
	
	@FXML
	public void initialize() {
		// Animation effects to the character images...
		FadeTransition ftChar1 = new FadeTransition(Duration.millis(3000), char1);
		ftChar1.setFromValue(1.0);
		ftChar1.setToValue(0.4);
		ftChar1.setCycleCount(Transition.INDEFINITE);
		ftChar1.setAutoReverse(true);
		ftChar1.play();
		
		FadeTransition ftChar2 = new FadeTransition(Duration.millis(3000), char2);
		ftChar2.setFromValue(0.4);
		ftChar2.setToValue(1.0);
		ftChar2.setCycleCount(Transition.INDEFINITE);
		ftChar2.setAutoReverse(true);
		ftChar2.play();
		
		// Also set a background music track to play					
		this.musicPlayer = new MusicPlayer(musicFile, true);	
		musicPlayer.play();
	}
	
	@FXML
	public void handlePlayMode() {
		DungeonScreen dungeonScreen = new DungeonScreen(currStage);
		musicPlayer.stop();
		dungeonScreen.start();
	}
	
	@FXML
	public void handleDesignMode() {
		DesignScreen designScreen = new DesignScreen(this.currStage);
		musicPlayer.stop();
		designScreen.start();
		
	}
	
}
