package com.viaviapp.allinonevideos;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.example.adapter.CategoryItemGridAdapter;
import com.example.item.ItemCategory;
import com.example.util.AlertDialogManager;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;
import com.viaviapp.allinonevideos.R;

public class CategoryItem extends SherlockActivity {
	
	 ListView lsv_cat;
	List<ItemCategory> arrayOfLatestVideo;
	CategoryItemGridAdapter objAdapter;
	AlertDialogManager alert = new AlertDialogManager();
	ArrayList<String> allListVideo,allListVideoCatName,allListVideoType;
	ArrayList<String> allListVideoId,allListVideoCatId,allListVideoUrl,allListVideoName,allListVideoDuration,allListVideoDesc,allListImageUrl;
	String[] allArrayVideo,allArrayVideoCatName,allArrayVideoType;
	String[] allArrayVideoId,allArrayVideoCatId,allArrayVideourl,allArrayVideoName,allArrayVideoDuration,allArrayVideoDesc,allArrayImageUrl;
	private ItemCategory objAllBean;
	private int columnWidth;
	JsonUtils util;
	int textlength = 0;
	private AdView mAdView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id,false));
        setContentView(R.layout.category_item_grid);
        StartAppAd.showSlider(this);
        
        mAdView = (AdView) findViewById(R.id.adView);
	    mAdView.loadAd(new AdRequest.Builder().build());
       
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle(Constant.CATEGORY_TITLE);
		lsv_cat=(ListView)findViewById(R.id.lsv_cat_item);
		
		arrayOfLatestVideo=new ArrayList<ItemCategory>();
		allListVideo=new ArrayList<String>();
		allListVideoCatName=new ArrayList<String>();
		allListVideoCatId=new ArrayList<String>();
		allListVideoId=new ArrayList<String>();
		allListVideoName=new ArrayList<String>();
		allListVideoUrl=new ArrayList<String>();
		allListVideoDuration=new ArrayList<String>();
		allListVideoDesc=new ArrayList<String>();
		allListImageUrl=new ArrayList<String>();
		allListVideoType=new ArrayList<String>();
		
		allArrayVideo=new String[allListVideo.size()];
		allArrayVideoCatName=new String[allListVideoCatName.size()];
		allArrayVideoId=new String[allListVideoId.size()];
		allArrayVideoCatId=new String[allListVideoCatId.size()];
		allArrayVideourl=new String[allListVideoUrl.size()];
		allArrayVideoName=new String[allListVideoName.size()];
		allArrayVideoDuration=new String[allListVideoDuration.size()];
		allArrayVideoDesc=new String[allListVideoDesc.size()];
		allArrayImageUrl=new String[allListImageUrl.size()];
		allArrayVideoType=new String[allListVideoType.size()];
		
		util=new JsonUtils(getApplicationContext());
		 
		
		if (JsonUtils.isNetworkAvailable(CategoryItem.this)) {
			new MyTask().execute(Constant.CATEGORY_ITEM_URL+Constant.CATEGORY_ID);
		} else {
			showToast("No Network Connection!!!");
			 alert.showAlertDialog(CategoryItem.this, "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
		}
		
		lsv_cat.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				objAllBean=arrayOfLatestVideo.get(position);
				int pos=objAllBean.getId();
				
				Intent intplay=new Intent(getApplicationContext(),VideoPlay.class);
				intplay.putExtra("POSITION", pos);
				intplay.putExtra("VIDEO_ID", allArrayVideo);
				intplay.putExtra("VIDEO_CATNAME", allArrayVideoCatName);
				intplay.putExtra("VIDEO_CATID", allArrayVideoCatId);
				intplay.putExtra("VIDEO_URL", allArrayVideourl);
				intplay.putExtra("VIDEO_NAME", allArrayVideoName);
				intplay.putExtra("VIDEO_CID", allArrayVideoId);
				intplay.putExtra("VIDEO_DURATION", allArrayVideoDuration);
				intplay.putExtra("VIDEO_DISCRIPTION", allArrayVideoDesc);
				intplay.putExtra("VIDEO_IMAGE_URL",allArrayImageUrl);
				intplay.putExtra("VIDEO_TYPE",allArrayVideoType);
				startActivity(intplay);
			}
		});
		
	}
	

	
	private	class MyTask extends AsyncTask<String, Void, String> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(CategoryItem.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return JsonUtils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == result || result.length() == 0) {
				showToast("Server Connection Error");
				alert.showAlertDialog(CategoryItem.this, "Server Connection Error",
	                    "May Server Under Maintaines Or Low Network", false);
				
			} else {

				try {
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(Constant.CATEGORY_ITEM_ARRAY_NAME);
					JSONObject objJson = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						  objJson = jsonArray.getJSONObject(i);

						  ItemCategory objItem = new ItemCategory();

						objItem.setId(objJson.getInt(Constant.CATEGORY_ITEM_ID));
						objItem.setCategoryId(objJson.getInt(Constant.CATEGORY_ITEM_CATID));
						objItem.setCategoryName(objJson.getString(Constant.CATEGORY_ITEM_CAT_NAME));
						objItem.setVideoUrl(objJson.getString(Constant.CATEGORY_ITEM_VIDEO_URL));
						objItem.setVideoId(objJson.getString(Constant.CATEGORY_ITEM_VIDEO_ID));
						objItem.setVideoName(objJson.getString(Constant.CATEGORY_ITEM_VIDEO_NAME));
						objItem.setDuration(objJson.getString(Constant.CATEGORY_ITEM_VIDEO_DURATION));
						objItem.setDescription(objJson.getString(Constant.LATEST_VIDEO_DESCRIPTION));
						objItem.setImageUrl(objJson.getString(Constant.LATEST_IMAGE_URL));
						objItem.setVideoType(objJson.getString(Constant.LATEST_VIDEOTYPE));
						arrayOfLatestVideo.add(objItem);
					 

					}
					 
				} catch (JSONException e) {
					e.printStackTrace();
				}
				for(int j=0;j<arrayOfLatestVideo.size();j++)
				{
					 
					objAllBean=arrayOfLatestVideo.get(j);
					
					allListVideo.add(objAllBean.getVideoId());
					allArrayVideo=allListVideo.toArray(allArrayVideo);
					
					allListVideoCatName.add(objAllBean.getCategoryName());
					allArrayVideoCatName=allListVideoCatName.toArray(allArrayVideoCatName);
					
					allListVideoId.add(String.valueOf(objAllBean.getId()));
					allArrayVideoId=allListVideoId.toArray(allArrayVideoId);
					
					allListVideoCatId.add(String.valueOf(objAllBean.getCategoryId()));
					allArrayVideoCatId=allListVideoCatId.toArray(allArrayVideoCatId);
					
					
					allListVideoUrl.add(String.valueOf(objAllBean.getVideoUrl()));
					allArrayVideourl=allListVideoUrl.toArray(allArrayVideourl);
					
					allListVideoName.add(String.valueOf(objAllBean.getVideoName()));
					allArrayVideoName=allListVideoName.toArray(allArrayVideoName);
					
					allListVideoDuration.add(String.valueOf(objAllBean.getDuration()));
					allArrayVideoDuration=allListVideoDuration.toArray(allArrayVideoDuration);
					
					allListVideoDesc.add(objAllBean.getDescription());
					allArrayVideoDesc=allListVideoDesc.toArray(allArrayVideoDesc);
					
					allListImageUrl.add(objAllBean.getImageUrl());
					allArrayImageUrl=allListImageUrl.toArray(allArrayImageUrl);
					
					allListVideoType.add(objAllBean.getVideoType());
					allArrayVideoType=allListVideoType.toArray(allArrayVideoType);
				}

  			setAdapterToListview();
  		}

		}
	}

	 
 
	public void setAdapterToListview() {
		objAdapter = new CategoryItemGridAdapter(CategoryItem.this, R.layout.latest_lsv_item,
				arrayOfLatestVideo,columnWidth);
		lsv_cat.setAdapter(objAdapter);
	}

	public void showToast(String msg) {
		Toast.makeText(CategoryItem.this, msg, Toast.LENGTH_LONG).show();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		  MenuInflater inflater = getSupportMenuInflater();
          inflater.inflate(R.menu.menu_search, menu);
          
          
          final SearchView searchView = (SearchView) menu.findItem(R.id.search)
                  .getActionView();
          
          final MenuItem searchMenuItem = menu.findItem(R.id.search);
  	    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
  			
  			@Override
  			public void onFocusChange(View v, boolean hasFocus) {
  				// TODO Auto-generated method stub
  				if(!hasFocus) {
  					 searchMenuItem.collapseActionView();
  					searchView.setQuery("", false);
  	            }
  			}
  		});
  	    
  	    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
  	        @Override
  	        public boolean onQueryTextChange(String newText) {
  	             
  	        	textlength=newText.length();
  	        	arrayOfLatestVideo.clear();
  	        	
  	        	for(int i=0;i< allArrayVideoName.length;i++)
  	        	{
  	        		if(textlength <= allArrayVideoName[i].length())
  	        		{
  	        			if(newText.toString().equalsIgnoreCase((String) allArrayVideoName[i].subSequence(0, textlength)))
  	        			{
  	        				
  	        				
  	        				ItemCategory objItem = new ItemCategory();
  	        				
  	        				objItem.setId(Integer.parseInt(allArrayVideoId[i]));
  	        				objItem.setCategoryId(Integer.parseInt(allArrayVideoCatId[i]));
  	        				objItem.setCategoryName(allArrayVideoCatName[i]);
  	        				objItem.setDescription(allArrayVideoDesc[i]);
  	        				objItem.setDuration(allArrayVideoDuration[i]);
  	        				objItem.setVideoId(allArrayVideo[i]);
  	        				objItem.setVideoName(allArrayVideoName[i]);
  	        				objItem.setVideoUrl(allArrayVideourl[i]);
  	        				objItem.setImageUrl(allArrayImageUrl[i]);
  	        				objItem.setVideoType(allArrayVideoType[i]);
  	        				
  	        				arrayOfLatestVideo.add(objItem);
  	        				
  	        			}
  	        		}
  	        	}
  	        	
  	        	setAdapterToListview();
  	        	return false;
  	        }

  	        @Override
  	        public boolean onQueryTextSubmit(String query) {
  	            // Do something
  	            return true;
  	        }
  	    });
  	    
  	
		return super.onCreateOptionsMenu(menu);
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
