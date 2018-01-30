package vnfAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Address;
import model.DataType_CT;
import model.DataType_GI;
import model.DataType_GICreationParam;
import model.Status;

public class VNFAdapter {
	
	// TODO implements the real vnf orchestrator
	// TODO add failure return values and tests
	
	/**
	 * Simulate data from vnf orchestrator
	 */
	private final ArrayList<DataType_GI> sampleData;

	public VNFAdapter() {
		// TODO Auto-generated constructor stub
		sampleData = new ArrayList<>();
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
		int idGI = createGI(params);
		deployGI(idGI, image);
		
		return idGI;
	}
	
	private static int countIP = 5;
	private int createGI(DataType_GICreationParam params) {
		// simulate creation GI
		int idGI = -1;
		try {
			Address adr = new Address(InetAddress.getByName("10.0.0." + countIP), 8080);
			countIP += 1;
			DataType_CT ct = new DataType_CT(adr, Status.Creating, params.getNbCPU(), params.getMaxRAM(), params.getMaxDisk());
			DataType_GI gi = new DataType_GI(ct);
			sampleData.add(gi);
			ct.setStatus(Status.Idle);
			idGI = sampleData.indexOf(gi);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idGI;
	}
	
	private void deployGI(int idGI, String image) {
		sampleData.get(idGI).getContainer().setStatus(Status.Working);
	}
	

	/**
	 * 
	 * @param idGI
	 * @return -1 if failure, >0 if succeed
	 */
	public int stopAndDeleteGI(int idGI) {
		int value = -1;
		if (stopGI(idGI) >= 0) {
			value = deleteGI(idGI);
		}
		return value;
	}
	
	private int stopGI(int idGI) {
		sampleData.get(idGI).getContainer().setStatus(Status.Idle);
		return 1;
	}
	
	private int deleteGI(int idGI) {
		sampleData.get(idGI).getContainer().setStatus(Status.Deleting);
		sampleData.remove(idGI);
		return 1;
	}
	
	public DataType_GI getGIInfo(int idGI) {
		return this.sampleData.get(idGI);
	}
	
	public ArrayList<DataType_GI> getAllGI(){
		return this.sampleData;
	}
	

}
