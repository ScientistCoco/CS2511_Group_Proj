package visuals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import other.Board;

public class DesignScreen {
	
	private Stage stage;
	private FXMLLoader fxmlloader;
	private String title;
	private Board board;

	public DesignScreen(Stage s) {
		this.stage = s;
		this.title = "Design Screen";
		this.fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/DesignMode.fxml"));
	}
	
	public DesignScreen(Stage s, Board board) {
		this.stage = s;
		this.title = "Design Screen";
		this.fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("visuals/DesignMode.fxml"));
		this.board = board;
	}
	
	public void start() {
		this.stage.setTitle(this.title);
		DesignController designController = new DesignController(this.stage);
		fxmlloader.setController(designController);
		try {
			Parent root = fxmlloader.load();
			Scene sc = new Scene(root, 800, 1280);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void continueDesign() {
		this.stage.setTitle(this.title);
		DesignController designController = new DesignController(this.stage, board);
		fxmlloader.setController(designController);
		try {
			Parent root = fxmlloader.load();
			Scene sc = new Scene(root, 800, 1280);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
