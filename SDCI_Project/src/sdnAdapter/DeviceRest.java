package sdnAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
 
public class DeviceRest {
	
	private DeviceRest() {
	}
	
	// Network setting
	public static final String LOCAL_IPV4 = "10.211.55.8";
	public static final String STATIC_ENTRY_PUSHER_URL = "http://"+LOCAL_IPV4+":8080/wm/staticentrypusher/json";
	public static final String STATIC_ENTRY_PUSHER_LIST_URL = "http://"+LOCAL_IPV4+":8080/wm/staticentrypusher/list/all/json";
	public static final String DEFAULT_SERVER_PORT = "8080";
	public static final String DEFAULT_IOT_PORT = "8080";
	public static final String DEFAULT_GINIT_PORT = "8080";
	
	public static final String SERVER_IPV4 = "10.0.0.2";
	public static final String SERVER_SWITCH_PORT = "5";
	public static final String GINIT_IPV4 = "10.0.0.3";
	public static final String GINIT_SWITCH_PORT = "4";
	public static final String GF1_IPV4 = "10.0.0.4";
	public static final String GF1_SWITCH_PORT = "1";
	public static final String GF2_IPV4 = "10.0.0.5";
	public static final String GF2_SWITCH_PORT = "2";
	public static final String GF3_IPV4 = "10.0.0.6";
	public static final String GF3_SWITCH_PORT = "3";
	
	public static final String DC_IPV4 = "10.0.0.8";
	public static final String DC_SWITCH_PORT = "2";
	
	public static final String SWITCH1_IPV4 = LOCAL_IPV4+"35398";
	public static final String SWITCH1_ID = "00:00:00:00:00:00:00:01";
	public static final String SWITCH1_TO_SWITCH2_PORT = "6";
	
	public static final String SWITCH2_IPV4 = LOCAL_IPV4+"35400";
	public static final String SWITCH2_ID = "00:00:00:00:00:00:00:02";
	public static final String SWITCH2_TO_SWITCH1_PORT = "1";
	
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public static final String METHOD_DELETE = "DELETE";
	
	/**
	 * 
	 * @param method
	 * @param url
	 * @param jsonObj null if not POST
	 * @return
	 */
	public static String send(String method, String url, JSONObject jsonObj) {
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpRequestBase request;
		if (method.equalsIgnoreCase(METHOD_POST)) {
			request = new HttpPost(url);
		}else if (method.equalsIgnoreCase(METHOD_GET)) {
			request = new HttpGet(url);
		}else if (method.equalsIgnoreCase(METHOD_DELETE)) {
			request = new HttpDelete(url);
		}else {
			System.out.println("[DeviceRest]Unknown method");
			return null;
		}
		
		
		String data = null;
		try {
			if (jsonObj != null) {
				// for POST only
				StringEntity entity = new StringEntity(jsonObj.toString());
				entity.setContentType("application/json");
				entity.setContentEncoding("UTF-8");
				((HttpEntityEnclosingRequestBase) request).setEntity(entity);
			}
			
			HttpResponse response = client.execute(request);
			data = EntityUtils.toString(response.getEntity());
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		return data;
	}
	
	public static String sendGet(String url) {
		return DeviceRest.send(METHOD_GET, url, null);
	}
	
	public static JSONObject createRequestObject(String name, int switchNumber,
			String ipSrc, String portSrc, String ipDst, String portDst, String inputSwPort,
			String setIpSrc, String setPortSrc, String setIpDst, String setPortDst, String outputSwPort) {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("cookie", "0");
		obj.put("priority", "32767");
		obj.put("eth_type", "0x0800");
		obj.put("active", "true");
		obj.put("ip_proto", "0x11"); //TCP 0x06, UDP 0x11
		
		String targetSwitchId;
		switch (switchNumber) {
		case 1:
			targetSwitchId = DeviceRest.SWITCH1_ID;
			break;
		case 2:
			targetSwitchId = DeviceRest.SWITCH2_ID;
			break;
		default:
			targetSwitchId = "";
		}
		obj.put("switch", targetSwitchId);
		
		obj.put("ipv4_src", ipSrc);
		//obj.put("udp_src", portSrc);
		obj.put("ipv4_dst", ipDst);
		//obj.put("udp_dst", portDst);
		obj.put("in_port", inputSwPort);
		
		obj.put("actions", "set_field=ipv4_src->" + setIpSrc + ","
				//+ "set_field=udp_src->" + setPortSrc + ","
				+ "set_field=ipv4_dst->" + setIpDst + ","
				//+ "set_field=udp_dst->"+setPortDst + ","
				+ "output="+outputSwPort);
		
		return obj;
	}
	
	public static JSONObject createDropAllObject(String name, String swID) {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("switch", swID);
		obj.put("cookie", "0");
		obj.put("priority", "30000");
		obj.put("eth_type", "0x0800");
		obj.put("ipv4_src", "10.0.0.0/255.0.0.0");
		obj.put("ipv4_dst", "10.0.0.0/255.0.0.0");
		obj.put("active", "true");
		//obj.put("actions", "");
		
		return obj;
	}
}
