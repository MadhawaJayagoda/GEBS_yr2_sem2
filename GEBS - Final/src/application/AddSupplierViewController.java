package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Dbconnect;import application.util.Toast;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Main;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class AddSupplierViewController {

	/**
	 * When below method is called, it will change the scene to AddSupplierView
	 * @throws IOException 
	 */
	
	
	@FXML 
	TextField fname;

	@FXML 
	TextField lname;
	
	@FXML
	TextField company;
	
	@FXML
	TextField address;
	
	@FXML
	TextField item;
	
	@FXML
	TextField phoneNum;
	
	@FXML
	private ChoiceBox categoryBox;
	
	//list with listeners to track changes
	ObservableList <String> categoryList = FXCollections.observableArrayList("Construction", "Trade");

	
	@FXML
	private void initialize() {
		categoryBox.setItems(categoryList);
	}
	
	Connection connection = null;
	PreparedStatement ps = null;
	
	public void addToDB(Supplier supplier) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		// opening a connection to the database
		try {
			connection = Dbconnect.connect();
			
			// Query execution
			  ps = connection.prepareStatement(
					"INSERT INTO supplier (fname, lname, company, address, category, item, phoneNum) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
					ps.setString(1, supplier.getSupplier_fname());
					ps.setString(2, supplier.getSupplier_lname());
					ps.setString(3, supplier.getCompany());
					ps.setString(4, supplier.getAddress());
					ps.setString(5, supplier.getCategory());
					ps.setString(6, supplier.getItem());
					ps.setString(7, supplier.getPhoneNum());
					
			
			int status = ps.executeUpdate(); // executeUpdate method returns an non-zero integer only if the execution
												// successful, else it returns zero.

			if (status != 0) {
				System.out.println("Database connection successful !");
				System.out.println("Records are Inserted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Finally block used to cleaning up and close resources
		finally {
			if (ps != null)
				ps.close();

			if (connection != null)
				connection.close();
		}
	}
	
	public void buttonSubmit(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		
		if((fname.getText().isEmpty() || lname.getText().isEmpty() || company.getText().isEmpty() || address.getText().isEmpty() || categoryBox.getSelectionModel().isEmpty() || item.getText().isEmpty() || phoneNum.getText().isEmpty()) == true ) {
			Toast.show("Error : Please fill all requested fields", fname);
		}
		else if(validatePhoneNumber() == false) {
			Toast.show("Error : Please enter a valide Phone Number !", fname);
		}
		else {
			
			Supplier supplier = new Supplier();
			
			supplier.setSupplier_fname(fname.getText());
			supplier.setSupplier_lname(lname.getText());
			supplier.setCompany(company.getText());
			supplier.setAddress(address.getText());
			supplier.setCategory(categoryBox.getValue().toString());
			supplier.setItem(item.getText());
			supplier.setPhoneNum(phoneNum.getText());
			
			
			try {
				this.addToDB(supplier);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				fname.clear();
				lname.clear();
				company.clear();
				address.clear();
				categoryBox.setValue(null);
				item.clear();
				phoneNum.clear();
				
			}
			Toast.show("Supplier details added successfully !", fname);
		}
		
	}
	
	public boolean validatePhoneNumber() {
		Pattern p = Pattern.compile("(0)[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
		Matcher m = p.matcher(phoneNum.getText());
		
		if(m.find() && m.group().equals(phoneNum.getText())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void demo() {
		fname.setText("Kavindya");
		lname.setText("Jayagoda");
		company.setText("Kalani");
		address.setText("Nawala");
		categoryBox.setValue("Construction");
		item.setText("Wires");
		phoneNum.setText("0773452684");
	}
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent addSupplierParent = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene addSupplierScene = new Scene(addSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		addSupplierScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(addSupplierScene);
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
