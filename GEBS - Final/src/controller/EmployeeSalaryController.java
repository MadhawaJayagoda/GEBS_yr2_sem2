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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import model.Main;
import model.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.generator;


public class EmployeeSalaryController {
		
			Connection con = null;
	
	ObservableList<String> nopaylist = FXCollections.observableArrayList("Yes" , "No");
	
	ObservableList<String> ottlist = FXCollections.observableArrayList("Yes" , "No");
	@FXML
	public Label userId;
	@FXML
	public Label UserType;
	@FXML
	private Button calbtn;
	@FXML
	private TextField fnamefield;
	@FXML
	private TextField lnamefield;
	@FXML
	private TextField salaryfield;
	@FXML
	private TextField othoursfield;
	@FXML
	private TextField idfield;
	@FXML
	private ComboBox<String> nopayfield;
	@FXML
	private ComboBox<String> otfield;
	@FXML
	private Button refreshbtn;
	@FXML
	private Text finalsaldisplay;
	@FXML
	private Button submitbtn;
	@FXML
	private Button logOutButton;
	@FXML
	private Button reprtBtn;
	
	@FXML
	private TableView<Employee> tablefield;
	@FXML
	private TableColumn<Employee,String> idField;
	@FXML
	private TableColumn<Employee,String> Fnamefield;
	@FXML
	private TableColumn<Employee,String> Lnamefield;
	@FXML
	private TableColumn<Employee,String> basicsalaryfield;
	@FXML
	private TableColumn<Employee,String> fsalarycol;
	
	public int privilegelevel = 0;
	
	public User userObj = new User();
 
	
	
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
		Parent view = FXMLLoader.load(getClass().getResource("/view/EmployeeSalary.fxml"));
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
		load();
		nopayfield.setValue("Select");
		nopayfield.setItems(nopaylist);
		
	
		otfield.setValue("Select");
		otfield.setItems(ottlist);

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
public void tablemouseclick(MouseEvent event) {
	Employee e1= tablefield.getSelectionModel().getSelectedItem();
	 String eid = e1.getId();
	 String fname = e1.getFirstname();
	 String lname = e1.getLastname();
	 Double salary = e1.getSalary();
	
	 
	 
	 idfield.setText(eid);
	 fnamefield.setText(fname);
	 lnamefield.setText(lname);
	 salaryfield.setText(salary.toString());
	

}

@FXML
public void load() {
	ObservableList<Employee> salarydetails = FXCollections.observableArrayList();
	try {
		 
		tablefield.setItems(null);
		con = Dbconnect.connect();
		String sql = "SELECT eid,fname,lname,salary,finalsalary from employee";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			salarydetails.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5)));
		}
	}catch(SQLException e) {
		System.out.println(e);
	}

	idField.setCellValueFactory(new PropertyValueFactory<>("id"));
	Fnamefield.setCellValueFactory(new PropertyValueFactory<>("firstname"));
	Lnamefield.setCellValueFactory(new PropertyValueFactory<>("lastname"));
	basicsalaryfield.setCellValueFactory(new PropertyValueFactory<>("salary"));
	fsalarycol.setCellValueFactory(new PropertyValueFactory<>("finalsalary"));
	
	tablefield.setItems(salarydetails);
	clearFields();
	
}
public void calculate() {
	boolean isMyComboBoxEmpty = nopayfield.getSelectionModel().isEmpty();
	if(isMyComboBoxEmpty == true) {
		generator.getAlert("Combobox Empty", "Please select a no-pay option");
		return;
	}
	boolean isComboBoxEmpty = otfield.getSelectionModel().isEmpty();
	if(isComboBoxEmpty == true) {
		generator.getAlert("Combobox Empty", "Please select OT option");
		return;
	}
	if(othoursfield.getText().isEmpty()) {
		generator.getAlert("Error", "OT hours field empty");
		return;
	}
	
	String nopayval = nopayfield.getValue();
	if(nopayval == "Yes") {
		double finalsal = 0;
		finalsaldisplay.setText(Double.toString(finalsal));
		
	}else {
		
	
	double bpay = Double.parseDouble(salaryfield.getText());
	int othours = Integer.parseInt(othoursfield.getText());
	double otpay = othours * 500;
	double finalsal = otpay + bpay;
	finalsaldisplay.setText(Double.toString(finalsal));
	}
}
public void submit() {
	boolean isMyComboBoxEmpty = nopayfield.getSelectionModel().isEmpty();
	if(isMyComboBoxEmpty == true) {
		generator.getAlert("Combobox Empty", "Please select an option");
		return;
	}
	boolean isComboBoxEmpty = otfield.getSelectionModel().isEmpty();
	if(isComboBoxEmpty == true) {
		generator.getAlert("Combobox Empty", "Please select OT option");
		return;
	}
	String eid = idfield.getText();
	double finalsal = Double.parseDouble(finalsaldisplay.getText());
	 String v = "UPDATE employee SET finalsalary = ?  WHERE eid = '"+eid+"'";
try {
	PreparedStatement ps = con.prepareStatement(v);
	ps.setDouble(1, finalsal);
	ps.execute();

}catch(Exception e) {
	System.out.println(e);
}
clearFields();
load();
	
}
public void clearFields() {
	idfield.clear();
	fnamefield.clear();
	lnamefield.clear();
	salaryfield.clear();
	othoursfield.clear();
	finalsaldisplay.setText("0.00");
	nopayfield.setValue("Select");
	nopayfield.setItems(nopaylist);
	otfield.setValue("Select");
	otfield.setItems(ottlist);
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
public void setUser (String user, String ID) {
	
	UserType.setText(user);
	userId.setText(ID);
	}
public void generateReport() {
	con = Dbconnect.connect();
	String reportPath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\Generated\\empReport.pdf";
	String compilePath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\EmployeeReport.jrxml";
	try {
	
	JasperReport jr = JasperCompileManager.compileReport(compilePath);
	
	JasperPrint jp = JasperFillManager.fillReport(jr, null,con);
	
	JasperExportManager.exportReportToPdfFile(jp,reportPath);
	JasperViewer jv = new JasperViewer(jp,false);
	jv.viewReport(jp,false);
	}catch(JRException jre) {
		System.out.println(jre);
	}
	generator.getAlert("Successful generation", "Report was successfully generated!");
}

}



