package com.example.todolist;

import java.util.ArrayList;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.os.Build;

@SuppressLint("NewApi")
public class ToDoListActivity extends ActionBarActivity implements NewItemFragment.OnNewItemAddedListener {
    private ArrayList<ToDoItem> todoItem;
    private ToDoItemAdapter aa;
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        
        android.app.FragmentManager fm = getFragmentManager();
        
        
        ToDoListFragment todoListFragment = (ToDoListFragment)fm.findFragmentById(R.id.ToDoListFragment);
        todoItem = new ArrayList<ToDoItem>();
        int resId = android.R.layout.simple_list_item_1;
        resId = R.layout.todolist_item;
        aa = new ToDoItemAdapter(
        		this, resId, todoItem);
        
        todoListFragment.setListAdapter(aa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	System.out.println("Action settings clicked!");
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
            View rootView = inflater.inflate(R.layout.new_item_fragment, container, false);
            return rootView;
        }
    }

	@Override
	public void onNewItemAdded(String newItem) {
		todoItem.add(0, new ToDoItem(newItem));
		aa.notifyDataSetChanged();
		
	}

}
