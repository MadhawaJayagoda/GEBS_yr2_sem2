package controller;



import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Main;
import model.Repair;
import util.generator;

public class RepairsViewController {
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
	    private Button repViewSearch;

	    @FXML
	    private Button RepViewInsert;

	    @FXML
	    private TableView<Repair> repairsTable;

	    @FXML
	    private TableColumn<Repair, Integer> repIDCol;

	    @FXML
	    private TableColumn<Repair, String> repTypeCol;

	    @FXML
	    private TableColumn<Repair, String> repTStatus;

	    @FXML
	    private TableColumn<Repair, Date> repTWarD;

	    @FXML
	    private TableColumn<Repair, String> repTDamT;

	    @FXML
	    private TableColumn<Repair, Date> repTCurrentD;

	    @FXML
	    private TableColumn<Repair, Date> repTNextD;

	    @FXML
	    private TableColumn<Repair, Double> repTCost;

	    @FXML
	    private Button logOutButton;

	    @FXML
	    private Button closeButton;
	    
	    @FXML
	    private TextArea updateID;

	    @FXML
	    private TextArea updateType;

	    @FXML
	    private TextArea updateStat;

	    @FXML
	    private TextArea updateDam;

	    @FXML
	    private TextArea updateCost;

	    @FXML
	    private DatePicker updateNext;

	    @FXML
	    private DatePicker updateCurrent;

	    @FXML
	    private DatePicker updateWar;

	    @FXML
	    private Button repUpdateButton;

	    @FXML
		private Label UserType;
		@FXML
		private Label userId;
		
	    Connection con = null;
	    PreparedStatement pst = null;
	    
	    public void clear() {
	    	updateID.clear();
	    	updateType.clear();
	    	updateStat.clear();
	    	updateWar.getEditor().clear();
	    	updateDam.clear();
	    	updateCurrent.getEditor().clear();
	    	updateNext.getEditor().clear();
	    	updateCost.clear();
	    }
	    
	  //validations
		
	  		public boolean validation() {
	  			Pattern p1 = Pattern.compile("[0-9]+");
	  			Matcher m1 = p1.matcher(updateID.getText());
	  			if(updateID.getText().isEmpty()) {
	  				generator.getAlert("Error", "Item ID is empty!");
	  				return false;
	  			}
	  			if(!m1.matches()) {
	  				generator.getAlert("Item ID field ", "Invalid Characters!");
	  				return false;
	  			}
	  			
	  			Pattern p2 = Pattern.compile("[a-zA-Z]+");
	  			Matcher m2 = p2.matcher(updateType.getText());
	  			if(updateType.getText().isEmpty()) {
	  				generator.getAlert("Error", "Item Type is empty!");
	  				return false;
	  			}
	  			if(!m2.matches()) {
	  				generator.getAlert("Item Type field ", "Invalid Characters!");
	  				return false;
	  			}
	  			
	  			Pattern p3 = Pattern.compile("[a-zA-Z]+");
	  			Matcher m3 = p3.matcher(updateStat.getText());
	  			if(updateStat.getText().isEmpty()) {
	  				generator.getAlert("Error", "Status is empty!");
	  				return false;
	  			}
	  			if(!m3.matches()) {
	  				generator.getAlert("Status field ", "Invalid Characters!");
	  				return false;
	  			}
	  			
	  			Pattern p4 = Pattern.compile("[0-9]+");
	  			Matcher m4 = p4.matcher(updateCost.getText());
	  			if(updateCost.getText().isEmpty()) {
	  				generator.getAlert("Error", "Cost is empty!");
	  				return false;
	  			}
	  			if(!m4.matches()) {
	  				generator.getAlert("Cost field ", "Invalid Characters!");
	  				return false;
	  			}
	  			
	  			Pattern p5 = Pattern.compile("[a-zA-Z]+");
	  			Matcher m5 = p5.matcher(updateDam.getText());
	  			
	  			if(!m5.matches()) {
	  				generator.getAlert("Damage Type field ", "Invalid Characters!");
	  				return false;
	  			}
	  			
	  			final String validate1 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
				
				Pattern p6 = Pattern.compile(validate1);
				Matcher m6 = p6.matcher((CharSequence) updateWar.getConverter());
				
//				if(m6.matches()) {
//					m6.reset();
//				}
				if(m6.find()) {
					String day1 = m6.group(1);
					String mon1 = m6.group(2);
					int yr1 = Integer.parseInt(m6.group(3));
					
					if(day1.equals("31") && (mon1.equals("4") || mon1.equals("6") || mon1.equals("9") || mon1.equals("11") || mon1.equals("04") || mon1.equals("06") || mon1.equals("09"))) {
						generator.getAlert("Warranty Date field", "Invalid Date!");
						return false;
					}
					else if(mon1.equals("2") || mon1.equals("02")) {
						if(yr1 % 4 == 0) {
							if(day1.equals("30") || day1.equals("31")) {
								generator.getAlert("Warranty Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
						else {
							if(day1.equals("29") || day1.equals("30") || day1.equals("31")) {
								generator.getAlert("Warranty Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
					}
					else {
						return true;
					}
				}
				
				final String validate2 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
				
				Pattern p7 = Pattern.compile(validate2);
				Matcher m7 = p7.matcher((CharSequence) updateCurrent.getConverter());
				
//				if(m6.matches()) {
//					m6.reset();
//				}
				if(m7.find()) {
					String day2 = m7.group(1);
					String mon2 = m7.group(2);
					int yr2 = Integer.parseInt(m7.group(3));
					
					if(day2.equals("31") && (mon2.equals("4") || mon2.equals("6") || mon2.equals("9") || mon2.equals("11") || mon2.equals("04") || mon2.equals("06") || mon2.equals("09"))) {
						generator.getAlert("Repair Date field", "Invalid Date!");
						return false;
					}
					else if(mon2.equals("2") || mon2.equals("02")) {
						if(yr2 % 4 == 0) {
							if(day2.equals("30") || day2.equals("31")) {
								generator.getAlert("Repair Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
						else {
							if(day2.equals("29") || day2.equals("30") || day2.equals("31")) {
								generator.getAlert("Repair Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
					}
					else {
						return true;
					}
				}
				
				final String validate3 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
				
				Pattern p8 = Pattern.compile(validate3);
				Matcher m8 = p8.matcher((CharSequence) updateNext.getConverter());
				
//				if(m6.matches()) {
//					m6.reset();
//				}
				if(m8.find()) {
					String day3 = m8.group(1);
					String mon3 = m8.group(2);
					int yr3 = Integer.parseInt(m8.group(3));
					
					if(day3.equals("31") && (mon3.equals("4") || mon3.equals("6") || mon3.equals("9") || mon3.equals("11") || mon3.equals("04") || mon3.equals("06") || mon3.equals("09"))) {
						generator.getAlert("Next Date field", "Invalid Date!");
						return false;
					}
					else if(mon3.equals("2") || mon3.equals("02")) {
						if(yr3 % 4 == 0) {
							if(day3.equals("30") || day3.equals("31")) {
								generator.getAlert("Next Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
						else {
							if(day3.equals("29") || day3.equals("30") || day3.equals("31")) {
								generator.getAlert("Next Date field", "Invalid Date!");
								return false;
							}
							else {
								return true;
							}
						}
					}
					else {
						return true;
					}
				}
	  			
	  			return true;
	  		}
	    
	  	//operations
	    public void ShowRepViews(){
			ObservableList<Repair> RepairsView = FXCollections.observableArrayList();
			
			try {
				repairsTable.setItems(null);
				con = Dbconnect.connect();
				String query = "SELECT * FROM repairsgebs";
				pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					
					RepairsView.add(new Repair(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getDouble(8)));
				}

			}catch (Exception e) {
				System.out.println(e);
			}

			repIDCol.setCellValueFactory(new PropertyValueFactory<>("itemID"));
			repTypeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
			repTStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
			repTWarD.setCellValueFactory(new PropertyValueFactory<>("warDate"));
			repTDamT.setCellValueFactory(new PropertyValueFactory<>("damType"));
			repTCurrentD.setCellValueFactory(new PropertyValueFactory<>("repDate"));
			repTNextD.setCellValueFactory(new PropertyValueFactory<>("repNext"));
			repTCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
			repairsTable.setItems(RepairsView);
	    }
	    
	    @FXML
		public void submitUpdate ( ActionEvent event ) throws Exception{
			if(validation()) {
			int id = Integer.parseInt(updateID.getText());
			String type = updateType.getText();
			String status = updateStat.getText();
			Date warranty = Date.valueOf(updateWar.getValue());
			String damage = updateDam.getText();
			Date currentD = Date.valueOf(updateCurrent.getValue());
			Date nextD = Date.valueOf(updateNext.getValue());
			double cost = Double.parseDouble(updateCost.getText());
			
			try {
				con = Dbconnect.connect();
				String query = "UPDATE repairsgebs SET ItemID = ?, ItemType = ?, Status = ?, WarrantyDate = ?, DamageType = ?, RepairDateCurrent = ?, RepairDateNext = ?, TotalCost = ? WHERE ItemID = '" + id + "'";
				pst = con.prepareStatement(query);
				
				pst.setInt(1, id);
				pst.setString(2, type);
				pst.setString(3, status);
				pst.setDate(4, warranty);
				pst.setString(5, damage);
				pst.setDate(6, currentD);
				pst.setDate(7, nextD);
				pst.setDouble(8, cost);
				
				pst.execute();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			clear();
			ShowRepViews();
			}
	    }
	    @FXML
		public void submitDelete ( ActionEvent event ) throws Exception{
			String id = updateID.getText();
		
			String query = "DELETE FROM repairsgebs WHERE ItemID ='" +id+ "'";
			try {
				pst = con.prepareStatement(query);
			
				pst.execute();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			clear();
			ShowRepViews();
		}
	    
	    @FXML
	    public void tableMouseClicked(MouseEvent event) {
	    	Repair r = repairsTable.getSelectionModel().getSelectedItem();
	    	
	    	int id = r.getitemID();
	    	String type = r.getitemType();
	    	String status = r.getStatus();
	    	LocalDate  warDate = (r.getWarDate()).toLocalDate();
	    	String damage = r.getDamType();
	    	LocalDate current = (r.getRepDate().toLocalDate());
	    	LocalDate next = (r.getRepNext().toLocalDate());
	    	double cost = r.getCost();
	    	
	    	String idstr = Integer.toString(id);
	    	String coststr = Double.toString(cost);
	    
	    	
	    	updateID.setText(idstr);
	    	updateType.setText(type);
	    	updateStat.setText(status);
	    	updateWar.setValue(warDate);
	    	updateDam.setText(damage);
	    	updateCurrent.setValue(current);
	    	updateNext.setValue(next);
	    	updateCost.setText(coststr);
	    	
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
	    
	    public void RepSearchScreen ( ActionEvent event ) throws Exception{
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RepairsSearch.fxml"));
			Parent view = (Parent) loader.load();
			RepairsSearchController rsc = loader.getController();
			rsc.setUser(UserType.getText(),userId.getText());	
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
		
	
		

		
		public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
		
	
		
	
		

		
	
	
		@FXML
		private void initialize(){
			Dbconnect con = new Dbconnect();
			ShowRepViews();
		}
		
		
}
