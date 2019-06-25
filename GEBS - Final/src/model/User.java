package model;

public class User {
	
	
	private String username;
	private String password;
	private String id;
	private String designation;
	
	
	public User() {
		super();
		this.username = null;
		this.password = null;
		this.id = null;
		this.designation = null;
	}

	public User(String id, String designation, String username, String password) {
		
		this.id = id;
		this.designation = designation;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
