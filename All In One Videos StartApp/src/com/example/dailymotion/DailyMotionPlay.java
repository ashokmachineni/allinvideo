package com.example.dailymotion;

import com.viaviapp.allinonevideos.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.WindowManager;

public class DailyMotionPlay extends Activity {

    private DMWebVideoView mVideoView;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.dailymotion);
        Intent i=getIntent();
        Id=i.getStringExtra("id");
   
        mVideoView = ((DMWebVideoView) findViewById(R.id.dmWebVideoView));
        mVideoView.setVideoId(Id, true);
        
        }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        mVideoView.handleBackPress(this);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//            mVideoView.onPause();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//            mVideoView.onResume();
//        }
//    }
}
