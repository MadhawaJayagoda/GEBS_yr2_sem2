package model;

import java.sql.Date;

public class Transaction {
private int transactID;
private String ObjID;
private double amount;
private Date transactDate;
private String transType;

public Transaction() {
	
	this.ObjID = null;
	this.amount = 0.00;
	this.transactDate = null;
	this.transType = null;
	}

public Transaction(int transactid, String objID,  String transType, double amount, Date transactDate) {
	super();
	this.transactID = transactid;
	this.ObjID = objID;
	this.transType = transType;
	this.amount = amount;
	this.transactDate = transactDate;
	
}




public int getTransactID() {
	return transactID;
}



public String getObjID() {
	return ObjID;
}
public void setObjID(String objID) {
	ObjID = objID;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getTransactDate() {
	return transactDate;
}
public void setTransactDate(Date transactDate) {
	this.transactDate = transactDate;
}
public String getTransType() {
	return transType;
}
public void setTransType(String transType) {
	this.transType = transType;
}
}
