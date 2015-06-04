package com.udacitycourse.udacityproject_mansadevi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationListAdapter extends BaseAdapter {

	public Context mContext;
	public String[] arr_txt = { "Home", "Travel", "Temple", "Accomodation",
			"Welfare Schemes", "Constitution Board", "Facilities" };
	public int[] arr_img = { R.drawable.home, R.drawable.travel,
			R.drawable.temple, R.drawable.accomodation, R.drawable.welfare,
			R.drawable.constitution, R.drawable.facilities };
	LayoutInflater inflater;

	public NavigationListAdapter(Context c) {
		mContext = c;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return arr_txt.length;
	}

	@Override
	public Object getItem(int position) {
		return arr_txt[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;

		v = inflater.inflate(R.layout.listview_layout, null);
		TextView mTextView = (TextView) v.findViewById(R.id.text_view);
		ImageView mImageView = (ImageView) v.findViewById(R.id.img_view);
		mTextView.setText(arr_txt[position]);
		mImageView.setImageResource(arr_img[position]);

		return v;
	}

}
