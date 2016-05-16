package com.viaviapp.allinonevideos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.viaviapp.allinonevideos.R;

public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener {
 
	private String[] tabs = { "LATEST", "ALL VIDEOS", "MY FAVORITES" };
	private TabsPagerAdapter mAdapter;
	private ViewPager viewPager;
	ActionBar.Tab tab;
	private AdView mAdView;
	private InterstitialAd mInterstitial;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdView = (AdView) findViewById(R.id.adView);
		mAdView.loadAd(new AdRequest.Builder().build());
		mInterstitial = new InterstitialAd(this);
		mInterstitial.setAdUnitId(getResources().getString(R.string.admob_intertestial_id));
		mInterstitial.loadAd(new AdRequest.Builder().build());

		mInterstitial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// TODO Auto-generated method stub
				super.onAdLoaded();
				if (mInterstitial.isLoaded()) {
					mInterstitial.show();
				}
			}
		});

		viewPager = (ViewPager) findViewById(R.id.pager);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);

		//tab added in action bar
		for(String tab_name:tabs)
		{
			tab = getSupportActionBar().newTab();
			tab.setText(tab_name);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				getSupportActionBar().setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction transaction) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction transaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);


		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) 
		{
		case R.id.menu_about:

			Intent about=new Intent(getApplicationContext(),AboutActivity.class);
			startActivity(about);

			return true;

		case R.id.menu_overflow:
			return true;

		case R.id.menu_moreapp:

			startActivity(new Intent(
					Intent.ACTION_VIEW,
					Uri.parse(getString(R.string.play_more_apps))));

			return true;

		case R.id.menu_rateapp:

			final String appName = getPackageName();//your application package name i.e play store application url
			try {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("market://details?id="
								+ appName)));
			} catch (android.content.ActivityNotFoundException anfe) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://play.google.com/store/apps/details?id="
								+ appName)));
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Toast.makeText(appContext, "BAck", Toast.LENGTH_LONG).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					MainActivity.this);
			alert.setTitle(getString(R.string.app_name));
			alert.setIcon(R.drawable.icon);
			alert.setMessage("Are You Sure You Want To Quit?");

			alert.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {


					finish();
				}



			});

			alert.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub


				}
			});
			alert.show();
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
	@Override
	protected void onPause() {
		mAdView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAdView.resume();

	}

	@Override
	protected void onDestroy() {
		mAdView.destroy();
		super.onDestroy();
	}



}
