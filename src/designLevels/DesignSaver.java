package designLevels;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import other.Board;

/**
 * This class receives the board class that is created through
 * the design controller. It saves the design into a file
 * so the player can access it later if desired.
 * @author court
 *
 */
public class DesignSaver {
	private Board board;
	
	public DesignSaver(Board board) {
		this.board = board;
	}
	
	public void saveDesign() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("design0");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.board);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Board getDesign() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("design0");
			ObjectInputStream oos = new ObjectInputStream(fis);
			Board board = (Board) oos.readObject();
			board.printBoard();
			oos.close();
			return board;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
