package visuals;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public DungeonScreen(Stage s) {
		this.s = s;
		this.title = "Dungeon Screen";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/base_map.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		DungeonController dungeonController = new DungeonController(s, fxmlLoader);
		fxmlLoader.setController(dungeonController);
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 800, 1280); // 490 * 500
			
			// This bit here handles the keyboard events from the user
			sc.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					dungeonController.onKeyPressed(event);
				}
				
			});
			s.setScene(sc);
			s.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
