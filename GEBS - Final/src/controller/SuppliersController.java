package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Main;

public class SuppliersController {

	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	public void StockScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Stocks.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void EmployeeScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/EmployeeScreen.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
	}
	
	public void DeliveryScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Delivery.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void RepairScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Repairs.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
	}
	
	public void CustomerOrderScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/CustomerOrder.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
	}
	
	public void ContractsScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Contracts.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void FinanceScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Finance.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	
	public void SuppliersScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
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
	
	public void changeScenetoPayrollHome(ActionEvent event) throws IOException {
		Parent PayrollHomeViewParent = FXMLLoader.load(getClass().getResource("/application/payrollHomeView.fxml"));
		Scene PayrollHomeViewScene = new Scene(PayrollHomeViewParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(PayrollHomeViewScene);
		window.show();
	}
}

