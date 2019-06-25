package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.util.DBconnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;
import net.sf.jasperreports.engine.JRException;

public class ViewAllPaymentsController implements Initializable{
	
	@FXML
	private TableView<Payroll> payroll_table;
	@FXML
	private TableColumn<Payroll, Integer> col_id;
	@FXML
	private TableColumn<Payroll, String> col_product;
	@FXML
	private TableColumn<Payroll, String> col_company;
	@FXML
	private TableColumn<Payroll, String> col_supplied_date;
	@FXML
	private TableColumn<Payroll, String> col_payment_date;
	@FXML
	private TableColumn<Payroll, Double> col_unitprice;
	@FXML
	private TableColumn<Payroll, Integer> col_quantity;
	@FXML
	private TableColumn<Payroll, Double> col_total_cost;
	@FXML
	private TableColumn<Payroll, String> col_status;
	
	ObservableList<Payroll> PayrollList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			Connection con = DBconnection.getConnection();
			
			ResultSet rs = con.createStatement().executeQuery("select * from payroll");
			
			while(rs.next()) {
				Payroll payroll = new Payroll();
				
				payroll.setId(rs.getInt("id"));
				payroll.setProduct(rs.getString("item"));
				payroll.setCompany(rs.getString("company"));
				payroll.setSupplied_date(rs.getDate("supplied_date").toLocalDate());
				payroll.setPayment_date(rs.getDate("payment_date").toLocalDate());
				payroll.setUnitprice(rs.getDouble("unitprice"));
				payroll.setQuantity(rs.getInt("quantity"));
				payroll.setTotal_payment(rs.getDouble("total_payment"));
				payroll.setPayment_status(rs.getString("payment_status"));
				
				PayrollList.add(payroll);
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_supplied_date.setCellValueFactory(new PropertyValueFactory<>("supplied_date"));
		col_payment_date.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
		col_unitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
		col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		col_total_cost.setCellValueFactory(new PropertyValueFactory<>("total_payment"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
		
		payroll_table.setItems(PayrollList);
	}
	
	public void generateReport() throws JRException {
		PrintReport report = new PrintReport();
		report.showReport();
	}
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent PayrollHomeViewParent = FXMLLoader.load(getClass().getResource("/application/payrollHomeView.fxml"));
		Scene PayrollHomeViewScene = new Scene(PayrollHomeViewParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		PayrollHomeViewScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(PayrollHomeViewScene);
		window.show();
	}
	
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

}
