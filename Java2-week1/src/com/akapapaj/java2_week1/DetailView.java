package com.akapapaj.java2_week1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DetailView extends Activity {
	String myUrl;
	String mydet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		Intent i = getIntent();
		final String[] details = i.getStringArrayExtra("product");
		
		for(int k=0; k<4; k++){
			mydet = details[k];
			
			tv.append(mydet);
		
		}
		mydet = details[4];
		myUrl = mydet;
		Log.i("myUrl", myUrl);
		Button b;
		b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(myUrl));
				startActivity(i);
			}
		});
	}
}
