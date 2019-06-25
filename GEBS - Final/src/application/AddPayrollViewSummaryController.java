package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.Date;

import application.util.DBconnection;
import application.util.Toast;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Main;
import net.sf.jasperreports.engine.JRException;

public class AddPayrollViewSummaryController implements Initializable{
	
	@FXML
	Label summaryLabel;
	
	Connection connection = null;
	PreparedStatement ps = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setSummary();
	}
	
	public void addDatatoDB() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// opening a connection to the database
		try {
			try {
				connection = DBconnection.getConnection();

				// Converting dates to string before adding to DB
				Date suppliedDate = Date.valueOf(Get_sup_payroll.getInstance().getSupplied_date());
				Date paymentDate = Date.valueOf(Get_sup_payroll.getInstance().getPayment_date());

				// Query execution
				ps = connection.prepareStatement(
						"INSERT INTO payroll (id, item, company, supplied_date, payment_date, unitprice, quantity, total_payment, payment_status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				ps.setInt(1, Get_sup_payroll.getInstance().getId());
				ps.setString(2, Get_sup_payroll.getInstance().getProduct());
				ps.setString(3, Get_sup_payroll.getInstance().getCompany());
				ps.setDate(4, suppliedDate);
				ps.setDate(5, paymentDate);
				ps.setDouble(6, Get_sup_payroll.getInstance().getUnitprice());
				ps.setInt(7, Get_sup_payroll.getInstance().getQuantity());
				ps.setDouble(8, Get_sup_payroll.getInstance().getTotal_payment());
				ps.setString(9, Get_sup_payroll.getInstance().getPayment_status());

				int status = ps.executeUpdate(); // executeUpdate method returns an non-zero integer only if the
													// execution
													// successful, else it returns zero.

				if (status != 0) {
					System.out.println("Database connection successful !");
					System.out.println("Records are Inserted");
					Toast.show("Invoice Data added to database successfully !", summaryLabel);
				}
			}

			catch (SQLIntegrityConstraintViolationException ex) {
				
				//Validation
				/**
				 * This Exception is used to catch Duplicate Entries. If found, it will
				 * show a Toast message to inform the user.
				 */
				
				ex.printStackTrace();
				Toast.show("Error : Supplier ID already inserted to Database (Duplicate Entry Violation)",
						summaryLabel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// Finally block used to cleaning up and close resources
		finally {
			if (ps != null)
				ps.close();

			if (connection != null)
				connection.close();
		}
		
	}

	public void setSummary() {
		//Accessing the object of Payroll and setting text for label
		
		int id = Get_sup_payroll.getInstance().getId();
		String product = Get_sup_payroll.getInstance().getProduct();
		String company = Get_sup_payroll.getInstance().getCompany();
		LocalDate supplied_date = Get_sup_payroll.getInstance().getSupplied_date();
		LocalDate payment_date = Get_sup_payroll.getInstance().getPayment_date();
		String payment_status = Get_sup_payroll.getInstance().getPayment_status();
		double unitprice = Get_sup_payroll.getInstance().getUnitprice();
		int quantity = Get_sup_payroll.getInstance().getQuantity();
		
		/**
		 *   Calculating the total Cost for supplied products
		 */
		
		double totalPayment = (double) unitprice * quantity;
		
		Get_sup_payroll.getInstance().setTotal_payment(totalPayment);
		
		Get_sup_payroll.print();   // use for testing 
		
		summaryLabel.setText("Supplier ID \t\t\t\t:\t\t " + id + "\n" + "Item \t\t\t\t\t:\t\t " + product + "\n"  
				+ "Company \t\t\t\t:\t\t " + company + "\n" + "Item supplied Date \t\t\t:\t\t " + supplied_date.toString() + "\n"
				+ "Unit price of an Item \t\t:\t\t " + unitprice + "\n" + "Quantity added \t\t\t:\t\t " + quantity 
				+ "\n" + "Total Cost \t\t\t\t:\t\t " + totalPayment + "\n" + "Date of Payment / Due date   :\t\t " + payment_date.toString()
				+ "\n" + "Payment status \t\t\t:\t\t " + payment_status );
		
	}
	
	public void generateReport() throws JRException {
		PrintReport report = new PrintReport();
		report.showReport();
	}
	
	public void changeScenetoPayrollHome(ActionEvent event) throws IOException {
		Parent PayrollHomeViewParent = FXMLLoader.load(getClass().getResource("/application/payrollHomeView.fxml"));
		Scene PayrollHomeViewScene = new Scene(PayrollHomeViewParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		PayrollHomeViewScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		window.setScene(PayrollHomeViewScene);
		window.show();
	}

	public void changeScene(ActionEvent event) throws IOException {
		Parent addPayrollViewSummaryParent = FXMLLoader.load(getClass().getResource("/application/AddPayrollView_2.fxml"));
		Scene addPayrollViewSummaryScene = new Scene(addPayrollViewSummaryParent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(addPayrollViewSummaryScene);
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
