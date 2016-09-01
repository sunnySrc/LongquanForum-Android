package com.mobcent.discuz.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.litesuits.common.utils.BitmapUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sun on 2016/8/31.
 * 表情处理
 */

public class EmoticonHelper {

    /**
     * 表情链接
     */
    public static final String PATTEN_E_LINK = "\\[mobcent_phiz=([^\\]]+)\\]";
    public static final String PATTEN_E_LOCAL = "\\[([^\\]]+)\\]";

    public static  void addEmoticonSpans(final Context context, CharSequence text, final int height, final EmoticonCallback callback) {
        final SpannableStringBuilder builder = new SpannableStringBuilder(text);
        Matcher matcherLink = Pattern.compile(PATTEN_E_LINK).matcher(text);
        //1 替换网络表情
        final LinkedList linkedList = new LinkedList();

        while (matcherLink.find()) {
            String url =  matcherLink.group(1);
            final SpanCache spanCache = new SpanCache(builder,  matcherLink.start(), matcherLink.end());
            linkedList.add(spanCache);
            Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                    spanCache.setSpan(new ImageSpan(context, BitmapUtil.scaleImageTo(resource, height, height)));
                    spanCache.build();
                    linkedList.remove(spanCache);
                    if (linkedList.isEmpty()) {
                        // 图片加载完毕
                        callback.onEmoticonReady(builder);
                    }
                }
            });
        }

        // 2 加载本地表情
//        Matcher matcher = buildLocalEmoPatten().matcher(text);
//        while (matcher.find()) {
//            int resId =  getResMap().get(matcher.group());
//            builder.setSpan(new ImageSpan(context, resId),
//                    matcher.start(), matcher.end(),
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
        // 没有网络图片 回调
        if (linkedList.isEmpty()) {
            callback.onEmoticonReady(builder);
        }
    }

    /**
     * 表情和本地资源映射
     * @return
     */
    private HashMap<String,Integer> getResMap() {
        HashMap<String,Integer> mSmileyToRes = new HashMap();
        return mSmileyToRes;
    }

    /**
     *
     * @return
     */
    private Pattern buildLocalEmoPatten() {

        return null;

    }

    public interface EmoticonCallback {

        void onEmoticonReady(SpannableStringBuilder builder);
    }

    private static class SpanCache {
        int start;
        int end;
        SpannableStringBuilder builder;
        ImageSpan span;

        public SpanCache(SpannableStringBuilder builder, int start, int end) {
            this.builder = builder;
            this.end = end;
            this.start = start;
        }

        public void setSpan(ImageSpan span) {
            this.span = span;
        }

        public void build() {
            builder.setSpan(span,
                    start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
