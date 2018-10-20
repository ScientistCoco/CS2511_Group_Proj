package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import media.MusicPlayer;

public class LevelFailController{
	
	private Stage stage;
	@FXML AnchorPane levelAnchorPane;
	@FXML Button yesBtn;
	@FXML Button noBtn;
	private MusicPlayer musicPlayer;
	private String mediaFile = "Super Mario Death.mp3";
	
	public LevelFailController(Stage s) {
		this.stage = s;
	}
	
	@FXML public void initialize() {
		this.musicPlayer = new MusicPlayer(mediaFile, false);
		this.musicPlayer.setVolume(0.2);
		this.musicPlayer.play();
	}
	
	@FXML
	public void clickYes() {
		this.musicPlayer.stop();
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
	
	@FXML
	public void clickNo() {
		this.musicPlayer.stop();
		MenuScreen ms = new MenuScreen(stage);
		ms.start();
	}
}