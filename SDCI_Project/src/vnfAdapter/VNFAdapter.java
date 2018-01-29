package vnfAdapter;

import java.util.ArrayList;
import model.DataType_GI;
import model.DataType_GICreationParam;

public class VNFAdapter {
	
	/**
	 * Simulate data from vnf orchestrator
	 */
	private final ArrayList<DataType_GI> sampleData = new ArrayList<>();

	public VNFAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public int createAndDeployGI(DataType_GICreationParam params, String image) {
		// TODO
		return 0;
	}
	
	public void stopAndDeleteGI(int idGI) {
		// TODO
	}
	
	public DataType_GI getGIInfo(int idGI) {
		// TODO
		return this.sampleData.get(idGI);
	}
	
	public ArrayList<DataType_GI> getAllGI(){
		// TODO
		return this.sampleData;
	}
	

}
