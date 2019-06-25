package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
			Parent root = loader.load();

			// creating connection between Main and the MainControlller java classes.
			MainController mainController = loader.getController();
			mainController.setMain(this);

			Scene scene = new Scene(root, 1654, 931);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
