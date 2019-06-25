package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class UpdateSupplierController implements Initializable{
	
	@FXML
	private TableView<Supplier> table;
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
	private TextField input_fname;
	@FXML
	private TextField input_lname;
	@FXML
	private TextField input_company;
	@FXML
	private TextField input_address;
	@FXML
	private ChoiceBox input_category;
	@FXML
	private TextField input_item;
	@FXML
	private TextField input_phoneNum;
	
	static int tempSupplierID;
	static String name = null;
	
	ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
	
	//list with listeners to track changes
	ObservableList <String> categoryList = FXCollections.observableArrayList("Construction", "Trade");

	
	public void changeScene(ActionEvent event) throws IOException {
		Parent updateSupplierParent = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene updateSupplierScene = new Scene(updateSupplierParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		updateSupplierScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(updateSupplierScene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		input_category.setItems(categoryList);
		
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
		
		col_fname.setCellValueFactory(new PropertyValueFactory<>("supplier_fname"));
		col_lname.setCellValueFactory(new PropertyValueFactory<>("supplier_lname"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
		
		table.setItems(supplierList);
	}
	
	public void loadDatabaseData() {
		try {
			
			input_fname.clear();
			input_lname.clear();
			input_company.clear();
			input_address.clear();
			input_category.setValue(null);
			input_item.clear();
			input_phoneNum.clear();
			supplierList.clear();
			
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
		
		col_fname.setCellValueFactory(new PropertyValueFactory<>("supplier_fname"));
		col_lname.setCellValueFactory(new PropertyValueFactory<>("supplier_lname"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
		
		table.setItems(supplierList);
	}
	
	public void showOnClick() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			Connection con = DBconnection.getConnection();
			Supplier supplier = (Supplier) table.getSelectionModel().getSelectedItem();
			String query = "select * from supplier";
			PreparedStatement ps = con.prepareStatement(query);
			
			tempSupplierID = supplier.getSupplierID();
			input_fname.setText(supplier.getSupplier_fname());
			input_lname.setText(supplier.getSupplier_lname());
			input_company.setText(supplier.getCompany());
			input_address.setText(supplier.getAddress());
			input_category.setValue(supplier.getCategory());
			input_item.setText(supplier.getItem());
			input_phoneNum.setText(supplier.getPhoneNum());
			
			name = (String)(supplier.getSupplier_fname()+" "+supplier.getSupplier_lname());
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public void updateSupplier() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String query = "update supplier set fname=?, lname=?, company=?, address=?, category=?, item=?, phoneNum=? where supplier_ID='"+tempSupplierID+"'";
		
		
		if((input_fname.getText().isEmpty() || input_lname.getText().isEmpty() || input_company.getText().isEmpty() || input_address.getText().isEmpty() || input_category.getSelectionModel().isEmpty() || input_item.getText().isEmpty() || input_phoneNum.getText().isEmpty()) == true ) {
			Toast.show("Error : Please fill all requested fields", input_fname);
		}
		else if(validatePhoneNumber() == false) {
			Toast.show("Error : Please enter a valide Phone Number !", input_fname);
		}
		else {
			try {
				Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, input_fname.getText());
				ps.setString(2, input_lname.getText());
				ps.setString(3, input_company.getText());
				ps.setString(4, input_address.getText());
				ps.setString(5, input_category.getValue().toString());
				ps.setString(6, input_item.getText());
				ps.setString(7, input_phoneNum.getText());

				ps.execute();
				ps.close();
				application.util.Toast.show("Supplier : " + name + " updated Successfully !", input_fname);
				loadDatabaseData();

			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
	}
	
	public boolean validatePhoneNumber() {
		Pattern p = Pattern.compile("(0)[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
		Matcher m = p.matcher(input_phoneNum.getText());
		
		if(m.find() && m.group().equals(input_phoneNum.getText())) {
			return true;
		}
		else {
			return false;
		}
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
