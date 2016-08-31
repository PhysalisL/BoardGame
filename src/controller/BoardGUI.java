package controller;

import java.util.ArrayList;

import calculation.Vector;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GameBoard;
import model.PieceCreationData;
import view.CellManager;
import view.PieceAnimationManager;
import view.SoundManager;
import view.TiledMap;

public class BoardGUI extends Application{//THIS IS A CONTROLLER
	private GameBoard gameModel;
	private CellManager cellManager;//CELL MANAGER IS THE ONE DECIDING THE COORDINATES
	private SoundManager soundManager = new SoundManager();
	private PieceAnimationManager animationManager;
	
	double cellWidth = 16;
	double cellHeight = 16;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		
		//soundManager.playBgm();
		TiledMap tiledMap = new TiledMap();
		tiledMap.init("assets/boardgametile.tmx");
		//StackPane group = new StackPane();
		
		PieceCreationData data = new PieceCreationData();//temp creation data 
		
		gameModel = new GameBoard(data);
		gameModel.populateBoard(data);//need change
		/*
		cellManager = new CellManager(data.numberOfRows(), data.numberOfCol());//width height of board + each cell sprite
		cellManager.populateCells(data);
		cellManager.setParentNode(group);
		cellManager.displayContainerBound();
		cellManager.setHandler(new ClickHandler());
		*/
		///*
		Image image = new Image("Floor.png");
		cellManager = new CellManager(tiledMap.getRows(), tiledMap.getColumns());
		for(int i=0; i<tiledMap.getMapMatrix().length; i++){
			for(int j=0; j<tiledMap.getMapMatrix()[i].length; j++){
				int spriteNum = tiledMap.getMapMatrix()[i][j];
				Vector viewPos = tiledMap.spriteIndexToPortCoordinates(spriteNum);
				double viewWidth = tiledMap.getCellWidth();
				double viewHeight = tiledMap.getCellHeight();
				Rectangle2D viewport = new Rectangle2D(viewPos.getX(), viewPos.getY(), viewWidth, viewHeight);
				
				cellManager.createCell(image, viewport, j*viewWidth, i*viewHeight);
			}
		}
		cellManager.setParentNode(group);
		//cellManager.setScale(2);
		cellManager.setHandler(new ClickHandler());
		//*/
		
		animationManager = new PieceAnimationManager();//pieces
		animationManager.populate(data);//need change
		animationManager.setParentNode(group);
		
		group.setScaleX(2);
		group.setScaleY(2);
		group.setTranslateX(300);
		group.setTranslateY(300);
		
		primaryStage.setScene(new Scene(group));
		primaryStage.setWidth(1000);
		primaryStage.setHeight(700);
		primaryStage.show();
	}
	
	interface SelectionState{
		public void act(MouseEvent event);
	}
	private SelectionState currentState = new PointerSelectionState();
	class PointerSelectionState implements SelectionState{
		public void act(MouseEvent event){
			int x = cellManager.mouseXCellIndex(event.getX(), cellWidth);
			int y = cellManager.mouseYCellIndex(event.getY(), cellHeight);
			
			//Debug
			System.out.println("SELECTED");
			//Model
			gameModel.selectTile(x, y);
			//Cell
			ArrayList<Vector>posList = gameModel.getMovableTiles(x, y);
			cellManager.clearEffects();
			cellManager.setEffect(posList, CellManager.INNER_SHADOW);
			//Animation
			animationManager.saveValue(x, y);
			//State
			if(gameModel.tileAt(x, y).isOccupied()){
				currentState = new MoveSelectionState();
				soundManager.selectSound();
				System.out.println("SELECT SUCCESS! ENTER MOVE STATE");
			}//else stay in this state
		}
	}
	class MoveSelectionState implements SelectionState{
		public void act(MouseEvent event){
			int x = cellManager.mouseXCellIndex(event.getX(), cellWidth);
			int y = cellManager.mouseYCellIndex(event.getY(), cellHeight);
			
			cellManager.clearEffects();
			if(gameModel.move(x, y)){
				System.out.println("MOVE SUCCESS");
				soundManager.placeDown();
				cellManager.playAnimation(x, y);
				cellManager.setEffect(gameModel.getAttackableTiles(x, y), CellManager.INNER_SHADOW);
				
				animationManager.move(x, y, cellWidth, cellHeight);
				
				currentState = new AttackSelectionState();
				System.out.println("ENTER ATTACK STATE");
			}
			else{
				//When Move failed
				System.out.println("MOVE FAILED, GOING BACK TO SELECT STATE");
				currentState = new PointerSelectionState();
				
				//make it smoother
				if(gameModel.tileAt(x, y).isOccupied()){
					currentState.act(event);
				}
			}
		}
	}
	class AttackSelectionState implements SelectionState{
		public void act(MouseEvent event){
			int x = cellManager.mouseXCellIndex(event.getX(), cellWidth);
			int y = cellManager.mouseYCellIndex(event.getY(), cellHeight);
			
			System.out.println("ATTACKED");
			cellManager.clearEffects();
			if(gameModel.attack(x, y)){
				System.out.println("ATTACK SUCCESS, GOING BACK TO SELECT STATE");
				cellManager.playAnimation(x, y);
				//undo movement here
			}
			else{
				System.out.println("ATTACK FAILED, GOING BACK TO SELECT STATE");
			}
			currentState = new PointerSelectionState();
		}
	}
	
	class ClickHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent event) {
			currentState.act(event);
		}
	}
	
	public static void main(String[]args){launch(args);}
}