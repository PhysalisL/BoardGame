package unusedclasses;


import java.io.File;

import calculation.Vector;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.CellManager;
import view.TiledMap;

public class test extends Application{

	
	public static void main(String[]args){ 
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group pane = new Group();
		//pane.setMinWidth(100);
		//pane.setMinHeight(100);
		
		Pane parent = new Pane();
		
		TiledMap r = new TiledMap();
		r.init("assets/boardgametile.tmx");
		Image image = new Image("Floor.png");
		
		CellManager cm = new CellManager(r.getRows(), r.getColumns());
		for(int i=0; i<r.getMapMatrix().length; i++){
			for(int j=0; j<r.getMapMatrix()[i].length; j++){
				
				//if(r.getMapMatrix()[i][j]-1 < 0) continue;
				
				int spriteNum = r.getMapMatrix()[i][j];
				Vector viewPos = r.spriteIndexToPortCoordinates(spriteNum);
				double viewWidth = r.getCellWidth();
				double viewHeight = r.getCellHeight();
				Rectangle2D viewport = new Rectangle2D(viewPos.getX(), viewPos.getY(), viewWidth, viewHeight);
				
				cm.createCell(image, viewport, j*viewWidth, i*viewHeight);
			}
		}
		cm.setParentNode(pane);
		
		parent.getChildren().add(pane);
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
		
	}
	
	
}
