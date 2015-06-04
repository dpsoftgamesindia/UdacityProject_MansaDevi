package com.udacitycourse.udacityproject_mansadevi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationsListAdapter extends BaseAdapter {

	ArrayList<String> msg = new ArrayList<String>();
	ArrayList<String> dates = new ArrayList<String>();
	Context context;
	LayoutInflater inflater;

	public NotificationsListAdapter(Context context, ArrayList<String> msg,
			ArrayList<String> dates) {
		this.context = context;
		this.msg = msg;
		this.dates = dates;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return msg.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (position == 0) {
			v = inflater.inflate(R.layout.webview_mata, null);
			Display display;
			Point size;
			int width;
			display = ((ActionBarActivity) context).getWindowManager()
					.getDefaultDisplay();
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
				size = new Point();
				display.getSize(size);
				width = size.x;
			} else {
				width = display.getWidth();
			}

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					width, width);
			params.leftMargin = -8;
			params.topMargin = -8;
			params.rightMargin = -8;
			params.bottomMargin = -8;
			WebView wv = (WebView) v.findViewById(R.id.wvHome);
			wv.loadUrl("file:///android_asset/webpage.html");
			wv.getSettings().setJavaScriptEnabled(true);
			wv.setLayoutParams(params);
		} else {
			v = inflater.inflate(R.layout.notification_list_item, null);
			TextView tvMsg = (TextView) v.findViewById(R.id.tvShowNotif);
			TextView tvDate = (TextView) v.findViewById(R.id.tvShowDate);

			tvMsg.setText(msg.get(msg.size() - position));
			tvDate.setText(dates.get(dates.size() - position));
		}
		return v;
	}
}
