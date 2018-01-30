package model;

public class DataType_RoutingRule {
	
	private Address srcIPAddress;
	
	private Address destIPAddress;
	
	private Address adrRoute;
	
	private NetworkPolicy networkPolicy;
	
	public DataType_RoutingRule(Address srcIPAddress, Address destIPAddress, Address adrRoute, NetworkPolicy networkPolicy){
		this.setSrcIPAddress(srcIPAddress);
		this.setDestIPAddress(destIPAddress);
		this.setAdrRoute(adrRoute);
		this.setNetworkPolicy(networkPolicy);
	}

	public Address getSrcIPAddress() {
		return srcIPAddress;
	}

	public void setSrcIPAddress(Address srcIPAddress) {
		this.srcIPAddress = srcIPAddress;
	}

	public Address getDestIPAddress() {
		return destIPAddress;
	}

	public void setDestIPAddress(Address destIPAddress) {
		this.destIPAddress = destIPAddress;
	}

	public Address getAdrRoute() {
		return adrRoute;
	}

	public void setAdrRoute(Address adrRoute) {
		this.adrRoute = adrRoute;
	}

	public NetworkPolicy getNetworkPolicy() {
		return networkPolicy;
	}

	public void setNetworkPolicy(NetworkPolicy networkPolicy) {
		this.networkPolicy = networkPolicy;
	}

}
