package visuals;

import java.util.ArrayList;

import enemies.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
/**
 * Notes: The Game will be 1280 x 800 in dimensions. In that case we should make the maximum size for the map to be 704 x 704.
 * That means a 22 x 22 matrix is the biggest we can make
 */
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import levels.BoardLevel;
import levels.LevelSaver;
import levels.level1;
import levels.level2;
import other.Board;
import other.Buff;
import other.ObjectiveComponent;
import other.Player;
import other.PlayerObservable;
import other.PlayerObserver;

public class DungeonController implements PlayerObserver{
	@FXML private AnchorPane base;
	@FXML private GridPane baseMap;
	@FXML private VBox objectivesList;
	@FXML private TextArea systemTextUpdates;
	@FXML private HBox buffBar;	// Size is 185 (W) * 42 (H)
	private ArrayList<Buff> buffs;
	private InventoryController inv;
	@FXML private AnchorPane inventoryPane;
	private ObservableList<Node> objectives; 
	private Stage currStage;
	private Board board;
	private LevelSaver levelSaver;
	private int rowSize = 10;
	private int colSize = 10;
	private Class<?> boardLevel;	// This holds the playing level instance so we can retrieve the board object
	
	public DungeonController(Stage s) {
		currStage = s;
		
		// When dungeonController gets instantiated we also
		// need to instantiate the level that the dungeonController
		// will show to the View.
		if (checkIfLevelExists()) {
			try {
				this.board = ((BoardLevel)boardLevel.newInstance()).getBoard();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			// If level doesn't exist then that means player has completed all the levels
			// We are making the assumption here that there are no levels missing and that
			// the levels are always incrementing
			GameCompleteScreen screen = new GameCompleteScreen(currStage);
			screen.start();
		}
	}
	
	/**
	 * This method checks the players current progress and determines whether the 
	 * player has anymore levels that they can play.
	 * @return true/false
	 */
	private boolean checkIfLevelExists() {
		levelSaver = new LevelSaver();
		try {
			this.boardLevel = Class.forName("levels.level"+levelSaver.getNextLevel());
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	@FXML
	public void initialize() {
		this.buffs = new ArrayList<Buff>();
		
		// Now the gridpane will observe the board:
		for (int i = 0; i < colSize; i ++) {
			for (int j = 0; j < rowSize; j ++ ) {
				baseMap.add(board.getFloor(i, j), i, j);		
			}
		}
		
		// We add this controller to the players list of observers so that when the player dies
		// it can notify the controller about this
		board.getPlayerObject().addObserver(this);
		
		// We get the objectives to 'observe' the objectiveComponent for any changes
		// if there are any changes it will update the visual component to reflect this.
		this.objectives = this.board.getObjectivesOnThisBoard().getObservableObjectives();
		this.objectives.addListener((ListChangeListener<Node>) event -> {
			objectivesList.getChildren().clear();
			objectivesList.getChildren().addAll(this.objectives);
			// We can also check if all the objectives have been cleared as well
			checkIfObjectivesClear();
		});
		
		objectivesList.getChildren().clear();
		objectivesList.getChildren().addAll(this.objectives);	
		inv = new InventoryController(currStage, board);
		inv.setPlayer(board.getPlayerObject());
		inv.setSystemTextUpdates(systemTextUpdates);
		inventoryPane.getChildren().add(inv);
		systemTextUpdates.setEditable(false);
		
		// We add a key listener to the base map so we can determine whether the player wants to move or just
		// change directions. We do this by measuring how long the key was pressed down for.
		// If it was pressed for < 100 ms then we assume the player just wants to change direction.
		// If it was pressed for > 100 ms then we assume the player wants to move.
		base.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
			long startTime;
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().isArrowKey()) {
					if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
						startTime = System.currentTimeMillis();
					} else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
						long length = System.currentTimeMillis() - startTime;
						if (length < 40) {
							board.getPlayerObject().changeDirection(event.getCode().toString());
						} else {							
							board.getPlayerObject().moveSelf(event.getCode().toString());
							inv.showItems();
				
						}
					}
				}
			}
		});
		
	}
	
	/**
	 * This method checks on the progress of the objectives, if all the objectives 
	 * have been cleared then it will change the game screen to reflect this.
	 */
	public void checkIfObjectivesClear() {
		if (board.getObjectivesOnThisBoard().checkProgress()) {			
			levelSaver.levelComplete();
			// We determine which screen to show next. If there are more levels that the
			// player can player then we prompt them with a screen asking if they
			// would like to continue playing
			if (this.checkIfLevelExists()) {
				LevelCompleteScreen sc = new LevelCompleteScreen(currStage);
				sc.start();
			}
			// If not then we congratulate the player for finishing the game
			else {
				GameCompleteScreen screen = new GameCompleteScreen(currStage);
				screen.start();
			}
			
		}
	}
	
	/**
	 * This method updates the buffs on the buffBar view
	 */
	public void updateBuffs() {		
		// We remove the reference to the buff list in the player object so we can
		// check later on if a buff has been removed through comparison of before & after.
		this.buffs = new ArrayList<>(board.getPlayerObject().getBuffs());
		
		ArrayList<ImageView> buffIcons = new ArrayList<ImageView>();
		for (Buff b : this.buffs) {
			if (b.equals(Buff.Hover)) {
				buffIcons.add(new ImageView(new Image("icons/hover_buff.png")));
			} else if (b.equals(Buff.Invincibility)) {
				buffIcons.add(new ImageView(new Image("icons/invincibility_buff.png")));
			}
		}
		
		buffBar.getChildren().clear();		
		for (ImageView b : buffIcons) {
			buffBar.getChildren().add(b);
		}
	}
	
	// This method handles events that are not arrow keys
	@FXML
	public void onKeyPressed(KeyEvent key) {
		if (key.getCode().toString().equals("I")) {
			inv.onActionInv();		
		} 
		inv.showItems();	// Gets the inventoryController to check if there has been new additions. So that if the player has the inv open and they pick up an item, it will show up in their inv.		
	}
	

	@Override
	public void update(PlayerObservable po) {
		if (!po.checkIfAlive()) {
			LevelFailScreen sc = new LevelFailScreen(currStage);
			sc.start();
			return;
		}
		
		// Check if an invincibility buff has been removed
		if (this.buffs.contains(Buff.Invincibility) && !board.getPlayerObject().containBuff(Buff.Invincibility)) {
			systemTextUpdates.appendText("Invinciblity Buff has expired");
		}

		updateBuffs();
	}
	
}
