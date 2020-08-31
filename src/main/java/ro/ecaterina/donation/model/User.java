package ro.ecaterina.donation.model;

public class User {
	 private int id;
	 private String firstName;
	 private String lastName;
	 private String password;
	 private String phone;
	 private String email;
	 private int adressId;
	 
	//Constructori
	 public User(){};
	 
	 
	 public User(String firstName, String lastName, String password, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phone = phone;
		this.email = email;
	
	}
	 
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", adressId=" + adressId + "]";
	}

	//Gettere si Settere
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAdressId() {
		return adressId;
	}
	public void setAdressId(int adressId) {
		this.adressId = adressId;
	}
	 
	
	
	 

}
