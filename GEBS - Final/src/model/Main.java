package model;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	private static Stage primaryStageObj; 
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStageObj = primaryStage; 
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.TRANSPARENT); //disable the outer window border
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static Stage getStageObj() {
		return  primaryStageObj;
	}
}
