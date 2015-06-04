package com.udacitycourse.udacityproject_mansadevi;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ParseApplication extends Application {

	String APPLICATION_ID = "FatzgYWkQeZAHxFLsQr8lDxh9dn6deiZq1R7ydrA";
	String CLIENT_ID = "eL0TcUDf5mGFyzXr4uOEjlmwX9lfsBkHPJISNH95";

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, APPLICATION_ID, CLIENT_ID);
		// for push notifications
		ParsePush.subscribeInBackground("MansaDeviPush", new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Log.d("com.parse.push",
							"successfully subscribed to the broadcast channel.");
				} else {
					Log.e("com.parse.push", "failed to subscribe for push", e);
				}
			}
		});

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove
		// this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}
}
