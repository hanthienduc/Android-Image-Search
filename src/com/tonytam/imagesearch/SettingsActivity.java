package com.tonytam.imagesearch;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		

		loadSpinner("size", R.id.spImageSize, R.array.image_size_array);
		loadSpinner("color", R.id.spColorFilter, R.array.color_filter_array);
		loadSpinner("type", R.id.spImageType, R.array.image_type_filter_array);
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);		
		
		Map <String,String> prefs = ImageSearchSettings.loadPreferences(this);
		etSiteFilter.setText (prefs.get("site").toString());
	}

	public void loadSpinner(String prefKeyName, int res, int resArray) {
		// Populate Image Type
		Spinner spinner = (Spinner) findViewById(res);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = 
				ArrayAdapter.createFromResource
					(
							this,
							resArray, 
							android.R.layout.simple_spinner_item
							);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Map <String,String> prefs = ImageSearchSettings.loadPreferences(this);
		
		// Apply the adapter to the spinner		
		spinner.setAdapter(adapter);
		for(int i=0; i < adapter.getCount(); i++) {
			Log.d("DEBUG", "preference key "+ prefKeyName);
			if (prefs != null  && prefs.get(prefKeyName) != null) {	
				if (prefs.get(prefKeyName).trim().equals(adapter.getItem(i).toString())) {
					spinner.setSelection(i);
				}
			}
		}
		
	}
	
	public void onSavePreference(View v) {
		Log.d("DEBUG", "user clicked save preferences");
		
		// Get the relative positions
		Spinner spinner = (Spinner) findViewById(R.id.spImageSize);
		Spinner spinnerColorFilter = (Spinner) findViewById(R.id.spColorFilter);
		Spinner spinnerImageType = (Spinner) findViewById(R.id.spImageType);
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);		
		
	    SharedPreferences settings = getSharedPreferences("ImageSearchPrefs", MODE_PRIVATE);
	    SharedPreferences.Editor ed = settings.edit();
	    ed.putString("size", spinner.getSelectedItem().toString());
	    ed.putString("color", spinnerColorFilter.getSelectedItem().toString());
	    ed.putString("type", spinnerImageType.getSelectedItem().toString());
	    ed.putString("site", etSiteFilter.getText().toString());
	    ed.commit();
	    Log.d("DEBUG", settings.toString());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// we don't need a menu at the settings 
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
