package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import model.Main;
import model.Transaction;
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



public class FinanceController{
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ObservableList<String> transactionType =FXCollections.observableArrayList("Sale","Contract","Salary","Purchase");
	ObservableList <Transaction> data = FXCollections.observableArrayList();
	@FXML
	private Button FinanceBtn;
	@FXML
	private Button reportBtn;
	@FXML
	private TextField ObjectIDField;
	
	@FXML 
	private TextField amountField;
	
	@FXML
	private Label UserType;
	
	@FXML
	private Label userId;
	@FXML
	private DatePicker transactionDate;
	
	@FXML
	private Button DemoBtn;
	@FXML
	private ComboBox<String> reportTransType;
	
	@FXML
	private ComboBox<String> transactionTypeBox;
	
	@FXML
	private Button submitBtn;
	
	@FXML 
	private Button cancelBtn;
	
	@FXML 
	private TextField SearchField;
	@FXML
	private Button homeButton;
	@FXML
	private Button closeButton;
	@FXML
	private Button logOutButton;
	@FXML
	private TableView<Transaction> TransactionTable;
	@FXML
	private TableColumn<Transaction,String> tIDCol;
	@FXML
	private TableColumn<Transaction,String> ObjIDCol;
	@FXML
	private TableColumn<Transaction,String> tTypeCol;
	@FXML
	private TableColumn<Transaction,Double> AmountCol;
	@FXML
	private TableColumn<Transaction,Date> DateCol;
	@FXML
	private Button refreshBtn;
	
	@FXML
	private Button updateBtn;

	@FXML
	private Label transIDLabel;
	@FXML
	private Label idLabel;

	@FXML
	private Button deleteBtn;
	@FXML
	private Button profitBtn;
	
	@FXML
	public void DeleteTransaction(ActionEvent event) {
		String transactID = transIDLabel.getText();
		
		
		String sql = "DELETE from transactions where tID = '"+transactID+"'";
		try {
		pst = con.prepareStatement(sql);
		pst.execute();
		}catch (Exception e) {
			System.out.println(e);
		}
		loadTable();
		clearFields();
	}

	
	
	@FXML
	private void initialize() {
		
		con = Dbconnect.connect();
		reportTransType.setValue("Select a transaction type");
		reportTransType.setItems(transactionType);
		transactionTypeBox.setValue("Select a transaction type");
		transactionTypeBox.setItems(transactionType);
		loadTable();
		
		
	
	}
	
	
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	@FXML
	public void updateTransaction(ActionEvent event) {
	Transaction t1 = new Transaction();
	try {
	t1.setAmount(Double.parseDouble(amountField.getText()));
	}catch(NumberFormatException nfe) {
		generator.getAlert("NFE Error", "Please enter a valid number");
		return;
	}
	try {
	@SuppressWarnings("unused")
	Date sqlDate = Date.valueOf(transactionDate.getValue());
	}catch(NullPointerException npe) {
		generator.getAlert("No date", "Please insert a valid date");
		return;
	} 
	boolean isMyComboBoxEmpty = transactionTypeBox.getSelectionModel().isEmpty();
	if(isMyComboBoxEmpty == true) {
		generator.getAlert("Combobox Empty", "Please select a valid transaction type");
		return;
	}
	String transactID = transIDLabel.getText();
	String ObjID = ObjectIDField.getText();
	String transType = transactionTypeBox.getValue();
	Date sqlDate = Date.valueOf(transactionDate.getValue());
	if(ObjID == null || ObjID.length() == 0) {
		generator.getAlert("objid empty", "Please enter an object ID");
		return;
	}

	String sql = "UPDATE transactions SET ID =? , tType = ? , price = ? , date = ?  WHERE tID ='"+transactID+"'";
	try {
	pst = con.prepareStatement(sql);
	pst.setString(1, ObjID);
	pst.setString(2, transType);
	pst.setDouble(3,t1.getAmount());
	pst.setDate(4, sqlDate);
	
	pst.execute();
	}catch(SQLException e) {
		System.out.println(e);
	}
	loadTable();
	clearFields();
	}
	
	public void clearFields() {
		ObjectIDField.clear();
		amountField.clear();
		transactionDate.getEditor().clear();
		transactionTypeBox.setValue("Select a transaction type");
		transactionTypeBox.setItems(transactionType);
		transIDLabel.setText("ID");
	}
	@FXML
	public void loadTable() {
		ObservableList<Transaction> TableDetails= FXCollections.observableArrayList();

		try {
		TransactionTable.setItems(null);

		con = Dbconnect.connect();
		String sql ="SELECT * from transactions";
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()) {
			TableDetails.add(new Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5)));	
		}
		
		
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		tIDCol.setCellValueFactory(new PropertyValueFactory<>("transactID"));
		ObjIDCol.setCellValueFactory(new PropertyValueFactory<>("ObjID"));
		tTypeCol.setCellValueFactory(new PropertyValueFactory<>("transType"));
		AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		DateCol.setCellValueFactory(new PropertyValueFactory<>("transactDate"));
		TransactionTable.setItems(TableDetails);
		
		FilteredList<Transaction> filteredData = new FilteredList<>(TableDetails, e -> true);
		SearchField.setOnKeyReleased(e ->{
			SearchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredData.setPredicate( transaction ->{
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowercasefilter = newValue.toLowerCase();
				if(transaction.getObjID().toLowerCase().contains(lowercasefilter)) {
					return true;
				}
				if(transaction.getTransType().toLowerCase().contains(lowercasefilter)) {
					return true;
				}
				try {
					int newInt = Integer.parseInt(newValue);
					if(transaction.getTransactID() == newInt) {
						return true;
					}
				}catch(NumberFormatException NFE) {
					System.out.println(NFE);
				}
				
					return false;
				});
			});
			SortedList<Transaction> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(TransactionTable.comparatorProperty());
			TransactionTable.setItems(sortedData);
		});
	}
	
	@FXML
	public void tablemouseClicked(MouseEvent event) {
	Transaction t = TransactionTable.getSelectionModel().getSelectedItem();
	int transID = t.getTransactID();
	String ObjectID = t.getObjID();
	Double amount = t.getAmount();
	LocalDate daate = (t.getTransactDate()).toLocalDate();
	String transType = t.getTransType();
	String transIDStr =Integer.toString(transID);
	transIDLabel.setText(transIDStr);
	ObjectIDField.setText(ObjectID);
	amountField.setText(amount.toString());
	transactionDate.setValue(daate);
	transactionTypeBox.setValue(transType);
	
	}
	
	@FXML
	public void submitTransaction(ActionEvent event) {
		Transaction t1 = new Transaction();
		String ObjIDstr = ObjectIDField.getText();
		boolean isComboBoxEmpty = transactionTypeBox.getSelectionModel().isEmpty();
		if(isComboBoxEmpty == true) {
			generator.getAlert("Transaction type empty", "Please choose a transaction type");
			return;
		}
		try{
			t1.setAmount(Double.parseDouble(amountField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("NFE","Please enter a valid amount");
			return;
		}
		try{
			@SuppressWarnings("unused")
			Date sqlDate = Date.valueOf(transactionDate.getValue());
		}catch(NullPointerException npe) {
			generator.getAlert("no date","Please enter a valid date");
			return;
		}		
		if(ObjIDstr == null || ObjIDstr.length() == 0) {
			generator.getAlert("objid empty", "Please enter an object id");
			return;
		}
		Date sqlDate = Date.valueOf(transactionDate.getValue());
		t1.setTransactDate(sqlDate);
		t1.setTransType(transactionTypeBox.getValue());
		t1.setObjID(ObjectIDField.getText());

		String q = "INSERT INTO transactions ( ID,tType,price,date) values (?,?,?,?)";
		
		try{
			
			pst = con.prepareStatement(q);
			pst.setString(1, t1.getObjID());
			pst.setString(2, t1.getTransType());
			pst.setDouble(3, t1.getAmount());
			pst.setDate(4, t1.getTransactDate());
			pst.execute();
			loadTable();
			
		}catch (Exception e){
			System.out.println(e);
		}
		clearFields();
	}
	
	@SuppressWarnings("static-access")
	@FXML
	public void genReport(){
		String transType = reportTransType.getValue();
		
		con = Dbconnect.connect();
		String reportPath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\FinReport.pdf";
		try {
			
			
		JasperDesign jd = JRXmlLoader.load("C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\FinanceReport.jrxml");
		JRDesignQuery query = new JRDesignQuery();
		query.setText("SELECT * FROM gebs.transactions WHERE tType = '"+transType+"' ");
		jd.setQuery(query);
		JasperReport jr = JasperCompileManager.compileReport(jd);
		
		JasperPrint jp = JasperFillManager.fillReport(jr, null,con);
		
		JasperExportManager.exportReportToPdfFile(jp,reportPath);
		JasperViewer jv = new JasperViewer(jp,false);
		jv.viewReport(jp,false);
		
		}catch(JRException jre) {
			System.out.println(jre);
		}
		generator.getAlert("Report generation success", "Report has been printed!");
	}
	 @FXML
	public void homeScreen(ActionEvent event) throws Exception{
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
	@FXML
	public void ProfitScreen(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfitView.fxml"));
		Parent view = (Parent) loader.load();
		ProfitController pc = loader.getController();
		pc.setUser(UserType.getText(),userId.getText());		
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj(); 
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
		
	}
	
	public void demo() {
		Connection conn = Dbconnect.connect();
		Transaction t1 = new Transaction();
		t1.setObjID("E12");
		t1.setTransType("Salary");
		
		Date sqlDate = Date.valueOf(("2019-06-20"));
		t1.setTransactDate(sqlDate);
		t1.setAmount(30000.00);
		ObjectIDField.setText(t1.getObjID());
		amountField.setText((Double.toString(t1.getAmount())));
		LocalDate daate = (t1.getTransactDate()).toLocalDate();
		transactionDate.setValue(daate);
		transactionTypeBox.setValue(t1.getTransType());
		transIDLabel.setText("19");

		
		
	}
	public void setUser (String user, String ID) {
		
		UserType.setText(user);
		userId.setText(ID);
		}
}
