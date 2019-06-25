package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.util.DBconnection;
import application.util.Toast;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;

public class DeletePayrollViewController implements Initializable{
	
	@FXML
	private TableView<Payroll> invoiceTable;
	@FXML
	private TableColumn<Payroll, Integer> col_id;
	@FXML
	private TableColumn<Payroll, String> col_item;
	@FXML
	private TableColumn<Payroll, String> col_company;
	@FXML
	private TableColumn<Payroll, Double> col_total;
	@FXML
	private TableColumn<Payroll, String> col_status;
	@FXML
	private TextField input_id;
	@FXML
	private TextField input_item;
	@FXML
	private TextField input_company;
	
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
		col_item.setCellValueFactory(new PropertyValueFactory<>("product"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("total_payment"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
		
		invoiceTable.setItems(PayrollList);
	}
	
	public void loadDatabase() {
		
		try {
			
			input_id.clear();
			input_item.clear();
			input_company.clear();
			
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
		col_item.setCellValueFactory(new PropertyValueFactory<>("product"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("total_payment"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
		
		invoiceTable.setItems(PayrollList);
		
	}
	
	public void showOnClick() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection con = DBconnection.getConnection();
		Payroll payroll = (Payroll) invoiceTable.getSelectionModel().getSelectedItem();
		String query = "select * from payroll";
		PreparedStatement ps = con.prepareStatement(query);
		
		input_id.setText(Integer.toString(payroll.getId()));
		input_item.setText(payroll.getProduct());
		input_company.setText(payroll.getCompany());
	}
	
	public void deleteInvoice() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		if ((input_id.getText().isEmpty() || input_item.getText().isEmpty()
				|| input_company.getText().isEmpty()) == true) {
			Toast.show("One or many fields are empty!\n Please click on the required payment to delete from the list",
					input_id);
		} else {

			Connection con = DBconnection.getConnection();
			Payroll payroll = (Payroll) invoiceTable.getSelectionModel().getSelectedItem();
			String query = "delete from payroll where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, payroll.getId());
			ps.executeUpdate();

			// closing resources
			ps.close();
			con.close();

			invoiceTable.getItems().clear();
			loadDatabase();
			Toast.show("Invoice with supplier ID " + payroll.getId() + " deleted successfully!", input_id);
		}

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
