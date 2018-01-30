package model;

public class DataType_CT {
	
	/**
	 * The final gateway IP address
	 */
	private Address address;
	
	/**
	 * The status of the current object
	 */
	private Status status;
	
	/**
	 * The number of CPU of the current object
	 */
	private int nbCPU;
	
	/**
	 * The maximum RAM allowed of the current object
	 */
	private int maxRAM;
	
	/**
	 * The maximum Disk allowed of the current object
	 */
	private int maxDisk;
	
	/**
	 * The used CPU of the current object
	 */
	private int usedCPU;
	
	/**
	 * The used RAM of the current object
	 */
	private int usedRAM;
	
	/**
	 * The usedDisk of the current object
	 */
	private int usedDisk;
	
	
	public DataType_CT(Address address, Status status, int nbCPU, int maxRAM, int maxDisk){
		this.setAddress(address);
		this.setStatus(status);
		this.setMaxRAM(maxRAM);
		this.setMaxDisk(maxDisk);
		this.setUsedCPU(0);
		this.setUsedRAM(0);
		this.setUsedDisk(0);
	}
	
	
	public DataType_CT(Address address, Status status, int nbCPU, int maxRAM, int maxDisk, int usedCPU, int usedRAM, int usedDisk){
		this.setAddress(address);
		this.setStatus(status);
		this.setMaxRAM(maxRAM);
		this.setMaxDisk(maxDisk);
		this.setUsedCPU(usedCPU);
		this.setUsedRAM(usedRAM);
		this.setUsedDisk(usedDisk);
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


	public int getNbCPU() {
		return nbCPU;
	}


	public void setNbCPU(int nbCPU) {
		this.nbCPU = nbCPU;
	}


	public int getMaxRAM() {
		return maxRAM;
	}


	public void setMaxRAM(int maxRAM) {
		this.maxRAM = maxRAM;
	}


	public int getMaxDisk() {
		return maxDisk;
	}


	public void setMaxDisk(int maxDisk) {
		this.maxDisk = maxDisk;
	}


	public int getUsedCPU() {
		return usedCPU;
	}


	public void setUsedCPU(int usedCPU) {
		this.usedCPU = usedCPU;
	}


	public int getUsedRAM() {
		return usedRAM;
	}


	public void setUsedRAM(int usedRAM) {
		this.usedRAM = usedRAM;
	}


	public int getUsedDisk() {
		return usedDisk;
	}


	public void setUsedDisk(int usedDisk) {
		this.usedDisk = usedDisk;
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
		DataType_CT other = (DataType_CT) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}


}
