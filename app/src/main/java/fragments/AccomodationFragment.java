package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.udacitycourse.udacityproject_mansadevi.MainActivity;
import com.udacitycourse.udacityproject_mansadevi.R;

public class AccomodationFragment extends Fragment {

	String text = "The Mansa Devi shrine has two dharmshalas ñ one of 30 rooms and other of 15.The one built by board, Lajwanti Dharmshala ,has 15 rooms with modern facilities such as attached toilets ,geysers and air conditioners .Each room has been provided with double bed with mattresses ,pillows ,bed sheets, blankets ,one table ,two chairs and an almirah .A hall is also available for the pilgrims. For the proper upkeep of this Dharamshala a nominal charge of Rs. 200/- per day is charged.\n\nThe Haryana Tourism Corporation has also constructed a Yatrika called ìJatayuî which is at a distance of 50 yards from the Temple and provides dormitory type accommodation and facilities of restaurant to the pilgrims. Each room has been provided with attached toilet and each room has four beds.";
	WebView wv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_accomodation, container,
				false);
		String txt = "<html><body><p align=\"justify\">" + text
				+ "</p></body></html>";
		wv = (WebView) v.findViewById(R.id.wvAccomText);
		wv.loadData(txt, "text/html", "utf-8");
		return v;
	}

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static AccomodationFragment newInstance(int sectionNumber) {
		AccomodationFragment fragment = new AccomodationFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public AccomodationFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
