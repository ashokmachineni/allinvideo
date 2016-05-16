package com.example.adapter;

import java.util.List;

import com.example.imageloader.ImageLoader;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.viaviapp.allinonevideos.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LatestGridAdapter extends ArrayAdapter<ItemLatest>{
	
	
	private Activity activity;
	private List<ItemLatest> itemsLatest;
	private ItemLatest objLatestBean;
	private int row;
	public ImageLoader imageLoader; 
	 
	 public LatestGridAdapter(Activity act, int resource, List<ItemLatest> arrayList, int columnWidth) {
			super(act, resource, arrayList);
			this.activity = act;
			this.row = resource;
			this.itemsLatest = arrayList;
			imageLoader=new ImageLoader(activity);
			 
			 
		}
	 
	 @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(row, null);

				holder = new ViewHolder();
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
				return view;

			objLatestBean = itemsLatest.get(position);

			 
			 holder.imgv_latetst=(ImageView)view.findViewById(R.id.picture);
			 holder.name=(TextView)view.findViewById(R.id.text);
			 holder.txt_time=(TextView)view.findViewById(R.id.second);
			 holder.txt_category=(TextView)view.findViewById(R.id.text_category);
			 
			 if(objLatestBean.getVideoType().equals("local"))
			 {
				 imageLoader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER+objLatestBean.getImageUrl().toString(), holder.imgv_latetst);
			 }
			 else if(objLatestBean.getVideoType().equals("youtube"))
			 {
				 imageLoader.DisplayImage(Constant.YOUTUBE_IMAGE_FRONT+objLatestBean.getVideoId().toString()+Constant.YOUTUBE_SMALL_IMAGE_BACK, holder.imgv_latetst);
			 }
			 
			 else if(objLatestBean.getVideoType().equals("dailymotion"))
			 {
				 imageLoader.DisplayImage(Constant.DAILYMOTION_IMAGE_PATH+objLatestBean.getVideoId().toString(), holder.imgv_latetst);
			 }
			 
			 else if(objLatestBean.getVideoType().equals("vimeo"))
			 {
				 imageLoader.DisplayImage(objLatestBean.getImageUrl().toString(), holder.imgv_latetst);
			 }
			 
			 
			 holder.name.setText(objLatestBean.getVideoName().toString());
			 holder.txt_time.setText(objLatestBean.getDuration().toString());
			 holder.txt_category.setText(objLatestBean.getCategoryName().toString());
			
						
			return view;
			
		}

		public class ViewHolder {
		 
			public ImageView imgv_latetst;
			public  TextView name,txt_time,txt_category;
			 
		}

}
