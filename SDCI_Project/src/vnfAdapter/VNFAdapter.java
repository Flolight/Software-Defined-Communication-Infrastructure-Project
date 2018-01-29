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
	
	private ArrayList<DataType_GI> sampleData;

	public VNFAdapter() {
		// TODO Auto-generated constructor stub
		try {
			this.sampleData = generateSampleData();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<DataType_GI> generateSampleData() throws UnknownHostException {
		ArrayList<DataType_GI> data = new ArrayList<>();
		
		// sample 1
		Address adr1 = new Address(InetAddress.getByName("10.0.0.4"), 8080);
		DataType_CT ct1 = new DataType_CT(adr1, Status.Working, 20, 20, 2, 2, 5, 1);
		DataType_GI gi1 = new DataType_GI(ct1,"Initial Gateway");
		data.add(gi1);
		
		// sample 2
		Address adr2 = new Address(InetAddress.getByName("10.0.0.5"), 8080);
		DataType_CT ct2 = new DataType_CT(adr2, Status.Working, 15, 15, 2, 2, 5, 1);
		DataType_GI gi2 = new DataType_GI(ct2);
		data.add(gi2);
		
		return data;
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
