package controller;

import model.DataType_GICreationParam;
import model.DataType_Link;
import model.Topology;
import sdnAdapter.SDNAdapter;
import vnfAdapter.VNFAdapter;

/**
 *
 * Controller class for the Controller of the MVC pattern
 *
 */

public class Controller {
	
	// TODO replace with the right path
	private static String IMAGE_GI = "pathToImage";
	
	private final SDNAdapter sdn;
	private final VNFAdapter vnf;
	private final Topology topologyCache;

    private static class SingletonHolder{
        public static final Controller instance = new Controller();
    }

    public static Controller getInstance() {
        return SingletonHolder.instance;
    }

    private Controller(){
    	sdn = new SDNAdapter();
    	vnf = new VNFAdapter();
    	topologyCache = sdn.getTopology();
    	
    	// TODO request update view
    }
    
	public Topology getTopologyCache() {
		return topologyCache;
	}

	public void askGICreation(DataType_GICreationParam giConfig) {
    	vnf.createAndDeployGI(giConfig, IMAGE_GI);
    	// TODO analyze result, update view
    }
    
    public void askGIDeletion(int idGI) {
    	vnf.stopAndDeleteGI(idGI);
    	// TODO
    }
    
    public void askLinkCreation(DataType_Link link) {
    	// TODO
    }
    
    public void askLinkDeletion(DataType_Link link) {
    	// TODO
    }
    
    public void askLinkUpdate(DataType_Link oldLink, DataType_Link newLink) {
    	this.askLinkDeletion(oldLink);
    	this.askLinkCreation(newLink);
    }

}
