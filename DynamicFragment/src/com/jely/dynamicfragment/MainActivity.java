package com.jely.dynamicfragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.os.Build;

@SuppressLint({ "NewApi", "CommitTransaction" })
public class MainActivity extends ActionBarActivity {

	private Point screenSizePoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		android.app.FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		WindowManager wm = getWindowManager();
		Display d = wm.getDefaultDisplay();
		d.getSize(screenSizePoint);
		if (screenSizePoint.x > screenSizePoint.y) {
			Fragment1 f1 = new Fragment1();
			ft.replace(android.R.id.content, f1);
		} else {
			Fragment2 f1 = new Fragment2();
			ft.replace(android.R.id.content, f1);
		}
		ft.commit();
	}


}
