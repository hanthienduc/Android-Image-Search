package com.tonytam.imagesearch;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

/*
 * Displays image in a full screen mode
 * 
 * TODO: allow for swiping left and right
 * TODO: double tap to return to list activity
 * TODO: fav locally
 * TODO: Share
 * DEPENDS ON : (R.layout.activity_image_display
 * DEPENDS ON : calling intent to setExtra("result) with value of ImageResult object
 * @author Tony Tam
 * @version %I%, %G%
 * @see R.menu.image_display
 */

public class ImageDisplayActivity extends Activity {

	/*
	 * Show the full size image, loading it asynchronously
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * TODO: Change the title to search for <term>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_image_display);
		
		// The calling intent must pass ImageResult
		ImageResult result = (ImageResult) getIntent().getSerializableExtra("result");
		if (result.getClass() == ImageResult.class) {
			SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
			ivImage.setImageUrl(result.getFullUrl());
		} else {
			Log.d("DEBUG", "ImageDisplayActivity::onCreate did not see ImageResult in getIntent() getExtra for 'result'");
		}
	}

	/*
	 *
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}
