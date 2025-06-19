package model;

import java.io.Serializable;

public class Account implements Serializable {
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	private String prefecture;
	private String city;
	private String address1;
	private String address2;
	private String phone;

	public Account() {}
	public Account(String email, String password, String lastName, String firstName, String prefecture, String city,
			String address1, String address2, String phone) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.prefecture = prefecture;
		this.city = city;
		this.address1 = address1;
		this.address2 = address2;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}