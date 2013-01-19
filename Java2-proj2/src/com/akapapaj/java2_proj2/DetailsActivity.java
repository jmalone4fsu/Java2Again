package com.akapapaj.java2_proj2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class DetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// not supporting landscape orientation in mobile devices still will
			// in tablets
			// finish();
			DetailsFragment details = new DetailsFragment();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit();
			return;
		}
		if (savedInstanceState == null) {
			// get details fragment during setup
			DetailsFragment details = new DetailsFragment();
			details.setArguments(getIntent().getExtras());
			setResult(1);
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit();
		}
	}
}
