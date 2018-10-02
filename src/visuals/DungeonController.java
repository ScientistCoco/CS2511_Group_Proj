package visuals;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DungeonController {
	@FXML
	private StackPane background;	// This is used as empty space... visual purposes so that we can resize the playable map
	@FXML
	private GridPane baseMap;
	
	private Stage currStage;
	
	public DungeonController(Stage s) {
		currStage = s;
	}
	
	@FXML
	public void initialize() {
		StackPane[][] map = new StackPane[10][10];
		
		// Since we are doing a 10 x 10 grid and each floor tile is 32x32px
		// we will need to rescale the GridPane to 320x320px
		baseMap.getRowConstraints().add(new RowConstraints(32));
		baseMap.getColumnConstraints().add(new ColumnConstraints(32));
		baseMap.setPrefSize(320, 320);
		baseMap.setMaxSize(320, 320);
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j ++ ) {
				map[i][j] = new StackPane();
				ImageView a = new ImageView();
				Image floor = new Image("visuals/dirt_1_new.png");
				a.setImage(floor);
				map[i][j].getChildren().add(a);
				baseMap.add(map[i][j], i, j);
				
			}
		}
	}
	
	
}
