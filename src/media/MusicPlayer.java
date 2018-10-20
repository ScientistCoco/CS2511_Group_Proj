package media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * This class can be used to play music files through the 
 * MediaPlayer class. It handles the instantiation of the 
 * MediaPlayer class so that the user only needs to supply the file name
 * in order to play the file.
 * @author court
 *
 */
public class MusicPlayer {
	
	private MediaPlayer mediaPlayer;
	/**
	 * 
	 * @param fileName the name of the soundtrack to be played. Assumes 
	 * 					that the file is located in the media package
	 * @param loop true/false if we want the soundtrack to loop
	 */
	public MusicPlayer(String fileName, boolean loop) {
		Media bgMusic = new Media(ClassLoader.getSystemResource("media/" + fileName).toExternalForm());
		mediaPlayer = new MediaPlayer(bgMusic);
		// If player wants the track to loop 
		if (loop) {
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					System.out.println("Hello");
					mediaPlayer.seek(Duration.ZERO);
				}
			});
		}
	}
	
	public void setVolume(double value) {
		mediaPlayer.setVolume(value);
	}
	
	public void play() {
		this.mediaPlayer.play();
	}
	
	public void stop() {
		this.mediaPlayer.stop();
	}
}
