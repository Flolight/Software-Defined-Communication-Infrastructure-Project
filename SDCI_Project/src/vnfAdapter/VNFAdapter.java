package vnfAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Address;
import model.DataType_CT;
import model.DataType_GI;
import model.DataType_GICreationParam;
import model.Status;
import sdnAdapter.DeviceRest;

public class VNFAdapter {
	
	// TODO implements the real vnf orchestrator
	// TODO add failure return values and tests
	
	/**
	 * Simulate data from vnf orchestrator
	 */
	private final ArrayList<DataType_GI> sampleData;
	private final VNFDocker vnfDocker;

	public VNFAdapter() {
		sampleData = new ArrayList<>();
		vnfDocker = new VNFDocker();
	}
	
	public void addGInit(DataType_GI gInit) {
		sampleData.add(gInit);
	}
	
	/**
	 * 
	 * @param params
	 * @param image
	 * @return -1 if failure, idGI if succeed
	 */
	public int createAndDeployGI(DataType_GICreationParam params, String image) {
		int idGI = -1;
		
		idGI = createGI(params);
		if(idGI<0){return idGI;}
		deployGI(idGI, image);
		
		return idGI;
	}
	
	private static int countBindPort = 28001;
	private int createGI(DataType_GICreationParam params) {
		// String containerId = vnfDocker.createContainer(80, 8080);
		// DataType_CT ct = //Analyze containerInfo
		// simulate creation GI
		int idGI = -1;
		if(HasSameName(params.getName(),this.sampleData)){
			return idGI;
		}
		try 
		{
			Address adr = new Address(InetAddress.getByName(DeviceRest.DC_IPV4), countBindPort);
			DataType_CT ct = new DataType_CT(adr, Status.Creating, params.getNbCPU(), params.getMaxRAM(), params.getMaxDisk());
			DataType_GI gi = new DataType_GI(ct, params.getName());
			sampleData.add(gi);
			// implements
			vnfDocker.createContainer(VNFDocker.DC_PORT, countBindPort);
			countBindPort ++;
			// end
			ct.setStatus(Status.Idle);
			idGI = sampleData.indexOf(gi);
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return idGI;
	}
	
	private boolean HasSameName(String name, ArrayList<DataType_GI> list){
		boolean found = false;
		for (DataType_GI currentGI: list) {
		    if (currentGI.getName().equals(name)) {
		    	found = true;
		    }
		};
		return found;
	}
	
	private void deployGI(int idGI, String image) {
		sampleData.get(idGI).getContainer().setStatus(Status.Working);
	}
	

	/**
	 * 
	 * @param idGI
	 * @return the object removed
	 */
	public DataType_GI stopAndDeleteGI(int idGI) {
		DataType_GI value = null;
		if (stopGI(idGI) >= 0) {
			value = deleteGI(idGI);
		}
		return value;
	}
	
	private int stopGI(int idGI) {
		sampleData.get(idGI).getContainer().setStatus(Status.Idle);
		return 1;
	}
	
	private DataType_GI deleteGI(int idGI) {
		sampleData.get(idGI).getContainer().setStatus(Status.Deleting);
		return sampleData.remove(idGI);
	}
	
	public DataType_GI getGIInfo(int idGI) {
		return this.sampleData.get(idGI);
	}
	
	public ArrayList<DataType_GI> getAllGI(){
		return this.sampleData;
	}
	

}
