package com.bluetooth.anlaiye;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author 高小黑
 *
 * 2016年5月14日下午8:32:42
 */
public class BluetoothBroadcastReceiver extends BroadcastReceiver{

	private static final String TAG = "BluetoothBroadcastReceiver";
	public static OnBluetoothListener onBluetoothListener;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自动生成的方法存根
		String action = intent.getAction();
		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			BluetoothDevice device = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			BluetoothBroadcastReceiver.onBluetoothListener.onSearch(device);
		} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
			BluetoothBroadcastReceiver.onBluetoothListener.onSearchOver();
		}
	}

}
