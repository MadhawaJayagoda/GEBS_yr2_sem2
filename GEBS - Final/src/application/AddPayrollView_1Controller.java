package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.util.DBconnection;
import application.util.Toast;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class AddPayrollView_1Controller{
	
	@FXML
	TextField supplier_ID;
	
	@FXML
	TextField supplier_name;

	@FXML
	TextField item;
	
	@FXML
	TextField company;
	
	public void onSubmit() {
		try {

			Supplier supplier = new Supplier();

			try {
				Connection con = DBconnection.getConnection();

				PreparedStatement ps = con
						.prepareStatement("select fname, lname, company, item from supplier where supplier_ID = ?");
				int sup_id = Integer.parseInt(supplier_ID.getText().toString());

				ps.setInt(1, sup_id);

				ResultSet rs = ps.executeQuery();

				if (rs.next() == false) {
					Toast.show("No data found!\n\nPlease check again", supplier_ID);
					supplier_ID.clear();
					supplier_name.clear();
					item.clear();
					company.clear();
					return;

				} else {

					do {

						supplier.setSupplier_fname(rs.getString("fname"));
						supplier.setSupplier_lname(rs.getString("lname"));
						supplier.setCompany(rs.getString("company"));
						supplier.setItem(rs.getString("item"));

						String supplier_fullname = supplier.getSupplier_fname() + " " + supplier.getSupplier_lname();

						supplier_name.setText(supplier_fullname);
						item.setText(supplier.getItem());
						company.setText(supplier.getCompany());
					} while (rs.next());
				}

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			assignValuestoPayroll();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			Toast.show("Please enter a valid ID for the Supplier", supplier_name);
		}
	}
	
	public void assignValuestoPayroll() {
		int id = Integer.parseInt(supplier_ID.getText().toString());
		String product = item.getText();
		String comp = company.getText();
				
		Get_sup_payroll.getInstance().setId(id);
		Get_sup_payroll.getInstance().setProduct(product);
		Get_sup_payroll.getInstance().setCompany(comp);
		
		//Get_sup_payroll.print();   // use for testing 
	}
	
	// Check whether all the fields are filled and not any of the TextFields empty
	//return true if all fields are filled properly.
	public boolean validate(){
		if(supplier_ID.getText().isEmpty() || supplier_name.getText().isEmpty() || item.getText().isEmpty() || company.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void changeScenetoPayrollView_2(ActionEvent event) throws IOException {
		
		boolean res = validate();
		if (res == false) {
			Toast.show("One or many Fields are empty!\n Please fill all TextFields to proceed", supplier_ID);
			return;
		} else {

			Parent PayrollView_2Parent = FXMLLoader.load(getClass().getResource("/application/AddPayrollView_2.fxml"));
			Scene PayrollView_2Scene = new Scene(PayrollView_2Parent);

			// getting stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(PayrollView_2Scene);
			window.show();
		}
	}

	public void changeScene(ActionEvent event) throws IOException {
		Parent PayrollHomeViewParent = FXMLLoader.load(getClass().getResource("/application/payrollHomeView.fxml"));
		Scene PayrollHomeViewScene = new Scene(PayrollHomeViewParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		PayrollHomeViewScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(PayrollHomeViewScene);
		window.show();
	}
	
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
}
