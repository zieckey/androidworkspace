package com.jely.dialogfragmentexample;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyDialogFragment f = MyDialogFragment.newInstance("Are you sure you want to do this?");
		f.show(getFragmentManager(), "dialog");
	}
	
	public void doPositiveClick() {
		Log.d("MainActivity", "User clicks on OK");
	}
	
	public void doNegativeClick() {
		Log.d("MainActivity", "User clicks on Cancel");
	}
}


