package model;

import java.sql.Date;

public class Contracts {
	private int contractNum;
	private String customerName;
	private String contractType;
	private Date accept;
	private Date start;
	private Date end;
	private double cost;
	private String description;
	
public Contracts(){
		super();
		this.contractNum = 0;
		this.customerName = null;
		this.contractType = null;
		this.accept = null;
		this.start = null;
		this.end = null;
		this.cost = 0.00;
		this.description = null;
		
	}

public int getContractNum() {
	return contractNum;
}

public void setContractNum(int contractNum) {
	this.contractNum = contractNum;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getContractType() {
	return contractType;
}

public void setContractType(String contractType) {
	this.contractType = contractType;
}

public Date getAccept() {
	return accept;
}

public void setAccept(Date accept) {
	this.accept = accept;
}

public Date getStart() {
	return start;
}

public void setStart(Date start) {
	this.start = start;
}

public Date getEnd() {
	return end;
}

public void setEnd(Date end) {
	this.end = end;
}

public double getCost() {
	return cost;
}

public void setCost(double cost) {
	this.cost = cost;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Contracts(int contractNum, String customerName, String contractType, Date accept, Date start, Date end,
		double cost, String description) {
	super();
	this.contractNum = contractNum;
	this.customerName = customerName;
	this.contractType = contractType;
	this.accept = accept;
	this.start = start;
	this.end = end;
	this.cost = cost;
	this.description = description;
}

	
	
	
}
