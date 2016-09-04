package com.mobcent.discuz.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobcent.common.ImageLoader;

/**
 * Created by sun on 2016/8/30.
 */

public class ViewHolder {
    // private View mConvertView;
    private SparseArray<View> mViews;

    private View mConvertView;


    public ViewHolder(View convertView) {
        mConvertView = convertView;

        mViews = new SparseArray<View>();
        convertView.setTag(this);
    }




    public static ViewHolder get(View convertView) {

        if (convertView == null) {
            return new ViewHolder(convertView);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     *
     *
     *
     *            parent view( also called 'convert view')
     * @param id
     *            child view's resId
     * @return 瑙嗗浘View
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {

        View childView = mViews.get(id);
        if (childView == null) {
            childView = mConvertView.findViewById(id);
            mViews.put(id, childView);
        }
        return (T) childView;
    }


    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setTextColor(Context context, int viewId, int colorId) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(colorId));
        return this;
    }


    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public ViewHolder setImageBackground(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }

    public ViewHolder setImage(int viewId, String url) {

        if (url == null) {
            return this;
        }
        ImageView view = getView(viewId);
        ImageLoader.load(url, view);
        return this;
    }


    public ViewHolder setCheckBox(int viewId, boolean state) {
        CheckBox box = getView(viewId);
        box.setChecked(state);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }

}
