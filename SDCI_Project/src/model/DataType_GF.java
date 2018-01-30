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

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataType_GF other = (DataType_GF) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

}
