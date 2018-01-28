package model;

public class DataType_GICreationParam {

	private int nbCPU;
	
	private int maxRAM;
	
	private int maxDisk;
	
	public DataType_GICreationParam(int nbCPU, int maxRAM, int maxDisk){
		this.setNbCPU(nbCPU);
		this.setMaxRAM(maxRAM);
		this.setMaxDisk(maxDisk);
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
}
