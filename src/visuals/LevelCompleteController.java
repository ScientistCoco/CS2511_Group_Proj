package visuals;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class LevelCompleteController {
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	@FXML Button yesBtn;
	@FXML Button noBtn;
	private MediaPlayer mediaPlayer;
	
	public LevelCompleteController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		Media bgMusic = new Media(ClassLoader.getSystemResource("media/Lively Meadow Victory Fanfare.mp3").toExternalForm());
		this.mediaPlayer = new MediaPlayer(bgMusic);
		this.mediaPlayer.setVolume(0.5);
		mediaPlayer.play();
	}
	
	@FXML
	public void clickYes() {
		mediaPlayer.stop();
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
	
	@FXML
	public void clickNo() {
		mediaPlayer.stop();
		MenuScreen ms = new MenuScreen(stage);
		ms.start();
	}
	
}
