package model;

public class DataType_GICreationParam {

	private int nbCPU;
	
	private int maxRAM;
	
	private int maxDisk;
	
	private String name;
	
	public DataType_GICreationParam(int nbCPU, int maxRAM, int maxDisk, String name){
		this.setNbCPU(nbCPU);
		this.setMaxRAM(maxRAM);
		this.setMaxDisk(maxDisk);
		this.setName(name);
	}

	public int getNbCPU() {
		return nbCPU;
	}

	private void setNbCPU(int nbCPU) {
		this.nbCPU = nbCPU;
	}

	public int getMaxRAM() {
		return maxRAM;
	}

	private void setMaxRAM(int maxRAM) {
		this.maxRAM = maxRAM;
	}

	public int getMaxDisk() {
		return maxDisk;
	}

	private void setMaxDisk(int maxDisk) {
		this.maxDisk = maxDisk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
