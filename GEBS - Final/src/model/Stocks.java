package model;

public class Stocks {
	private int id;
	private String supplierId;
	private String itemName;
	private String brandName;
	private int quantity;
	private double buyingPrice;
	private double sellingPrice;
	
	public Stocks() {
		super();
		this.supplierId = null;
		this.itemName = null;
		this.brandName = null;
		this.quantity = 0;
		this.buyingPrice = 0.00;
		this.sellingPrice = 0.00;
	}
	
	public Stocks(int id, String supplierId, String itemName, String brandName, int quantity, double buyingPrice,
			double sellingPrice) {
		super();
		this.id = id;
		this.supplierId = supplierId;
		this.itemName = itemName;
		this.brandName = brandName;
		this.quantity = quantity;
		this.buyingPrice = buyingPrice;
		this.sellingPrice = sellingPrice;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	

}
