 
package com.kuber.medicapclassrooms.model;


public class Logininfo {
	String email;
	String password;
	public Logininfo(String email, String pass) {
		this.email=email;
		this.password=pass;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
}
