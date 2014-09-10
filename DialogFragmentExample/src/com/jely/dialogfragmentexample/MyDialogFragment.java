package com.jely.dialogfragmentexample;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("NewApi")
public class MyDialogFragment extends DialogFragment {
	static MyDialogFragment newInstance(String title) {
		MyDialogFragment f = new MyDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		f.setArguments(args);
		return f;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");
		Builder b = new Builder(getActivity());
		b.setIcon(R.drawable.ic_launcher);
		b.setTitle(title);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((MainActivity)getActivity()).doPositiveClick();
			}
		});
		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((MainActivity)getActivity()).doNegativeClick();
			}
		});
		return b.create();
	}
}
