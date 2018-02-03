package model;

import java.net.InetAddress;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This is an address to produce much whow beautiful code
 * 
 * @author Florian Clanet
 *
 */
public class Address {
	
	private InetAddress ipAddress;
	
	private int port;
	
	private StringProperty stringPropertyAddress;
	
	
	/**
	 * A basic Address constructor
	 * 
	 * @param ipAddress
	 * @param port
	 */
	public Address(InetAddress ipAddress, int port){
		this.ipAddress = ipAddress;
		this.port = port;
		this.stringPropertyAddress = new SimpleStringProperty(new String(this.toString()));
	}
	
	public Address(InetAddress ipAddress){
		this.ipAddress = ipAddress;
		this.port = 80;
		this.stringPropertyAddress = new SimpleStringProperty(new String(this.toString()));
	}

	public InetAddress getIpAdress() {
		return ipAddress;
	}

	public void setIpAdress(InetAddress ipAdress) {
		this.ipAddress = ipAdress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public StringProperty getStringPropertyAddress() {
		return stringPropertyAddress;
	}

	public void setStringPropertyAddress(StringProperty stringPropertyAddress) {
		this.stringPropertyAddress = stringPropertyAddress;
	}

	@Override
	public String toString(){
		return ipAddress.toString() + ":" + port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (port != other.port)
			return false;
		return true;
	}
	
	
	

}
