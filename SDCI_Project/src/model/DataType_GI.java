package model;

public class DataType_GI extends DataType_CT{
	
	/**
	 * The name of the current GI
	 */
	private String name;
	
	/**
	 * A basic GI constructor
	 */
	public DataType_GI(Address address, Status status, int nbCPU, int maxRAM, int maxDisk, int usedCPU, int usedRAM, int usedDisk, String name){
		super(address, status, usedDisk, usedDisk, usedDisk, usedDisk, usedDisk, usedDisk);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
