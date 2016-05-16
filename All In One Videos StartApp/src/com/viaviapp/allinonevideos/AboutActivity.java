package com.viaviapp.allinonevideos;

import java.io.IOException;
import java.io.InputStream;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viaviapp.allinonevideos.R;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends SherlockActivity {
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("About Us");
		 
		WebView webview = new WebView(this);
		setContentView(webview);
		try {
			InputStream fin = getAssets().open("index.html");
			byte[] buffer = new byte[fin.available()];
			fin.read(buffer);
			fin.close();
			webview.loadData(new String(buffer), "text/html", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
        {
        case android.R.id.home: 
            onBackPressed();
            break;

        default:
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
	}

}
