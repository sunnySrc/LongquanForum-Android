package com.mobcent.discuz.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by sun on 2016/8/31.
 */

public class ItemWebView extends WebView{
    private boolean isAttached;
    private String mUrl;

    public ItemWebView(Context context) {
        super(context);
        init();
    }



    public ItemWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttached = true;
        if (mUrl != null) {
            loadUrl(mUrl);
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    @Override
    public void loadUrl(String url) {
        if (isAttached) {
            super.loadUrl(url);
        } else {
            mUrl = url;
        }
    }
}
