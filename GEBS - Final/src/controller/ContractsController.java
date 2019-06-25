package controller;

import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.Dbconnect;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Contracts;
import model.Main;
import util.generator;



public class ContractsController {
	
	  @FXML
	    private Button homeButton;
	  @FXML
	  private Label UserType;
	  
	  @FXML
	  private Label userId;
	    @FXML
	    private Button closeButton;

	    @FXML
	    private Button logOutButton;

	    @FXML
	    private TextField CustomerNameField;

	    @FXML
	    private TextField ContractTypeField;

	    @FXML
	    private TextField CostField;

	    @FXML
	    private TextField DescriptionField;

	    @FXML
	    private Button saveBtn;

	    @FXML
	    private Button updateBtn;

	    @FXML
	    private Button deleteBtn;

	    @FXML
	    private TableView<Contracts> contractTable;

	    @FXML
	    private TableColumn<Contracts, Integer> idColumn;

	    @FXML
	    private TableColumn<Contracts, String> customerNameColumn;

	    @FXML
	    private TableColumn<Contracts, String> typeColumn;

	    @FXML
	    private TableColumn<Contracts, Date> acceptDateColumn;

	    @FXML
	    private TableColumn<Contracts, Date> startDateColumn;

	    @FXML
	    private TableColumn<Contracts, Date> endDateColumn;

	    @FXML
	    private TableColumn<Contracts, Double> costColumn;

	    @FXML
	    private TableColumn<Contracts, String> descriptionColumn;

	    @FXML
	    private Label contractNumLabel;

	    @FXML
	    private DatePicker AcceptedField;

	    @FXML
	    private DatePicker StartField;

	    @FXML
	    private DatePicker EndField;
	    
	    Connection con = null;
	    ResultSet res =  null;
	    PreparedStatement pst = null;

	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
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
	@FXML
	public void saveContract( ActionEvent event)throws Exception{
		Contracts cu1 =  new Contracts();
//		cu1.setContractNum(Integer.parseInt(contractNumLabel.getText()));
		cu1.setCustomerName(CustomerNameField.getText());
		cu1.setContractType(ContractTypeField.getText());
		try {
			Date acceptDate = Date.valueOf(AcceptedField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("acceptDate empty", "Please enter a value for accepted date");
			return;
		}
		Date acceptDate = Date.valueOf(AcceptedField.getValue());

		cu1.setAccept(acceptDate);
		try {
			Date startDate = Date.valueOf(StartField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("startDate empty", "Please enter a value for start date");
			return;
		}
		Date startDate = Date.valueOf(StartField.getValue());
		cu1.setStart(startDate);
		try {
			Date endDate = Date.valueOf(EndField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("endDate empty", "Please enter a value for end date");
			return;
		}
		Date endDate = Date.valueOf(EndField.getValue());
		cu1.setEnd(endDate);
		try {
			cu1.setCost(Double.parseDouble(CostField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("invalid cost", "Please enter a valid cost");
			return;
		}
		cu1.setCost(Double.parseDouble(CostField.getText()));
		cu1.setDescription(DescriptionField.getText());
		
		String insertquery = "INSERT INTO contract (CustomerName,ContractType,AcceptedDate,StartDate,EndDate,Cost,Description) values (?,?,?,?,?,?,?)";
	
		try{
			pst = con.prepareStatement(insertquery);
			pst.setString(1, cu1.getCustomerName());
			pst.setString(2, cu1.getContractType());
			pst.setDate(3, cu1.getAccept());
			pst.setDate(4, cu1.getStart());
			pst.setDate(5, cu1.getEnd());
			pst.setDouble(6, cu1.getCost());
			pst.setString(7, cu1.getDescription());
			pst.execute();		
			
		}catch(Exception e ) {
		System.out.println(e);
	}
		clearFields();
		loadTable();
	}
	
	public void clearFields(){
		
	
		CustomerNameField.clear();
		ContractTypeField.clear();
		AcceptedField.getEditor().clear();
		StartField.getEditor().clear();
		EndField.getEditor().clear();
		CostField.clear();
		DescriptionField.clear();
		
	}
	@FXML
	public void loadTable(){
		ObservableList<Contracts> contractDetails = FXCollections.observableArrayList();
		try {
			
			contractTable.setItems(null);
			con = Dbconnect.connect();
			String loadTableQuery = "SELECT * from contract";
			PreparedStatement pst = con.prepareStatement(loadTableQuery);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				contractDetails.add(new Contracts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getDate(6),rs.getDouble(7),rs.getString(8)));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		idColumn.setCellValueFactory(new PropertyValueFactory<>("contractNum"));
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("contractType"));
		acceptDateColumn.setCellValueFactory(new PropertyValueFactory<>("accept")); 
		startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
		endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
		costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		contractTable.setItems(contractDetails);
		clearFields();
		
	}
	@FXML
	public void tableMouseClicked(MouseEvent event) {
		 Contracts c1 = contractTable.getSelectionModel().getSelectedItem();
		 
		 int contractId = c1.getContractNum();
		 String customerName = c1.getCustomerName();
		 String contractType = c1.getContractType();
		 LocalDate acceptDate = (c1.getAccept()).toLocalDate();
		 LocalDate startDate = (c1.getStart()).toLocalDate();
		 LocalDate endDate = (c1.getEnd()).toLocalDate();
		 Double cost = c1.getCost();
		 String description = c1.getDescription();
		 
		 String contractsIdstr = Integer.toString(contractId);
		 String costStr = Double.toString(cost);
		 
		 
		 contractNumLabel.setText(contractsIdstr);
		 CustomerNameField.setText(customerName);
		 ContractTypeField.setText(contractType);
		 AcceptedField.setValue(acceptDate);
		 StartField.setValue(startDate);
		 EndField.setValue(endDate);
		 CostField.setText(costStr);
		 DescriptionField.setText(description);
		 
		
	}
	@FXML
	public void updateContract(ActionEvent event)throws Exception{
		int id = Integer.parseInt(contractNumLabel.getText());
		String cuName = CustomerNameField.getText();
		String conType = ContractTypeField.getText();
		try {
			Date acceptDate = Date.valueOf(AcceptedField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("acceptDate empty", "Please enter a value for accepted date");
			return;
		}
		Date acceptDate = Date.valueOf(AcceptedField.getValue());
		try {
			Date startDate = Date.valueOf(StartField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("starttDate empty", "Please enter a value for start date");
			return;
		}
		Date startDate = Date.valueOf(StartField.getValue());
		try {
			Date endDate = Date.valueOf(EndField.getValue());

		}catch(NullPointerException npe) {
			generator.getAlert("endDate empty", "Please enter a value for end date");
			return;
		}
		Date endDate = Date.valueOf(EndField.getValue());
		try {
			Double cost = Double.parseDouble(CostField.getText());
		}catch(NumberFormatException nfe) {
			generator.getAlert("costfield empty", "Please enter a valid cost");
			return;
		}
		Double cost = Double.parseDouble(CostField.getText());
		String des = DescriptionField.getText();
		
		String updateQuery  = "UPDATE contract SET CustomerName = ?,ContractType =?,AcceptedDate =?,StartDate =?,EndDate=?,COst=?,Description=? where contract_no='"+id+"'";
		try {
			pst = con.prepareStatement(updateQuery);
			pst.setString(1, cuName);
			pst.setString(2, conType);
			pst.setDate(3, acceptDate);
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.setDouble(6, cost);
			pst.setString(7, des);
			pst.execute();
		}catch (Exception e) {
			System.out.println("e");
		}
			
			loadTable();
			initialize();
	}

	
	
	@FXML
	public void deleteContract(ActionEvent event) throws Exception{
		String id  =  contractNumLabel.getText();
		
		String deleteQuery = "DELETE from contract where  CustomerID = '"+id+"'";
		try {
			pst = con.prepareStatement(deleteQuery);
			pst.execute();
		}catch(Exception e) {
			System.out.println(e);
		}
		loadTable();
		clearFields();
	}
	
	@FXML
	public void initialize() {
		Connection connect =  Dbconnect.connect();
		loadTable();
	}
public void setUser (String user, String ID) {
		
		UserType.setText(user);
		userId.setText(ID);
		}
	
	
	
}
