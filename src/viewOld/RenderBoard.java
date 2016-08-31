package viewOld;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameBoard;
import model.PieceCreationData;
import model.Tile;

public class RenderBoard extends Application{
	private Canvas canvas;
	private GraphicsContext gc;
	private CellEventHandler cellEventHandler;
	private int cellWidth = 50;
	private int cellHeight = 50;
	private ArrayList<RenderableEntity> entityList = new ArrayList<>();
	
	public static void main(String[]args){launch(args);}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		Group group = new Group();
		
		GameBoard board = new GameBoard(3, 5);
		board.populateBoard(new PieceCreationData());
		Tile[][]matrix = board.getTiles();
		/*
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				gc.setStroke(Color.BLACK);
				gc.strokeRect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
			}
		}
		group.getChildren().add(canvas);
		*/
		
		//set up pane
		Pane pane = new Pane();
		pane.setMinWidth(800);pane.setMinHeight(500);
		pane.setLayoutX(100);pane.setLayoutY(100);
		pane.setStyle("-fx-border-color: red;");
		group.getChildren().add(pane);
		
		//test
		GridPane gp = new GridPane();
		
		//set up board
		Image spriteSheet = new Image("tile.png");
		double width = 100;
		double height = 100;
		double viewX = 0;
		double viewY = 0;
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				pane.getChildren().add(new Cell(j*width, i*height, spriteSheet, viewX, viewY, width, height));
			}
		}
		
		primaryStage.setScene(new Scene(group));
		primaryStage.setWidth(1000);
		primaryStage.setHeight(700);
		primaryStage.show();
	}
	
	public void init(){
		canvas = new Canvas(300, 500);
		canvas.setLayoutX(100);canvas.setLayoutY(100);
		drawCanvasBoundary();
		cellEventHandler = new CellEventHandler();
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, cellEventHandler);
	}
	
	public void drawCanvasBoundary(){
		gc = canvas.getGraphicsContext2D();
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public double canvasX(double mouseX){
		return mouseX - canvas.getLayoutX();
	}
	
	public double canvasY(double mouseY){
		return mouseY - canvas.getLayoutY();
	}
	
	class CellEventHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent event) {
			double x = canvasX(event.getSceneX());
			double y = canvasY(event.getSceneY());
			double cellX = x - (x%cellWidth);
			double cellY = y - (y%cellHeight);
			gc.fillRect(cellX, cellY, cellWidth, cellHeight);
		}
	}
}
