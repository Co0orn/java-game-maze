package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
  public void start(Stage primaryStage) {
	 try {
		 window maze = new window(640,480);
		 Scene sc = new Scene(maze.getGp(),640,480);
		 maze.drowmap();
		 primaryStage.setScene(sc);
		 primaryStage.setTitle("ÀÏÊóÃÔ¹¬");
		 primaryStage.show();
		 sc.setOnKeyPressed(e->{
			 switch(e.getCode()) {
			 case UP->{maze.move(1);maze.isWin();}
			 case DOWN->{maze.move(2);maze.isWin();}
			 case LEFT->{maze.move(3);maze.isWin();}
			 case RIGHT->{maze.move(4);maze.isWin();}
			 case ESCAPE->System.exit(0);
			 case ENTER->{maze.auto();maze.isWin();}
			 }});
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	 
  }
  
}

