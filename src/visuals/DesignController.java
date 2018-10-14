package visuals;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Board;

import java.util.ArrayList;

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
	@FXML private AnchorPane itemsPane;
	@FXML private GridPane itemsGrid;
	private StackPane[][] itemsStackPane;
	private int itemsPaneRow = 5;
	private int itemsPaneCol = 3;
	private ArrayList<Item> allitems;
	@FXML Label itemName;
	@FXML AnchorPane itemDescriptionPane;
	@FXML Text itemDescription;
	
	public DesignController(Stage s) {
		this.board = new Board();
		this.currStage = s;
		itemsStackPane = new StackPane[3][5];
		initAllitems();
	}
	
	private void initAllitems() {
		allitems = new ArrayList<Item>();
		allitems.add(new Arrow(board));
		allitems.add(new Bomb(board));
		allitems.add(new HoverPotion(board));
		allitems.add(new InvincibilityPotion(board));
		//allitems.addAll(new Key(board));
		allitems.add(new Sword(board));
		allitems.add(new Treasure(board));
	}

	@FXML
	public void initialize() {
		// init player grid
		for (int i = 0; i < colSize; i ++) {
			for (int j = 0; j < rowSize; j ++ ) {
				grid.add(board.getFloor(i, j), i, j);		
			}
		}
		// init items grid
		for (int i = 0; i < itemsPaneCol; i++) {
			for (int j = 0; j < itemsPaneRow; j++) {
				if ((itemsPaneCol*j + i) < allitems.size()) {
					System.out.println(i);
					System.out.println(j);
					Item it = allitems.get(itemsPaneCol*j + i);
					//System.out.println(it.getItemName());
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
				showDescription(col, row);
			});
		});
		itemsGrid.getChildren().forEach((item) -> {
			item.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
				int row = itemsGrid.getRowIndex(item);
				int col = itemsGrid.getColumnIndex(item);
				hideDescription();
			});
		});
	}
	
	public void showDescription(int col, int row) {
		if (itemsStackPane[col][row].getChildren().size() != 0) {
			Item it = this.findItemByImage((ImageView) itemsStackPane[col][row].getChildren().get(0));
			itemName.setText(it.getItemName());
			itemDescription.setText(it.getDescription());
			itemDescriptionPane.setOpacity(1);
			
		}
	}
	
	public void hideDescription() {
		itemDescriptionPane.setOpacity(0);
	}
	
	public Item findItemByImage(ImageView image) {
		for (Item it : allitems) {
			if (it.getEntityIcon().equals(image)) {
				return it;
			}
		}
		return null;
	}

}
