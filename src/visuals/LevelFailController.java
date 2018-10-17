package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelFailController{
	
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	@FXML Button yesBtn;
	@FXML Button noBtn;
	private MediaPlayer mediaPlayer;
	
	public LevelFailController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		Media bgMusic = new Media(ClassLoader.getSystemResource("media/Super Mario Death.mp3").toExternalForm());
		this.mediaPlayer = new MediaPlayer(bgMusic);
		this.mediaPlayer.setVolume(0.2);
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