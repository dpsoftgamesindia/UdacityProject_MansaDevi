package helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class CheckConnection {

	Context context;

	public CheckConnection(Context context) {
		this.context = context;
	}

	public boolean isConnected() {
		ConnectivityManager connectivityManager;
		NetworkInfo wifiInfo, mobileInfo;
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		mobileInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifiInfo.isConnected() || mobileInfo.isConnected())
			return true;
		else
			return false;
	}

	public boolean isGpsOn() {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}

	public void showGPSErrorDialog() {
		new AlertDialog.Builder(context)
				.setTitle("Enable GPS!")
				.setMessage(
						"There is problem fetching your location. Please turn on your gps. And Check the High Accuracy Option")
				.setPositiveButton("Settings",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent viewIntent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								context.startActivity(viewIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						}).setCancelable(false).show();
	}

	public void showConnectionErrorDialog() {
		new AlertDialog.Builder(context)
				.setTitle("Enable Connection!")
				.setMessage(
						"There is no internet connection. Please enable your connection.")
				.setPositiveButton("Settings",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								context.startActivity(new Intent(
										Settings.ACTION_SETTINGS));
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						}).setCancelable(false).show();
	}
}
