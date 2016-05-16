package com.viaviapp.allinonevideos;

import android.app.Application;
import com.parse.Parse;
import com.parse.PushService;
import com.viaviapp.allinonevideos.R;

public class MyApplication extends Application {

	public MyApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize the Parse SDK.
		Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_id)); 

		// Specify an Activity to handle all pushes by default.
		PushService.setDefaultPushCallback(this, MainActivity.class);
	}
}