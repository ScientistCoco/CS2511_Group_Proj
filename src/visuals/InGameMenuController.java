package visuals;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InGameMenuController extends Pane {
	private Stage stage;
	@FXML private Text levelText;
	@FXML private Button mainMenuBtn;
	@FXML private Button restartBtn;
	private boolean menuOpened;
	private String level;
	
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
	
	@FXML
	public void restartBtnClicked() {
		DungeonScreen ds = new DungeonScreen(stage);
		ds.start();
	}
}
