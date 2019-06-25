package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.util.DBconnection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class ViewAllSupplierController implements Initializable{
	
	@FXML
	private TableView<Supplier> allSupplierList;
	@FXML
	private TableColumn<Supplier, Integer> col_id;
	@FXML
	private TableColumn<Supplier, String> col_fname;
	@FXML
	private TableColumn<Supplier, String> col_lname;
	@FXML
	private TableColumn<Supplier, String> col_company;
	@FXML
	private TableColumn<Supplier, String> col_address;
	@FXML
	private TableColumn<Supplier, String> col_category;
	@FXML
	private TableColumn<Supplier, String> col_item;
	@FXML
	private TableColumn<Supplier, String> col_phoneNum;
	
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
				supplier.setAddress(rs.getString("address"));
				supplier.setCategory(rs.getString("category"));
				supplier.setItem(rs.getString("item"));
				supplier.setPhoneNum(rs.getString("phoneNum"));
				
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
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
		
		allSupplierList.setItems(supplierList);
	}
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent viewAllSupplierParent = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene viewAllSupplierScene = new Scene(viewAllSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		viewAllSupplierScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(viewAllSupplierScene);
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
