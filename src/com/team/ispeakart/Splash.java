package com.team.ispeakart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;

public class Splash extends Activity {
	
	private boolean backButton;
	//private ImageButton start = (ImageButton)findViewById(R.id.start);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
        		
		Handler handler=new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
				
				if(!backButton) {
					Intent i=new Intent(Splash.this,Menu.class);
					Splash.this.startActivity(i);
				}
			}
		}, 2000);
	}
}
