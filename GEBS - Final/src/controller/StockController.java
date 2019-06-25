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
import model.Stocks;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.generator;

public class StockController {

	@FXML
	private TextField inputReportID;

    @FXML
    private Button homeButton;

    @FXML
    private Label idLabel;
    
    @FXML
    private Label UserType;
    
    @FXML
    private Label userId;

    @FXML
    private TextField supplierIdField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemBrandField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField buyingPriceField;

    @FXML
    private TextField sellingPriceField;

    @FXML
    private Button addBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Button closeButton;

    @FXML
    private Button logOutButton;
    
    @FXML
    private Button demobtn;

    
    @FXML
    private TableView<Stocks> stocksTable;

    @FXML
    private TableColumn<Stocks, Integer> tableIdColumn;

    @FXML
    private TableColumn<Stocks, String> tableSupplierIdColumn;

    @FXML
    private TableColumn<Stocks, String> tableItemNameColumn;

    @FXML
    private TableColumn<Stocks, String> tableBrandNameColumn;

    @FXML
    private TableColumn<Stocks, Integer> tableQuantityField;

    @FXML
    private TableColumn<Stocks, Double> tableBuyingPriceCoulmn;

    @FXML
    private TableColumn<Stocks, Double> tableSellingPriceCoulmn;
    
    @FXML
    private Button reportGenerateButton;
    
    Connection con  = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void clearFields() {
		
		supplierIdField.clear();
		itemNameField.clear();
		itemBrandField.clear();
		quantityField.clear();
		buyingPriceField.clear();
		sellingPriceField.clear();
	}
    
    @FXML
    public void demo(ActionEvent event) throws Exception{
    	supplierIdField.setText("CMA1");
    	itemNameField.setText("Water Pump");
    	itemBrandField.setText("Mitsui");
    	quantityField.setText("600");
    	buyingPriceField.setText("800");
    	sellingPriceField.setText("1000");
    	
    }
	public void ExitWindow(ActionEvent event) throws Exception{
		Platform.exit();
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
	
        public void SearchScreen(ActionEvent event)throws Exception{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchStocks.fxml"));
    		Parent view = (Parent) loader.load();
    		SearchStocksController ssc = loader.getController();
    		ssc.setUser(UserType.getText(),userId.getText());		
    		Scene scene = new Scene(view);
    		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    		Stage window = Main.getStageObj();
		
    		window.setScene(scene);
    		window.show();
    		window.centerOnScreen();
	}
	
	
	
	public void DeleteStock(ActionEvent event)throws Exception{
          String id = idLabel.getText();
		
		
		String sql = "DELETE from inventory where id = '"+id+"'";
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
	public void AddStock(ActionEvent event)throws Exception{
		Stocks s1  = new Stocks();
		s1.setSupplierId(supplierIdField.getText());
		s1.setItemName(itemNameField.getText());
		s1.setBrandName(itemBrandField.getText());
		try{
			s1.setQuantity(Integer.parseInt(quantityField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("NFE Integer","Please enter a valid integer value for quantity");
			return;
		}
		try {
		s1.setBuyingPrice(Double.parseDouble(buyingPriceField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("NFE BuyPrice","Please enter a valid value for buying price");
			return;
		}
		try {
		s1.setSellingPrice(Double.parseDouble(sellingPriceField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("NFE SellPrice","Please enter a valid value for selling price");
			return;
		}
		
		String query = "INSERT INTO inventory(supplierId,itemname,brand,quantity,buyingprice,sellingprice) values (?,?,?,?,?,?)";
		
		try {
			pst =  con.prepareStatement(query);

			pst.setString(1, s1.getSupplierId());
			pst.setString(2, s1.getItemName());
			pst.setString(3, s1.getBrandName());
			pst.setInt(4, s1.getQuantity());
			pst.setDouble(5, s1.getBuyingPrice());
			pst.setDouble(6, s1.getSellingPrice());
			pst.execute();
		}catch(Exception e) {
			System.out.println(e);
		}
		clearFields();
		loadTable();
	}
	
	@FXML
	public void UpdateStock(ActionEvent event) throws Exception{
		Stocks s1 = new Stocks();
		int id = Integer.parseInt(idLabel.getText());
		String supplierId  = supplierIdField.getText();
		String itemName = itemNameField.getText();
		String itemBrand = itemBrandField.getText();
		try{
			s1.setQuantity(Integer.parseInt(quantityField.getText()));
		}catch(NumberFormatException nfe) {
			generator.getAlert("NFE Integer","Please enter a valid integer value for quantity");
			return;
		}
		try {
			s1.setBuyingPrice(Double.parseDouble(buyingPriceField.getText()));
			}catch(NumberFormatException nfe) {
				generator.getAlert("NFE BuyPrice","Please enter a valid value for buying price");
				return;
			}
			try {
			s1.setSellingPrice(Double.parseDouble(sellingPriceField.getText()));
			}catch(NumberFormatException nfe) {
				generator.getAlert("NFE SellPrice","Please enter a valid value for selling price");
				return;
			}
		String updateQuery = "UPDATE inventory SET  supplierId = ? , itemname = ? , brand = ?, quantity = ?, buyingprice = ?, sellingprice = ?  WHERE id ='"+id+"'";
		
		try {
			pst = con.prepareStatement(updateQuery);
			pst.setString(1, supplierId);
			pst.setString(2, itemName);
			pst.setString(3, itemBrand);
			pst.setInt(4, s1.getQuantity());
			pst.setDouble(5, s1.getBuyingPrice());
			pst.setDouble(6, s1.getSellingPrice());
			
			pst.execute();
		}catch(SQLException e) {
			System.out.println(e);

		}
		loadTable();
		clearFields();
	}
	@FXML
	public void loadTable() {
		ObservableList<Stocks>StocksTableDetails = FXCollections.observableArrayList();
		try {
			stocksTable.setItems(null);
			con = Dbconnect.connect();
			String selectQuery =  "SELECT * from inventory";
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while(rs.next()){
				StocksTableDetails.add(new Stocks(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));	
				}
		}catch(SQLException e) {
				 System.out.println(e);
		}
			tableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableSupplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
			tableItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
			tableBrandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
			tableQuantityField.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			tableBuyingPriceCoulmn.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
			tableSellingPriceCoulmn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
			stocksTable.setItems(StocksTableDetails);
		
	}
	
	@FXML
	public void tableMouseClicked(MouseEvent event) {
		Stocks s1 = stocksTable.getSelectionModel().getSelectedItem();
		
		int id = s1.getId();
		String supplierId = s1.getSupplierId();
		String itemName = s1.getItemName();
		String brandName = s1.getBrandName();
		int quantity = s1.getQuantity();
		Double buyingPrice = s1.getBuyingPrice();
		Double sellingPrice = s1.getSellingPrice();
		String idstr = Integer.toString(id);
		String quantityStr = Integer.toString(quantity);
		
		idLabel.setText(idstr);
		supplierIdField.setText(supplierId);
		itemNameField.setText(itemName);
		itemBrandField.setText(brandName);
		quantityField.setText(quantityStr);
		buyingPriceField.setText(buyingPrice.toString());
		sellingPriceField.setText(sellingPrice.toString());
		
	}
	@FXML
	private void initialize() {
		
		con = Dbconnect.connect();
		loadTable();
			
	}

		
	 @FXML
	 private void Refresh() {
		 clearFields();
		 loadTable();
	 }
	 
	 @FXML
	 public void genReport(ActionEvent event)throws Exception{
		 String id = inputReportID.getText();
		 con = Dbconnect.connect();
			String reportPath = "C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\Generated\\SReport.pdf";
			try {
				
				
			JasperDesign jd = JRXmlLoader.load("C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\reports\\StockReport.jrxml");
			JRDesignQuery query = new JRDesignQuery();
			query.setText("SELECT * FROM gebs.inventory WHERE Id = '"+id+"' ");
			jd.setQuery(query);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			JasperPrint jp = JasperFillManager.fillReport(jr, null,con);
			
			JasperExportManager.exportReportToPdfFile(jp,reportPath);
			generator.getAlert("Report generation success", "Report has been printed!");
			JasperViewer.viewReport(jp);
			}catch(JRException jre) {
				System.out.println(jre);
			}
			inputReportID.clear();
	 }
	 public void setUser (String user, String ID) {
			
			UserType.setText(user);
			userId.setText(ID);
			}
}
