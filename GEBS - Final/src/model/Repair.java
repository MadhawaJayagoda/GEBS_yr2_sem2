package model;

import java.sql.Date;

public class Repair {
	private int itemID;
	private String itemType;
	private String status;
	private Date warDate;
	private String damType;
	private Date repDate;
	private Date repNext;
	private double cost;
	
	public Repair(int itemID, String itemType, String status, Date warDate, String damType, Date repDate, Date repNext, double cost) {
		super();
		this.itemID = itemID;
		this.itemType = itemType;
		this.status = status;
		this.warDate = warDate;
		this.damType = damType;
		this.repDate = repDate;
		this.repNext = repNext;
		this.cost = cost;
	}
	
	public Repair() {
		this.itemType = null;
		this.status = null;
		this.warDate = null;
		this.damType = null;
		this.repDate = null;
		this.repNext = null;
		this.cost = 0.0;
	}
	public int getitemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getitemType() {
		return itemType;
	}
	public void setitemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getWarDate() {
		return warDate;
	}
	public void setWarDate(Date warDate) {
		this.warDate = warDate;
	}
	
	public String getDamType() {
		return damType;
	}
	public void setDamType(String damType) {
		this.damType = damType;
	}
	
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
	
	public Date getRepNext() {
		return repNext;
	}
	public void setRepNext(Date repNext) {
		this.repNext = repNext;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
//	public String toString() {
//		return itemID + itemType + status + warDate + damType + repDate + repNext + cost;
//	}
}

