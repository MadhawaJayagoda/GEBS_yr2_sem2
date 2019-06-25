package controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Main;
import model.Repair;
import util.generator;

public class RepairsController {
	
    //from insert
	@FXML
	public Label userId;
	@FXML
	public Label UserType;
	@FXML
    private Button homeButton;
	@FXML
	private Button demo;
    @FXML
    private TextArea repItemID;

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
    private TextArea repStatus;

    @FXML
    private DatePicker repWarDate;

    @FXML
    private TextArea repDamType;

    @FXML
    private DatePicker repRepDate;

    @FXML
    private TextArea repCost;

    @FXML
    private DatePicker repRepNext;

    @FXML
    private Button repInsConfirm;

    @FXML
    private Button repInsSearch;

    @FXML
    private Button repInsView;

    @FXML
    private TextArea repItemID1;

    @FXML
    private Button closeButton;

    @FXML
    private Button logOutButton;
    
    @FXML
    private Button repInsUpdate;
    
	    Connection con = null;
	    PreparedStatement pst = null;
	    @FXML
	    public void clear() {
	    	repItemID.clear();
	    	repItemID1.clear();
	    	repStatus.clear();
	    	repWarDate.getEditor().clear();
	    	repDamType.clear();
	    	repRepDate.getEditor().clear();
	    	repRepNext.getEditor().clear();
	    	repCost.clear();
	    }
	    @FXML
		public void submitRepair( ActionEvent event ) throws Exception{
//			if(validation()) {
	    	Repair r1 =  new Repair();
			
			r1.setItemID(Integer.parseInt(repItemID.getText()));
			r1.setitemType(repItemID1.getText());
			r1.setStatus(repStatus.getText());
			Date sqlDate = Date.valueOf(repWarDate.getValue());
			r1.setWarDate(sqlDate);
			r1.setDamType(repDamType.getText());
			Date sqlDate2 = Date.valueOf(repRepDate.getValue());
			r1.setRepDate(sqlDate2);
			Date sqlDate3 = Date.valueOf(repRepNext.getValue());
			r1.setRepNext(sqlDate3);
			r1.setCost(Double.parseDouble(repCost.getText()));
					
			try {
				con = Dbconnect.connect();
				String query = "INSERT INTO repairsgebs(ItemID, ItemType, Status, WarrantyDate, DamageType, RepairDateCurrent, RepairDateNext, TotalCost) VALUES (?,?,?,?,?,?,?,?)";
				pst = con.prepareStatement(query);
				
				pst.setInt(1, r1.getitemID());
				pst.setString(2, r1.getitemType());
				pst.setString(3, r1.getStatus());
				pst.setDate(4, r1.getWarDate());
				pst.setString(5,  r1.getDamType());
				pst.setDate(6,  r1.getRepDate());
				pst.setDate(7,  r1.getRepNext());
				pst.setDouble(8,  r1.getCost());
				
				pst.execute();
			}catch (Exception e) {
				System.out.println(e);
			}
			clear();
		}
	    //}

		public void repViewScreen ( ActionEvent event ) throws Exception{
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
	    
		public void repInsSearch( ActionEvent event ) throws Exception{
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
		
		//validations
		
//				public boolean validation() {
//					Pattern p1 = Pattern.compile("[0-9]+");
//					Matcher m1 = p1.matcher(repItemID.getText());
//					if(repItemID.getText().isEmpty()) {
//						generator.getAlert("Error", "Item ID is empty!");
//						return false;
//					}
//					if(!m1.matches()) {
//						generator.getAlert("Item ID field ", "Invalid Characters!");
//						return false;
//					}
//					
//					Pattern p2 = Pattern.compile("[a-zA-Z]+");
//					Matcher m2 = p2.matcher(repItemID1.getText());
//					if(repItemID1.getText().isEmpty()) {
//						generator.getAlert("Error", "Item Type is empty!");
//						return false;
//					}
//					if(!m2.matches()) {
//						generator.getAlert("Item Type field ", "Invalid Characters!");
//						return false;
//					}
//					
//					Pattern p3 = Pattern.compile("[a-zA-Z]+");
//					Matcher m3 = p3.matcher(repStatus.getText());
//					if(repStatus.getText().isEmpty()) {
//						generator.getAlert("Error", "Status is empty!");
//						return false;
//					}
//					if(!m3.matches()) {
//						generator.getAlert("Status field ", "Invalid Characters!");
//						return false;
//					}
//					
//					Pattern p4 = Pattern.compile("[0-9]+");
//					Matcher m4 = p4.matcher(repCost.getText());
//					if(repCost.getText().isEmpty()) {
//						generator.getAlert("Error", "Cost is empty!");
//						return false;
//					}
//					if(!m4.matches()) {
//						generator.getAlert("Cost field ", "Invalid Characters!");
//						return false;
//					}
//					
//					Pattern p5 = Pattern.compile("[a-zA-Z]+");
//					Matcher m5 = p5.matcher(repDamType.getText());
//					
//					if(!m5.matches()) {
//						generator.getAlert("Damage Type field ", "Invalid Characters!");
//						return false;
//					}
//
////WARRANTY DATE VALIDATION
//					
//					final String validate1 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
//					
//					Pattern p6 = Pattern.compile(validate1);
//					Matcher m6 = p6.matcher((CharSequence) repWarDate.getConverter());
//					
////					if(m6.matches()) {
////						m6.reset();
////					}
//					if(m6.find()) {
//						String day1 = m6.group(1);
//						String mon1 = m6.group(2);
//						int yr1 = Integer.parseInt(m6.group(3));
//						
//						if(day1.equals("31") && (mon1.equals("4") || mon1.equals("6") || mon1.equals("9") || mon1.equals("11") || mon1.equals("04") || mon1.equals("06") || mon1.equals("09"))) {
//							generator.getAlert("Warranty Date field", "Invalid Date!");
//							return false;
//						}
//						else if(mon1.equals("2") || mon1.equals("02")) {
//							if(yr1 % 4 == 0) {
//								if(day1.equals("30") || day1.equals("31")) {
//									generator.getAlert("Warranty Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//							else {
//								if(day1.equals("29") || day1.equals("30") || day1.equals("31")) {
//									generator.getAlert("Warranty Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//						}
//						else {
//							return true;
//						}
//					}
//					
//					final String validate2 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
//					
//					Pattern p7 = Pattern.compile(validate2);
//					Matcher m7 = p7.matcher((CharSequence) repRepDate.getConverter());
//					
////					if(m6.matches()) {
////						m6.reset();
////					}
//					if(m7.find()) {
//						String day2 = m7.group(1);
//						String mon2 = m7.group(2);
//						int yr2 = Integer.parseInt(m7.group(3));
//						
//						if(day2.equals("31") && (mon2.equals("4") || mon2.equals("6") || mon2.equals("9") || mon2.equals("11") || mon2.equals("04") || mon2.equals("06") || mon2.equals("09"))) {
//							generator.getAlert("Repair Date field", "Invalid Date!");
//							return false;
//						}
//						else if(mon2.equals("2") || mon2.equals("02")) {
//							if(yr2 % 4 == 0) {
//								if(day2.equals("30") || day2.equals("31")) {
//									generator.getAlert("Repair Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//							else {
//								if(day2.equals("29") || day2.equals("30") || day2.equals("31")) {
//									generator.getAlert("Repair Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//						}
//						else {
//							return true;
//						}
//					}
//					
//					final String validate3 = ("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
//					
//					Pattern p8 = Pattern.compile(validate3);
//					Matcher m8 = p8.matcher((CharSequence) repRepNext.getConverter());
//					
////					if(m6.matches()) {
////						m6.reset();
////					}
//					if(m8.find()) {
//						String day3 = m8.group(1);
//						String mon3 = m8.group(2);
//						int yr3 = Integer.parseInt(m8.group(3));
//						
//						if(day3.equals("31") && (mon3.equals("4") || mon3.equals("6") || mon3.equals("9") || mon3.equals("11") || mon3.equals("04") || mon3.equals("06") || mon3.equals("09"))) {
//							generator.getAlert("Next Date field", "Invalid Date!");
//							return false;
//						}
//						else if(mon3.equals("2") || mon3.equals("02")) {
//							if(yr3 % 4 == 0) {
//								if(day3.equals("30") || day3.equals("31")) {
//									generator.getAlert("Next Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//							else {
//								if(day3.equals("29") || day3.equals("30") || day3.equals("31")) {
//									generator.getAlert("Next Date field", "Invalid Date!");
//									return false;
//								}
//								else {
//									return true;
//								}
//							}
//						}
//						else {
//							return true;
//						}
//					}
//					return true;
//				}
				
//REPAIR DATE VALIDATION
				
				
				
//THIS DATE VALIDATION
				
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
	

	public void demo() {
		
		repItemID.setText("10");
		repItemID1.setText("I1");
		repStatus.setText("In Progress");
		Date sqlDate = Date.valueOf(("2019-06-20"));
		Date sqlDate1 = Date.valueOf(("2019-07-18"));
		Date sqlDate2 = Date.valueOf(("2019-08-20"));

		LocalDate warDate = sqlDate.toLocalDate();
		LocalDate repDate = sqlDate1.toLocalDate();
		LocalDate nextDate = sqlDate2.toLocalDate();

		repWarDate.setValue(warDate);
		repDamType.setText("None");
		repRepDate.setValue(repDate);
		repRepNext.setValue(nextDate);
		repCost.setText("20000");
	}

	

	

	
	
	
	

	
	 public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
}
