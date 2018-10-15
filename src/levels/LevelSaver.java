package levels;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class reads the file levelsCompleted and retrieves the next level number
 * that the player should play next.
 * It can also clear all the players save data if required - making them start at level 1 again.
 * @author court
 *
 */
public class LevelSaver {
	Path path = Paths.get("levelsCompleted.txt");

	/**
	 * This method gets the level that the player should play next
	 * @return a string of the level number
	 */
	public String getNextLevel() {
		String level = null;
		try {
			level = Files.readAllLines(path).get(0);			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return level;
	}
	
	/**
	 * This method updates the players progress
	 */
	public void levelComplete() {
		int level = 0;
		try {
			level = Integer.parseInt(Files.readAllLines(path).get(0)) + 1;
			Files.write(path, Integer.toString(level).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * This method clears the users save data and reverts their
	 * data to start at level 1
	 */
	public void clearSaveData() {	
		String str = "1";		
		try {
			Files.write(path, str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**public static void main(String[] args) {
		LevelSaver l = new LevelSaver();
		System.out.println(l.getNextLevel());
		l.levelComplete();
		System.out.println(l.getNextLevel());
		l.clearSaveData();
		System.out.println(l.getNextLevel());
	}**/
}
