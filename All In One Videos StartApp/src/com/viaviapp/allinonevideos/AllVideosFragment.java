package com.viaviapp.allinonevideos;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.example.adapter.AllVideosListAdapter;
import com.example.item.ItemAllVideos;
import com.example.util.AlertDialogManager;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.viaviapp.allinonevideos.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AllVideosFragment extends SherlockFragment {
	
	ListView lsv_allvideos;
	List<ItemAllVideos> arrayOfAllvideos;
	AllVideosListAdapter objAdapter;
	AlertDialogManager alert = new AlertDialogManager();
	private ItemAllVideos objAllBean;
	ArrayList<String> allListCatid,allListCatName,allListCatImageUrl;
	String[] allArrayCatid,allArrayCatname,allArrayCatImageurl;
	 int textlength = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.fragment_allvideos, container, false);
		lsv_allvideos=(ListView)rootView.findViewById(R.id.lsv_allphotos);
		arrayOfAllvideos=new ArrayList<ItemAllVideos>();
		setHasOptionsMenu(true);
		
		allListCatid=new ArrayList<String>();
		allListCatImageUrl=new ArrayList<String>();
		allListCatName=new ArrayList<String>();
		
		allArrayCatid=new String[allListCatid.size()];
		allArrayCatname=new String[allListCatName.size()];
		allArrayCatImageurl=new String[allListCatImageUrl.size()];
		
		
		
		
		
		lsv_allvideos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				objAllBean=arrayOfAllvideos.get(position);
				int Catid=objAllBean.getCategoryId();
				Constant.CATEGORY_ID=objAllBean.getCategoryId();
				Log.e("cat_id",""+Catid);
				Constant.CATEGORY_TITLE=objAllBean.getCategoryName();
				
				Intent intcat=new Intent(getActivity(),CategoryItem.class);
				startActivity(intcat);
			}
		});
		
		
		if (JsonUtils.isNetworkAvailable(getActivity())) {
			new MyTask().execute(Constant.CATEGORY_URL);
		} else {
			showToast("No Network Connection!!!");
			 alert.showAlertDialog(getActivity(), "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
		}
		return rootView;
	}
	
	
	private	class MyTask extends AsyncTask<String, Void, String> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(getActivity());
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
				showToast("No data found from web!!!");
				 
			} else {

				try {
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(Constant.CATEGORY_ARRAY_NAME);
					JSONObject objJson = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						  objJson = jsonArray.getJSONObject(i);

						ItemAllVideos objItem = new ItemAllVideos();
						objItem.setCategoryName(objJson.getString(Constant.CATEGORY_NAME));
						objItem.setCategoryId(objJson.getInt(Constant.CATEGORY_CID));
						objItem.setCategoryImageurl(objJson.getString(Constant.CATEGORY_IMAGE));
						arrayOfAllvideos.add(objItem);
					 

					}
					 
				} catch (JSONException e) {
					e.printStackTrace();
				}
				 
				
				for(int j=0;j<arrayOfAllvideos.size();j++)
				{
					objAllBean=arrayOfAllvideos.get(j);
					
					allListCatid.add(String.valueOf(objAllBean.getCategoryId()));
					allArrayCatid=allListCatid.toArray(allArrayCatid);
					
					allListCatName.add(objAllBean.getCategoryName());
					allArrayCatname=allListCatName.toArray(allArrayCatname);
					
					allListCatImageUrl.add(objAllBean.getCategoryImageurl());
					allArrayCatImageurl=allListCatImageUrl.toArray(allArrayCatImageurl);
					
				}
				

  			setAdapterToListview();
  		}

		}
	}

	 
 
	public void setAdapterToListview() {
		objAdapter = new AllVideosListAdapter(getActivity(), R.layout.allvideos_lsv_item,
				arrayOfAllvideos);
		lsv_allvideos.setAdapter(objAdapter);
	}

	public void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
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
				// TODO Auto-generated method stub
	    		textlength=newText.length();
	    		arrayOfAllvideos.clear();
	    		
	    		for(int i=0;i<allArrayCatname.length;i++)
	    		{
	    			if(textlength <= allArrayCatname[i].length())
	        		{
	    				if(newText.toString().equalsIgnoreCase((String) allArrayCatname[i].subSequence(0, textlength)))
	        			{
	    					ItemAllVideos objItem = new ItemAllVideos();
	    					objItem.setCategoryId(Integer.parseInt(allArrayCatid[i]));
	    					objItem.setCategoryName(allArrayCatname[i]);
	    					objItem.setCategoryImageurl(allArrayCatImageurl[i]);
	    					
	    					arrayOfAllvideos.add(objItem);
	        			}
	        		}
	    		}
	    		
	    		setAdapterToListview();
	    	
	    		return false;
			}
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	    
	    
	}
	
	

}
