package com.mobcent.discuz.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * 通用 简单 Adapter
 *
 * @param <T>
 * @author sundxing
 */
public abstract class ComAdapter<T> extends ArrayAdapter<T> {

    private int mItemResId;
    public ComAdapter(Context context, int resource, List<T> objects) {

        super(context, android.R.layout.simple_list_item_1, objects);
        mItemResId = resource;

    }

    /**
     *
     * @param objects
     */
    @SuppressLint("NewApi")
    public void updateListView(List<T> objects) {

        if (objects == null || objects.isEmpty()) {
            notifyDataSetInvalidated();
        }

        clear();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            addAll(objects);
        } else {
            setNotifyOnChange(false);
            for (T Obj : objects) {
                add(Obj);
            }
            notifyDataSetChanged();

        }

    }


    public abstract void customSet(ViewHolder holder, T item, int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mItemResId, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        customSet(holder, getItem(position), position);
        return holder.getConvertView();
    }


}

