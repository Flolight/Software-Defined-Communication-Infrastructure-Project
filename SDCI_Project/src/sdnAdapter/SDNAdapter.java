package sdnAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Address;
import model.DataType_CT;
import model.DataType_GF;
import model.DataType_GI;
import model.DataType_Link;
import model.DataType_RoutingRule;
import model.DataType_Server;
import model.GFArray;
import model.GIArray;
import model.LinkArray;
import model.Status;
import model.Topology;

public class SDNAdapter {
	
	private final ArrayList<DataType_RoutingRule> routingRules;
	
	public SDNAdapter() {
		// TODO Auto-generated constructor stub
		this.routingRules = new ArrayList<>();
	}

	public void createRoutingRule(DataType_RoutingRule rule) {
		// TODO test different case and add rules to switch
		this.routingRules.add(rule);
	}
	
	public void deleteRoutingRule(DataType_RoutingRule rule) {
		// TODO test different case and remove rules from switch
		this.routingRules.remove(rule);
	}
	
	public ArrayList<DataType_RoutingRule> getAllRoutingRule() {
		return this.routingRules;
	}
	
	public Topology getTopology() {
		// TODO replace with real structure
		Topology topo = null;
		try {
			topo = this.generateSampleTopology();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return topo;
	}
	
	private Topology generateSampleTopology() throws UnknownHostException {
		DataType_Server server = new DataType_Server(new Address(InetAddress.getByName("10.0.0.240"), 80));
		
		GFArray gfArray = new GFArray();
		DataType_GF gf1 = new DataType_GF(new Address(InetAddress.getByName("10.0.0.1"), 80), Status.Working, "GF Zone 1");
		gfArray.addGF(gf1);
		gfArray.addGF(new DataType_GF(new Address(InetAddress.getByName("10.0.0.2"), 80), Status.Working, "GF Zone 2"));
		gfArray.addGF(new DataType_GF(new Address(InetAddress.getByName("10.0.0.3"), 80), Status.Working, "GF Zone 3"));
		
		GIArray giArray = new GIArray();
		Address adrInit = new Address(InetAddress.getByName("10.0.0.4"), 8080);
		DataType_CT ctInit = new DataType_CT(adrInit, Status.Working, 2, 20, 20, 2, 5, 1);
		DataType_GI giInit = new DataType_GI(ctInit,"Initial Gateway");
		giArray.addGI(giInit);
		
		LinkArray linkArray = new LinkArray();
		linkArray.addLink(new DataType_Link(giInit, gf1));
		
		Topology topology = new Topology(server, gfArray, giArray, linkArray);
		return topology;
	}

}
