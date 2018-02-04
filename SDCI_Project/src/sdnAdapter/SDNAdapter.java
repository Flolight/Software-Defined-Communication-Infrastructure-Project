package sdnAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONObject;

import model.Address;
import model.DataType_CT;
import model.DataType_GF;
import model.DataType_GI;
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
		this.routingRules = new ArrayList<>();
	}

	public void createRoutingRule(DataType_RoutingRule rule) {
		String adrSrc = rule.getSrcIPAddress().getIpAdress().toString();
		Address adrRoute = rule.getAdrRoute();
		String ipGF = "";
		String swPortGF = "";
		if (adrSrc.contains(DeviceRest.GF1_IPV4)){
			ipGF = DeviceRest.GF1_IPV4;
			swPortGF = DeviceRest.GF1_SWITCH_PORT;
		}else if (adrSrc.contains(DeviceRest.GF2_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
		}else if (adrSrc.contains(DeviceRest.GF3_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
		}else {
			System.out.println("[SDNAdapter]Unknow GF ipv4:"+adrSrc);
		}
		
		
		if (adrRoute.getIpAdress().toString().equals(DeviceRest.GINIT_IPV4)) {
			// if adrRoute == Gateway Init
			// ------ GF to Server ------
			// for sw1, flow from GFx goes to GI
			JSONObject obj1 = DeviceRest.createRequestObject(1, swPortGF, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.GINIT_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj1);
			// for sw1, flow from GI goes to Server
			JSONObject obj2 = DeviceRest.createRequestObject(1, DeviceRest.GINIT_SWITCH_PORT, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.SERVER_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj2);
			
			// ------ Server to GF ------
			// for sw1, flow from Server goes to GI
			JSONObject obj3 = DeviceRest.createRequestObject(1, DeviceRest.SERVER_SWITCH_PORT, DeviceRest.SERVER_IPV4,
					ipGF, DeviceRest.GINIT_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj3);
			// for sw1, flow from GI goes to GFx
			JSONObject obj4 = DeviceRest.createRequestObject(1, DeviceRest.GINIT_SWITCH_PORT, DeviceRest.SERVER_IPV4,
					ipGF, swPortGF);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj4);
			
		}else {
			// if adrRoute in DC
			// ------ GF to Server ------
			// for sw1, flow from GFx goes to SW2
			JSONObject obj1 = DeviceRest.createRequestObject(1, swPortGF, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.SWITCH1_TO_SWITCH2_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj1);
			
			// for sw2, flow from SW1 goes to DC; DST became GI
			JSONObject obj2 = DeviceRest.createRequestObject(2,
					DeviceRest.SWITCH2_TO_SWITCH1_PORT, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.DC_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj2);
			
			// for sw2, flow from GI goes to SW1; DST became Server
			JSONObject obj3 = DeviceRest.createRequestObject(2,
					DeviceRest.DC_SWITCH_PORT, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.SWITCH2_TO_SWITCH1_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj3);
			
			// for sw1, flow from SW2 goes to Server
			JSONObject obj4 = DeviceRest.createRequestObject(1,
					DeviceRest.SWITCH1_TO_SWITCH2_PORT, ipGF,
					DeviceRest.SERVER_IPV4, DeviceRest.SERVER_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj4);
			
			// ------ Server to GF ------
			// for sw1, flow from Server goes to SW2
			JSONObject obj5 = DeviceRest.createRequestObject(1, swPortGF, DeviceRest.SERVER_IPV4,
					ipGF, DeviceRest.SWITCH1_TO_SWITCH2_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST,DeviceRest.STATIC_ENTRY_PUSHER_URL, obj5);
			
			// for sw2, flow from SW1 goes to DC; DST became GI
			JSONObject obj6 = DeviceRest.createRequestObject(2,
					DeviceRest.SWITCH2_TO_SWITCH1_PORT, DeviceRest.SERVER_IPV4,
					ipGF, DeviceRest.DC_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj6);
			
			// for sw2, flow from DC goes to SW1; DST became GFx
			JSONObject obj7 = DeviceRest.createRequestObject(2,
					DeviceRest.DC_SWITCH_PORT, DeviceRest.SERVER_IPV4,
					ipGF, DeviceRest.SWITCH2_TO_SWITCH1_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj7);
			
			// for sw1, flow from SW2 goes to GFx
			JSONObject obj8 = DeviceRest.createRequestObject(1,
					DeviceRest.SWITCH1_TO_SWITCH2_PORT, DeviceRest.SERVER_IPV4,
					ipGF, DeviceRest.SERVER_SWITCH_PORT);
			DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj8);
		}
		
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
		DataType_Server server = new DataType_Server(new Address(InetAddress.getByName(DeviceRest.SERVER_IPV4)));
		
		GFArray gfArray = new GFArray();
		gfArray.addGF(new DataType_GF(new Address(InetAddress.getByName(DeviceRest.GF1_IPV4)), Status.Working, "GF Zone 1"));
		gfArray.addGF(new DataType_GF(new Address(InetAddress.getByName(DeviceRest.GF2_IPV4)), Status.Working, "GF Zone 2"));
		gfArray.addGF(new DataType_GF(new Address(InetAddress.getByName(DeviceRest.GF3_IPV4)), Status.Working, "GF Zone 3"));
		
		GIArray giArray = new GIArray();
		Address adrInit = new Address(InetAddress.getByName(DeviceRest.GINIT_IPV4));
		DataType_CT ctInit = new DataType_CT(adrInit, Status.Working, 2, 20, 20, 2, 5, 1);
		DataType_GI giInit = new DataType_GI(ctInit,"Initial Gateway");
		giArray.addGI(giInit);
		
		LinkArray linkArray = new LinkArray();
		//linkArray.addLink(new DataType_Link(giInit, gf1));
		
		Topology topology = new Topology(server, gfArray, giArray, linkArray);
		return topology;
	}

}
