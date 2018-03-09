package com.tmacbo.eqdushu.ui.book;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.util.FRToast;
import com.bumptech.glide.Glide;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.StringUtils;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.model.book.BookDetailData;
import com.tmacbo.eqdushu.presenter.book.BookDetailPresenter;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  15:19
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BookDetailActivity extends BaseActivity<BookDetailPresenter>
    implements BookDetailView<BaseResponse<BookDetailData>> {

    @BindView(R.id.textBookDetailImage)
    ImageView textBookDetailImage;

    @BindView(R.id.textBookDetailTitle)
    TextView textBookDetailTitle;

    @BindView(R.id.textBookDetailAuthor)
    TextView textBookDetailAuthor;

    @BindView(R.id.textBookDetailPublic)
    TextView textBookDetailPublic;

    @BindView(R.id.textBookIntroduce)
    TextView textBookIntroduce;

    private String isbnStr;

    @Override
    protected int layoutRes() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    @Override
    protected void initView() {
        isbnStr = getIntent().getStringExtra("isbn");
    }

    @Override
    protected void initData() {
        addBackAction();
        mPresenter.getBookDetailInfo(this);
    }

    @Override
    public void onRequestSuccessData(BaseResponse<BookDetailData> data) {
        if (data.getData() != null) {
            setTitle(data.getData().getBookTitle());
            textBookDetailTitle.setText("书名：" + data.getData().getBookTitle());
            textBookDetailAuthor.setText(
                "作者:" + StringUtils.stringTirm(data.getData().getBookAuthor()));
            if (data.getData().getBookPublish() != null) {
                textBookDetailPublic.setText("出版社" + data.getData().getBookPublish());
            }
            textBookIntroduce.setText("简介:\n\n" + data.getData().getBookSummary());
            Glide.with(getContext())
                 .load(String.valueOf(data.getData().getBookImg()))
                 .placeholder(R.drawable.ic_import_contacts_black_24dp)
                 .error(R.drawable.ic_import_contacts_black_24dp)
                 .into(textBookDetailImage);
        }
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    public String getIsBn() {
        return isbnStr;
    }
}
