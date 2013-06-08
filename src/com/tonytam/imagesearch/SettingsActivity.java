package com.tonytam.imagesearch;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// Populate Image Size
		Spinner spinner = (Spinner) findViewById(R.id.spImageSize);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.image_size_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		// Populate Color Filter
		Spinner spinnerColorFilter = (Spinner) findViewById(R.id.spColorFilter);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterColorFilter = ArrayAdapter.createFromResource(this,
		        R.array.color_filter_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerColorFilter.setAdapter(adapterColorFilter);
		
		// Populate Image Type
		Spinner spinnerImageType = (Spinner) findViewById(R.id.spImageType);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterImageType = ArrayAdapter.createFromResource(this,
		        R.array.image_type_filter_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerImageType.setAdapter(adapterImageType);		

		loadPreferences();

	}

	public void onSavePreference(View v) {
		Log.d("DEBUG", "user clicked save preferences");
		
		// Get the relative positions
		Spinner spinner = (Spinner) findViewById(R.id.spImageSize);
		Spinner spinnerColorFilter = (Spinner) findViewById(R.id.spColorFilter);
		Spinner spinnerImageType = (Spinner) findViewById(R.id.spImageType);
		TextView tvSiteFilter = (TextView) findViewById(R.id.tvSiteFilter);		
		
	    SharedPreferences settings = getPreferences(MODE_WORLD_READABLE);
	    SharedPreferences.Editor ed = settings.edit();
	    ed.putString("size", spinner.getSelectedItem().toString());
	    ed.putString("color", spinnerColorFilter.getSelectedItem().toString());
	    ed.putString("type", spinnerImageType.getSelectedItem().toString());
	    ed.putString("site", tvSiteFilter.toString());
	    ed.commit();
	    Log.d("DEBUG", settings.toString());
	}

	public Map<String,String> loadPreferences () {
	    SharedPreferences settings = getPreferences(MODE_WORLD_READABLE);
	    Log.d("DEBUG", settings.getAll().toString());
		return null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// we don't need a menu at the settings 
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
