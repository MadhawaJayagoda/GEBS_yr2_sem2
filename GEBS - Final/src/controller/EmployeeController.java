package controller;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Employee;
import model.Main;
import model.User;
import util.generator;

public class EmployeeController {

	Connection con = null;
	
	ObservableList<String> genderlist = FXCollections.observableArrayList("Male","Female");
	@FXML
	private Button demoBtn;
	@FXML
	public Label userId;
	@FXML
	public Label UserType;
	@FXML
	private Button logOutButton;
	@FXML
	private TextField empIDField;
	@FXML
	private TextField fnameField;
	@FXML
	private TextField lnameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField NICField;
	@FXML
	private TextField telephoneField;
	@FXML
	private TextField designationField;
	@FXML
	private TextField salaryField;
	@FXML
	private DatePicker dobField;
	@FXML
	private ComboBox<String> genderfield;
	@FXML
	private Button addBtn;
	@FXML
	private Button updateBtn;
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button refreshfield;

	
	@FXML
	private TableView<Employee> tablefield;
	@FXML
	private TableColumn<Employee,String> eidfield;
	@FXML
	private TableColumn<Employee,String> efnamefield;
	@FXML
	private TableColumn<Employee,String> elnamefield;
	@FXML
	private TableColumn<Employee,Date> ebdatefield;
	@FXML
	private TableColumn<Employee,String> egenderfield;
	@FXML
	private TableColumn<Employee,String> eaddressfield;
	@FXML
	private TableColumn<Employee,String> enicfield;
	@FXML
	private TableColumn<Employee,String> etelenumfield;
	@FXML
	private TableColumn<Employee,String> edesignationfield;
	@FXML
	private TableColumn<Employee,Double> esalaryfield;

	public int privilegelevel = 0;
	
	public User userObj = new User();
 
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	//------Validation-------///
	public boolean validate()
	{
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(fnameField.getText());
		
		if(fnameField.getText().isEmpty()) {
			generator.getAlert("Error", "Employee first name field empty");
			return false;
		}
		
		if(!m.matches()) {
			generator.getAlert("Employee First Name field", "Invalid Character");
			return false;
		}
		
		Pattern p1 = Pattern.compile("[0-9]+");
		@SuppressWarnings("unused")
		Matcher m1 = p1.matcher(empIDField.getText());
		if(empIDField.getText().isEmpty()) {
			generator.getAlert("Error", "Employee ID field empty");
			return false;
		}
		
		if(!m.matches()) {
			generator.getAlert("Employee ID field", "Invalid Character");
			return false;
		}
		
		if(NICField.getText().isEmpty()) {
			generator.getAlert("Error", "NIC field empty");
			return false;
		}
		
		if(telephoneField.getText().isEmpty()) {
			generator.getAlert("Error", "Telephone field empty");
			return false;
		}
		
		return true;
		
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserManage.fxml"));
		Parent view = (Parent) loader.load();
		UserManageController umc = loader.getController();
		umc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	@FXML
	private void initialize() {
		
		con = Dbconnect.connect();
		genderfield.setValue("Select gender");
		genderfield.setItems(genderlist);
		load(); 
		
		
	}
	@FXML
	public void submitted(ActionEvent event) {
		String emPID = empIDField.getText();
		 String NiC = NICField.getText();
		 String tel = telephoneField.getText();
		 
		 		 
		 if(validate() ){
			 boolean isMyComboBoxEmpty = genderfield.getSelectionModel().isEmpty();
			if(isMyComboBoxEmpty == true) {
				generator.getAlert("Combobox Empty", "Please select a valid gender");
				return;
			}
			 
		
			 if(NiC.length() != 10) {
				 generator.getAlert("NIC Field Error", "10 characters are required");
				 return;
			 }
			 if(NiC.charAt(9) != 'V') {
				 generator.getAlert("NIC Field Error", "'V' required at the end of the NIC number ");
				 return;
			 }
			 if(tel.length() != 10) {
				 generator.getAlert("Contact Number Field Error", "10 characters are required");
				 return;
			 }
			 
			 if(emPID == null || emPID.length() == 0) {
				 generator.getAlert("Employee ID not entered", "Please enter the employee ID");
				 return;
			 }
			 try {
				 @SuppressWarnings("unused")
				int tele = Integer.parseInt(tel);
			 }catch(NumberFormatException nfe) {
				 generator.getAlert("Phone number contains other digits", "Please enter a valid phone number");
				 return;
			 }
			
		 
		 con = Dbconnect.connect();
		
		 Employee e = new Employee();
		 
		 e.setId(empIDField.getText());
		 e.setFirstname(fnameField.getText());
		 e.setLastname(lnameField.getText());
		 try {
		 @SuppressWarnings("unused")
		Date sqlDate = Date.valueOf(dobField.getValue());
		 }catch(NullPointerException npe) {
			 generator.getAlert("No valid date", "Please enter a valid date");
			 return;
		 }
		 Date sqlDate = Date.valueOf(dobField.getValue());
		 e.setBday(sqlDate);
		 e.setGender(genderfield.getValue());
		 e.setAddress(addressField.getText());
		 e.setNic(NICField.getText());
		 e.setTelenum(telephoneField.getText());
		 e.setDesignation(designationField.getText());
		 try{
			 e.setSalary(Double.parseDouble(salaryField.getText()));
		 }catch(NumberFormatException nfe) {
			 generator.getAlert("Format Error", "Please enter a valid number for salary");
			 return;
		 }
		

		
			 
		 String q = "INSERT INTO employee (eid, fname, lname, bdate, gender, address, nic, telnum, designation, salary, finalsalary) values (?,?,?,?,?,?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pst = con.prepareStatement(q);
			 
			 pst.setString(1, e.getId());
			 pst.setString(2, e.getFirstname());
			 pst.setString(3, e.getLastname());
			 pst.setDate(4, e.getBday());
			 pst.setString(5, e.getGender());
			 pst.setString(6, e.getAddress());
			 pst.setString(7, e.getNic());
			 pst.setString(8, e.getTelenum());
			 pst.setString(9, e.getDesignation());
			 pst.setDouble(10, e.getSalary());
			 pst.setDouble(11, e.getFinalsalary());
			 pst.execute();
			 load();
		 }catch(Exception e2)
		 {
			 System.out.println(e2);
		 }
		 }else {
			 generator.getAlert("Error", "Invalid character");
		 }
		 clearFields();
	} 

	@FXML
	public void load() {
		ObservableList<Employee> employeedetails = FXCollections.observableArrayList();
		try {
			 
			tablefield.setItems(null);
			con = Dbconnect.connect();
			String sql = "SELECT * from employee";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				employeedetails.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDouble(10)));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		eidfield.setCellValueFactory(new PropertyValueFactory<>("id"));
		efnamefield.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		elnamefield.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		ebdatefield.setCellValueFactory(new PropertyValueFactory<>("bday")); 
		egenderfield.setCellValueFactory(new PropertyValueFactory<>("gender"));
		eaddressfield.setCellValueFactory(new PropertyValueFactory<>("address"));
		enicfield.setCellValueFactory(new PropertyValueFactory<>("nic"));
		etelenumfield.setCellValueFactory(new PropertyValueFactory<>("telenum"));
		edesignationfield.setCellValueFactory(new PropertyValueFactory<>("designation"));
		esalaryfield.setCellValueFactory(new PropertyValueFactory<>("salary"));
		tablefield.setItems(employeedetails);
		clearFields();
		
		
		
	}@FXML
	public void update(ActionEvent event) {
		Employee e1 = new Employee();
		
		 String eid = empIDField.getText();
		 String fname = fnameField.getText();
		 String lname = lnameField.getText();
		 try {
		 @SuppressWarnings("unused")
		Date sqlDate = Date.valueOf(dobField.getValue());
		 }catch(NullPointerException npe) {
			 generator.getAlert("No date found", "Please enter a valid date");
			 return;
		 }
		 Date sqlDate = Date.valueOf(dobField.getValue());

		 String gender = genderfield.getValue();
		 String address = addressField.getText();
		 String nic = NICField.getText();
		 String telnum = telephoneField.getText();
		 String designation = designationField.getText();
		 try {
		 e1.setSalary(Double.parseDouble(salaryField.getText()));
		 }catch(NumberFormatException nfe) {
			 generator.getAlert("NumberFormatException", "Please enter a valid number for salary");
			 return;
		 }
		 
		 if(validate() ){
			 boolean isMyComboBoxEmpty = genderfield.getSelectionModel().isEmpty();
				if(isMyComboBoxEmpty == true) {
					generator.getAlert("Combobox Empty", "Please select a valid gender");
					return;
				}
				 
				
				 if(nic.length() != 10) {
					 generator.getAlert("NIC Field Error", "10 characters are required");
					 return;
				 }
				 if(nic.charAt(9) != 'V') {
					 generator.getAlert("NIC Field Error", "'V' required at the end of the NIC number ");
					 return;
				 }
				 if(telnum.length() != 10) {
					 generator.getAlert("Contact Number Field Error", "10 characters are required");
					 return;
				 }
				 try {
					 @SuppressWarnings("unused")
					int tele = Integer.parseInt(telnum);
				 }catch(NumberFormatException nfe) {
					 generator.getAlert("Phone number contains other digits", "Please enter a valid phone number");
					 return;
				 }
				 if(eid == null|| eid.length() == 0) {
					 generator.getAlert("Employee ID not entered", "Please enter the employee ID");
					 return;
				 }
		 
		 String v = "UPDATE employee SET eid =? ,  fname = ? , lname = ? , bdate = ? , gender = ? , address = ? , nic = ? , telnum = ? , designation = ? , salary = ?  WHERE eid = '"+eid+"'";
		 
		 try {
			 
			 PreparedStatement pst = con.prepareStatement(v);
			 
			 pst.setString(1, eid);
			 pst.setString(2, fname);
			 pst.setString(3, lname);
			 pst.setDate(4, sqlDate);
			 pst.setString(5, gender);
			 pst.setString(6, address);
			 pst.setString(7, nic);
			 pst.setString(8, telnum);
			 pst.setString(9, designation);
			 pst.setDouble(10, e1.getSalary());
			 
			 pst.execute();
			 load();
		 }catch(Exception e2)
		 {
			 System.out.println(e2);
		 }
		 
		 }else {
			 generator.getAlert("Error", "Invalid character");
		 }
		 
	     load();
	     clearFields();
	}
	@FXML
	public void delete(ActionEvent event) {
		String r = "delete from employee where eid = ?";
		try {
		PreparedStatement pst = con.prepareStatement(r);
		pst.setString(1, empIDField.getText());
		pst.execute();
		
		
	}catch(SQLException ex) {
		System.out.println(ex);
	}
		load();
		clearFields();
	}
	@FXML
	public void tableMouseClicked(MouseEvent event) {
		 Employee e = tablefield.getSelectionModel().getSelectedItem();
		 String eid = e.getId();
		 String fname = e.getFirstname();
		 String lname = e.getLastname();
		 LocalDate sqlDate = (e.getBday()).toLocalDate();
		 String gender = e.getGender();
		 String address = e.getAddress();
		 String nic = e.getNic();
		 String telnum = e.getTelenum();
		 String designation = e.getDesignation();
		 Double salary = e.getSalary();
		 
		 empIDField.setText(eid);
		 fnameField.setText(fname);
		 lnameField.setText(lname);
		 addressField.setText(address);
		 NICField.setText(nic);
		 telephoneField.setText(telnum);
		 designationField.setText(designation);
		 salaryField.setText(salary.toString());
		 dobField.setValue(sqlDate);
		 genderfield.setValue(gender);
		 

		
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
	public void clearFields() {
		empIDField.clear();
		fnameField.clear();
		lnameField.clear();
		addressField.clear();
		NICField.clear();
		telephoneField.clear();
		designationField.clear();
		salaryField.clear();
		dobField.getEditor().clear();
		genderfield.setValue("Gender");
		genderfield.setItems(genderlist);
	}
	 @FXML
	    void homeScreen(ActionEvent event) throws Exception{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
			Parent view = (Parent) loader.load();
			homeController hc = loader.getController();
			hc.receieveDetails(UserType.getText(),userId.getText());
			Scene scene = new Scene(view);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj(); 
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();

	    }
	 
			
		
	 public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
	public void demo() {
		
		Employee e1 = new Employee();
		e1.setId("13");
		e1.setFirstname("Abeeth");
		e1.setLastname("Karunanayake");
		e1.setAddress("Delkanda");
		e1.setNic("971821100V");
		e1.setTelenum("0766780306");
		e1.setDesignation("Manager");
		e1.setSalary(80000);
		Date sqlDate = Date.valueOf(("1996-08-20"));
		e1.setBday(sqlDate);
		e1.setGender("Male");
		
		 empIDField.setText(e1.getId());
		 fnameField.setText(e1.getFirstname());
		 lnameField.setText(e1.getLastname());
		 addressField.setText(e1.getAddress());
		 NICField.setText(e1.getNic());
		 telephoneField.setText(e1.getTelenum());
		 designationField.setText(e1.getDesignation());
		 String salarystr = Double.toString(e1.getSalary());
		 salaryField.setText(salarystr);
		 LocalDate daate = (e1.getBday().toLocalDate());
		 dobField.setValue(daate);
		 genderfield.setValue(e1.getGender());
		
	
		
		
	}
	
			

			
			
			
			
		}
	 

