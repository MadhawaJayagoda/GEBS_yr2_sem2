package controller;

import java.sql.SQLException;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Main;
import model.Stocks;



public class SearchStocksController {
	
    @FXML
    private Button homeButton;
   
    @FXML
    private Label UserType;
    
    @FXML
    private Label userId;
   
    @FXML
    private Button searchBtn;

    @FXML
    private TableView<Stocks> stocksSearchTable;

    @FXML
    private TableColumn<Stocks, Integer> searchTableIdColumn;

    @FXML
    private TableColumn<Stocks, String> searchTableSupplierIdColumn;

    @FXML
    private TableColumn<Stocks, String> searchTableItemNameColumn;

    @FXML
    private TableColumn<Stocks, String> searchTableBrandNameColumn;

    @FXML
    private TableColumn<Stocks, Integer> searchTableQuantityField;

    @FXML
    private TableColumn<Stocks, Double> searchTableBuyingPriceCoulmn;

    @FXML
    private TableColumn<Stocks, Double> searchTableSellingPriceCoulmn;

    @FXML
    private TextField searchField;

    @FXML
    private Button closeButton;

    @FXML
    private Button logOutButton;
    
    Connection con  = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
	}
	

	public void StockScreen( ActionEvent event ) throws Exception{
		  
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
	
	
	
	

	
	
	
	

	
	 @FXML
	public void homeScreen(ActionEvent event) throws Exception{
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
	@FXML
	public void loadTable() {
			ObservableList<Stocks>StocksSearchTableDetails = FXCollections.observableArrayList();
			try {
				stocksSearchTable.setItems(null);
				con = Dbconnect.connect();
				String selectQuery =  "SELECT * from inventory";
				pst = con.prepareStatement(selectQuery);
				rs = pst.executeQuery();
				while(rs.next()){
					StocksSearchTableDetails.add(new Stocks(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));	
				}
			}catch(SQLException e) {
				 System.out.println(e);
			}
				searchTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
				searchTableSupplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
				searchTableItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
				searchTableBrandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
				searchTableQuantityField.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				searchTableBuyingPriceCoulmn.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
				searchTableSellingPriceCoulmn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
				stocksSearchTable.setItems(StocksSearchTableDetails);
			
			FilteredList<Stocks> filteredData = new FilteredList<>(StocksSearchTableDetails, e -> true);
			searchField.setOnKeyReleased(e ->{
				searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
					filteredData.setPredicate( stocks -> {
						if(newValue == null || newValue.isEmpty()) {
							return true;
						}
					String lowercaseFilter = newValue.toLowerCase();
					try {
							int newInt = Integer.parseInt(newValue);
							if(stocks.getId() == newInt){
								return true;
							}
					}catch(NumberFormatException NFE) {
						System.out.println(NFE);
					}
					if(stocks.getSupplierId().toLowerCase().contains(lowercaseFilter)) {
						return true;
					}
					if(stocks.getItemName().toLowerCase().contains(lowercaseFilter)) {
						return true;
					}
					if(stocks.getBrandName().toLowerCase().contains(lowercaseFilter)) {
						return true;
					}
					return false;
						
				});
			});
				SortedList<Stocks> sortedData = new SortedList<>(filteredData);
				sortedData.comparatorProperty().bind(stocksSearchTable.comparatorProperty());
				stocksSearchTable.setItems(sortedData);
		});
		
	}
	
	@FXML
	private void initialize() {
		
		con = Dbconnect.connect();
		loadTable();
			
	}
public void setUser (String user, String ID) {
		
		UserType.setText(user);
		userId.setText(ID);
		}
	
	
}
