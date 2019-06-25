package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	private Main main;
	
	/**
	 * setMain method is used to create the connection to the main class.
	 * Therefore performing any action triggered on the Main scene. 
	 */
	public void setMain(Main main) {
		this.main = main; 
	}
	
	
	/**
	 * When below method is called, it will change the scene to AddSupplierView
	 * @throws IOException 
	 */
	
	public void changeScenetoAddSupplier(ActionEvent event) throws IOException {
		Parent addSupplierParent = FXMLLoader.load(getClass().getResource("/application/AddSupplierView.fxml"));
		Scene addSupplierScene = new Scene(addSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(addSupplierScene);
		window.show();
	}
	
	public void changeScenetoViewAll(ActionEvent event) throws IOException {
		Parent viewAllSupplierParent = FXMLLoader.load(getClass().getResource("/application/ViewAllSuppliers.fxml"));
		Scene viewAllSupplierScene = new Scene(viewAllSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(viewAllSupplierScene);
		window.show();
	}
	
	public void changeScenetoDeleteSupplier(ActionEvent event) throws IOException {
		Parent deleteSupplierParent = FXMLLoader.load(getClass().getResource("/application/DeleteSupplierView.fxml"));
		Scene deleteSupplierScene = new Scene(deleteSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(deleteSupplierScene);
		window.show();
	}
	
	public void changeScenetoUpdateSupplier(ActionEvent event) throws IOException {
		Parent updateSupplierParent = FXMLLoader.load(getClass().getResource("/application/UpdateSupplierView.fxml"));
		Scene updateSupplierScene = new Scene(updateSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(updateSupplierScene);
		window.show();
	}
	
	public void changeScenetoSearchSupplier(ActionEvent event) throws IOException {
		Parent searchSupplierParent = FXMLLoader.load(getClass().getResource("/application/SearchSupplierView.fxml"));
		Scene searchSupplierScene = new Scene(searchSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(searchSupplierScene);
		window.show();
	}
	
	public void changeScenetoPayrollView_1(ActionEvent event) throws IOException {
		Parent PayrollView_1Parent = FXMLLoader.load(getClass().getResource("/application/AddPayrollView_1.fxml"));
		Scene PayrollView_1Scene = new Scene(PayrollView_1Parent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(PayrollView_1Scene);
		window.show();
	}

}
