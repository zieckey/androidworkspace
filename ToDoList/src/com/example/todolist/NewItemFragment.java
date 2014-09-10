package com.example.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



@SuppressLint("NewApi")
public class NewItemFragment extends Fragment {
	private OnNewItemAddedListener onNewItemAddedListener;
	
	public interface OnNewItemAddedListener {
		public void onNewItemAdded(String newItem);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.new_item_fragment, container, false);
		
		final EditText inputToDoEditText = (EditText)view.findViewById(R.id.inputToDoEditText);

        inputToDoEditText.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() != KeyEvent.ACTION_DOWN) {
					return false;
				}
				if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER 
						|| keyCode == KeyEvent.KEYCODE_ENTER) {
					String newItem = inputToDoEditText.getText().toString();
					onNewItemAddedListener.onNewItemAdded(newItem);
					inputToDoEditText.setText("");
					return true;
				}
				return false;
			}
		});
		
		return view;
	}
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		onNewItemAddedListener = (OnNewItemAddedListener)activity;
	}
	
}
