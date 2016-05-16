package com.example.adapter;

import java.util.List;

import com.example.imageloader.ImageLoader;
import com.example.item.ItemCategory;
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

public class CategoryItemGridAdapter extends ArrayAdapter<ItemCategory>{
	
	private Activity activity;
	private List<ItemCategory> itemsCategory;
	private ItemCategory objCategoryBean;
	private int row;
	public ImageLoader imageLoader; 
	 
	 public CategoryItemGridAdapter(Activity act, int resource, List<ItemCategory> arrayList, int columnWidth) {
			super(act, resource, arrayList);
			this.activity = act;
			this.row = resource;
			this.itemsCategory = arrayList;
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

			if ((itemsCategory == null) || ((position + 1) > itemsCategory.size()))
				return view;

			objCategoryBean = itemsCategory.get(position);

			 
			 holder.imgv_latetst=(ImageView)view.findViewById(R.id.picture);
			 holder.name=(TextView)view.findViewById(R.id.text);
			 holder.txt_time=(TextView)view.findViewById(R.id.second);
			 holder.txt_category=(TextView)view.findViewById(R.id.text_category);
			 

			 
			 if(objCategoryBean.getVideoType().equals("local"))
			 {
				 imageLoader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER+objCategoryBean.getImageUrl().toString(), holder.imgv_latetst);
			 }
			 else if(objCategoryBean.getVideoType().equals("youtube"))
			 {
				 imageLoader.DisplayImage(Constant.YOUTUBE_IMAGE_FRONT+objCategoryBean.getVideoId().toString()+Constant.YOUTUBE_SMALL_IMAGE_BACK, holder.imgv_latetst);
			 }
			 
			 else if(objCategoryBean.getVideoType().equals("dailymotion"))
			 {
				 imageLoader.DisplayImage(Constant.DAILYMOTION_IMAGE_PATH+objCategoryBean.getVideoId().toString(), holder.imgv_latetst);
			 }
			 
			 else if(objCategoryBean.getVideoType().equals("vimeo"))
			 {
				 imageLoader.DisplayImage(objCategoryBean.getImageUrl().toString(), holder.imgv_latetst);
			 }
	
			 holder.name.setText(objCategoryBean.getVideoName().toString());
			 holder.txt_time.setText(objCategoryBean.getDuration().toString());
			 holder.txt_category.setText(objCategoryBean.getCategoryName().toString());
			 
			return view;
			
		}

		public class ViewHolder {
		 
			public ImageView imgv_latetst;
			public  TextView name,txt_time,txt_category;
			 
		}
}
