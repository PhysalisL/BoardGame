package view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	private AudioClip placeDownSound = new AudioClip(getClass().getResource("tud.wav").toString());
	private AudioClip selectSound = new AudioClip(getClass().getResource("kichin.wav").toString());
	//private AudioClip clickSound = new AudioClip(getClass().getResource("wheelTurn.wav").toString());
	private MediaPlayer bgm = new MediaPlayer(new Media(getClass().getResource("bgm.mp3").toString()));
	
	public void playBgm(){
		bgm.play();
	}
	
	public void placeDown(){
		placeDownSound.play();
	}
	
	public void placeFailed(){
		
	}
	
	public void selectSound(){
		selectSound.play();
	}
	
	public void selectFailed(){
		
	}
}
