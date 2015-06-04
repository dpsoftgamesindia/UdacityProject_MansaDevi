package com.udacitycourse.udacityproject_mansadevi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Receiver extends ParsePushBroadcastReceiver {

	int date, month, year, hh, mm;
	MyDataBase db;

	public ArrayList<String> arr_Notification = new ArrayList<String>();
	public ArrayList<Integer> arr_Date = new ArrayList<Integer>();

	@Override
	protected void onPushReceive(Context context, Intent intent) {
		super.onPushReceive(context, intent);
		String message = intent != null ? intent
				.getStringExtra("com.parse.Data") : "";
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String msg = "";
		try {
			msg = jObject.getString("alert");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		date = cal.get(Calendar.DATE);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		hh = cal.get(Calendar.HOUR_OF_DAY);
		mm = cal.get(Calendar.MINUTE);

		db = new MyDataBase(context);
		db.open();
		db.insert(msg, date + "/" + month + "/" + year + " " + hh + ":" + mm);
		db.close();

	}

	@Override
	public void onPushOpen(Context context, Intent intent) {
		Log.e("Push", "Clicked");
		Intent i = new Intent(context, MainActivity.class);
		// i.putExtras(intent.getExtras());
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}