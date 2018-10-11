package visuals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreen {
	private Stage stage;
	private FXMLLoader fxmlloader;
	private String title;
	
	public MenuScreen(Stage s) {
		this.stage = s;
		this.title = "Menu Screen";
		this.fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/Menu.fxml"));
	}
	
	public void start() {
		stage.setTitle(title);
		MenuController menuController = new MenuController(this.stage);
		this.fxmlloader.setController(menuController);
		try {
			Parent root = this.fxmlloader.load();
			Scene sc = new Scene(root, 800, 1280);
			this.stage.setScene(sc);
			this.stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
