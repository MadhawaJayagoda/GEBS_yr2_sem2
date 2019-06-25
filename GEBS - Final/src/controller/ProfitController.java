package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Main;
import model.Transaction;
import model.User;

public class ProfitController {
	public int privilegelevel = 0;
	
	public User userObj = new User();
 
	@FXML
	public Label userId;
	@FXML
	public Label UserType;
	@FXML
	private BarChart<String,Double> BarChart;

	@FXML
	private Button btnLoad;
	
	@FXML
    private Button homeButton;
	
	@FXML
    private Button closeButton;
	
	@FXML
    private Button logOutButton;
	
	 @FXML
	 private ChoiceBox<String> monthChoice;

    @FXML
    private Label profitResulttxt;
	
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private CategoryAxis xAxis;
	Connection con = null;
	ObservableList<String> Month = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
	@FXML
	private void initialize() {
	
		
		con = Dbconnect.connect();
		monthChoice.setItems(Month);
		
	}
    @FXML
    void loadChart(ActionEvent event) {
    	
    	
    }
	@FXML
	private void calculate() {
		
		ArrayList<Transaction> transactDetails= new ArrayList<>();
		int m = monthToDigit();
		try {
		String sql ="SELECT * from transactions where MONTH(date) = "+m;
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()) {
			transactDetails.add(new Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5)));	
			
		}
		}catch(SQLException e) {
			System.out.println(e);
		}
		double amount = 0;
		double profit = 0;
		double loss = 0;
		for(int i=0; i<transactDetails.size();i++){
			Transaction t = transactDetails.get(i);
			String transactType = t.getTransType();
			String Sale,Contract;
			Sale = "Sale";
			Contract = "Contract";
			double value = t.getAmount();
			if(transactType.equals(Sale) || transactType.equals(Contract)) {
				profit = profit + value;
			}
			
			else {
				loss = loss + value;
			}
			
	}
		amount = profit - loss;
		
		if(amount > 0) {
			profitResulttxt.setText("Overall profit of "+amount+" rupees");
		}
		
		if(amount < 0) {
			amount = amount * -1;
			profitResulttxt.setText("Overall loss of "+amount+" rupees");
		}
		loadChart(profit,loss);
		
	}
	private void loadChart(double profit, double loss) {
		
		String month = monthChoice.getValue();
		XYChart.Series<String,Double> dataSeries1 = new XYChart.Series<>();
		dataSeries1.setName(month+" Profit");
		dataSeries1.getData().add(new XYChart.Data<>("Profit", profit));
        
		
		XYChart.Series<String, Double> ds2 = new XYChart.Series<>();
		ds2.setName(month+" Loss");
		ds2.getData().add(new XYChart.Data<>("Loss",loss));
        
        BarChart.getData().add(dataSeries1);
        BarChart.getData().add(ds2);
        
		
	}
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	
	
	
	
	
	public void FinanceScreen( ActionEvent event ) throws Exception{
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
	
	
	


	 @FXML
	    void homeScreen(ActionEvent event) throws Exception{
		 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
			Parent view = (Parent) loader.load();;
			homeController hc = loader.getController();
			Scene scene = new Scene(view);
			hc.receieveDetails(UserType.getText(),userId.getText());
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			Stage window = Main.getStageObj(); 
			
			window.setScene(scene);
			window.show();
			window.centerOnScreen();

	    }
	public int monthToDigit() {
		String month = monthChoice.getValue();
		int m = 0;
		if(month == "January") {
			m = 1;
		}
		if(month == "February") {
			m = 2;
		}
		if(month == "March") {
			m = 3;
		}
		if(month == "April") {
			m = 4;
		}
		if(month == "May") {
			m = 5;
		}
		if(month == "June") {
			m = 6;
		}
		if(month == "July") {
			m = 7;
		}
		if(month == "August") {
			m = 8;
		}
		if(month == "September") {
			m = 9;
		}
		if(month == "October") {
			m = 10;
		}
		if(month == "November") {
			m = 11;
		}
		if(month == "December") {
			m = 12;
		}
		return m;
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
	
}
