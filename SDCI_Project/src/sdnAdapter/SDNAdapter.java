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
		String namePrefix = "";
		String altPortGFx = "";
		if (adrSrc.contains(DeviceRest.GF1_IPV4)){
			ipGF = DeviceRest.GF1_IPV4;
			swPortGF = DeviceRest.GF1_SWITCH_PORT;
			namePrefix = "flow-gf1";
			altPortGFx = "18001";
		}else if (adrSrc.contains(DeviceRest.GF2_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
			namePrefix = "flow-gf2";
			altPortGFx = "18002";
		}else if (adrSrc.contains(DeviceRest.GF3_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
			namePrefix = "flow-gf3";
			altPortGFx = "18003";
		}else {
			System.out.println("[SDNAdapter]Unknow GF ipv4:"+adrSrc);
		}
		
		createDCRules(ipGF, swPortGF, namePrefix, altPortGFx, Integer.toString(adrRoute.getPort()));
		
		this.routingRules.add(rule);
	}
	
	public void deleteRoutingRule(DataType_RoutingRule rule) {
		// similar to create, only create different
		String adrSrc = rule.getSrcIPAddress().getIpAdress().toString();
		//Address adrRoute = rule.getAdrRoute();
		String ipGF = "";
		String swPortGF = "";
		String namePrefix = "";
		String altPortGFx = "";
		if (adrSrc.contains(DeviceRest.GF1_IPV4)){
			ipGF = DeviceRest.GF1_IPV4;
			swPortGF = DeviceRest.GF1_SWITCH_PORT;
			namePrefix = "flow-gf1";
			altPortGFx = "18001";
		}else if (adrSrc.contains(DeviceRest.GF2_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
			namePrefix = "flow-gf2";
			altPortGFx = "18002";
		}else if (adrSrc.contains(DeviceRest.GF3_IPV4)){
			ipGF = DeviceRest.GF2_IPV4;
			swPortGF = DeviceRest.GF2_SWITCH_PORT;
			namePrefix = "flow-gf3";
			altPortGFx = "18003";
		}else {
			System.out.println("[SDNAdapter]Unknow GF ipv4:"+adrSrc);
		}
		
		createInitRules(ipGF, swPortGF, namePrefix, altPortGFx);
		
		
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
		// initialization of routing rules
		initRules();
		
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
	
	private void initRules() {
		String data;
		data = DeviceRest.sendGet(DeviceRest.STATIC_ENTRY_PUSHER_LIST_URL);
		System.out.println("before:"+data);
		// drop All
		/**DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, 
				DeviceRest.createDropAllObject("dropall-1", DeviceRest.SWITCH1_ID));
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, 
				DeviceRest.createDropAllObject("dropall-2", DeviceRest.SWITCH2_ID));**/
		
		createInitRules(DeviceRest.GF1_IPV4, DeviceRest.GF1_SWITCH_PORT, "flow-gf1", "18001");
		createInitRules(DeviceRest.GF2_IPV4, DeviceRest.GF2_SWITCH_PORT, "flow-gf2", "18002");
		createInitRules(DeviceRest.GF3_IPV4, DeviceRest.GF3_SWITCH_PORT, "flow-gf3", "18003");
		
		data = DeviceRest.sendGet(DeviceRest.STATIC_ENTRY_PUSHER_LIST_URL);
		System.out.println("after:"+data);
	}
	
	/**
	 * 
	 * @param ipGFx
	 * @param swPortGFx
	 * @param namePrefix
	 * @param altPortGFx 1800x, spatial for each GFx
	 */
	private void createInitRules(String ipGFx, String swPortGFx, String namePrefix, String altPortGFx) {
		// GFx:8080, S:8080 = GFx:8080, GI:8080
		JSONObject obj1 = DeviceRest.createRequestObject(namePrefix+"-1", 1,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, swPortGFx,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.GINIT_IPV4, DeviceRest.DEFAULT_GINIT_PORT, DeviceRest.GINIT_SWITCH_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj1);
		
		// GI:8080, GFx:8080 = GFx:8080, S:8080
		JSONObject obj2 = DeviceRest.createRequestObject(namePrefix+"-2", 1,
				DeviceRest.GINIT_IPV4, DeviceRest.DEFAULT_GINIT_PORT, ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.GINIT_SWITCH_PORT,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, DeviceRest.SERVER_SWITCH_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj2);
		
		// S:8080, GFx:8080 = S:1800x, GI:8080
		JSONObject obj3 = DeviceRest.createRequestObject(namePrefix+"-3", 1,
				DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_SWITCH_PORT,
				DeviceRest.SERVER_IPV4, altPortGFx, DeviceRest.GINIT_IPV4, DeviceRest.DEFAULT_GINIT_PORT, DeviceRest.GINIT_SWITCH_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj3);
		
		// GI:8080, S:1800x = GFx:8080, S:8080
		JSONObject obj4 = DeviceRest.createRequestObject(namePrefix+"-4", 1,
				DeviceRest.GINIT_IPV4, DeviceRest.DEFAULT_GINIT_PORT, DeviceRest.SERVER_IPV4, altPortGFx, DeviceRest.GINIT_SWITCH_PORT,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, DeviceRest.SERVER_SWITCH_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj4);
		
	}
	
	private void createDCRules(String ipGFx, String swPortGFx, String namePrefix, String altPortGFx, String dcBindport) {
		
		// GFx:8080, S:8080 = GFx:8080, DC:2800y
		JSONObject obj1 = DeviceRest.createRequestObject(namePrefix+"-1", 1,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, swPortGFx,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.DC_IPV4, dcBindport, DeviceRest.SWITCH1_TO_SWITCH2_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj1);
		
		// DC:2800y, GFx:8080 = GFx:8080, S:8080
		JSONObject obj2 = DeviceRest.createRequestObject(namePrefix+"-2", 2,
				DeviceRest.DC_IPV4, dcBindport, ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.DC_SWITCH_PORT,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, DeviceRest.SWITCH2_TO_SWITCH1_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj2);
		
		// S:8080, GFx:8080 = S:1800x, DC:2800y
		JSONObject obj3 = DeviceRest.createRequestObject(namePrefix+"-3", 1,
				DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_SWITCH_PORT,
				DeviceRest.SERVER_IPV4, altPortGFx, DeviceRest.DC_IPV4, dcBindport, DeviceRest.SWITCH1_TO_SWITCH2_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj3);
		
		// DC:2800y, S:1800x = GFx:8080, S:8080
		JSONObject obj4 = DeviceRest.createRequestObject(namePrefix+"-4", 2,
				DeviceRest.DC_IPV4, dcBindport, DeviceRest.SERVER_IPV4, altPortGFx, DeviceRest.DC_SWITCH_PORT,
				ipGFx, DeviceRest.DEFAULT_IOT_PORT, DeviceRest.SERVER_IPV4, DeviceRest.DEFAULT_SERVER_PORT, DeviceRest.SWITCH2_TO_SWITCH1_PORT);
		DeviceRest.send(DeviceRest.METHOD_POST, DeviceRest.STATIC_ENTRY_PUSHER_URL, obj4);
		
	}

}
