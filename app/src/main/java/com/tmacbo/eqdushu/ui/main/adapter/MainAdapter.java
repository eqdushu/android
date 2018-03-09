package com.tmacbo.eqdushu.ui.main.adapter;

import android.content.Context;
import android.view.ViewGroup;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerAdapter;
import cn.share.jack.cygwidget.utils.CygView;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.model.main.BookInfo;
import com.tmacbo.eqdushu.ui.main.viewholder.MainViewHolder;

/**
 * Created by jack on 2017/6/14
 */

public class MainAdapter extends CygBaseRecyclerAdapter<BookInfo, MainViewHolder> {

    public MainAdapter(Context context, OnItemClickListener<MainViewHolder> listener) {
        super(context, listener);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(
            CygView.inflateLayout(getContext(), R.layout.item_book, parent, false));
    }
}
