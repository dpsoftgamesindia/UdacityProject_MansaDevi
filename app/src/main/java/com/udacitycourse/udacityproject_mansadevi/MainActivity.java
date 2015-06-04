package com.udacitycourse.udacityproject_mansadevi;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import fragments.AccomodationFragment;
import fragments.ConstitutionBoardFragment;
import fragments.FacilitiesFragment;
import fragments.HomeFragment;
import fragments.TempleFragment;
import fragments.TravelFragment;
import fragments.WelfareSchemesFragment;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	public static android.app.FragmentManager fm;
	FragmentManager fragmentManager;

	int currentPos = -1;
	int currentNum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fm = getFragmentManager();
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		fragmentManager = getSupportFragmentManager();
		if (position == 0 && currentPos != 0) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							HomeFragment.newInstance(position + 1)).commit();
		}
		if (position == 1 && currentPos != 1) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							TravelFragment.newInstance(position + 1)).commit();
		}
		if (position == 2 && currentPos != 2) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							TempleFragment.newInstance(position + 1)).commit();
		}
		if (position == 3 && currentPos != 3) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							AccomodationFragment.newInstance(position + 1))
					.commit();
		}
		if (position == 4 && currentPos != 4) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							WelfareSchemesFragment.newInstance(position + 1))
					.commit();
		}
		if (position == 5 && currentPos != 5) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							ConstitutionBoardFragment.newInstance(position + 1))
					.commit();
		}
		if (position == 6 && currentPos != 6) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							FacilitiesFragment.newInstance(position + 1))
					.commit();
		}
		currentPos = position;
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			if (currentNum != 1)
				mTitle = getString(R.string.title_Home);
			break;
		case 2:
			if (currentNum != 2)
				mTitle = getString(R.string.title_Travel);
			break;
		case 3:
			if (currentNum != 3)
				mTitle = getString(R.string.title_Temples);
			break;
		case 4:
			if (currentNum != 4)
				mTitle = getString(R.string.title_Accomodation);
			break;
		case 5:
			if (currentNum != 5)
				mTitle = getString(R.string.title_Welfare_Schemes);
			break;
		case 6:
			if (currentNum != 6)
				mTitle = getString(R.string.title_Constitution_Board);
			break;
		case 7:
			if (currentNum != 7)
				mTitle = getString(R.string.title_facilities);
			break;

		}
		currentNum = number;
	}

	@Override
	public void onBackPressed() {
		if (currentPos == 0) {
			super.onBackPressed();
		} else {
			currentPos = 0;
			mTitle = getString(R.string.title_Home);
			restoreActionBar();
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							HomeFragment.newInstance(currentPos + 1)).commit();
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			// getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}
}
