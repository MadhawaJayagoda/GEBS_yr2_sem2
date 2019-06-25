package application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.util.Toast;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Main;

public class AddPayrollView_2Controller {
	@FXML
	TextField unitprice;
	
	@FXML
	TextField quantity;
	
	@FXML
	DatePicker supplied_date;
	
	@FXML
	private ChoiceBox status;
	
	@FXML
	DatePicker payment_date;
	
	ObservableList <String> statusList = FXCollections.observableArrayList("Paid", "Not Paid");

	
	@FXML
	private void initialize() {
		status.setItems(statusList);
	}
	
	public void onSubmit(ActionEvent event) throws IOException, InvocationTargetException  {
		try {
			String unit_price = unitprice.getText();
			String qty = quantity.getText();
			Boolean result = validateDate();

			if ((unitprice.getText().isEmpty() || quantity.getText().isEmpty()
					|| status.getSelectionModel().isEmpty()) == true) {
				Toast.show("Error : Please fill all requested fields", unitprice);
			} else if (supplied_date.getValue() == null || payment_date.getValue() == null) {
				Toast.show("Error : Please fill the date fields", unitprice);
			} else if (result == false) {
				Toast.show("Error : Invalid supplied Date and payment Date\n \t\tPlease check again !", unitprice);
			} else {

				try {

					// Validation
					/**
					 * Catching NumberformatException for inputs unit price and quantity Validation
					 * for Numbers
					 */

					Integer.parseInt(qty);
					Double.parseDouble(unit_price);

					// If an Exception caught, it will not execute rest of the lines below.

					// first assign values to Payroll object
					assignValuestoPayroll();

					// Then change the scene
					Parent AddPayrollViewSummaryParent = FXMLLoader
							.load(getClass().getResource("/application/AddPayrollViewSummary.fxml"));
					Scene AddPayrollViewSummary = new Scene(AddPayrollViewSummaryParent);

					// getting stage information
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(AddPayrollViewSummary);
					window.show();

				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					Toast.show("Error: Invalid price or quantity input \n \t\tPlease check again !", unitprice);
				}

			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			Toast.show("Error : Please fill all requested fields", unitprice);
		}
	}
	
	public boolean validateDate() {
		LocalDate supp_date = supplied_date.getValue();
		LocalDate pay_date = payment_date.getValue();
		
		if(pay_date.isBefore(supp_date))
			return false;
		else if(supp_date.isBefore(pay_date))
			return true;
		else if(supp_date.isEqual(pay_date))
			return true;
		return false;
	}
	
	public void assignValuestoPayroll() {
		double unit_price = Double.parseDouble(unitprice.getText().toString());
		int qty = Integer.parseInt(quantity.getText().toString());
		LocalDate supp_date = supplied_date.getValue();
		LocalDate pay_date = payment_date.getValue();
		String state = status.getValue().toString();
				
		Get_sup_payroll.getInstance().setUnitprice(unit_price);
		Get_sup_payroll.getInstance().setQuantity(qty);
		Get_sup_payroll.getInstance().setSupplied_date(supp_date);
		Get_sup_payroll.getInstance().setPayment_date(pay_date);
		Get_sup_payroll.getInstance().setPayment_status(state);
		
		
		//Get_sup_payroll.print();   // use for testing 
	}
	
	public void demo() {
		unitprice.setText(Double.toString(580.0));
		quantity.setText(Integer.toString(50));
		try {
			supplied_date.setValue(LOCAL_DATE("2019-05-02"));
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("NUll pointer exception from LOCAL_DATE !");
		}
		status.setValue("Paid");
		try {
			payment_date.setValue(LOCAL_DATE("2019-05-06"));
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("NUll pointer exception from LOCAL_DATE !");
		}
		
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent AddPayrollView_2Parent = FXMLLoader.load(getClass().getResource("/application/AddPayrollView_1.fxml"));
		Scene AddPayrollView_2Scene = new Scene(AddPayrollView_2Parent);
		
		//getting stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(AddPayrollView_2Scene);
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
