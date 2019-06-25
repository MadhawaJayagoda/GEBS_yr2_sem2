package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.LoginModel;
import model.Main;
import model.User;
import controller.homeController;
import controller.EmployeeController;
public class MainController {
	
	Connection con = null;
	@FXML
	private TextField userfield;
	@FXML
	private TextField passwordfield;
	@FXML
	private Button loginTestButton;
	@FXML
	private Label loginLabel;
	
    public LoginModel loginModel = new LoginModel();
    
    
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	public void MinimizeWindow(ActionEvent event) throws Exception{
		
		//get the stage from Main class
		Main.getStageObj().setIconified(true);
	}
	
	
	public void LogInSuccess(User usr1) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
		Parent view = (Parent) loader.load();
		homeController hc = loader.getController();
		hc.setUser(usr1);
		
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}		
	@FXML
	public void loginTest(ActionEvent event) throws Exception {
	User user = new User();
	user.setUsername(userfield.getText());

		try {
			if(loginModel.IsLogin(userfield.getText(), passwordfield.getText())) {
				
				LogInSuccess(user);
				}else {
					loginLabel.setText("Failure");
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


		
		
		/*if(userfield.getText().equals("user") && passwordfield.getText().equals("pass")) {
			try{
				LogInSuccess();
			}catch (Exception e) {
				System.out.println(e);
			}
		}else {
			loginLabel.setText("Failure");
		}
		
	}*/
}

