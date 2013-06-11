package com.tonytam.imagesearch;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {
	String prevSearchString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		

		loadSpinner("size", R.id.spImageSize, R.array.image_size_array);
		loadSpinner("color", R.id.spColorFilter, R.array.color_filter_array);
		loadSpinner("type", R.id.spImageType, R.array.image_type_filter_array);
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);		
		
		Map <String,String> prefs = ImageSearchSettings.loadPreferences(this);
		if (prefs.get("site") != null) {
			etSiteFilter.setText (prefs.get("site").toString());
		}
		
		// When this Activity is called somewhere else
		Bundle extras = getIntent().getExtras();
		if (extras !=null ) {
			String searchString = extras.getString(Intent.EXTRA_TEXT);
			if (searchString != null ) {
				prevSearchString = searchString;
			}		
		}
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
		
		if (prefs == null) {
			return;
		}
		
		// Apply the adapter to the spinner		
		spinner.setAdapter(adapter);
		for(int i=0; i < adapter.getCount(); i++) {
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
		
		// TODO: I could  move all this to ImageSearchSettings, have to pass in a Map<String,String>
	    SharedPreferences settings = getSharedPreferences("ImageSearchPrefs", MODE_PRIVATE);
	    SharedPreferences.Editor ed = settings.edit();
	    ed.putString("size", spinner.getSelectedItem().toString());
	    ed.putString("color", spinnerColorFilter.getSelectedItem().toString());
	    ed.putString("type", spinnerImageType.getSelectedItem().toString());
	    ed.putString("site", etSiteFilter.getText().toString());
	    ed.commit();
	    
	    // TODO: how to do I trigger the SearchActivity to do another search
		Intent i = new Intent(getApplicationContext(),
				SearchActivity.class	
		);

		if (prevSearchString != null ) {
			i.putExtra(android.content.Intent.EXTRA_TEXT, prevSearchString);
		}
		startActivityForResult(i,0);


	    Log.d("DEBUG", settings.toString());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// we don't need a menu at the settings 
		// getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
