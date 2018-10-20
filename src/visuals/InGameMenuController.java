package visuals;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Board;

public class InGameMenuController extends Pane {
	private Stage stage;
	@FXML private Text levelText;
	@FXML private Button mainMenuBtn;
	@FXML private Button restartBtn;
	@FXML private Button returnDesignButton;
	@FXML private Label warningText;
	private boolean menuOpened;
	private String level;
	private Board board;
	
	public InGameMenuController(Stage s, String level) {
		this.stage = s;
		this.setVisible(false);
		this.menuOpened = false;
		this.level = level;
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/visuals/ingame_menu.fxml"));
			l.setRoot(this);
			l.setController(this);
			l.load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		levelText.setText(level);
	}
	
	/**
	 * This method determines whether to open or close the in game menu
	 */
	public void onActionMenu() {
		if (this.menuOpened) {
			this.setVisible(false);
			this.menuOpened = false;
		} else {
			this.setVisible(true);
			this.menuOpened = true;
		}
	}
	
	@FXML
	public void mainMenuBtnClicked() {
		MenuScreen ms = new MenuScreen(stage);
		ms.start();
	}
	
	/**
	 * Restarts the level. Checks if the player is playing in 
	 * design mode or in playing mode to determine which board
	 * to switch back to.
	 */
	@FXML
	public void restartBtnClicked() {
		DungeonScreen ds = new DungeonScreen(stage);
		if (returnDesignButton.isVisible()) {
			ds.setController(new DungeonController(stage, this.board));
		}
		ds.start();
	}
	
	/**
	 * Design button is hidden by default, it will be shown when the 
	 * design controller calls this menu controller to show it. 
	 * It then requires the board that it needs to show when the button is clicked
	 */
	public void setButtonToReturnToDesign(Board board) {
		this.board = board;
		levelText.setText("Design Menu");
		returnDesignButton.setVisible(true);
	}
	
	@FXML
	public void handleReturnDesign() {
		DesignScreen de = new DesignScreen(stage, this.board);
		de.continueDesign();
	}
	
}
