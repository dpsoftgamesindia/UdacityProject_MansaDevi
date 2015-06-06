package fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.udacitycourse.udacityproject_mansadevi.MainActivity;
import com.udacitycourse.udacityproject_mansadevi.R;

public class TempleFragment extends Fragment {

	String text = "Mata Mansa Devi temple at Panchkula has been a symbol of Himalayan culture and faith. The shrine, located in the Shivalik foothills of village Bilaspur, is an epitome of tradition of \'SHAKTI\' worship in north India. Himalaya, being the abode of Shiv Shakti, has become the centre of Shakti worship. There are numerous centres of Shakti worship in the vicinity of Panchkula such as Chandi, kalika, Mansa, Bhima etc.\n\nThe present main temple of Shri Mansa Devi in Panchkula was constructed by Maharaja Gopal Singh of Manimajra during the period 1811-1815.At about 200 metres from the main temple is the Patiala temple, constructed by Sh. Karam Singh, the then Maharaja Patiala in the year 1840.\n\nThe temple, which stands a witness to the past of the Shivalik region, is about two hundred years old. The archaeological treasures unearthed from Chandigarh-Panchkula region throw eloquent light on the history and culture of area from the prehistoric to recent times.\n\nThough the onslaught of time and climate has led to the loss of various tangible realities of cultural entities, yet there are certain things that never die out .Mansa Devi Shrine and the Shaktism practiced in the region is one of those sustained realities.";
	WebView wv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_temple, container, false);
		String txt = "<html><body><p align=\"justify\">" + text
				+ "</p></body></html>";

		wv = (WebView) v.findViewById(R.id.wvTempleText);
		ProgressDialog pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Please Wait...");
		pDialog.show();
		wv.loadData(txt, "text/html", "utf-8");
		pDialog.cancel();
		return v;
	}

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static TempleFragment newInstance(int sectionNumber) {
		TempleFragment fragment = new TempleFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public TempleFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}