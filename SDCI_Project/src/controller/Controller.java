package controller;

import model.Topology;
import sdnAdapter.SDNAdapter;
import vnfAdapter.VNFAdapter;

/**
 *
 * Controller class for the Controller of the MVC pattern
 *
 */

public class Controller {
	
	private static SDNAdapter sdn;
	private static VNFAdapter vnf;
	private static Topology topology;

    private static class SingletonHolder{
        public static final Controller instance = new Controller();
    }

    public static Controller getInstance() {
        return SingletonHolder.instance;
    }

    private Controller(){
    	sdn = new SDNAdapter();
    	vnf = new VNFAdapter();
    	
    	
    }
    
    
    
    

}
