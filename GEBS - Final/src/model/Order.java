package model;

public class Order {
	private int orderId;
	private String customerName;
	private String customerAdress;
	private String items;
	private Double itemPrice;
	private int quantity;
	private int teleponeNu;


	public Order() {
		super();
		this.customerName = null;
		this.customerAdress = null;
		this.items = null;
		this.itemPrice =  null;
		this.quantity = 0;
		this.teleponeNu = 0;
	}
	public Order(int orderId, String customerName, String customerAdress, String items,Double itemPrice, int quantity,
			int telephoneNu) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.customerAdress = customerAdress;
		this.items = items;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.teleponeNu = telephoneNu;
	}
	
	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerAdress() {
		return customerAdress;
	}


	public void setCustomerAdress(String customerAdress) {
		this.customerAdress = customerAdress;
	}


	public String getItems() {
		return items;
	}


	public void setItems(String items) {
		this.items = items;
	}


	public Double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getTelephoneNu() {
		return teleponeNu;
	}


	public void setTelephoneNu(int telephoneNu) {
		this.teleponeNu = telephoneNu;
	}

}
