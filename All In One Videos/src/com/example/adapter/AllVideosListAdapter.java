package com.example.adapter;

import java.util.List;

import com.example.imageloader.ImageLoader;
import com.example.item.ItemAllVideos;
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

public class AllVideosListAdapter extends ArrayAdapter<ItemAllVideos> {
	
	private Activity activity;
	private List<ItemAllVideos> itemsAllphotos;
	private ItemAllVideos objAllBean;
	private int row;
	public ImageLoader imageLoader; 
	 
	
	public AllVideosListAdapter(Activity act, int resource, List<ItemAllVideos> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.itemsAllphotos = arrayList;
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

		if ((itemsAllphotos == null) || ((position + 1) > itemsAllphotos.size()))
			return view;

		objAllBean = itemsAllphotos.get(position);
		
		holder.txt=(TextView)view.findViewById(R.id.txt_allvideos_categty);
		holder.img_cat=(ImageView)view.findViewById(R.id.img_cat);
		holder.txt.setText(objAllBean.getCategoryName().toString());

		imageLoader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER+objAllBean.getCategoryImageurl().toString(), holder.img_cat);
		
		return view;
		
	}

	public class ViewHolder {
	 
		public TextView txt;
		public ImageView img_cat;
	}

}
