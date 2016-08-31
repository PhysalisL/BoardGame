package unusedclasses;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FrameTransition extends Transition{
	private ImageView imageView;
	//private Image spriteSheet;
	private double totalFrames;
	private double timeLine;
	
	private double vX = 0;
	private double vY = 0;
	private double vW;
	private double vH;
	
	
	private double totalWidth;
	public FrameTransition(ImageView imageView, double totalImageWidth, double width, double height){
		vW = width;
		vH = height;
		this.imageView = imageView;
		totalFrames = (totalWidth/width);
		timeLine = 0;
		totalWidth = totalImageWidth;
		setInterpolator(Interpolator.LINEAR);
	}
	/*
	public void setSpriteSheet(Image newSheet, double width, double height){
		spriteSheet = newSheet;
		currentFrame.setImage(newSheet);
		currentFrame.setViewport(new Rectangle2D(0, 0, width, height));
	}
	*/
	public void setFrame(int index){
		//index = index*vW%spriteSheet.getWidth();
		imageView.setViewport(new Rectangle2D(index*vW, vY, vW, vH));
		System.out.println("SS");
	}

	@Override
	protected void interpolate(double frac) {
		int index = (int)(frac*totalWidth);
		setFrame(index);
		System.out.println("Current index: "+index);
	}

}
