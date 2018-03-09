package com.tmacbo.eqdushu.ui.main.viewholder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerViewHolder;
import com.bumptech.glide.Glide;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;

/**
 * @Author tmacbo
 * @Since on 2017/11/8  22:35
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BorrowInfoViewHolder extends CygBaseRecyclerViewHolder<BorrowInfoData> {
    private ImageView bookImage;
    private ImageView circleHeader;
    private TextView textViewTile;
    private TextView textViewBorrowTime;

    private TextView textViewLeftTime;

    public BorrowInfoViewHolder(View view) {
        super(view);
        bookImage = findView(R.id.bookImage);
        circleHeader = findView(R.id.circleHeader);
        textViewTile = findView(R.id.textBookTitle);
        textViewBorrowTime = findView(R.id.textBorrowTime);
        textViewLeftTime = findView(R.id.textBorrowLeft);
    }

    @Override
    protected void onItemDataUpdated(@Nullable BorrowInfoData borrowInfoData) {
        textViewTile.setText(borrowInfoData.getBookTitle());
        textViewBorrowTime.setText(
            "借阅时间:" + borrowInfoData.getBorrowBegDt() + "~" + borrowInfoData.getBorrowEndDt());
        Glide.with(getContext())
             .load(String.valueOf(borrowInfoData.getBookImg()))
             .placeholder(R.drawable.ic_import_contacts_black_24dp)
             .error(R.drawable.ic_import_contacts_black_24dp)
             .into(bookImage);
    }
}
