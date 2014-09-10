package com.example.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.widget.SimpleAdapter;

public class ToDoItem {
	String task;
	Date created;
	
	public String getTask() {
		return task;
	}
	
	public Date getDate() {
		return created;
	}
	
	public ToDoItem(String task) {
		this.task = task;
		created = new Date(java.lang.System.currentTimeMillis());
	}
	
	@SuppressLint("SimpleDateFormat")
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String s = sdf.format(created);
		return "(" + s + ") " + task;
	}
}
