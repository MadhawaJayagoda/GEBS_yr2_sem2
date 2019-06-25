package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import application.util.DBconnection;
import application.util.Toast;
import controller.Dbconnect;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class SearchSupplierController implements Initializable{

		@FXML
		TableView<Supplier> allSupplierList;
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
		
		@FXML
		private TextField input_search;
		@FXML
		private Button btn_search;
		
		
		ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
		FilteredList<Supplier> filteredData = new FilteredList<>(supplierList, e->true);
		
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
		
		public void searchSupplier() {
			input_search.textProperty().addListener((ObservableValue,oldValue,newValue)->{
					filteredData.setPredicate((Predicate<? super Supplier>)supplier->{
							
							if(newValue == null || newValue.isEmpty()) {
								return true;
							}
							
							String lowerCaseFilter = newValue.toLowerCase();
							
							if(supplier.getSupplier_fname().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}
							else if(supplier.getSupplier_lname().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}
							else if(supplier.getCompany().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}
							else if(supplier.getCategory().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}
							else if(supplier.getItem().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}
							else if(Integer.toString(supplier.getSupplierID()).contains(lowerCaseFilter)) {
								return true;
							}
							else if(supplier.getPhoneNum().contains(lowerCaseFilter)) {
								return true;
							}
							return false;
					});
			});
			
			SortedList<Supplier> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(allSupplierList.comparatorProperty());
			allSupplierList.setItems(sortedData);
			
			btn_search.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					searchSupplier();
					if(filteredData.isEmpty() == true) {
						application.util.Toast.changeToastDuration(2500);  
						application.util.Toast.show("Sorry, No Results found for \"" + input_search.getText() + "\" !", input_search);
					}
					
				}
			});
		
		}
		
		public void changeScene(ActionEvent event) throws IOException {
			Parent searchSupplierParent = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
			Scene searchSupplierScene = new Scene(searchSupplierParent);
			
			//getting stage information
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			searchSupplierScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			window.setScene(searchSupplierScene);
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
