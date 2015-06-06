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

public class FacilitiesFragment extends Fragment {

	String text1 = "The temple remains open from 4-00 A.M. to 10-00 P.M. in the summer and from 5-00 A.M. to 9-00 P.M. in the winter. Anybody can have darshan during this period by entering from main gate. There is a separate gate for exit. Red Stone Pavement of size 75íX105í has been provided in form of Shakti Dhawaj where devotees wait for their turn. Brass railing from Shakti Dhwaj to Ardh Mandap has been laid so that devotees may enter the temple in two queues and have easy Darshan without any difficulty of rush. The devotees are allowed to bring packed parshad in the mandir. The Parshad so offered by the devotees is placed in the feet of the deity and returned to the devotees. Offering in kind is poured in the Dan Patar placed in front of each temple.";
	String text2 = "Three bhandaras (voluntary free kitchen) are functioning in the shrine complex. The Board does not run any Bhandara on its own. However three Bhandaras are being run in the temple complex by three registered bodies. These Bhandaras provide food to the pilgrims without any charge. During Navratra Melas, more Bhandaras are arranged by private parties with the permission of the Board.";
	String text3 = "For the benefit of the pilgrims visiting, Shri Mata Mansa Devi Shrine Board operates a dispensary at the shrine complex itself.  A general hospital is also situated in Sector 6 , Panchkula.";
	String text4 = "The nearest Railway Reservation Counters are at Panchkula- Chandigarh railway station and Post Office Sector 8 Panchkula.";

	WebView wv1, wv2, wv3, wv4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_facilities, container,
				false);
		wv1 = (WebView) v.findViewById(R.id.wvFacility1);
		wv2 = (WebView) v.findViewById(R.id.wvFacility2);
		wv3 = (WebView) v.findViewById(R.id.wvFacility3);
		wv4 = (WebView) v.findViewById(R.id.wvFacility4);
		String txt1 = "<html><body><p align=\"justify\">" + text1
				+ "</p></body></html>";
		String txt2 = "<html><body><p align=\"justify\">" + text2
				+ "</p></body></html>";
		String txt3 = "<html><body><p align=\"justify\">" + text3
				+ "</p></body></html>";
		String txt4 = "<html><body><p align=\"justify\">" + text4
				+ "</p></body></html>";
		ProgressDialog pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Please Wait...");
		pDialog.show();
		wv1.loadData(txt1, "text/html", "utf-8");
		wv2.loadData(txt2, "text/html", "utf-8");
		wv3.loadData(txt3, "text/html", "utf-8");
		wv4.loadData(txt4, "text/html", "utf-8");
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
	public static FacilitiesFragment newInstance(int sectionNumber) {
		FacilitiesFragment fragment = new FacilitiesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public FacilitiesFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}