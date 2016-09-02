package com.mobcent.discuz.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.TopicReply;

import java.util.List;

/**
 * Created by sun on 2016/9/2.
 */
public class ReplyActionPopup extends PopupWindow implements View.OnClickListener {
    private final List<TopicReply.ManagePanelBean> managePanel;
    private final TopicReply reply;
    Context mContext;

    public ReplyActionPopup(Context context, TopicReply reply) {
        super(context);
        this.managePanel = reply.getManagePanel();
        this.reply = reply;
        mContext = context;
        setBackgroundDrawable(
                new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
        setOutsideTouchable(true);
        setFocusable(true);
        setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.topic_detail1_opt_menu, null);
        setContentView(view);

        boolean isOtherReply = managePanel == null || managePanel.isEmpty();

        View manageView = view.findViewById(R.id.menu_manage_layout);
        manageView.setVisibility(isOtherReply ? View.GONE : View.VISIBLE);
        View reportView = view.findViewById(R.id.menu_report_layout);
        reportView.setVisibility(isOtherReply ? View.GONE : View.VISIBLE);
        manageView.setOnClickListener(this);
        reportView.setOnClickListener(this);
        view.findViewById(R.id.menu_comment_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_comment_layout:
//                UIJumper.reply(mContext, reply.get);
                break;
            case R.id.menu_report_layout:
                //
                break;
            case R.id.menu_manage_layout:

                showManageDialog();
                //管理帖子
                break;

        }
        dismiss();
    }

    private void showManageDialog() {
        String[] names = new String[managePanel.size()];
        for (int i = 0; i < managePanel.size(); i++) {
            names[i] = managePanel.get(i).getTitle();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.mc_forum_topic_function))
                .setCancelable(true)
                .setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UIJumper.to(mContext, managePanel.get(which).getAction());
                    }
                });
        builder.create().show();
    }
}