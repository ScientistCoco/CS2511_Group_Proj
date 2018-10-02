package visuals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		fxmlLoader.setController(new DungeonController(s));
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 500, 500);
			s.setScene(sc);
			s.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
