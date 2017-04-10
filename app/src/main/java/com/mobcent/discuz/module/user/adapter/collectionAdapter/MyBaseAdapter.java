package com.mobcent.discuz.module.user.adapter.collectionAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MyBaseAdapter<T> extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	List<T> datasource;
	public MyBaseAdapter(Context context,List <T> datasource){
		super();
		this.context=context;
		this.datasource=datasource;
		this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datasource.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datasource.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
	
	public void addAll(List<T> list,boolean isClear){
		if(isClear){
			datasource.clear();
		}
		datasource.addAll(list);
		notifyDataSetChanged();
	}

}
