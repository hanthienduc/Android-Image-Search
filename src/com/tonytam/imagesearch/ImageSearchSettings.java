package com.tonytam.imagesearch;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

	
public  class ImageSearchSettings {
	
	public static Map<String,String> loadPreferences (Activity a) {
	    SharedPreferences settings = a.getSharedPreferences("ImageSearchPrefs", android.content.Context.MODE_PRIVATE);
	    Log.d("DEBUG", settings.getAll().toString());
		return (Map<String,String>) settings.getAll();
	}
}
