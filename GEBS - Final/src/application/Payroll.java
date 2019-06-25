package application;

import java.time.LocalDate;

public class Payroll {
	
	//variables used declaration
	private int id;
	private String product;
	private String company;
	private LocalDate supplied_date;
	private LocalDate payment_date;
	private String payment_status;
	private double unitprice;
	private int quantity;
	private double total_payment;
	
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public LocalDate getSupplied_date() {
		return supplied_date;
	}
	public void setSupplied_date(LocalDate supplied_date) {
		this.supplied_date = supplied_date;
	}
	public LocalDate getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_payment() {
		return total_payment;
	}
	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
	}
	
}
