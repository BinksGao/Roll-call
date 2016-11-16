package com.bluetooth.anlaiye;

import android.bluetooth.BluetoothDevice;

/**
 * @author 高小黑
 *
 * 2016年5月14日下午8:34:59
 */
public interface OnBluetoothListener {

	public void onSearch(BluetoothDevice device);

	public void onSearchOver();

}
