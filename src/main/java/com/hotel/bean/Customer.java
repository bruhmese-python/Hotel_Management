package com.hotel.bean;

public class Customer {
	private String CustomerName;
    private String Email;
    private String MobileNo;
    private String Address;
    private String CustomerId;
    private String password;
   
    public Customer() {}

	public Customer(String CustomerName, String Email, String MobileNo, String Address, String CustomerId,
			String password) {
		super();
		this.CustomerName = CustomerName;
		this.Email = Email;
		this.MobileNo = MobileNo;
		this.Address = Address;
		this.CustomerId = CustomerId;
		this.password = password;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    

}


