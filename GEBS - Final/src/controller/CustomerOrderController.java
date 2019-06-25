package controller;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import controller.Dbconnect;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Order;


public class CustomerOrderController {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
		
    @FXML
    private Button homeButton;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField customerNamefield;

    @FXML
    private TextField itemsField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label OrderIDLable;

    @FXML
    private TextField priceField;

    @FXML
    private TextField telephoneNumberField;

    @FXML
    private TableView<Order> customerorderTable;

    @FXML
    private TableColumn<Order, Integer> idColumn;

    @FXML
    private TableColumn<Order, String> customerNameColumn;

    @FXML
    private TableColumn<Order, String> customerAddressColumn;

    @FXML
    private TableColumn<Order, Integer> itemsColumn;

    @FXML
    private TableColumn<Order, Double> PriceColumn;

    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    @FXML
    private TableColumn<Order, Integer> telephoneNumberColumn;

    @FXML
    private Button closeButton;

    @FXML
    private Button logOutButton;
    
    

	@FXML
	public  void AddOrder(ActionEvent event)throws Exception {
		Order o1 = new Order();
		
		o1.setCustomerName(customerNamefield.getText());
		o1.setCustomerAdress(customerAddressField.getText());
		o1.setItems(itemsField.getText());
		o1.setItemPrice(Double.parseDouble(priceField.getText()));
		o1.setQuantity(Integer.parseInt(quantityField.getText()));
		o1.setTelephoneNu(Integer.parseInt(telephoneNumberField.getText()));
		
		
		String query = "INSERT INTO customerorder(customerName,customerAddress,items,itemPrice,quantity,telephoneNu) values (?,?,?,?,?,?)";
		
		try {
			pst =  con.prepareStatement(query);

			pst.setString(1, o1.getCustomerName());
			pst.setString(2, o1.getCustomerAdress());
			pst.setString(3, o1.getItems());
			pst.setDouble(4, o1.getItemPrice());
			pst.setInt(5, o1.getQuantity());
			pst.setInt(6, o1.getTelephoneNu());
			pst.execute();
		}catch(Exception e) {
			System.out.println(e);
		}
		clearFields();
		loadTable();
	}
	    
	@FXML    
	public void updateOrder(ActionEvent event)throws Exception { 
		int id = Integer.parseInt(OrderIDLable.getText());
		String customerName  = customerNamefield.getText();
		String customerAddress = customerAddressField.getText();
		String items = itemsField.getText();
		Double price = Double.parseDouble(priceField.getText());
		int quantity = Integer.parseInt(quantityField.getText());
		int telephoneNu = Integer.parseInt(telephoneNumberField.getText());
		
		String updateQuery = "UPDATE customerorder SET   customerName = ? , customerAddress = ?, items = ?, itemPrice = ?, quantity = ?, telephoneNu = ?  WHERE id ='"+id+"'";
		
		try {
			pst = con.prepareStatement(updateQuery);
			pst.setString(1, customerName);
			pst.setString(2, customerAddress);
			pst.setString(3, items);
			pst.setDouble(4, price);
			pst.setInt(5, quantity);
			pst.setInt(6, telephoneNu);
			
			pst.execute();
		}catch(SQLException e) {
			System.out.println(e);

		}
		loadTable();
		clearFields();
	
		
	}
	
	@FXML
	public void DeleteOrder(ActionEvent event)throws Exception{
	    String id = OrderIDLable.getText();
		
		 
			String sql = "DELETE from customerorder where id = '"+id+"'";
			try {
			pst = con.prepareStatement(sql);
			pst.execute();
			}catch (Exception e) {
				System.out.println(e);
			}
			loadTable();
			clearFields();
		
	}
	
	@FXML
	public void clearFields(){
		customerAddressField.clear();
		customerNamefield.clear();
		itemsField.clear();
		priceField.clear();
		quantityField.clear();
		telephoneNumberField.clear();
		
	}
	@FXML
	public void loadTable() {
		ObservableList<Order>CustomerOrderTableDetails = FXCollections.observableArrayList();
		try {
			customerorderTable.setItems(null);
			con = Dbconnect.connect();
			String selectQuery =  "SELECT * from customerorder";
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while(rs.next()){ 
				CustomerOrderTableDetails.add(new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getDouble(5), rs.getInt(6),  rs.getInt(7)));	
				}
		}catch(SQLException e) {
				 System.out.println(e);
		}
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
			customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
			itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items"));
			PriceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
			quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNu"));
			customerorderTable.setItems(CustomerOrderTableDetails);
	}
	
	@FXML
	
	public void tableMouseClicked(MouseEvent event) {
		Order o1 = customerorderTable.getSelectionModel().getSelectedItem();
		
		int id = o1.getOrderId();
		String customerName = o1.getCustomerName();
		String customerAddress = o1.getCustomerAdress();
		String items = o1.getItems();
		double itemPrice = o1.getItemPrice();
		int quantity = o1.getQuantity();
		int telephoneNu = o1.getTelephoneNu();
		
		String idstr = Integer.toString(id);
		String quantityStr = Integer.toString(quantity);
		String telephoneNuStr = Integer.toString(telephoneNu);
		String itemPriceStr = Double.toString(itemPrice);
		
		OrderIDLable.setText(idstr);
		customerNamefield.setText(customerName);
		customerAddressField.setText(customerAddress);
		itemsField.setText(items);
		priceField.setText(itemPriceStr);
		quantityField.setText(quantityStr);
		telephoneNumberField.setText(telephoneNuStr);
		
		
	}
	
	@FXML
	private void initialize() {
		
		con = Dbconnect.connect();
		loadTable();
			
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
