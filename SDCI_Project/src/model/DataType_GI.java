package model;

public class DataType_GI {
	
	/**
	 *  Occurrence of current GI
	 */
	private static int OCCURRENCE = 0;
	
	/**
	 * The name of the current GI
	 */
	private String name;

	/**
	 * The container that contains GI
	 */
	private DataType_CT container;
	
	/**
	 * A basic GI constructor
	 */
	public DataType_GI(DataType_CT container){
		this.container = container;
		this.name = "GI_" + OCCURRENCE;
		DataType_GI.OCCURRENCE +=1;
	}
	
	public DataType_GI(DataType_CT container, String name){
		this.container = container;
		this.name = name;
		DataType_GI.OCCURRENCE +=1;
	}

	public String getName() {
		return name;
	}
	
	public DataType_CT getContainer() {
		return container;
	}
	
	public Address getAddress() {
		return container.getAddress();
	}
	
}
