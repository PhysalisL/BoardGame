package view;

import java.util.ArrayList;

import calculation.Vector;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.PieceCreationData;
import model.PieceCreationData.Symbol;

public class PieceAnimationManager {
	private Pane container = new Pane();
	private ArrayList<PieceSprite>spriteList = new ArrayList<>();
	private PieceSprite[][] storage;
	
	//TEMP
	int CELL_WIDTH = 16;
	int CELL_HEIGHT = 16;
	
	public PieceAnimationManager(){
		container.setMouseTransparent(true);
		container.setStyle("-fx-border-color:red");
	}
	
	public void populate(PieceCreationData data){
		storage = new PieceSprite[data.getMatrix().length][data.getMatrix()[0].length];

		for(int i=0; i < data.getMatrix().length; i++){
			for(int j=0; j < data.getMatrix()[i].length; j++){
				if(data.getMatrix()[i][j] != Symbol.EMPTY){
					storage[i][j] = createGenericSprite(j,i);
				}
			}
		}
	}
	/*
	public void populate(PieceCreationData data){
		for(int i=0; i < data.getMatrix().length; i++){
			for(int j=0; j < data.getMatrix()[i].length; j++){
				if(i < data.getMatrix().length && j < data.getMatrix()[0].length){
					switch(data.getMatrix()[i][j]){
					case M: case H: case A: createGenericSprite(j,i); break;
					default: break;
					}
				}
			}
		}
	}
	*/
	
	private double viewHeight = 16;
	private double viewWidth = 16;
	public PieceSprite createGenericSprite(int cellX, int cellY){
		PieceSprite sprite = new PieceSprite(new Image("add.png"), 0, 0, viewWidth, viewHeight);
		//double x = cellX * CELL_WIDTH +(CELL_WIDTH - sprite.getFitWidth());
		//double y = cellY * CELL_HEIGHT +(CELL_HEIGHT - sprite.getFitHeight());
		double x = cellX * CELL_WIDTH;
		double y = cellY * CELL_HEIGHT;
		sprite.setX(x);
		sprite.setY(y);
		spriteList.add(sprite);
		container.getChildren().add(sprite);
		System.out.println("PIECE CREATED AT " +x/CELL_WIDTH+","+y/CELL_HEIGHT);
		return sprite;
	}
	
	public void setParentNode(Group parent){
		parent.getChildren().add(container);
	}
	
	public void setParentNode(StackPane stackpane){
		stackpane.getChildren().add(container);
	}
	
	private PieceSprite savedValue;
	public void saveValue(int cellX, int cellY){
		savedValue = storage[cellY][cellX];
	}
	public Vector getCoordinate(PieceSprite sprite){
		for(int i=0; i < storage.length; i++){
			for(int j=0; j < storage[i].length; j++){
				if(storage[i][j] == sprite){
					return new Vector(j,i);
				}
			}
		}
		return null;
	}
	/*
	public void saveValue(double cellX, double cellY, double cellWidth, double cellHeight){
		for(int i=0; i<spriteList.size(); i++){

			System.out.println("coords: "+Math.floor(spriteList.get(i).getX()/cellWidth)+" "+Math.floor(spriteList.get(i).getY()/cellHeight));
			if(	Math.floor(spriteList.get(i).getX()/cellWidth) == cellX && 
				Math.floor(spriteList.get(i).getY()/cellHeight) == cellY){
				savedValue = spriteList.get(i); 
				return;
			}
		}
		System.out.println(cellX+" "+cellY);
		System.out.println("NO MATCHING!!!!!!");
		System.out.println("NO MATCHING!!!!!!");
		System.out.println("NO MATCHING!!!!!!");
		System.out.println("NO MATCHING!!!!!!");
	}
	*/
	public PieceSprite getPieceAt(int cellX, int cellY){
		return null;
	}
	
	public void move(int cellX, int cellY, double cellWidth, double cellHeight){
		Vector coord = getCoordinate(savedValue);
		storage[cellY][cellX]= savedValue;
		storage[coord.getY()][coord.getX()] = null;
		double xDisplacement = (cellX*cellWidth) - savedValue.getX();
		double yDisplacement = (cellY*cellHeight) - savedValue.getY();
		savedValue.playTranslate(xDisplacement, yDisplacement);
	}
	/*
	public void move(double cellX, double cellY){
		double xDisplacement = (cellX*CELL_WIDTH) - savedValue.getX();
		double yDisplacement = (cellY*CELL_HEIGHT) - savedValue.getY();
		savedValue.playTranslate(xDisplacement, yDisplacement);
		//savedValue.positionTranslate(xDisplacement, yDisplacement);
	}
	*/
	/*
	public void move(double cellX, double cellY, double toCellX, double toCellY){
		for(int i=0; i<spriteList.size(); i++){
			if(spriteList.get(i).getX() == cellX*CELL_WIDTH && spriteList.get(i).getBottomY() == cellY*CELL_HEIGHT){
				spriteList.get(i).playTranslate(toCellX*CELL_WIDTH, toCellY*CELL_HEIGHT);
				break;
			}
		}
		
	}
	*/
	public void tick(){
		
	}
	
}
