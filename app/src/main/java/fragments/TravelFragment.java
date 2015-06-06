package fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.udacitycourse.udacityproject_mansadevi.MainActivity;
import com.udacitycourse.udacityproject_mansadevi.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helpers.CheckConnection;
import helpers.DirectionsJSONParser;
import helpers.GPSTracker;

public class TravelFragment extends Fragment implements LocationListener {

	GoogleMap map;
	double currentLat = 0.0, currentLng = 0.0, templetLat = 30.724665,
			templeLng = 76.859033;
	LatLng templeLocation;
	GPSTracker gps;
	int currentZoom = 17;
	boolean isPosChanged;
	MapFragment fm;
	LatLng latLng;
	CheckConnection chk;

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		try {
			if (fm != null) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.remove(fm).commit();
			} else {
				Intent i = new Intent(getActivity(), MainActivity.class);
				startActivity(i);
				getActivity().finish();
			}
		} catch (Exception e) {
			Intent i = new Intent(getActivity(), MainActivity.class);
			startActivity(i);
			getActivity().finish();
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		try {
			v = inflater.inflate(R.layout.fragment_travel, container, false);
		} catch (Exception e) {
			Intent i = new Intent(getActivity(), MainActivity.class);
			startActivity(i);
			getActivity().finish();
			e.printStackTrace();
		}
		gps = new GPSTracker(getActivity());
		chk = new CheckConnection(getActivity());

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		currentLat = gps.getLatitude();
		currentLng = gps.getLongitude();

		templeLocation = new LatLng(templetLat, templeLng);
		if (chk.isConnected()) {
			if (chk.isGpsOn()) {
				if (map == null && currentLat != 0.0) {
					createMap();
				}
			} else {
				chk.showGPSErrorDialog();
			}
		} else {
			chk.showConnectionErrorDialog();
		}
	}

	public void createMap() {
		fm = (MapFragment) MainActivity.fm.findFragmentById(R.id.map);
		map = fm.getMap();

		map.addMarker(new MarkerOptions().position(templeLocation)
				.title("Mansa Devi")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
		map.setMyLocationEnabled(true);
		map.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {

			@Override
			public boolean onMyLocationButtonClick() {
				isPosChanged = false;
				double latitude = gps.getLatitude();
				double longitude = gps.getLongitude();
				latLng = new LatLng(latitude, longitude);
				map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
				map.animateCamera(CameraUpdateFactory.zoomTo(currentZoom));
				return true;
			}
		});
		map.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition position) {
				if (position.zoom != currentZoom) {
					currentZoom = (int) position.zoom;
				}
				isPosChanged = true;
			}
		});
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(templeLocation, 20));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
		}
		locationManager.requestLocationUpdates(provider, 100, 0, this);

		LatLng origin = new LatLng(currentLat, currentLng);
		LatLng dest = new LatLng(templetLat, templeLng);

		String url = getDirectionsUrl(origin, dest);
		DownloadTask downloadTask = new DownloadTask();
		downloadTask.execute(url);
	}

	@Override
	public void onLocationChanged(Location location) {

		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		latLng = new LatLng(latitude, longitude);
		if (!isPosChanged)
			map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(currentZoom));

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	private String getDirectionsUrl(LatLng origin, LatLng dest) {

		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(5);
				lineOptions.color(Color.RED);

			}

			// Drawing polyline in the Google Map for the i-th route
			if(lineOptions != null) {
				try {
					map.addPolyline(lineOptions);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static TravelFragment newInstance(int sectionNumber) {
		TravelFragment fragment = new TravelFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public TravelFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}