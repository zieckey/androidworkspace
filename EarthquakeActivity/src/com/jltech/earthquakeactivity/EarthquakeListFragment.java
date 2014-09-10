package com.jltech.earthquakeactivity;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.auth.MalformedChallengeException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jlteck.earthquakeactivity.R;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;

@SuppressLint("SimpleDateFormat")
public class EarthquakeListFragment extends ListFragment {
	ArrayAdapter<Quake> aa;
	ArrayList<Quake> earthquakes = new ArrayList<Quake>();
	
	private static final String TAG = "EARTHQUAKE";
	private Handler handler = new Handler();
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		int layoutID = android.R.layout.simple_list_item_1;
		aa = new ArrayAdapter<Quake>(getActivity(), layoutID, earthquakes);
		setListAdapter(aa);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				refreshEarthquakes();
			}
		});
		t.start();
	}
	
	public void refreshEarthquakes() {
		URL url;
		try {
			String feed = getString(R.string.quake_feed);
			url = new URL(feed);
			URLConnection conn = url.openConnection();
			
			HttpURLConnection httpconn = (HttpURLConnection)conn;
			int respCode = httpconn.getResponseCode();
			if (respCode == HttpURLConnection.HTTP_OK) {
				InputStream in = httpconn.getInputStream();
				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = f.newDocumentBuilder();
				
				Document dom = db.parse(in);
				Element docEle = dom.getDocumentElement();
				
				earthquakes.clear();
				
				NodeList nl = docEle.getElementsByTagName("entry");
				for (int i = 0; nl != null && i < nl.getLength(); i++) {
					Element entry = (Element)nl.item(i);
					Element title = (Element)entry.getElementsByTagName("title").item(0);
					Element g = (Element)entry.getElementsByTagName("georss:point").item(0);
					Element when = (Element)entry.getElementsByTagName("updated").item(0);
					Element link = (Element)entry.getElementsByTagName("link").item(0);

					String details = title.getFirstChild().getNodeValue();
					String hostname = "http://earthquake.usgs.gov";
					String linkString = hostname + link.getAttribute("href");
					
					if (details.contains("Deprecated")) {
						continue;
					}
					
					String point = g != null ? g.getFirstChild().getNodeValue() : "";
					String dt = when.getFirstChild().getNodeValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
					Date qdate = new GregorianCalendar(0, 0, 0).getTime();
					
					try {
						qdate = sdf.parse(dt);
					} catch (ParseException e) {
						Log.d(TAG, "Date parsing exception.", e);
					}
					
					String[] locations = point.split(" ");
					Location l = new Location("dummyGPS");
					if (point.length() > 0 && locations.length == 2) {
						l.setLatitude(Double.parseDouble(locations[0]));
						l.setLongitude(Double.parseDouble(locations[1]));
					}
					
					String mg = details.split(" ")[1];
					int end = mg.length() - 1;
					double magnitude = Double.parseDouble(mg.substring(0, end));
					
					details = details.split(",")[1].trim();
					
					final Quake quake = new Quake(qdate, details, l, magnitude, linkString);
					
					handler.post(new Runnable() {
						public void run() {
							addNewQuake(quake);
						}
					});
				}
			}
		} catch (MalformedURLException e) {
			Log.d(TAG, "MalformedURLException");
		} catch (IOException e) {
			Log.d(TAG, "IOException");
		} catch (ParserConfigurationException e) {
			Log.d(TAG, "ParserConfigurationException");
		} catch (SAXException e) {
			Log.d(TAG, "SAXException");
		}
	}
	
	private void addNewQuake(Quake quake) {
		earthquakes.add(quake);
		aa.notifyDataSetChanged();
	}
	
}
