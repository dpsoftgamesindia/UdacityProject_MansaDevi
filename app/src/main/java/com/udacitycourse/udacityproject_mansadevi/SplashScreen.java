package com.udacitycourse.udacityproject_mansadevi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.VideoView;

public class SplashScreen extends ActionBarActivity {

	private VideoView myVideoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		myVideoView = (VideoView) findViewById(R.id.videoView);
		myVideoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.vid));
		myVideoView.start();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(i);
				finish();
			}
		}, 3000);
	}
}
