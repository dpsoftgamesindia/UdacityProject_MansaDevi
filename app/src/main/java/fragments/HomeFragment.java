package fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.udacitycourse.udacityproject_mansadevi.MainActivity;
import com.udacitycourse.udacityproject_mansadevi.MyDataBase;
import com.udacitycourse.udacityproject_mansadevi.NotificationsListAdapter;
import com.udacitycourse.udacityproject_mansadevi.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

	ListView lvNotifs;
	MyDataBase db;

	ArrayList<Integer> id = new ArrayList<Integer>();
	ArrayList<String> msg = new ArrayList<String>();
	ArrayList<String> date = new ArrayList<String>();

	NotificationsListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		db = new MyDataBase(getActivity());

		fetchDatabase();

		lvNotifs = (ListView) v.findViewById(R.id.lvNotifications);
		adapter = new NotificationsListAdapter(getActivity(), msg, date);
		lvNotifs.setAdapter(adapter);
		lvNotifs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				deleteMsg(pos);
			}
		});

		return v;
	}

	public void deleteMsg(final int pos) {
		new AlertDialog.Builder(getActivity())
				.setTitle("Notification")
				.setMessage(
						msg.get(id.size() - pos) + "\n" + "      ~"
								+ date.get(id.size() - pos))
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				})
				.setNegativeButton("Delete",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								db.open();
								db.deleteEmpList(id.get(id.size() - pos));
								db.close();
								fetchDatabase();
								adapter.notifyDataSetChanged();
							}
						}).show();
	}

	private void fetchDatabase() {
		db.open();
		Cursor cur = db.getEmpValues();
		id.clear();
		msg.clear();
		date.clear();
		cur.moveToFirst();
		while (!cur.isAfterLast()) {
			id.add(cur.getInt(cur.getColumnIndex(MyDataBase.ColID)));
			msg.add(cur.getString(cur
					.getColumnIndex(MyDataBase.ColNotification)));
			date.add(cur.getString(cur.getColumnIndex(MyDataBase.ColDate)));
			cur.moveToNext();
		}
		db.close();
	}

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static HomeFragment newInstance(int sectionNumber) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public HomeFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
