package com.example.vimeo;

import com.viaviapp.allinonevideos.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.PluginState;

public class Vimeo extends Activity {
	private final String TAG = "MainActivity";
	private HTML5WebView mWebView;
	String Id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebView = new HTML5WebView(this);
		
		Intent i=getIntent();
	    Id=i.getStringExtra("id");
	    
		//Auto playing vimeo videos in Android webview
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setPluginState(PluginState.OFF);
		mWebView.getSettings().setAllowFileAccess(true);
		
		mWebView.loadUrl("http://player.vimeo.com/video/"+Id+"?player_id=player&autoplay=1&title=0&byline=0&portrait=0&api=1&maxheight=480&maxwidth=800");
		
		setContentView(mWebView.getLayout());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.reload){
			mWebView.destroyDrawingCache();
			Log.d(TAG,"Reloading..");
			mWebView.reload();
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mWebView.handleBack(this);
	}
	
}
