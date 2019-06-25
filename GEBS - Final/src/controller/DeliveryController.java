package controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Delivery;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Main;
import util.generator;
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

public class DeliveryController {

	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	@FXML
	private Label UserType;
	@FXML
	private Label userId;

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
    private Button homeButton;

    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button updatebutton;

    @FXML
    private Button VehicleButton;
    
    @FXML
    private Button deliveryreportButton;

	@FXML
	private Label didLabel;

	
	@FXML
	private TextField desField;
	
	@FXML
	private TextField addressField;
	
	@FXML
	private TextField itemField;
	
	@FXML
    private TextField deliverydateField;
	@FXML
    private TableView<Delivery> tablefield;
	
	@FXML
	private TableColumn<Delivery, String> ItemCode;

	@FXML
	private TableColumn<Delivery, String> Description;

	@FXML
	private TableColumn<Delivery, String> Address;
	
	@FXML
	private TableColumn<Delivery, Integer> DeliveryNumber;
	
	 @FXML
	    private TableColumn<Delivery, String> DeliveryDate;
	 
	 @FXML
	    private Button closeButton;

	 @FXML
	    private Button logOutButton;
	 @FXML
	    private Button searchbutton;

	 @FXML
	    private TextField searchField;
	 @FXML
	    private TextField reportItemcode;
	 
	PreparedStatement pst =  null;
	Connection con =  null;

 @FXML
  public void addDelivery(ActionEvent event)throws Exception {
	 	con = Dbconnect.connect();
 		Delivery d1 = new Delivery();
    	d1.setItemcode(itemField.getText());
    	d1.setDes(desField.getText());
    	d1.setAddress(addressField.getText());
    	d1.setDeliverydate(deliverydateField.getText());

	 	String q = "INSERT INTO deliveries (icode, des, address, date) values (?,?,?,?)";
	 	validate();
	 	
 try {	
	 pst = con.prepareStatement(q);
	 pst.setString(1,d1.getItemcode());
	 pst.setString(2,d1.getDes());
	 pst.setString(3,d1.getAddress());
	 pst.setString(4,d1.getDeliverydate());

	 pst.execute();
	 
  }catch(Exception e) {
	  System.out.println(e);
  }
 	clearFields();
 	//load();
 }
 
 
 public boolean validate() {
	 
	 Pattern p = Pattern.compile("[a-zA-Z]+");
	 Matcher m = p.matcher(desField.getText());
	 
	 if(!m.matches()) {
		generator.getAlert("ERROR", "Invalid characters"); 
	 }
	 if(itemField.getText().isEmpty()) {
		 generator.getAlert("ERROR","Item Code is Empty");
		 return false;
	 }

	 if(desField.getText().isEmpty()) {
		 generator.getAlert("ERROR","Description Field is Empty");
		 return false;
	 }
	 if(addressField.getText().isEmpty()) {
		 generator.getAlert("ERROR","Address Field is Empty");
		 return false;
	 }
	 
	 return true;
	 
 }
 @FXML
 public void load() {
 	ObservableList<Delivery> deliverydetails = FXCollections.observableArrayList();
 	try {
 		tablefield.setItems(null);
 		con = Dbconnect.connect();
  		
 		String sql= "SELECT * from deliveries";
 		
 		PreparedStatement pst = con.prepareStatement(sql);
 		ResultSet rs = pst.executeQuery();
 		while(rs.next()) {
 			deliverydetails.add(new Delivery(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(2),rs.getString(5)));
 		}
 	}catch(SQLException e) {
 		System.out.println(e);
 	}
 	DeliveryNumber.setCellValueFactory(new PropertyValueFactory<>("did"));
 	ItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
 	Description.setCellValueFactory(new PropertyValueFactory<>("des"));
 	Address.setCellValueFactory(new PropertyValueFactory<>("address"));
 	DeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliverydate"));
 	tablefield.setItems(deliverydetails);
 	
 	FilteredList<Delivery> filteredData = new FilteredList<>(deliverydetails, e -> true);
	searchField.setOnKeyReleased(e ->{
		searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredData.setPredicate( delivery ->{
			if(newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			String lowercasefilter = newValue.toLowerCase();
			if(delivery.getItemcode().toLowerCase().contains(lowercasefilter)) {
				return true;
			}
			if(delivery.getDes().toLowerCase().contains(lowercasefilter)) {
				return true;
			}
			try {
				int newInt = Integer.parseInt(newValue);
				if(delivery.getDid() == newInt) {
					return true;
				}
			}catch(NumberFormatException NFE) {
				System.out.println(NFE);
			}
			
				return false;
			});
		});
		SortedList<Delivery> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablefield.comparatorProperty());
		tablefield.setItems(sortedData);
	});

}
	@FXML
 	public void initialize() {
 		Connection con = Dbconnect.connect();
 			load();
 	}
	@FXML
	public void deliveryReport() {
		String itemcode = reportItemcode.getText();
		
		con = Dbconnect.connect();
		String reportPath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\Generated\\DeliveryReport.pdf";
		
		try {
			JasperDesign jd = JRXmlLoader.load("C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\DeliveryReport.jrxml");
			JRDesignQuery query = new JRDesignQuery();
			query.setText("SELECT * FROM gebs.deliveries WHERE icode='"+itemcode+"' ");
			jd.setQuery(query);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			JasperPrint jp = JasperFillManager.fillReport(jr, null,con);
			
			JasperExportManager.exportReportToPdfFile(jp,reportPath);
			generator.getAlert("Delivery report generation success!", "Delivery report has been printed!");
			JasperViewer jv = new JasperViewer(jp,false);
			jv.viewReport(jp,false);
		}catch(JRException jre) {
			System.out.println(jre);
		}
			
			
			
		
		
}	
 	@FXML
	public void deleteDelivery(ActionEvent event) {
	String ItemNo = itemField.getText();
	
	String sql = "Delete from deliveries where icode = '"+ItemNo+"' ";
	
	try {
		pst = con.prepareStatement(sql);
		pst.execute();
		
	}catch(Exception e) {
		System.out.println(e);
	}
	load();
}
	public void clearFields() {
		
	}
	 @FXML
	 public void tableclick(MouseEvent event) {
		 Delivery d = tablefield.getSelectionModel().getSelectedItem();
		 int did=d.getDid();
		 String didStr = Integer.toString(did);
		 String icode=d.getItemcode();
		 String des=d.getDes();
		 String address=d.getAddress();
		 String date=d.getDeliverydate();

		didLabel.setText(didStr);
		 itemField.setText(icode);
		 desField.setText(des);
		 addressField.setText(address);
		 deliverydateField.setText(date);		
	    }
	 @FXML
	 public void updateDelivery(ActionEvent event) {
		 Delivery d1 = new Delivery();
		 
		 int did = Integer.parseInt(didLabel.getText());
		 String icode= itemField.getText();
		 String des=desField.getText();
		 String address=addressField.getText();
		 String date=deliverydateField.getText();

		 
		 String v = "UPDATE deliveries SET  icode = ? ,des = ? , address = ? , date = ? WHERE did = ? ";
		 
		 try {
			 PreparedStatement pst = con.prepareStatement(v);
			 
			 pst.setString(1, icode);
			 pst.setString(2, des);
			 pst.setString(3, address);
			 pst.setString(4, date);
			 pst.setInt(5, did);

			 pst.execute();
			 load();
		 }catch(Exception e2)
		 {
			 System.out.println(e2);
		 }
		 
		 //load();
		 clearFields();
	 }
	 public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
	
}
