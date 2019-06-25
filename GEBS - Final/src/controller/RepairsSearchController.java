package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;
import model.Repair;
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
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
import util.generator;

public class RepairsSearchController {
		@FXML
		private Label UserType;
		@FXML
		private Label userId;
	 	@FXML
	    private Button homeButton;

	    @FXML
	    private Button stockButton;

	    @FXML
	    private Button repairsButton;

	    @FXML
	    private Button employeeButton;

	    @FXML
	    private Button deliveryButton;

	    @FXML
	    private Button customerOrderButton;

	    @FXML
	    private Button contractsButton;

	    @FXML
	    private Button financeButton;

	    @FXML
	    private Button suppliersButton;

	    @FXML
	    private Button repSearchInsert;

	    @FXML
	    private Button RepSearchView;

	    @FXML
	    private TextField searchRep;

	    @FXML
	    private TextField reportID;
	    
	    @FXML
	    private Button RepSearchButton;

	    @FXML
	    private Button RepSearchReport;
	    
	    @FXML
	    private TableView<Repair> repSearchTable;
	    
	    @FXML
	    private TableColumn<Repair, Integer> searchTid;

	    @FXML
	    private TableColumn<Repair, String> searchTType;

	    @FXML
	    private TableColumn<Repair, String> searchStatus;

	    @FXML
	    private TableColumn<Repair, Date> searchTWarDate;

	    @FXML
	    private TableColumn<Repair, String> searchTDamType;

	    @FXML
	    private TableColumn<Repair, Date> searchTRepDate;

	    @FXML
	    private TableColumn<Repair, Date> searchTnextDate;

	    @FXML
	    private TableColumn<Repair, Double> searchTCost;

	    @FXML
	    private Button closeButton;

	    @FXML
	    private Button logOutButton;
    
    Connection con = null;
    PreparedStatement pst = null;
    
    public void RepViewScreen ( ActionEvent event ) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RepairsView.fxml"));
		Parent view = (Parent) loader.load();
		RepairsViewController rvc = loader.getController();
		rvc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    	Stage window = Main.getStageObj();
    	
    	window.setScene(scene);
    	window.show();
    	window.centerOnScreen();
    }
    
    public void RepInsScreen ( ActionEvent event ) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Repairs.fxml"));
		Parent view = (Parent) loader.load();
		RepairsController rc = loader.getController();
		rc.setUser(UserType.getText(),userId.getText());
    	Scene scene = new Scene(view);
    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    	Stage window = Main.getStageObj();
    	
    	window.setScene(scene);
    	window.show();
    	window.centerOnScreen();
    }
	    
	    // common buttons
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
		
		public void Logout ( ActionEvent event ) throws Exception{
			Parent view = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(view);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj();
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();
		}

	
		
		public void RepairScreen( ActionEvent event ) throws Exception{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Repairs.fxml"));
			Parent view = (Parent) loader.load();
			RepairsController rc = loader.getController();
			rc.setUser(UserType.getText(),userId.getText());
			Scene scene = new Scene(view);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj();
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();
		}
		
	
		
		
		
	
			
		@FXML
		void RepSearchClick ( ActionEvent event ) {
			
	
		}
		
		@FXML
	    public void RepSearchById() {
			
			ObservableList<Repair> RepairsSearch = FXCollections.observableArrayList();
			
				try {
					repSearchTable.setItems(null);
					con = Dbconnect.connect();
					String query = "SELECT * FROM repairsgebs";
					pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						RepairsSearch.add(new Repair(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getDouble(8)));
					}
					pst.close();

			}catch (Exception e) {
				System.out.println(e);
			}

			searchTid.setCellValueFactory(new PropertyValueFactory<>("itemID"));
			searchTType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
			searchStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
			searchTWarDate.setCellValueFactory(new PropertyValueFactory<>("warDate"));
			searchTDamType.setCellValueFactory(new PropertyValueFactory<>("damType"));
			searchTRepDate.setCellValueFactory(new PropertyValueFactory<>("repDate"));
			searchTnextDate.setCellValueFactory(new PropertyValueFactory<>("repNext"));
			searchTCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
			repSearchTable.setItems(RepairsSearch);
			
			FilteredList<Repair> filteredData = new FilteredList<>(RepairsSearch, e -> true);
			searchRep.setOnKeyReleased(e ->{
				searchRep.textProperty().addListener((observableValue, oldValue, newValue) -> {
					filteredData.setPredicate( repair -> {
						if(newValue == null || newValue.isEmpty()) {
							return true;
						}
					String lowercaseFilter = newValue.toLowerCase();
					try {
							int newInt = Integer.parseInt(newValue);
							if(repair.getitemID() == newInt){
								return true;
							}
					}catch(NumberFormatException NFE) {
						System.out.println(NFE);
					}
					
					return false;
						
				});
			});
				SortedList<Repair> sortedData = new SortedList<>(filteredData);
				sortedData.comparatorProperty().bind(repSearchTable.comparatorProperty());
				repSearchTable.setItems(sortedData);
			});
		}
	    
		@FXML
		private void initialize(){
			Dbconnect con = new Dbconnect();
			RepSearchById();
		}
		
		@FXML
		 public void repairsReport(ActionEvent event)throws Exception{
				String id = reportID.getText();
				con = Dbconnect.connect();
				String reportPath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\Generated\\RepairsReport.pdf";
				try {
					
				JasperDesign jd = JRXmlLoader.load("C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\repairs.jrxml");
				JRDesignQuery query = new JRDesignQuery();
				query.setText("SELECT * FROM repairsgebs WHERE ItemID = '"+ id +"' ");
				jd.setQuery(query);
				JasperReport jr = JasperCompileManager.compileReport(jd);
				
				JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
				
				JasperExportManager.exportReportToPdfFile(jp, reportPath);
				generator.getAlert("Report generation success", "Report has been printed!");
				JasperViewer.viewReport(jp);
				}catch(JRException jre) {
					System.out.println(jre);
				}
				reportID.clear();
		 }
		public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
		
}
