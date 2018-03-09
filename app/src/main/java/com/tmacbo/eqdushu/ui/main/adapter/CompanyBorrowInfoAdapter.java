package com.tmacbo.eqdushu.ui.main.adapter;

import android.content.Context;
import android.view.ViewGroup;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerAdapter;
import cn.share.jack.cygwidget.utils.CygView;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.ui.main.viewholder.BorrowInfoViewHolder;

/**
 * @Author tmacbo
 * @Since on 2017/11/8  22:34
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class CompanyBorrowInfoAdapter
    extends CygBaseRecyclerAdapter<BorrowInfoData, BorrowInfoViewHolder> {

    public CompanyBorrowInfoAdapter(Context context,
        OnItemClickListener<BorrowInfoViewHolder> listener) {
        super(context, listener);
    }

    @Override
    public BorrowInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BorrowInfoViewHolder(
            CygView.inflateLayout(getContext(), R.layout.item_manage_info, parent, false));
    }
}
