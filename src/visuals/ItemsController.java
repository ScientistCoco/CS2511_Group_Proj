package visuals;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ItemsController extends Pane{

	@FXML private GridPane grid;
	private Stage s;
	//private FXMLLoader fxmlloader;
	private StackPane[][] itemsStackPane;
	private int itemsCol = 4;
	private int itemsRow = 5;
	private ArrayList<ImageView> allItems;
	private ImageView im;
	
	public ItemsController(Stage currStage) {
		this.setVisible(true);
		this.s = currStage;
		this.itemsStackPane = new StackPane[4][5];
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/visuals/ItemsInDesign.fxml"));
			//fxmlloader.setRoot(this);
			fxmlloader.setController(this);
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		im = new ImageView(new Image("icons/arrow.png"));
		for (int i = 0; i < this.itemsCol; i++) {
			for (int j = 0; j < this.itemsRow; j++) {
				itemsStackPane[i][j] = new StackPane();
				itemsStackPane[i][j].getChildren().add(0, im);
				itemsStackPane[i][j].getStylesheets().add("/visuals/application.css");
				//itemsStackPane[i][j].getStyleClass().add("inventory-cells");	
				grid.add(itemsStackPane[i][j], i, j);
			}
		}
	}

}
