package model;

public class DataType_GF {
	
	/**
	 * The final gateway IP address
	 */
	private Address address;
	
	/**
	 * The status of the current object
	 */
	private Status status;
	
	/**
	 * The name of the current GF
	 */
	private String name;
	
	
	/**
	 * A basic GF constructor
	 */
	public DataType_GF(Address address, Status status, String name){
		this.setAddress(address);
		this.setStatus(status);
		this.setName(name);
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	

	public boolean equals(DataType_GF gf) {
		return gf.getAddress().equals(this.getAddress());
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
