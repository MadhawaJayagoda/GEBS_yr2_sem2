package model;

import java.sql.Date;

public class Employee {
			private String id;
			private String firstname;
			private String lastname;
			private Date bday;
			private String gender;
			private String address;
			private String nic;
			private String telenum;
			private String designation;
			private double salary;
			private double finalsalary;
			
			

			public Employee() {
				super();
				this.id = null;
				this.firstname = null;
				this.lastname = null;
				this.bday = null;
				this.gender = null;
				this.address = null;
				this.nic = null;
				this.telenum = null;
				this.designation = null;
				this.salary = 0;
				this.finalsalary = 0;
			}

			 

			public Employee(String id, String firstname, String lastname, Date bday, String gender, String address,
					String nic, String telenum, String designation, double salary) {
				super();
				this.id = id;
				this.firstname = firstname;
				this.lastname = lastname;
				this.bday = bday;
				this.gender = gender;
				this.address = address;
				this.nic = nic;
				this.telenum = telenum;
				this.designation = designation;
				this.salary = salary;
			
			}



			public Employee(String id, String firstname, String lastname, double salary, double finalsalary) {
				this.id = id;
				this.firstname = firstname;
				this.lastname = lastname;
				this.salary = salary;
				this.finalsalary = finalsalary;
				
			}



			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getFirstname() {
				return firstname;
			}

			public void setFirstname(String firstname) {
				this.firstname = firstname;
			}

			public String getLastname() {
				return lastname;
			}

			public void setLastname(String lastname) {
				this.lastname = lastname;
			}

			public Date getBday() {
				return bday;
			}

			public void setBday(Date bday) {
				this.bday = bday;
			}

			public String getGender() {
				return gender;
			}

			public void setGender(String gender) {
				this.gender = gender;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getNic() {
				return nic;
			}

			public void setNic(String nic) {
				this.nic = nic;
			}

			public String getTelenum() {
				return telenum;
			}

			public void setTelenum(String telenum) {
				this.telenum = telenum;
			}

			public String getDesignation() {
				return designation;
			}

			public void setDesignation(String designation) {
				this.designation = designation;
			}

			public double getSalary() {
				return salary;
			}

			public void setSalary(double salary) {
				this.salary = salary;
			}

			public double getFinalsalary() {
				return finalsalary;
			}

			public void setFinalsalary(double finalsalary) {
				this.finalsalary = finalsalary;
			}
			
			
}
			
			