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

	RelativeLayout.LayoutParams params;

	public NotificationsListAdapter(Context context, ArrayList<String> msg,
									ArrayList<String> dates) {
		this.context = context;
		this.msg = msg;
		this.dates = dates;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

		params = new RelativeLayout.LayoutParams(
				width, width);
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

	class ViewHolder{
		WebView wv;
		TextView tvMsg, tvDate;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		ViewHolder vh = new ViewHolder();
		if (position == 0) {
			v = inflater.inflate(R.layout.webview_mata, null);
			params.leftMargin = -8;
			params.topMargin = -8;
			params.rightMargin = -8;
			params.bottomMargin = -8;
			vh.wv = (WebView) v.findViewById(R.id.wvHome);
			vh.wv.loadUrl("file:///android_asset/webpage.html");
			vh.wv.getSettings().setJavaScriptEnabled(true);
			vh.wv.setLayoutParams(params);
		} else {
			v = inflater.inflate(R.layout.notification_list_item, null);
			vh.tvMsg = (TextView) v.findViewById(R.id.tvShowNotif);
			vh.tvDate = (TextView) v.findViewById(R.id.tvShowDate);

			vh.tvMsg.setText(msg.get(msg.size() - position));
			vh.tvDate.setText(dates.get(dates.size() - position));
		}
		return v;
	}
}
