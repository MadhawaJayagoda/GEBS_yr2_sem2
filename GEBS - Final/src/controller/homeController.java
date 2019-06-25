package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Main;
import model.User;
import util.generator;

public class homeController {
	
	@FXML
	public Label userId;
	
	@FXML
	public Label UserType;
	@FXML
	private Button logOutButton;
	
	
	public User userObj = new User(); //creating user object
	public String designation;
	public int privilegelevel = 0;
	Connection con = null;
	ResultSet resultSet=null;
	@FXML

	//loading user ID & designation
	public void loadUser(User user) {
		String uname = user.getUsername();
		User usr = new User();
		usr.setUsername(uname);
		Connection con = null;
		try {
		con = Dbconnect.connect();
		String query = "Select id, designation from gebs.user where user = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ps = con.prepareStatement(query);
		ps.setString(1, uname);
		
		rs = ps.executeQuery();
		if(rs.next()) {
		String uID = rs.getString(1);
		String desig = rs.getString(2);
		
		usr.setDesignation(desig);
		usr.setId(uID);
		}
		
		userId.setText(usr.getId());
		UserType.setText(usr.getDesignation());
		
		
	
		privilegelevel = privlevelCheck(usr.getDesignation());
		
		}catch(SQLException E) {
			System.out.println(E);
		}
		
		
		
		
	}
	
	
	
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}

	public void StockScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 5 && privilegelevel != 3) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		else {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Stocks.fxml"));
		Parent view = (Parent) loader.load();
		StockController sc = loader.getController();
		sc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
		}
	}
	
	public void RepairScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 5 && privilegelevel != 4 ) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
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
	
	public void EmployeeScreen( ActionEvent event ) throws Exception{
		
		if(privilegelevel < 5 && privilegelevel != 1) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		else {
			
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
	}
	

	
	public void DeliveryScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 5 && privilegelevel != 3) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Delivery.fxml"));
		Parent view = (Parent) loader.load();
		DeliveryController dc = loader.getController();
		dc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	
	public void CustomerOrderScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 5 && privilegelevel !=3) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		Parent view = FXMLLoader.load(getClass().getResource("/view/CustomerOrder.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void ContractsScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 4) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Contracts.fxml"));
		Parent view = (Parent) loader.load();
		ContractsController cs = loader.getController();
		cs.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	public void FinanceScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 5  && privilegelevel != 2) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Finance.fxml"));
		Parent view = (Parent) loader.load();
		FinanceController fc = loader.getController();
		fc.setUser(UserType.getText(),userId.getText());
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	
	
	public void SuppliersScreen( ActionEvent event ) throws Exception{
		if(privilegelevel < 4 && privilegelevel != 3) {
			generator.getAlert("Access Denied", "You do not have access to this function");
			return;
		}
		Parent view = FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"));
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage window = Main.getStageObj();
		
		window.setScene(scene);
		window.show();
		window.centerOnScreen();
	}
	public void setUser (User user) {
		this.userObj = user;
		loadUser(userObj);
		
		
		
	}
	public int privlevelCheck(String desig) {
		String mgr = "Manager";
		String acct = "Accountant";
		String hr = "Human Resources";
		String pm = "Project Manager";
		String sp = "Salesperson";
		
		String designation = desig;
		
		if(designation.equals(mgr)){
			return 5;
		}
		if(designation.equals(pm)) {
			return 4;
		}
		else if(designation.equals(sp)) {
			return 3;
		}
		else if(designation.equals(acct))
		{
			return 2;
		}
		
		else if(designation.equals(hr)){
			return 1;
		}
		else {
			return 0;
		}
		
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
	public void receieveDetails(String user, String ID) {
		UserType.setText(user);
		userId.setText(ID);
		String desig = user;
		privilegelevel = privlevelCheck(desig);		
	}
	

}
