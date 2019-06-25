package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import model.Main;
import model.User;
import util.generator;

public class UserManageController {
	
	    Connection con = null;
	
	    @FXML
		public Label userId;
		@FXML
		public Label UserType;
		@FXML
		private TextField userfield;
		@FXML
		private TextField passwordfield;
		@FXML
		private TextField idField;
		@FXML
	    private ChoiceBox<String> designationField;
		@FXML
		private Button insertbtn;
		@FXML
		private Button refreshfbtn; 
		@FXML
		private Button deletebtn;
		@FXML
		private Button logOutButton;
		
		
		@FXML
		private TableView<User> tablefield;
		@FXML
		private TableColumn<User,String> idfield;
		@FXML
		private TableColumn<User,String> designationfield;
		@FXML
		private TableColumn<User,String> userField;
		public int privilegelevel = 0;
		
		public User userObj = new User();
	 
		
		
		ObservableList<String> employeeType =FXCollections.observableArrayList("Manager","Human Resources","Accountant","Project Manager","Salesperson");

		public void initialize() {
			loaduser();
			designationField.setValue("Select a designation");
			designationField.setItems(employeeType);
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
	
	public void RepairScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/Repairs.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
	}
	
	public void EmployeeScreen( ActionEvent event ) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeScreen.fxml"));
		Parent view = (Parent) loader.load();
		EmployeeController ec = loader.getController();
		ec.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
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
	
	
	public void CustomerOrderScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/CustomerOrder.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
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
	
	
	public void EmpSalaryScreen( ActionEvent event ) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeSalary.fxml"));
		Parent view = (Parent) loader.load();
		EmployeeSalaryController sc = loader.getController();
		sc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void UserManageScreen( ActionEvent event ) throws Exception{
		Parent view = FXMLLoader.load(getClass().getResource("/view/UserManage.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	 @FXML
	    void homeScreen(ActionEvent event) throws Exception{
		 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
			Parent view = (Parent) loader.load();			
			homeController hc = loader.getController();
			Scene scene = new Scene(view);
			hc.receieveDetails(UserType.getText(),userId.getText());
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj(); 
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();

	    }
	 @FXML
		public void insertPressed(ActionEvent event) {
			con = Dbconnect.connect();
			
			User u =new User();
			u.setId(idField.getText());
			u.setDesignation(designationField.getValue());
			 boolean isMyComboBoxEmpty = designationField.getSelectionModel().isEmpty();
				if(isMyComboBoxEmpty == true) {
					generator.getAlert("Combobox Empty", "Please select a valid designation");
					return;
				}
			u.setUsername(userfield.getText());
			u.setPassword(passwordfield.getText());
			if(userfield.getText().isEmpty()) {
				generator.getAlert("Error", "Employee username field empty");
				return;
			}
			if(passwordfield.getText().isEmpty()) {
				generator.getAlert("Error", "Employee password field empty");
				return;
			}
			if(idField.getText().isEmpty()) {
				generator.getAlert("Error", "Employee id field empty");
				return;
			}

			
			String p = "INSERT INTO user (id, designation, user, password) values (?,?,?,?)";
			
			try {
				PreparedStatement pst = con.prepareStatement(p);
				  
				pst.setString(1, u.getId());
				pst.setString(2, u.getDesignation());
				pst.setString(3, u.getUsername());
				pst.setString(4, u.getPassword());
				pst.execute();
				loaduser ();
			
				generator.getAlert("Success", "Record successfully inserted");
				
			}
			catch(Exception e1) {
				System.out.println(e1);
			}
			clearFields();

		}
	 @FXML
		public void deleteuser(ActionEvent event) {
		 String r = "delete from user where id = ?";
			try {
			PreparedStatement pst = con.prepareStatement(r);
			pst.setString(1, idField.getText());
			pst.execute();
			
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
			loaduser();
			clearFields();
		}
	
	@FXML
		public void loaduser(){
		ObservableList<User> userdetails = FXCollections.observableArrayList();
		try {
			 
			tablefield.setItems(null);
			con = Dbconnect.connect();
			String sql = "SELECT * from user";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				userdetails.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		idfield.setCellValueFactory(new PropertyValueFactory<>("id"));
		designationfield.setCellValueFactory(new PropertyValueFactory<>("designation"));
		userField.setCellValueFactory(new PropertyValueFactory<>("username"));
		
		tablefield.setItems(userdetails);
		clearFields();

			
		}
	@FXML
	public void tablemouseclick(MouseEvent event) {
		 User u= tablefield.getSelectionModel().getSelectedItem();
		 String id = u.getId();
		 String designation = u.getDesignation();
		 String user = u.getUsername();
		 String password = u.getPassword();
		 
		 idField.setText(id);
		 designationField.setValue(designation);
		 userfield.setText(user);
		 passwordfield.setText(password);
		
	
	}
	public void clearFields() {
		idField.clear();
		designationField.setValue("Select a designation");
		designationField.setItems(employeeType);
		userfield.clear();
		passwordfield.clear();
			}
	
	 public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
	 public void logout(ActionEvent event) {
			try {
			Parent view = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(view);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj();
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();
			}catch(Exception e) {
				System.out.println(e);
			}
}
}
