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
		this.setName("GI_" + OCCURRENCE);
		DataType_GI.OCCURRENCE +=1;
	}
	
	public DataType_GI(DataType_CT container, String name){
		this.container = container;
		this.setName(name);
		DataType_GI.OCCURRENCE +=1;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DataType_CT getContainer() {
		return container;
	}
	
	public Address getAddress() {
		return container.getAddress();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((container == null) ? 0 : container.hashCode());
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
		DataType_GI other = (DataType_GI) obj;
		if (container == null) {
			if (other.container != null)
				return false;
		} else if (!container.equals(other.container))
			return false;
		return true;
	}
	
	
}
