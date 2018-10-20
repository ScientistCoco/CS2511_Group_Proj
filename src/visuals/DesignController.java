package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Board;
import other.Boulder;
import other.Door;
import other.Entity;
import other.Exit;
import other.Pit;
import other.Player;
import other.Switch;
import other.Wall;

import java.util.ArrayList;
import java.util.stream.IntStream;

import enemies.Coward;
import enemies.Enemy;
import enemies.Hound;
import enemies.Hunter;
import enemies.Strategist;
import items.Arrow;
import items.Bomb;
import items.HoverPotion;
import items.InvincibilityPotion;
import items.Item;
import items.Key;
import items.Sword;
import items.Treasure;

public class DesignController {

	private Stage currStage;
	private int rowSize = 10;
	private int colSize = 10;
	private Board board;
	@FXML private GridPane grid;
	@FXML private GridPane itemsGrid;
	@FXML private GridPane characterGrid;
	@FXML private GridPane EntityGrid;
	private StackPane[][] itemsStackPane;
	private StackPane[][] characterStackPane;
	private StackPane[][] entityStackPane;
	private int rowPane = 3;
	private int colPane = 3;
	private ArrayList<Item> allitems;
	private ArrayList<Enemy> allcharacters;
	private ArrayList<Entity> allentities;
	@FXML private Label itemName;
	@FXML private AnchorPane itemDescriptionPane;
	@FXML private Text itemDescription;
	
	@FXML private Label askForNumber;
	@FXML private TextField inputText;
	
	@FXML private Button startGameButton;
	@FXML private Button returnMenuButton;
	
	
	public DesignController(Stage s) {
		this.board = new Board();
		Player player = new Player(board);
		board.placeEntity(player, 0, 0);
		this.currStage = s;
		itemsStackPane = new StackPane[3][3];
		characterStackPane = new StackPane[3][3];
		entityStackPane = new StackPane[3][3];
		initAllitems();
		initAllcharacters();
		initAllentities();
	}
	
	public DesignController(Stage s, Board b) {
		this.board = b;
		this.currStage = s;
		itemsStackPane = new StackPane[3][3];
		characterStackPane = new StackPane[3][3];
		entityStackPane = new StackPane[3][3];
		initAllitems();
		initAllcharacters();
		initAllentities();
	}
	
	private void initAllitems() {
		allitems = new ArrayList<Item>();
		allitems.add(new Arrow(board));
		allitems.add(new Bomb(board));
		allitems.add(new HoverPotion(board));
		allitems.add(new InvincibilityPotion(board));
		allitems.add(new Key(board,0));
		allitems.add(new Sword(board));
		allitems.add(new Treasure(board));
	}
	
	private void initAllcharacters() {
		allcharacters = new ArrayList<Enemy>();
		allcharacters.add(new Coward(board));
		allcharacters.add(new Hound(board, null));
		allcharacters.add(new Hunter(board));
		allcharacters.add(new Strategist(board));
	}
	
	private void initAllentities() {
		allentities = new ArrayList<Entity>();
		allentities.add(new Door(board, 0));
		allentities.add(new Exit(board));
		allentities.add(new Pit(board));
		allentities.add(new Switch(board));
		allentities.add(new Wall(board));
	}

	@FXML
	public void initialize() {
		askForNumber.setVisible(false);
		inputText.setVisible(false);
		
		this.initPlayerGrid();
		
		// init items grid
		this.initItemsGrid();
		
		// init characters grid
		this.initCharacterGrid();
		
		// add entities grid
		this.initEntityGrid();

	}
	
	private void showEntityDescription(int col, int row) {
		if (entityStackPane[col][row].getChildren().size() != 0) {
			Entity it = this.findEntityByImage((ImageView) entityStackPane[col][row].getChildren().get(0));
			itemName.setText(it.getEntityName());
			itemName.setVisible(true);
		}
		
	}

	private Entity findEntityByImage(ImageView imageView) {
		for (Entity en : allentities) {
			if (this.compareImageView(en.getEntityIcon(), imageView) > 95) {
				return en;
			}
		}
		return null;
	}

	public void showItemDescription(int col, int row) {
		if (itemsStackPane[col][row].getChildren().size() != 0) {
			Item it = this.findItemByImage((ImageView) itemsStackPane[col][row].getChildren().get(0));
			itemName.setText(it.getItemName());
			itemName.setVisible(true);
			itemDescription.setText(it.getDescription());
			itemDescriptionPane.setOpacity(1);
			
		}
	}
	
	public void hideItemDescription() {
		itemDescriptionPane.setOpacity(0);
	}
	
	public Item findItemByImage(ImageView image) {
		for (Item it : allitems) {
			if (compareImageView(it.getEntityIcon(), image) > 95.0) {
				return it;
			}
		}
		return null;
	}
	
	private double compareImageView(ImageView imageView1, ImageView imageView2) {
		Image image1 = imageView1.getImage();
		Image image2 = imageView2.getImage();

		final int width = (int) image1.getWidth();
		final int height = (int) image1.getHeight();
		final PixelReader reader1 = image1.getPixelReader();
		final PixelReader reader2 = image2.getPixelReader();

		final double nbNonSimilarPixels = IntStream.range(0, width).parallel().
			mapToLong(i -> IntStream.range(0, height).parallel().filter(j -> reader1.getArgb(i, j) != reader2.getArgb(i, j)).count()).sum();

		return 100d - nbNonSimilarPixels / (width * height) * 100d;
	}

	public void showEnemyDescription(int col, int row) {
		if (characterStackPane[col][row].getChildren().size() != 0) {
			Enemy enemy = this.findEnemyByImage((ImageView) characterStackPane[col][row].getChildren().get(0));
			itemName.setText(enemy.getEnemyName());
			itemName.setVisible(true);
		}
	}
	
	public Enemy findEnemyByImage(ImageView image) {
		for (Enemy en : allcharacters) {
			if (this.compareImageView(en.getEntityIcon(), image) > 95.0) {
				return en;
			}
		}
		return null;
	}
	
	private Entity findByImage(ImageView image) {
		if (this.findEnemyByImage(image) != null) {
			return this.findEnemyByImage(image);
		} else if (this.findItemByImage(image) != null) {
			return this.findItemByImage(image);
		} else if (this.findEntityByImage(image)!=null) {
			return this.findEntityByImage(image);
		}
		return null;
	}
	
	
	private void initPlayerGrid() {

		// init player grid
		for (int i = 0; i < colSize; i ++) {
			for (int j = 0; j < rowSize; j ++ ) {
				grid.add(board.getFloor(i, j), i, j);		
			}
		}
		
		
		
		// user can drag entity into grid
		grid.setOnDragOver((DragEvent event) -> {
			if(event.getGestureSource() != grid && event.getDragboard().hasImage()){
                //allow for moving
                event.acceptTransferModes(TransferMode.MOVE);
            }
			event.consume();
		});
		
		grid.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();

			// get x,y of grid
			double currX = event.getX();
			double currY =  event.getY();
			int cellWidth = this.calcellWidth();		
			int cellHeight = this.calcellHeight();	
			int x = (int) (currX/cellWidth);
			int y = (int) (currY/cellHeight);
			if (x==0 && y==0) {
				askForNumber.setText("Please choose another position...");
				askForNumber.setTextFill(Color.web("ee0101"));
				askForNumber.setVisible(true);
				return;
			}
			
			
			boolean success = false;
			if ( db.hasImage()) {
				success = true;
				
				
				ImageView im = new ImageView(db.getImage());
				
				Entity en = this.findByImage(im);
				// need to input key number before drop key into board or input door number before drop
				if (en.getEntityName().equals("Key")) {
					if (!checkValidInput(inputText.getText())) {
						askForNumber.setText("Please input a valid number");
						askForNumber.setTextFill(Color.web("#ee0101"));
						askForNumber.setVisible(true);
						return;
					}
				}
				if (en.getEntityName().equals("Door")) {
					if (!checkValidInput(inputText.getText())) {
						askForNumber.setText("Please input a valid number");
						askForNumber.setTextFill(Color.web("#ee0101"));
						askForNumber.setVisible(true);
						return;
					}
				}
				if (en.getEntityName().equals("Hound")) {
					if (findHunter() == null) {
						askForNumber.setText("Hunter not found...");
						askForNumber.setTextFill(Color.web("#ee0101"));
						askForNumber.setVisible(true);
						return;
					}
				}
				
				Entity toClone = cloneEntity(en.getEntityName());
				
				this.board.placeEntity(toClone, x, y);
				//this.initPlayerGrid();
				
			}
			event.setDropCompleted(success);
			event.consume();
		});
		
		grid.setOnDragEntered((DragEvent event) -> {
			if(event.getGestureSource() != grid && event.getDragboard().hasImage()){
                //source.setVisible(false);
                grid.setOpacity(0.7);
                //System.out.println("Drag entered");
            }

			event.consume();
		});
		
		
		grid.setOnDragExited((DragEvent event) -> {
			grid.setOpacity(1);
			event.consume();
		});
	}
	
	private Entity findHunter() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board.getEntity(i, j) != null) {
					if (board.getEntity(i, j).getEntityName().equals("Hunter")) {
						return board.getEntity(i, j);
					}
				}
			}
		}
		return null;
	}

	private boolean checkValidInput(String text) {
		if (!text.matches("^[0-9]*$")) {
			System.out.println("input valid number");
			return false;
		} else if (text.isEmpty()) {
			System.out.println("text is empty");
			return false;
		}
		
		return true;
	}

	private Entity cloneEntity(String entityName) {
		switch (entityName) {
			case "Exit" :
				return new Exit(board);
			case "Hunter" :
				return new Hunter(board);
			case "Boulder" :
				return new Boulder(board);
			case "Switch" :
				return new Switch(board);
			
			case  "Door" :
				int doorNum = Integer.parseInt(inputText.getText());
				System.out.println("Door number is " + doorNum);
				return new Door(board, doorNum);
		
			case "Key" :
				int keyNum = Integer.parseInt(inputText.getText());
				System.out.println("Key number is " + keyNum);
				return new Key(board, keyNum);
			case "Pit" :
				return new Pit(board);
			case "Wall" :
				return new Wall(board);
			case "Arrow" :
				return new Arrow(board);
			case "Bomb" :
				return new Bomb(board);
			case "Sword" :
				return new Sword(board);
			case "Treasure" :
				return new Treasure(board);
			case "Hover Potion" :
				return new HoverPotion(board);
			case "Invincibility Potion":
				return new InvincibilityPotion(board);
			case "Coward":
				return new Coward(board);
			case "Hound":
				return new Hound(board, (Hunter)this.findHunter());
			case "Strategist":
				return new Strategist(board);
		}	
		return null;
	}

	private int calcellWidth() {
		@SuppressWarnings("deprecation")
		int rowSize = grid.impl_getRowCount();
		double lenGrid = grid.getWidth();
		int cellWidth = (int) (lenGrid/rowSize);
		return cellWidth;
	}
	
	private int calcellHeight() {
		int colSize = grid.impl_getColumnCount();
		double heightGrid = grid.getHeight();
		int cellHeight = (int) (heightGrid/rowSize);
		return cellHeight;
	}
	
	
	private void initItemsGrid() {
		for (int i = 0; i < colPane; i++) {
			for (int j = 0; j < rowPane; j++) {
				if ((colPane*j + i) < allitems.size()) {
					Item it = allitems.get(colPane*j + i);
					itemsStackPane[i][j] = new StackPane();
					itemsStackPane[i][j].getChildren().add(0, it.getEntityIcon());
					itemsStackPane[i][j].getStylesheets().add("/visuals/application.css");
					itemsStackPane[i][j].getStyleClass().add("inventory-cells");	
					itemsGrid.add(itemsStackPane[i][j], i, j);
				}
			}
		}
	
		// when mouse entered each item, it shows description
		itemsGrid.getChildren().forEach((item) -> {
			item.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
				int row = itemsGrid.getRowIndex(item);
				int col = itemsGrid.getColumnIndex(item);
				showItemDescription(col, row);
			});
		});
		
		
		itemsGrid.getChildren().forEach((item) -> {
			item.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
				int row = itemsGrid.getRowIndex(item);
				int col = itemsGrid.getColumnIndex(item);
				hideItemDescription();
				itemName.setVisible(false);
			});
		});
		
		
		// user can drag each item
		itemsGrid.getChildren().forEach((item) -> {
			item.setOnDragDetected((MouseEvent event)->{
				askForNumber.setVisible(false);
				inputText.setVisible(false);
				Dragboard db = item.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				int row = itemsGrid.getRowIndex(item);
				int col = itemsGrid.getColumnIndex(item);
				ImageView i = (ImageView)itemsStackPane[col][row].getChildren().get(0);
				Item it = this.findItemByImage(i);
				if (it.getEntityName().equals("Key")) {
					askForNumber.setTextFill(Color.web("#87c8e7"));
					askForNumber.setVisible(true);
					inputText.setVisible(true);
				}
				content.putImage(((ImageView) itemsStackPane[col][row].getChildren().get(0)).getImage());
				db.setContent(content);
				event.consume();
			});
		});
		
		
		itemsGrid.getChildren().forEach((item) -> {
			item.setOnDragDone((DragEvent event)->{
				//System.out.println("drag done...");
	            event.consume();
			});
		});
		
		itemsGrid.getChildren().forEach((item) -> {
			item.setOnMousePressed((MouseEvent event)-> {
				int row = itemsGrid.getRowIndex(item);
				int col = itemsGrid.getColumnIndex(item);
				ImageView i = (ImageView)itemsStackPane[col][row].getChildren().get(0);
				Item it = this.findItemByImage(i);
				if (it.getEntityName().equals("Key")) {
					askForNumber.setText("Input key number: ");
				}
				inputText.focusedProperty().addListener((arg0, oldValue, newValue) -> {
					if (!newValue) {
						if (inputText.getText().matches("^[0-9]*$")) {
							inputText.setText("");
						}
					}
				});
			});
		});

	}
	
	private void initCharacterGrid() {
		for (int i = 0; i < colPane; i++) {
			for (int j = 0; j < rowPane; j++) {
				if ((colPane*j + i) < allcharacters.size()) {
					//System.out.println(i);
					//System.out.println(j);
					Enemy en = allcharacters.get(colPane*j + i);
					//System.out.println(it.getItemName());
					characterStackPane[i][j] = new StackPane();
					characterStackPane[i][j].getChildren().add(0, en.getEntityIcon());
					characterStackPane[i][j].getStylesheets().add("/visuals/application.css");
					characterStackPane[i][j].getStyleClass().add("inventory-cells");	
					characterGrid.add(characterStackPane[i][j], i, j);
				}
			}
		}
		
		// add mouse event to characterPane
		characterGrid.getChildren().forEach((enemy) -> {
			enemy.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
				int row = characterGrid.getRowIndex(enemy);
				int col = characterGrid.getColumnIndex(enemy);
				showEnemyDescription(col, row);
			});
		});
		characterGrid.getChildren().forEach((item) -> {
			item.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
				int row = characterGrid.getRowIndex(item);
				int col = characterGrid.getColumnIndex(item);
				itemName.setVisible(false);
			});
		});
		
		// user can drag each item
		characterGrid.getChildren().forEach((en) -> {
			en.setOnDragDetected((MouseEvent event)->{
				askForNumber.setVisible(false);
				inputText.setVisible(false);
				Dragboard db = en.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				int row = characterGrid.getRowIndex(en);
				int col = characterGrid.getColumnIndex(en);
				content.putImage(((ImageView) characterStackPane[col][row].getChildren().get(0)).getImage());
				db.setContent(content);
				event.consume();
			});
		});
				
		characterGrid.getChildren().forEach((en) -> {
			en.setOnDragDone((DragEvent event)->{
				event.consume();
			});
		});
	}
	
	private void initEntityGrid() {
		for (int i = 0; i < colPane; i++) {
			for (int j = 0; j < rowPane; j++) {
				if ((colPane*j + i) < allentities.size()) {
					//System.out.println(i);
					//System.out.println(j);
					Entity en = allentities.get(colPane*j + i);
					//System.out.println(it.getItemName());
					entityStackPane[i][j] = new StackPane();
					entityStackPane[i][j].getChildren().add(0, en.getEntityIcon());
					entityStackPane[i][j].getStylesheets().add("/visuals/application.css");
					entityStackPane[i][j].getStyleClass().add("inventory-cells");	
					EntityGrid.add(entityStackPane[i][j], i, j);
				}
			}
		}
		// add mouse event into entity grid
		EntityGrid.getChildren().forEach((entity) -> {
			entity.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
				int row = EntityGrid.getRowIndex(entity);
				int col = EntityGrid.getColumnIndex(entity);
				showEntityDescription(col, row);
			});
		});
		EntityGrid.getChildren().forEach((entity) -> {
			entity.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
				int row = EntityGrid.getRowIndex(entity);
				int col = EntityGrid.getColumnIndex(entity);
				itemName.setVisible(false);
			});
		});
		
		// user can drag each item
		EntityGrid.getChildren().forEach((item) -> {
			item.setOnDragDetected((MouseEvent event)->{
				askForNumber.setVisible(false);
				inputText.setVisible(false);
				Dragboard db = item.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				int row = EntityGrid.getRowIndex(item);
				int col = EntityGrid.getColumnIndex(item);
				ImageView i = (ImageView)entityStackPane[col][row].getChildren().get(0);
				Entity it = this.findEntityByImage(i);
				if (it.getEntityName().equals("Door")) {
					askForNumber.setTextFill(Color.web("#87c8e7"));
					askForNumber.setVisible(true);
					inputText.setVisible(true);
				}
				content.putImage(((ImageView) entityStackPane[col][row].getChildren().get(0)).getImage());
				db.setContent(content);
				event.consume();
			});
		});
				
		EntityGrid.getChildren().forEach((item) -> {
			item.setOnDragDone((DragEvent event)->{
				event.consume();
			});
		});
		
		EntityGrid.getChildren().forEach((item) -> {
			item.setOnMousePressed((MouseEvent event)-> {
				int row = EntityGrid.getRowIndex(item);
				int col = EntityGrid.getColumnIndex(item);
				ImageView i = (ImageView)entityStackPane[col][row].getChildren().get(0);
				Entity it = this.findEntityByImage(i);
				if (it.getEntityName().equals("Door")) {
					askForNumber.setText("Input door number: ");
				}
				inputText.focusedProperty().addListener((arg0, oldValue, newValue) -> {
					if (!newValue) {
						if (inputText.getText().matches("^[0-9]*$")) {
							inputText.setText("");
						}
					}
				});
			});
		});
	}
	
	@FXML
	public void handleStartGame() {
		DungeonScreen sc = new DungeonScreen(this.currStage);
		sc.setController(new DungeonController(this.currStage, board));
		sc.start();
	}
	
	@FXML
	public void handleReturnMenu() {
		MenuScreen ms = new MenuScreen(this.currStage);
		ms.start();
	}
	
}
