package unusedclasses;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FrameAnimationTessst extends Application{
	public static void main(String[]args){
		launch(args);
	}
	
	public void start(Stage primaryStage){
		Group group = new Group();
		
		//CellManager cm = new CellManager(50, 50);
		
		
		Image src = new Image("charDefault.png");
		ImageView iv = new ImageView(src);
		iv.setViewport(new Rectangle2D(0,0,32,32));
		FrameTransition ft = new FrameTransition(iv, src.getWidth(), 32, 32);
		//ft.setCycleDuration(Duration.seconds(1));
		ft.setOnFinished(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e ){
				System.out.println("FINISHED");
			}
		});
		//ft.setCycleCount(10000);
		//ft.play();
		
		System.out.println(iv.viewportProperty().get().getHeight());
		
		group.getChildren().add(iv);
		
		primaryStage.setScene(new Scene(group));
		primaryStage.show();
	}
}
