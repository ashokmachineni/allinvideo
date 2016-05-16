package com.viaviapp.allinonevideos;

import java.util.List;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.dailymotion.DailyMotionPlay;
import com.example.favorite.DatabaseHandler;
import com.example.favorite.Pojo;
import com.example.imageloader.ImageLoader;
import com.example.play.OpenYouTubePlayerActivity;
import com.example.util.Constant;
import com.example.vimeo.Vimeo;
import com.example.youtube.YoutubePlay;
import com.startapp.android.publish.StartAppAd;
import com.viaviapp.allinonevideos.R;

public class VideoPlay extends SherlockActivity{
	
	int position;
	String[] allArrayVideo,allArrayVideoCatName,allArrayVideoType;
	String[] allArrayVideoId,allArrayVideoCatId,allArrayVideourl,allArrayVideoName,allArrayVideoDuration,allArrayVideoDesc,allArrayImageUrl;
	
	ImageView vp_imageview;
	ViewPager viewpager;
	public ImageLoader imageLoader; 
	int TOTAL_IMAGE;
	public DatabaseHandler db;
	private Menu menu;
	TextView text_title,text_catname,text_duration;
	String vid,video_id,videoname,videourl,videoduration,videocatname,videocatid,videodesc,videoimageurl,videotype;
	String VideoPlayId;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id,false));
        setContentView(R.layout.videoplay);
        StartAppAd.showSlider(this);
     
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		db = new DatabaseHandler(this);
		setTitle("");
		Intent i=getIntent();
		
		position=i.getIntExtra("POSITION", 0);
		allArrayVideo=i.getStringArrayExtra("VIDEO_ID");
		allArrayVideoCatName=i.getStringArrayExtra("VIDEO_CATNAME");
		allArrayVideoCatId=i.getStringArrayExtra("VIDEO_CATID");
		allArrayVideourl=i.getStringArrayExtra("VIDEO_URL");
		allArrayVideoName=i.getStringArrayExtra("VIDEO_NAME");
		allArrayVideoId=i.getStringArrayExtra("VIDEO_CID");
		allArrayVideoDuration=i.getStringArrayExtra("VIDEO_DURATION");
		allArrayVideoDesc=i.getStringArrayExtra("VIDEO_DISCRIPTION");
		allArrayImageUrl=i.getStringArrayExtra("VIDEO_IMAGE_URL");
		allArrayVideoType=i.getStringArrayExtra("VIDEO_TYPE");
		
		TOTAL_IMAGE=allArrayVideo.length-1;
		viewpager=(ViewPager)findViewById(R.id.video_slider);
		imageLoader=new ImageLoader(getApplicationContext());

		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewpager.setAdapter(adapter);

		boolean found = false;
		int j1=0;
		for(int i1=0;i1<allArrayVideoId.length;i1++)
		{
			if(allArrayVideoId[i1].contains(String.valueOf(position)))
			{
				found=true;
				j1=i1;
				break; 
			}
		}
		if(found)
		{
			viewpager.setCurrentItem(j1);
		}

	
		 
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				

				position=viewpager.getCurrentItem();
				vid=allArrayVideoId[position];
				
				List<Pojo> pojolist=db.getFavRow(vid);
				if(pojolist.size()==0)
				{
					 menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav));
				}
				else
				{	
					if(pojolist.get(0).getVId().equals(vid))
					{
						 menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav_hover));
					}
					
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int position) {
				// TODO Auto-generated method stub
				 
				
			}
			
			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO Auto-generated method stub
				
				
			}
		});
	 	
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.video_menu, menu);
        this.menu = menu;
        FirstFav();
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
        {
        case android.R.id.home: 
            onBackPressed();
            return true;
            
        case R.id.menu_back:
            position=viewpager.getCurrentItem();
			position--;
			if (position < 0) {
				position = 0;
			}
			viewpager.setCurrentItem(position);
          return true;  

        case R.id.menu_next:
        	
          	 position=viewpager.getCurrentItem();
   			  position++;
   			if (position == TOTAL_IMAGE) {
   				position = TOTAL_IMAGE;
   			}
   			viewpager.setCurrentItem(position);
         	return true;
         	
         case R.id.menu_fav:
        	 
        	 position=viewpager.getCurrentItem();
        	 vid=allArrayVideoId[position];
        	 
        	 List<Pojo> pojolist=db.getFavRow(vid);
 			if(pojolist.size()==0)
 			{
 				AddtoFav(position);//if size is zero i.e means that record not in database show add to favorite 
 			}
 			else
 			{	
 				if(pojolist.get(0).getVId().equals(vid))
 				{
 					RemoveFav(position);
 				}
 				
 			}
        	 
        	 return true;
        	 
         case R.id.menu_share:
        	 
        	 position=viewpager.getCurrentItem();
        	videoname=allArrayVideoName[position];
         	videourl=allArrayVideourl[position];
         	
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi I am Just Watched This Video"+videoname+" Watch Here "+videourl);
				sendIntent.setType("text/plain");
				startActivity(sendIntent); 
        	 
        	 return true;
            
        default:
            return super.onOptionsItemSelected(menuItem);
        }
		
	}
	
	
	public void AddtoFav(int position)
	{
		vid=allArrayVideoId[position];
    	video_id=allArrayVideo[position];
    	videoname=allArrayVideoName[position];
    	videourl=allArrayVideourl[position];
    	videoduration=allArrayVideoDuration[position];
    	videocatname=allArrayVideoCatName[position];
    	videocatid=allArrayVideoCatId[position];
    	videodesc=allArrayVideoDesc[position];
    	videoimageurl=allArrayImageUrl[position];
    	videotype=allArrayVideoType[position];
 
    	db.AddtoFavorite(new Pojo(vid, video_id, videoname, videourl,videoduration,videocatname,videocatid,videodesc,videoimageurl,videotype));
    	Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
    	menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav_hover));
	}
	
	//remove from favorite
	public void RemoveFav(int position)
	{
		vid=allArrayVideoId[position];
		db.RemoveFav(new Pojo(vid));
		Toast.makeText(getApplicationContext(), "Removed from Favorite", Toast.LENGTH_SHORT).show();
		menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav));
		
	}
	
	public void FirstFav()
	{
		int first=viewpager.getCurrentItem();
		String Image_id=allArrayVideoId[first];
		
		List<Pojo> pojolist=db.getFavRow(Image_id);
		if(pojolist.size()==0)
		{
			 menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav));
			 
		}
		else
		{	
			if(pojolist.get(0).getVId().equals(Image_id))
			{
				 menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.fav_hover));
				 
			}
			
		}
	}
	
	private class ImagePagerAdapter extends PagerAdapter {
		   
		private LayoutInflater inflater;
		
		public ImagePagerAdapter() {
			// TODO Auto-generated constructor stub
			
			inflater = getLayoutInflater();
		}
		
		
	     @Override
	    public int getCount() {
	      return allArrayVideo.length;
	     
	    }

	    @Override
	    public boolean isViewFromObject(View view, Object object) {
	    	return view.equals(object);
	    }

	    @Override
	    public Object instantiateItem(ViewGroup container, final int position) {
	    
	      View imageLayout = inflater.inflate(R.layout.newpager_item, container, false);
	  	  assert imageLayout != null;
	  	  
	  	  ImageView vp_imageview=(ImageView)imageLayout.findViewById(R.id.picture);
	  	  TextView txt_name=(TextView)imageLayout.findViewById(R.id.text);
	  	  TextView txt_time=(TextView)imageLayout.findViewById(R.id.second);
	  	  TextView txt_cat=(TextView)imageLayout.findViewById(R.id.text_category);
	  	  TextView txt_desc=(TextView)imageLayout.findViewById(R.id.text_desc);
	  	 
	  	 
	  	 final String VideoType=allArrayVideoType[position];
	  	final String VideoPlayId=allArrayVideo[position];
	  	final String VideoPlayImageUrl=allArrayImageUrl[position];
	  	final String VideoPlayUrl=allArrayVideourl[position];
	  	
	  	
	  	
	  	 
	  	if(VideoType.equals("local"))
		{
	  		imageLoader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER_OWN+VideoPlayImageUrl, vp_imageview);
		}
		else if(VideoType.equals("youtube"))
		{
			imageLoader.DisplayImage(Constant.YOUTUBE_IMAGE_FRONT+VideoPlayId+Constant.YOUTUBE_IMAGE_BACK, vp_imageview);
		}
		else if(VideoType.equals("dailymotion"))
		{
			imageLoader.DisplayImage(Constant.DAILYMOTION_IMAGE_PATH+VideoPlayId, vp_imageview);
		}
		else if(VideoType.equals("vimeo"))
		{
	  		imageLoader.DisplayImage(VideoPlayImageUrl, vp_imageview);
		}
	  	
	 
	      txt_name.setText(allArrayVideoName[position]);
	      txt_time.setText(allArrayVideoDuration[position]);
	      txt_cat.setText(allArrayVideoCatName[position]);
	      txt_desc.setText(allArrayVideoDesc[position]);
	      
	     vp_imageview.setOnClickListener(new View.OnClickListener() {

	    	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
	    		if(VideoType.equals("local"))
				{
			  		Intent lVideoIntent = new Intent(null, Uri.parse("file://" + VideoPlayUrl), VideoPlay.this, OpenYouTubePlayerActivity.class);
					startActivity(lVideoIntent);
				}
				else if(VideoType.equals("youtube"))
				{
					Intent i = new Intent(VideoPlay.this,YoutubePlay.class);
					i.putExtra("id", VideoPlayId);
					startActivity(i);
				}
				else if(VideoType.equals("dailymotion"))
				{
					Intent i = new Intent(VideoPlay.this,DailyMotionPlay.class);
					i.putExtra("id", VideoPlayId);
					startActivity(i);
				}
				else if(VideoType.equals("vimeo"))
				{
					Intent i = new Intent(VideoPlay.this,Vimeo.class);
					i.putExtra("id", VideoPlayId);
					startActivity(i);
				}
			 }
		});

	     container.addView(imageLayout, 0);
			return imageLayout;
	      
	    }

	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	      ((ViewPager) container).removeView((View) object);
	    }
	  }
	 @Override
	    protected void onPause() {
	       
	        super.onPause();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        
	    }

	    @Override
	    protected void onDestroy() {
	        
	        super.onDestroy();
	    }
}
