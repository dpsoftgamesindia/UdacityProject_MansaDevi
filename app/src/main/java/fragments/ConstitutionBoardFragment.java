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

public class ConstitutionBoardFragment extends Fragment {

	String text = "The temple complex and its environment are presently looked after by Shri Mata Mansa Devi Shrine Board (SMMDSB) Panchkula .In view of the popularity of the temple for its mythological and historical significance and lakhs of devotees thronging the complex, Haryana Government ,by an enactment (Haryana Act No. 14 of 1991),christened as Shri Mata Mansa Devi Shrine Act 1991 , took over the control of this temple to provide for better infrastructure development, management, administration and governance of Shri Mata Mansa Devi Shrine and its endowments including lands and buildings attached to the Shrine. A Shrine Board with Chief Minister of Haryana as Chairman was constituted for running of the Temple and preserving the heritage of the region.\n\nFor this purpose government has constituted a Board under section 4 of the Act ibid as follows: -\n\n(a)Chief Minister, Haryana, shall be the Chairman\n(a)Minister In charge Local govt. Department shall be the Vice Chairman\n(b)Secretary to Govt. Haryana, Local Govt. Department whether designated as Financial Commissioner Local Govt. or Commissioner Local Govt. , as the case may be shall be the ex-Officio member.(c)Deputy Commissioner, \nPanchkula shall be the ex-officio Member-Secretary.(d)Nine persons to be nominated by the Govt. as members in the following manner\n\t\t\t(i) Two persons who, in the opinion of the Govt. have distinguished themselves in the service of Hindu religion or culture.\n\t\t\t(ii)Two women , who in the opinion of the Govt. have distinguished themselves in the service of Hindu religion, culture or social work, especially in regard to advancement of women.\n\t\t\t(iii)Three persons , out of persons who have distinguished themselves in administration, legal affairs or financial matters.\n\t\t\t(iv)Two eminent Hindus of the State of Haryana\n";
	WebView wv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_constitutionboard,
				container, false);
		String txt = "<html><body><p align=\"justify\">" + text
				+ "</p></body></html>";
		wv = (WebView) v.findViewById(R.id.wvConstiText);
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
	public static ConstitutionBoardFragment newInstance(int sectionNumber) {
		ConstitutionBoardFragment fragment = new ConstitutionBoardFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ConstitutionBoardFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}