package com.bluetooth.anlaiye;
/**
 * @author 高小黑
 *
 * 2016年5月14日下午8:29:56
 */
public class Beacon {
	public String name;
	public String bluetoothAddress;
	public Beacon( String name, String bluetoothAddress){
		this.name=name;
		this.bluetoothAddress=bluetoothAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBluetoothAddress() {
		return bluetoothAddress;
	}
	public void setBluetoothAddress(String bluetoothAddress) {
		this.bluetoothAddress = bluetoothAddress;
	}
	
	
}
