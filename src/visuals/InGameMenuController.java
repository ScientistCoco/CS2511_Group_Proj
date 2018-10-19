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
		if (this.returnDesignButton == null) {
			this.returnDesignButton = new Button();
		}
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
	
	public InGameMenuController(Stage s, String level, Board board) {
		this.board = board;
		if (this.returnDesignButton == null) {
			this.returnDesignButton = new Button();
		}
		this.returnDesignButton.setVisible(true);
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
	
	@FXML
	public void restartBtnClicked() {
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
	
	@FXML
	public void handleReturnDesign() {
		if (board == null) {
			warningText.setText("Cannot design current board!");
			return;
		}
		DesignScreen de = new DesignScreen(stage, board);
		de.continueDesign();
	}
	
}
