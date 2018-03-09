/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tmacbo.eqdushu.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.StringUtils;
import com.tmacbo.eqdushu.model.book.GetMyBorrowData;
import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private final Context mContext;
    private List<GetMyBorrowData> data;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookAuthor;
        private TextView borrowTime;
        public ImageView bookImage;

        public SimpleViewHolder(View view) {
            super(view);
            //layoutBg = (RelativeLayout)view.findViewById(R.id.gallery_layout_topbg);
            bookImage = (ImageView)view.findViewById(R.id.galleryBookImage);
            bookAuthor = (TextView)view.findViewById(R.id.galleryBookAuthor);
            bookName = (TextView)view.findViewById(R.id.galleryBookName);
            borrowTime = (TextView)view.findViewById(R.id.galleryBorrowTime);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView,
        List<GetMyBorrowData> dataInfo) {
        mContext = context;
        data = dataInfo;
    }

    public void setData(List<GetMyBorrowData> dataInfo) {
        data = dataInfo;
        notifyDataSetChanged();
    }

    public void addItem(int position, GetMyBorrowData item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view =
            LayoutInflater.from(mContext).inflate(R.layout.gallery_item_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        Glide.with(mContext)
             .load(String.valueOf(data.get(position).getBookImg()))
             .placeholder(R.drawable.ic_import_contacts_black_24dp)
             .error(R.drawable.ic_import_contacts_black_24dp)
             .into(holder.bookImage);
        holder.bookAuthor.setText(StringUtils.stringTirm(data.get(position).getBookAuthor()));
        holder.bookName.setText(data.get(position).getBookTitle());

        holder.borrowTime.setText(
            "借阅时间:" + data.get(position).getBorrowBegDt() + " ~ " + data.get(position)
                                                                        .getBorrowEndDt());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
