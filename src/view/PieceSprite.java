package view;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PieceSprite extends ImageView{
	//private ImageView sprite;
	private TranslateTransition translateTransition = new TranslateTransition();
	private double height;
	
	public PieceSprite(Image image, double xPos, double yPos, double viewX, double viewY, double viewWidth, double viewHeight){
		setX(xPos);
		setY(yPos);
		setViewport(new Rectangle2D(viewX, viewY, viewWidth, viewHeight));
		setImage(image);
		height = viewHeight;
		translateTransition.setNode(this);
		translateTransition.setInterpolator(Interpolator.LINEAR);
	}
	
	public PieceSprite(Image image, double viewX, double viewY, double viewWidth, double viewHeight){
		setViewport(new Rectangle2D(viewX, viewY, viewWidth, viewHeight));
		setImage(image);
		height = viewHeight;
		translateTransition.setNode(this);
		translateTransition.setInterpolator(Interpolator.LINEAR);
	}
	
	public void init(){
		
	}
	
	public int getCellX(int cellWidth){
		return (int)getX()/cellWidth;
	}
	
	public int getCellY(int cellHeight){
		return (int)getY()/cellHeight;
	}
	
	public double getBottomY(){
		return this.getY()+height;
	}
	/*
	public void dataTranslate(double xDisplacement, double yDisplacement){
		setX(getX()+xDisplacement);
		setY(getY()+yDisplacement);
	}
	*/
	
	/**
	 * Translates the PieceSprite by the displacements. This method does not change the actual position of the PieceSprite.
	 * @param xDisplacement
	 * @param yDisplacement
	 */
	public void playTranslate(double xDisplacement, double yDisplacement){
		translateTransition.setToX(xDisplacement);
		translateTransition.setToY(yDisplacement);
		translateTransition.play();
	}
	
	public void playAttack(){
		
	}
	
	public void playHurt(){
		
	}
	
}
