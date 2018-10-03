package visuals;

import com.sun.javafx.scene.traversal.Direction;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import other.Board;
import other.Floor;
import other.Player;

public class DungeonController {
	@FXML
	private StackPane background;	// This is used as empty space... visual purposes so that we can resize the playable map
	@FXML
	private GridPane baseMap;
	
	private Stage currStage;
	
	public DungeonController(Stage s) {
		currStage = s;
	}
	
	private Board board;
	
	@FXML
	public void initialize() {
		board = new Board();
		//Floor[][] map = new Floor[10][10];	// Floor is a stackable pane
		
		// Since we are doing a 10 x 10 grid and each floor tile is 32x32px
		// we will need to rescale the GridPane to 320x320px
		baseMap.getRowConstraints().add(new RowConstraints(32));
		baseMap.getColumnConstraints().add(new ColumnConstraints(32));
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j ++ ) {
				//map[i][j] = new Floor();
				baseMap.add(board.getFloor(i, j), i, j);
				
			}
		}
		
		Player player = new Player(board);
		board.placeEntity(player, 0, 0);
		//map[0][0].addEntity(player);
		
	}
	
	// This method handles the keyboard events - right,left,up and down arrow keys
	@FXML
	public void onKeyPressed(KeyEvent key) {
		System.out.println(key.getCode());
		board.getPlayerObject().moveSelf(key.getCode().toString());
	}
}
