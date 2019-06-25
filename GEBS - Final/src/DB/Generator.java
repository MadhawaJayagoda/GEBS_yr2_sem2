package DB;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Generator {

	
	
	/********Alert Box********/
public static void getAlert(String title,  String message ) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
}
