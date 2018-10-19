package visuals;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import levels.LevelSaver;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;

public class GameCompleteController {
	private Stage stage;
	@FXML private ImageView char1;
	@FXML private ImageView char2;
	@FXML private ImageView char3;
	@FXML private Button mainMenuBtn;
	@FXML private Button exitGameBtn;
	@FXML private Button resetBtn;
	private MediaPlayer mediaPlayer;
	
	public GameCompleteController(Stage s) {
		this.stage = s;
	}
	
	@FXML
	public void initialize() {
		charJumpAnimation(char1);
		charJumpAnimation(char3);
		/*
		Media bgMusic = new Media(ClassLoader.getSystemResource("media/Lively Meadow (Victory Fanfare and Song).mp3").toExternalForm());
		this.mediaPlayer = new MediaPlayer(bgMusic);
		this.mediaPlayer.setVolume(0.6);
		mediaPlayer.play();
		*/
	}
	
	/**
	 * This receives a node and animates the node to appear to 
	 * 'jump' up and down indefinitely.
	 * @param character
	 */
	public void charJumpAnimation(Node character) {
		TranslateTransition translateTransition = new TranslateTransition();
		
		translateTransition.setDuration(Duration.millis(250));
		translateTransition.setNode(character);
		translateTransition.setByY(-50);
		translateTransition.setCycleCount(Timeline.INDEFINITE);
		translateTransition.setInterpolator(Interpolator.EASE_IN);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
	}
	
	/**
	 * Returns the player to the main screen
	 */
	@FXML
	public void mainMenuBtnClicked() {
		//mediaPlayer.stop();
		MenuScreen menuScreen = new MenuScreen(this.stage);
		//menuScreen.start();
	}
	
	/**
	 * Exits the game
	 */
	@FXML
	public void exitGameBtnClicked() {
		//mediaPlayer.stop();
		this.stage.close();
	}
	
	/**
	 * Resets the players progress
	 */
	@FXML
	public void resetBtnClicked() {
		//mediaPlayer.stop();
		LevelSaver levelSaver = new LevelSaver();
		levelSaver.clearSaveData();
		MenuScreen menuScreen = new MenuScreen(this.stage);
		menuScreen.start();
	}
}
