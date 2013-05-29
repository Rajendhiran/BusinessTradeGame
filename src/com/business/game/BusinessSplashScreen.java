package com.business.game;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;

public class BusinessSplashScreen extends Activity 
{
	Handler h;
	long handlerMilliseconds=800;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		init();
		process();
	}

	//Author: Rajendhiran May 28, 2013 3:05:26 PM
	private void process() 
	{
		h.postDelayed(new Runnable() 
		{
			public void run() 
			{
				finish();
				//startActivity(new Intent(BusinessSplashScreen.this,BusinessDashboard.class));
			}
		}, handlerMilliseconds);
	}

	//Author: Rajendhiran May 28, 2013 3:04:45 PM
	private void init() 
	{
		h = new Handler();
	}

}