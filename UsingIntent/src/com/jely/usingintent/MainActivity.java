package com.jely.usingintent;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	int requestCode = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void onClick(View v)	{
		//����һ��Indent�������ڴ��䷵�����ݸ���ǰ�����Activity��
		//requestCode�������룬��ʾһ��ID��������ʾ���ڵ��õĻ������Ǳ���ģ���Ϊ�������û����һ��ֵʱ����ǰ������а취�ܽ�����ʶ�����������磬�п��ܻ���ö�����
		Intent intent = new Intent("com.jely.usingintent.SecondActivity");
		intent.putExtra("str1", "string value pass by MainActivity");
		intent.putExtra("age1", 30);
		Bundle extras = new Bundle();
		extras.putString("str2", "another string value pass by MainActivity");
		extras.putInt("age2", 25);
		intent.putExtras(extras);
		startActivityForResult(intent, requestCode);
	}
	
	public void onActivityResult(int resultRequestCode, int resultCode, Intent data) {
		if (resultRequestCode == this.requestCode) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, data.getData().toString(), 10).show();
			}
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}