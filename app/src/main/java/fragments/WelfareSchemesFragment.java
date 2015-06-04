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

public class WelfareSchemesFragment extends Fragment {

	String text = "The board receives various articles in the form of donation or offerings.The articles might be fans,clocks,clothes,utensils etc.These articles are given away to welfare organisations or the needy.Some of these are also gifted to poor girls in their marriages.";
	WebView wv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_welfareschemes, container,
				false);
		String txt = "<html><body><p align=\"justify\">" + text
				+ "</p></body></html>";
		wv = (WebView) v.findViewById(R.id.wvWelfareText);
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
	public static WelfareSchemesFragment newInstance(int sectionNumber) {
		WelfareSchemesFragment fragment = new WelfareSchemesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public WelfareSchemesFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}