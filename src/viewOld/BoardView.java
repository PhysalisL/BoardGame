package viewOld;

import calculation.Vector;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import model.GameBoard;
import model.Tile;

public class BoardView extends Canvas implements View{
	
	private Cell[][] tileView;
	private GraphicsContext gc;
	private Image pointer;
	
	public BoardView(double x, double y, double width, double height){
		super(width, height);
		setLayoutX(x);
		setLayoutY(y);
		gc = this.getGraphicsContext2D();
		pointer = new Image("pointer.png");
	}
	
	public void populateBoard(Tile[][] modelTiles){
		Image spriteSheet = new Image("tile.png");
		double width = 100;
		double height = 100;
		double viewX = 0;
		double viewY = 0;
		for(int i=0; i<modelTiles.length; i++){
			for(int j=0; j<modelTiles[i].length; j++){
				tileView[i][j] = new Cell(j*width, i*height, spriteSheet, viewX, viewY, width, height);
			}
		}
	}
	
	public void addToParent(Group parentNode){
		parentNode.getChildren().add(this);
	}
	
	/**
	 * if moved
	 * 		pointTo
	 * @param position
	 */
	public void pointTo(Vector position){
		
	}
	
	public double getCanvasMouseX(double mouseX){
		return mouseX - getLayoutX();
	}
	
	public double getCanvasMouseY(double mouseY){
		return mouseY - getLayoutY();
	}
	
	public void renderBoard(){
		
	}
}
