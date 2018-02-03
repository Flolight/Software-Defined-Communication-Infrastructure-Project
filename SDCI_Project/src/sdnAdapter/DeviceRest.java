package sdnAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
 
public class DeviceRest {
	
	private DeviceRest() {
	}
	
	// Network setting
	public static final String STATIC_ENTRY_PUSHER_URL = "http://localhost:8080/wm/staticentrypusher/json";
	
	public static final String SERVER_IPV4 = "10.0.0.2";
	public static final String GINIT_IPV4 = "10.0.0.3";
	public static final String GF1_IPV4 = "10.0.0.4";
	public static final String GF2_IPV4 = "10.0.0.5";
	public static final String GF3_IPV4 = "10.0.0.6";
	public static final String DC_IPV4 = "10.0.0.8";
	public static final String SWITCH1_IPV4 = "10.0.2.15:35398";
	public static final String SWITCH1_ID = "00:00:00:00:00:00:00:01";
	public static final String SWITCH2_IPV4 = "10.0.2.15:35400";
	public static final String SWITCH2_ID = "00:00:00:00:00:00:00:02";
	
	public static String sendPost(String url, JSONObject jsonObj) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		try {
			StringEntity entity = new StringEntity(jsonObj.toString());
			entity.setContentType("application/json");
			entity.setContentEncoding("UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			return EntityUtils.toString(response.getEntity());
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		
		return null;
	}
	
	public static String sendGet(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		try {
			HttpResponse response = client.execute(get);
			
			return EntityUtils.toString(response.getEntity());
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		
		return null;
	}
	
	private static int nameCounter = 0;
	public static JSONObject createRequestObject(String targetSwitchId, String inPort,
			String ipSrc, String ipDst, String setEthDst, String outPort) {
		JSONObject obj = new JSONObject();
		obj.put("switch", targetSwitchId);
		obj.put("name", "flow-"+(nameCounter++));
		obj.put("priority", "36000");
		obj.put("in_port", inPort);
		obj.put("eth_type", "0x0800");
		obj.put("ipv4_src", ipSrc);
		obj.put("ipv4_dst", ipDst);
		obj.put("actions",
				"set_field=eth_dst->"+setEthDst+","
				+ "output="+outPort);
		
		return obj;
	}
	
}
