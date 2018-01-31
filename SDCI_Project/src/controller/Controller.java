package controller;

import model.Address;
import model.DataType_GI;
import model.DataType_GICreationParam;
import model.DataType_Link;
import model.DataType_RoutingRule;
import model.NetworkPolicy;
import model.Topology;
import sdnAdapter.SDNAdapter;
import vnfAdapter.VNFAdapter;

/**
 *
 * Controller class for the Controller of the MVC pattern
 *
 */

public class Controller {
	
	// TODO handle failure case for links
	
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
    	// add Ginit to vnf list, Ginit must have id=0
    	vnf.addGInit(topologyCache.getGIArray().getGIs().get(0));
    }
    
	public Topology getTopologyCache() {
		return topologyCache;
	}

	public void askGICreation(DataType_GICreationParam giConfig) {
    	int idGI = vnf.createAndDeployGI(giConfig, IMAGE_GI);
    	if (idGI >= 0) {
    		// update only if succeed
    		topologyCache.getGIArray().addGI(vnf.getGIInfo(idGI));
    	}
    }
    
    public void askGIDeletion(int idGI) {
    	DataType_GI value = vnf.stopAndDeleteGI(idGI);
    	if (value != null) {
    		// update only if succeed
    		topologyCache.getGIArray().deleteGI(value);
    	}
    }
    
    public void askLinkCreation(DataType_Link link) {
    	if (link == null || link.getGf() == null || link.getGi() == null) {
    		return;
    	}
    	
    	Address adrGF = link.getGf().getAddress();
    	Address adrGI = link.getGi().getAddress();
    	// suppose the gInit is always at the index 0
    	Address adrGInit = topologyCache.getGIArray().getGIInfo(0).getAddress();
    	Address adrServer = topologyCache.getServer().getAddress();
    	
    	sdn.deleteRoutingRule(new DataType_RoutingRule(adrGF, adrServer, adrGInit, NetworkPolicy.FORWARD));
    	sdn.createRoutingRule(new DataType_RoutingRule(adrGF, adrServer, adrGI, NetworkPolicy.FORWARD));
    	
    	// update topology
    	topologyCache.getLinkArray().addLink(link);
    }
    
    public void askLinkDeletion(DataType_Link link) {
    	if (link == null || link.getGf() == null || link.getGi() == null) {
    		return;
    	}
    	
    	Address adrGF = link.getGf().getAddress();
    	Address adrGI = link.getGi().getAddress();
    	// suppose the gInit is always at the index 0
    	Address adrGInit = topologyCache.getGIArray().getGIInfo(0).getAddress();
    	Address adrServer = topologyCache.getServer().getAddress();
    	
    	sdn.deleteRoutingRule(new DataType_RoutingRule(adrGF, adrServer, adrGI, NetworkPolicy.FORWARD));
    	sdn.createRoutingRule(new DataType_RoutingRule(adrGF, adrServer, adrGInit, NetworkPolicy.FORWARD));
    	
    	// update topology
    	topologyCache.getLinkArray().deleteLink(link);
    }
    
    public void askLinkUpdate(DataType_Link oldLink, DataType_Link newLink) {
    	if (oldLink == null || oldLink.getGf() == null || oldLink.getGi() == null) {
    		return;
    	}
    	if (newLink == null || newLink.getGf() == null || newLink.getGi() == null) {
    		return;
    	}
    	
    	Address adrGFold = oldLink.getGf().getAddress();
    	Address adrGIold = oldLink.getGi().getAddress();
    	Address adrGFnew = newLink.getGf().getAddress();
    	Address adrGInew = newLink.getGi().getAddress();
    	Address adrServer = topologyCache.getServer().getAddress();
    	
    	sdn.deleteRoutingRule(new DataType_RoutingRule(adrGFold, adrServer, adrGIold, NetworkPolicy.FORWARD));
    	sdn.createRoutingRule(new DataType_RoutingRule(adrGFnew, adrServer, adrGInew, NetworkPolicy.FORWARD));
    	
    	// update topology
    	topologyCache.getLinkArray().deleteLink(oldLink);
    	topologyCache.getLinkArray().addLink(newLink);
    }

}
