package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import calculation.Vector;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.NullPiece;
import model.Piece;
import model.PieceCreationData;
import model.Tile;

public class CellManager {
	
	//private HashMap<Vector2, Cell> cells = new HashMap<>();
	private ArrayList<Cell> cellsInEffect = new ArrayList<>();
	private Cell[][] cells;
	private final Image NORMAL_TILE = new Image("tile.png");
	private final Image SPECIAL_TILE = new Image("pointer.png");
	//private final int CELL_WIDTH = 32;
	//private final int CELL_HEIGHT = 32;
	private Cell readerCell;
	private Cell choiceCell;
	private Pane container = new Pane();
	
	public static final Glow GLOW = new Glow(0.5);
	public static final InnerShadow INNER_SHADOW = new InnerShadow();
	
	public CellManager(int initRow, int initColumn){
		cells = new Cell[initRow][initColumn];
		
		INNER_SHADOW.setWidth(100);
		INNER_SHADOW.setHeight(100);
		INNER_SHADOW.setRadius(50);
		container.setStyle("-fx-border-color:blue");
	}
	/*
	public void populateCells(PieceCreationData data){
		for(int i=0; i < data.getMatrix().length; i++){
			for(int j=0; j < data.getMatrix()[i].length; j++){
				if(i < data.getMatrix().length && j < data.getMatrix()[0].length){
					if(inBound(j ,i)){
						switch(data.getMatrix()[i][j]){
						case M: case H: case A: createSpecialCell(i,j); break;
						default: createNormalCell(i,j); break;
						}
					}
				}
			}
		}
	}
	*/
	public void createCell(Image image, Rectangle2D viewport, double xPos, double yPos){
		Cell newCell = new Cell(image, viewport, xPos, yPos);
		int cellX = (int)(xPos/viewport.getWidth());
		int cellY = (int)(yPos/viewport.getHeight());
		if(cells[cellY][cellX] != null){
			container.getChildren().remove(cells[cellY][cellX]);
		}
		cells[cellY][cellX] = newCell;
		container.getChildren().add(newCell);
	}
	
	public void addCell(Cell cell){
		int cellX = (int)(cell.getX()/cell.getWidth());
		int cellY = (int)(cell.getY()/cell.getHeight());
		if(cells[cellY][cellX] != null){
			container.getChildren().remove(cells[cellY][cellX]);
		}
		cells[cellY][cellX] = cell;
		container.getChildren().add(cell);
	}
	
	public void setScale(double scale){
		container.setScaleX(scale);
		container.setScaleY(scale);
	}
	
	/*
	public void createNormalCell(int row, int column){
		Cell newCell = new Cell(column*CELL_WIDTH, row*CELL_HEIGHT, NORMAL_TILE, 0,0,CELL_WIDTH,CELL_HEIGHT);
		if(cells[row][column]!=null) 
			container.getChildren().remove(cells[row][column]);
		cells[row][column] = newCell;
		container.getChildren().add(newCell);
	}
	
	public void createSpecialCell(int row, int column){
		Cell newCell = new Cell(column*CELL_WIDTH, row*CELL_HEIGHT, SPECIAL_TILE, 0,0,CELL_WIDTH,CELL_HEIGHT);
		if(cells[row][column]!=null) 
			container.getChildren().remove(cells[row][column]);
		cells[row][column] = newCell;
		container.getChildren().add(newCell);
	}
	*/
	public void setParentNode(Group group){
		group.getChildren().add(container);
	}
	
	public void setParentNode(StackPane stackpane){
		stackpane.getChildren().add(container);
	}
	
	private boolean inBound(Vector position){
		return position.getX()>=0 && position.getY()>=0 && position.getX()<cells[0].length && position.getY()<cells.length;
	}
	
	private boolean inBound(int x, int y){
		return x>=0 && y>=0 && x<cells[0].length && y<cells.length;
	}
	
	public void setEffect(ArrayList<Vector> positions, Effect effect){
		for(int i=0; i<positions.size(); i++){
			int x = positions.get(i).getX();
			int y = positions.get(i).getY();
			if(inBound(positions.get(i))){
				cells[y][x].setEffect(effect);
				cellsInEffect.add(cells[y][x]);
			}
		}
	}
	
	public void setEffect(Vector position, Effect effect){
		if(inBound(position)){
			int x = position.getX();
			int y = position.getY();
			cells[y][x].setEffect(effect);
			cellsInEffect.add(cells[y][x]);
		}
	}
	
	public void playAnimation(ArrayList<Vector> positions){
		for(int i=0; i<positions.size(); i++){
			int x = positions.get(i).getX();
			int y = positions.get(i).getY();
			playAnimation(x, y);
				/*
				st.setOnFinished(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						cells[y][x].setEffect(INNER_SHADOW);
						cellsInEffect.add(cells[y][x]);
					}
				});
				*/
		}
	}
	
	public void playAnimation(int x, int y){
		if(inBound(x, y)){
			RotateTransition rt = new RotateTransition();
			rt.setNode(cells[y][x]);
			rt.setDuration(Duration.seconds(1));
			rt.setByAngle(360);
			rt.setInterpolator(Interpolator.EASE_BOTH);
			rt.play();
		}
	}
	
	public void clearEffects(){
		/*
		for(int i=0; i<cellsInEffect.size(); i++){
			cellsInEffect.get(i).setEffect(null);
		}
		cellsInEffect.clear();
		*/
		for(Iterator<Cell> i = cellsInEffect.iterator(); i.hasNext();){
			i.next().setEffect(null);
			i.remove();
		}
	}
	/*
	public void readerMove(int x, int y){
		if(inBound(x, y))
			readerCell = cells[y][x];
	}
	
	public void choiceMove(int x, int y){
		if(inBound(x, y))
			choiceCell = cells[y][x];
	}
	
	public Vector2 getReaderPosition(double cellWidth, double cellHeight){
		return new Vector2( (int)(readerCell.getX()/cellWidth), (int)(readerCell.getY()/cellHeight));
	}
	
	public Vector2 getChoicePosition(double cellWidth, double cellHeight){
		return new Vector2( (int)(choiceCell.getX()/cellWidth), (int)(choiceCell.getY()/cellHeight));
	}
	*/
	public void displayContainerBound(){
		container.setStyle("-fx-border-color:blue;");
	}
	
	public int mouseXCellIndex(double mouseX, double cellWidth){
		return (int)((mouseX-container.getLayoutX())/(cellWidth*container.getScaleX()));
	}
	
	public int mouseYCellIndex(double mouseY, double cellHeight){
		return (int)((mouseY-container.getLayoutY())/(cellHeight*container.getScaleY()));
	}
	
	public void setHandler(EventHandler<MouseEvent> handler){
		container.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
	}
	
	/*
	public void createNormalCell(int x, int y){
		cells.put(new Vector2(x, y), new Cell(x*CELL_WIDTH, y*CELL_HEIGHT, NORMAL_TILE, 0,0,CELL_WIDTH,CELL_HEIGHT));
	}
	
	public void createSpecialCell(int x, int y){
		cells.put(new Vector2(x, y), new Cell(x*CELL_WIDTH, y*CELL_HEIGHT, SPECIAL_TILE, 0,0,CELL_WIDTH,CELL_HEIGHT));
	}
	*/
}
