package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Direction;
import other.MovementBehaviour;
import player.Player;

public class Arrow extends Item{
	
	public MovementBehaviour move;
	
	public Arrow(Board board) {
		super(board);
		this.name = "Arrow";
		this.icon = " ↖ ";
		this.entityIcon = new ImageView(new Image("icons/arrow.png"));
		this.description = "An arrow that can be used to kill enemies";
		this.move = new MovementBehaviour();
	}
	
	
	public void Fly(Direction d) {
		// Arrow will fly until it hits objects that have a higher zorder than itself
		switch (d) {
			case Up:
				while(this.move.moveUp(this, this.board)) {
					this.icon = " ↟ ";
					board.printBoard();
				}
				break;
			case Down:
				while (this.move.moveDown(this, this.board)) {
					this.icon = " ↡ ";
					board.printBoard();
				}
				break;
			case Right:
				while (this.move.moveRight(this, this.board)) {
					this.icon = " ↠ ";
					board.printBoard();
				}
				break;
			case Left:
				while (this.move.moveLeft(this, this.board)) {
					this.icon = " ↞ ";
					board.printBoard();
				}
				break;
		}
		board.removeEntity(this);
	}


	@Override
	public String useItem(Player player) {
		// To know if the arrow has hit anything we can get the number of enemies on the board before the arrow was launched
		// then compare it afterwards to see if anything has changed.
		int enemies = board.getEnemyObjects().size();
		board.addEntity(this);	// Add the arrow to the board again because it will appear on the map
		this.setCoordinates(player.getXCoordinate(), player.getYCoordinate());	// Update the coordinates so the arrow knows where it will 'fly'
		
		this.Fly(player.getCardinalDirection());
		if (enemies != board.getEnemyObjects().size()) {
			return "Arrow hit an enemy";
		} 
		return "The arrow did not kill any enemies";
	}
}
