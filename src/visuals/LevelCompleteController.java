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
import media.MusicPlayer;

public class LevelCompleteController {
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	@FXML Button yesBtn;
	@FXML Button noBtn;
	private MusicPlayer musicPlayer;
	private String mediaFile = "Lively Meadow Victory Fanfare.mp3";
	
	public LevelCompleteController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		this.musicPlayer = new MusicPlayer(mediaFile, false);
		this.musicPlayer.setVolume(0.5);
		musicPlayer.play();
	}
	
	@FXML
	public void clickYes() {
		musicPlayer.stop();
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
	
	@FXML
	public void clickNo() {
		musicPlayer.stop();
		MenuScreen ms = new MenuScreen(stage);
		ms.start();
	}
	
}
