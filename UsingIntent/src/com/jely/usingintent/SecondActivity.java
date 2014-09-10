package com.jely.usingintent;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		String str1 = getIntent().getStringExtra("str1");
		int age1 = getIntent().getIntExtra("age1", 1);
		
		String str2 = getIntent().getExtras().getString("str2");
		int age2 = getIntent().getExtras().getInt("age2");
		
		String s = "str1="+str1 + " age1="+age1 + " str2="+str2 + " age2="+age2;
		
		Toast.makeText(this, s, Toast.LENGTH_LONG + 30).show();
	}
	
	public void onClick(View view) {
		Intent data = new Intent();
		EditText username = (EditText)findViewById(R.id.txt_username);
		
		data.setData(Uri.parse(username.getText().toString()));
		setResult(RESULT_OK, data);
		
		finish();
	}
}
