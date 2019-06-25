package model;

public class Delivery {
	private int did;
	private String des;
	private String address;
	private String itemcode;
	private String deliverydate;

	
	public Delivery() {
		super();
		this.did = 0;
		this.des = null;
		this.itemcode = null;
		this.address = null;
		this.deliverydate=null;
	}
	
	public Delivery(int did, String des, String address, String itemcode, String deliverydate) {
		super();
		this.did = did;
		this.des = des;
		this.address = address;
		this.itemcode = itemcode;
		this.deliverydate= deliverydate;
		
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	
	

	
}
