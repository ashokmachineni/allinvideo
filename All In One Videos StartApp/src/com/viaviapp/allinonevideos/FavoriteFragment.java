package com.viaviapp.allinonevideos;

import java.util.ArrayList;
import java.util.List;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.example.adapter.FavoriteAdapter;
import com.example.favorite.DatabaseHandler;
import com.example.favorite.Pojo;
import com.example.favorite.DatabaseHandler.DatabaseManager;
import com.example.util.JsonUtils;
import com.viaviapp.allinonevideos.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteFragment extends SherlockFragment {
	
 
	ListView list_fav;
	DatabaseHandler db;
	private DatabaseManager dbManager;
	FavoriteAdapter adapter;
	TextView txt_no;
	private int columnWidth;
	JsonUtils util;
	List<Pojo> allData;
	ArrayList<String> allListVideo,allListVideoCatName,allListVideoType;
	ArrayList<String> allListVideoId,allListVideoCatId,allListVideoUrl,allListVideoName,allListVideoDuration,allListVideoDesc,allListImageUrl;
	String[] allArrayVideo,allArrayVideoCatName,allArrayVideoType;
	String[] allArrayVideoId,allArrayVideoCatId,allArrayVideourl,allArrayVideoName,allArrayVideoDuration,allArrayVideoDesc,allArrayImageUrl;
	 int textlength = 0;
	 Pojo pojo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
		setHasOptionsMenu(true);
		list_fav=(ListView)rootView.findViewById(R.id.lsv_favorite);
		txt_no=(TextView)rootView.findViewById(R.id.textView1);
		db=new DatabaseHandler(getActivity());
		dbManager = DatabaseManager.INSTANCE;
		dbManager.init(getActivity());
		util=new JsonUtils(getActivity());
	
		allData=db.getAllData();
		adapter=new FavoriteAdapter(allData,getActivity(),columnWidth);
		list_fav.setAdapter(adapter);
		if(allData.size()==0)
		{
			txt_no.setVisibility(View.VISIBLE);
		}
		else
		{
			txt_no.setVisibility(View.INVISIBLE);
		}
		
		list_fav.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				pojo=allData.get(position);
				int pos=Integer.parseInt(pojo.getVId());
				
				Intent intplay=new Intent(getActivity(),VideoPlay.class);
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
		return rootView;
	}
	 
	
	public void onDestroyView() {
		
		//Log.e("OnDestroy", "called");
		if(!dbManager.isDatabaseClosed())
			dbManager.closeDatabase();
		super.onDestroyView();
	}
	@Override
	public void onPause() {
	super.onPause();
	//Log.e("OnPaused", "called");
	if(!dbManager.isDatabaseClosed())
	dbManager.closeDatabase();
	}
	
	@Override
	public void onResume() {
	super.onResume();
	//Log.e("OnResume", "called");
	//when back key pressed or go one tab to another we update the favorite item so put in resume
	allData=db.getAllData();
	adapter=new FavoriteAdapter(allData,getActivity(),columnWidth);
	list_fav.setAdapter(adapter);
	if(allData.size()==0)
	{
		txt_no.setVisibility(View.VISIBLE);
	}
	else
	{
		txt_no.setVisibility(View.INVISIBLE);
	}
	
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
	
	for(int j=0;j<allData.size();j++)
	{
		 Pojo objAllBean=allData.get(j);
		 
		 	allListVideo.add(objAllBean.getVideoId());
			allArrayVideo=allListVideo.toArray(allArrayVideo);
			
			allListVideoCatName.add(objAllBean.getCategoryName());
			allArrayVideoCatName=allListVideoCatName.toArray(allArrayVideoCatName);
			
			allListVideoId.add(String.valueOf(objAllBean.getVId()));
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
	}
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
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
	        	allData.clear();
	        	
	        	for(int i=0;i< allArrayVideoName.length;i++)
	        	{
	        		if(textlength <= allArrayVideoName[i].length())
	        		{
	        			if(newText.toString().equalsIgnoreCase((String) allArrayVideoName[i].subSequence(0, textlength)))
	        			{
	        				
	        				
	        				Pojo objItem = new Pojo();
	        				
	        				objItem.setVId(allArrayVideoId[i]);
	        				objItem.setCategoryId(allArrayVideoCatId[i]);
	        				objItem.setCategoryName(allArrayVideoCatName[i]);
	        				objItem.setDescription(allArrayVideoDesc[i]);
	        				objItem.setDuration(allArrayVideoDuration[i]);
	        				objItem.setVideoId(allArrayVideo[i]);
	        				objItem.setVideoName(allArrayVideoName[i]);
	        				objItem.setVideoUrl(allArrayVideourl[i]);
	        				objItem.setImageUrl(allArrayImageUrl[i]);
	        				objItem.setVideoType(allArrayVideoType[i]);
	        				
	        				allData.add(objItem);
	        				
	        			}
	        		}
	        	}
	        	 
	        	adapter=new FavoriteAdapter(allData,getActivity(),columnWidth);
	        	list_fav.setAdapter(adapter);
	        	 
	        	return false;
	        }

	        @Override
	        public boolean onQueryTextSubmit(String query) {
	            // Do something
	            return true;
	        }
	    });
	    
	}
}
