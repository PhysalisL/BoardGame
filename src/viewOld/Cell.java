package viewOld;


import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Cell extends ImageView implements RenderableEntity{
	public static final Color DEFAULT_HIGHLIGHT = Color.AQUA;
	private Color highlight = DEFAULT_HIGHLIGHT;
	private boolean freeze = false;
	
	public Cell(double xPos, double yPos, Image tileSheet, double viewportX, double viewportY, double viewportW, double viewportH) {
		super(tileSheet);
		setX(xPos);
		setY(yPos);
		setViewport(new Rectangle2D(viewportX, viewportY, viewportW, viewportH));
		
		//test
		handlerInit();
	}
	
	public void freeze(){
		freeze = true;
	}
	
	public void unfreeze(){
		freeze = false;
	}
	
	public void setColor(Color color){
		highlight = color;
	}

	public void render(GraphicsContext gc) {
		//gc.setStroke(Color.BLACK);
		//gc.setFill(highlight);
		//gc.fillRect(x, y, width, height);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	private void handlerInit(){
		CellHandler handler = new CellHandler();
		setOnMouseExited(handler);
		setOnMouseEntered(handler);
		setOnMousePressed(handler);
		setOnMouseReleased(handler);
		
	}
	
	private void pushIn(boolean turnOn){
		InnerShadow is = new InnerShadow();
		is.setColor(Color.BLACK);
		if(turnOn){
			is.setWidth(100);
			is.setHeight(100);
			is.setRadius(50);
		}
		else{
			is.setWidth(0);
			is.setHeight(0);
		}
		setEffect(is);
	}
	
	private void glow(boolean turnOn){
		Glow glow = new Glow();
		if(turnOn){
			glow.setLevel(0.5);
		}
		else{
			glow.setLevel(0);
		}
		setEffect(glow);
	}
	
	private void popOut(double change, double imageWidth, double imageHeight){
		PerspectiveTransform pt;
		if(change == 0){
			setEffect(null);
		}
		else{
			pt = new PerspectiveTransform();
			pt.setUlx(getX()-change);
			pt.setUly(getY()-change);
			pt.setUrx(getX()+imageWidth+change);
			pt.setUry(getY()-change);
			pt.setLrx(getX()+imageWidth+change);
			pt.setLry(getY()+imageHeight+change);
			pt.setLlx(getX()-change);
			pt.setLly(getY()+imageHeight+change);
			setEffect(pt);
			this.toFront();
		}
	}
	
	private void colorAdjust(double value){
		ColorAdjust adjustment = new ColorAdjust();
		adjustment.setBrightness(value);
		setEffect(adjustment);
	}
	
	class CellHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if(!freeze){
				if(event.getEventType() == MouseEvent.MOUSE_ENTERED){
					glow(true);
				}
				if(event.getEventType() == MouseEvent.MOUSE_EXITED){
					glow(false);
				}
				if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
					pushIn(true);
				}
				if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
					pushIn(false);
				}
			}
		}
		
	}
	
}
