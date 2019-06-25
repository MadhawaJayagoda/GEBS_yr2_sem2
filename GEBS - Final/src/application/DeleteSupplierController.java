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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class DeleteSupplierController implements Initializable{
	
	@FXML
	private TableView<Supplier> table;
	@FXML
	private TableColumn<Supplier, Integer> col_id;
	@FXML
	private TableColumn<Supplier, String> col_fname;
	@FXML
	private TableColumn<Supplier, String> col_lname;
	@FXML
	private TableColumn<Supplier, String> col_company;
	@FXML
	private TableColumn<Supplier, String> col_category;
	@FXML
	private TableColumn<Supplier, String> col_item;
	@FXML
	private TextField input_id;
	@FXML
	private TextField input_fname;
	@FXML
	private TextField input_lname;
	
	ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Connection con = DBconnection.getConnection();
			
			ResultSet rs = con.createStatement().executeQuery("select * from supplier");
			
			while(rs.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setSupplierID(rs.getInt("supplier_ID"));
				supplier.setSupplier_fname(rs.getString("fname"));
				supplier.setSupplier_lname(rs.getString("lname"));
				supplier.setCompany(rs.getString("company"));
				supplier.setCategory(rs.getString("category"));
				supplier.setItem(rs.getString("item"));
				
				supplierList.add(supplier);
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
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		col_fname.setCellValueFactory(new PropertyValueFactory<>("supplier_fname"));
		col_lname.setCellValueFactory(new PropertyValueFactory<>("supplier_lname"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		
		table.setItems(supplierList);
	}

	public void loadDatabaseData() {
		try {
			
			input_id.clear();
			input_fname.clear();
			input_lname.clear();
			supplierList.clear();
			
			Connection con = DBconnection.getConnection();
			
			ResultSet rs = con.createStatement().executeQuery("select * from supplier");
			
			while(rs.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setSupplierID(rs.getInt("supplier_ID"));
				supplier.setSupplier_fname(rs.getString("fname"));
				supplier.setSupplier_lname(rs.getString("lname"));
				supplier.setCompany(rs.getString("company"));
				supplier.setCategory(rs.getString("category"));
				supplier.setItem(rs.getString("item"));
				
				supplierList.add(supplier);
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
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		col_fname.setCellValueFactory(new PropertyValueFactory<>("supplier_fname"));
		col_lname.setCellValueFactory(new PropertyValueFactory<>("supplier_lname"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		
		table.setItems(supplierList);
	}
	
	public void showOnClick() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			Connection con = DBconnection.getConnection();
			Supplier supplier = (Supplier) table.getSelectionModel().getSelectedItem();
			String query = "select * from supplier";
			PreparedStatement ps = con.prepareStatement(query);
			
			input_id.setText(Integer.toString(supplier.getSupplierID()));
			input_fname.setText(supplier.getSupplier_fname());
			input_lname.setText(supplier.getSupplier_lname());
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	public void deleteSupplier() {
		
		if ((input_id.getText().isEmpty() || input_fname.getText().isEmpty()
				|| input_lname.getText().isEmpty()) == true) {
			Toast.show("One or many fields are empty!\n Please click on the required supplier to delete from the list",
					input_lname);
		} else {

			String name = null;
			try {
				Connection con = DBconnection.getConnection();
				Supplier supplier = (Supplier) table.getSelectionModel().getSelectedItem();
				String query = "delete from supplier where supplier_ID=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, supplier.getSupplierID());
				name = supplier.getSupplier_fname() + " " + supplier.getSupplier_lname();
				ps.executeUpdate();

				// closing resources
				ps.close();
				con.close();

			} catch (SQLException ex) {
				System.out.println(ex);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			loadDatabaseData();
			application.util.Toast.show("Supplier : " + name + " deleted Successfully !", input_fname);
		}
	}
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent deleteSupplierParent = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene deleteSupplierScene = new Scene(deleteSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		deleteSupplierScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(deleteSupplierScene);
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
