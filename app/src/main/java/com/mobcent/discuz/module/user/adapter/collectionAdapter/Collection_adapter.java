package com.mobcent.discuz.module.user.adapter.collectionAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import discuz.com.net.service.model.bean.collectionBean.CollectionList;

public class Collection_adapter extends MyBaseAdapter<CollectionList> {
	InputStream inputStream = null;
	public Collection_adapter(Context context, List<CollectionList> datasource) {
		super(context, datasource);
		
	}
	
	@Override
	public int getItemViewType(int position) {
		return position;
		
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CollectionList collectionList =(CollectionList) getItem(position);
				convertView = inflater.inflate(R.layout.item_collection_follow, parent,false);
		ViewHolder vh = new ViewHolder();
		URL url = null;
		try {
			url = new URL(collectionList.getPicPath());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		vh.title = (TextView) convertView.findViewById(R.id.item_collection_title);
				vh.date = (TextView) convertView.findViewById(R.id.item_collection_date);
				vh.name = (TextView) convertView.findViewById(R.id.item_collection_name);
				vh.readed = (TextView) convertView.findViewById(R.id.item_collection_readed);
				vh.cream = (ImageView) convertView.findViewById(R.id.item_collection_imageview_cream);
				vh.head = (ImageView) convertView.findViewById(R.id.item_collection_imageview_head);
				convertView.setTag(vh);
		vh.title.setText(collectionList.getTitle());
		vh.date.setText(collectionList.getLastReplyDate());
		vh.name.setText(collectionList.getBoardName());
		vh.readed.setText(collectionList.getEssence());
		Glide.with(context).load(collectionList.getPicPath()).into(vh.head);
		Glide.with(context).load(collectionList.getUseravatar()).into(vh.cream);
//		vh.cream.setImageBitmap(bitmap);
//		vh.head.setImageBitmap();
		return convertView;
	}
	
	private class ViewHolder{
		ImageView head,cream;
		TextView title,date,name,readed;
	}

	

}
