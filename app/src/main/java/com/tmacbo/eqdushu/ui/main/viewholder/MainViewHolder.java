package com.tmacbo.eqdushu.ui.main.viewholder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerViewHolder;
import com.bumptech.glide.Glide;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.StringUtils;
import com.tmacbo.eqdushu.model.main.BookInfo;

/**
 * Created by jack on 2017/6/14
 */

public class MainViewHolder extends CygBaseRecyclerViewHolder<BookInfo> {

    private ImageView ivAvatar;
    private TextView tvTitle;
    private TextView tvContent;

    public MainViewHolder(View view) {
        super(view);
        ivAvatar = findView(R.id.im_iv_avatar);
        tvTitle = findView(R.id.im_tv_title);
        tvContent = findView(R.id.im_tv_content);
    }

    @Override
    protected void onItemDataUpdated(@Nullable BookInfo bookInfo) {
        if (null != bookInfo) {
            Glide.with(getContext())
                 .load(String.valueOf(bookInfo.getBookImg()))
                 .placeholder(R.drawable.ic_import_contacts_black_24dp)
                 .error(R.drawable.ic_import_contacts_black_24dp)
                 .into(ivAvatar);
            tvTitle.setText(StringUtils.stringTirm(String.valueOf(bookInfo.getBookAuthor())));
            tvContent.setText(String.valueOf(bookInfo.getBookTitle()));
        }
    }
}
