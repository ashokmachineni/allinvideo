package com.example.util;

import java.io.Serializable;

public class Constant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//this is the path of uploaded image of server where image store
		public static final String SERVER_IMAGE_UPFOLDER="http://viaviweb.in/Apps/youtube_vimeo_daily/images/thumbs/";
		//this url is used to get latest 15 image in 1st tab.here 15 indicate that display latest 15 image if you want change to another then do.
		public static final String LATEST_URL = "http://viaviweb.in/Apps/youtube_vimeo_daily/api.php?latest=15";
		//this url gives list of category in 2nd tab
		public static final String CATEGORY_URL = "http://viaviweb.in/Apps/youtube_vimeo_daily/api.php";
		//this url gives item of specific category.
		public static final String CATEGORY_ITEM_URL = "http://viaviweb.in/Apps/youtube_vimeo_daily/api.php?cat_id=";
		
	    public static final String SERVER_IMAGE_UPFOLDER_OWN="http://viaviweb.in/Apps/youtube_vimeo_daily/images/";
	    
		public static final String YOUTUBE_IMAGE_FRONT="http://img.youtube.com/vi/";
		public static final String YOUTUBE_IMAGE_BACK="/hqdefault.jpg";
		public static final String YOUTUBE_SMALL_IMAGE_BACK="/default.jpg";
		
		public static final String DAILYMOTION_IMAGE_PATH="http://www.dailymotion.com/thumbnail/video/";
		
		public static final String LATEST_ARRAY_NAME="HDvideo";
		public static final String LATEST_ID="id";
		public static final String LATEST_CATID="cat_id";
		public static final String LATEST_CAT_NAME="category_name";
		public static final String LATEST_VIDEO_URL="video_url";
		public static final String LATEST_VIDEO_ID="video_id";
		public static final String LATEST_VIDEO_DURATION="video_duration";
		public static final String LATEST_VIDEO_NAME="video_title";
		public static final String LATEST_VIDEO_DESCRIPTION="video_description";
		public static final String LATEST_IMAGE_URL="video_thumbnail";
		public static final String LATEST_VIDEOTYPE="video_type";
		
		
		
		public static final String CATEGORY_ARRAY_NAME="HDvideo";
		public static final String CATEGORY_NAME="category_name";
		public static final String CATEGORY_CID="cid";
		public static final String CATEGORY_IMAGE="category_image";
		
		
		//for title display in CategoryItemF
		public static String CATEGORY_TITLE;
		public static int CATEGORY_ID;
		
		
		public static final String CATEGORY_ITEM_ARRAY_NAME="HDvideo";
		public static final String CATEGORY_ITEM_ID="id";
		public static final String CATEGORY_ITEM_CATID="cat_id";
		public static final String CATEGORY_ITEM_CAT_NAME="category_name";
		public static final String CATEGORY_ITEM_VIDEO_URL="video_url";
		public static final String CATEGORY_ITEM_VIDEO_ID="video_id";
		public static final String CATEGORY_ITEM_VIDEO_DURATION="video_duration";
		public static final String CATEGORY_ITEM_VIDEO_NAME="video_title";
		public static final String CATEGORY_ITEM_VIDEO_DESCRIPTION="video_description";
		public static final String CATEGORY_ITEM_IMAGE_URL="video_thumbnail";
		public static final String CATEGORY_ITEM_VIDEOTYPE="video_type";

		 
	
}
