package com.tmacbo.eqdushu.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import cn.share.jack.cyghttp.callback.BaseImpl;
import com.tmacbo.eqdushu.R;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by jack on 2017/6/13
 */

public abstract class BaseActivity<PRESENTER extends BasePresenter> extends FragmentActivity
    implements BaseImpl {

    private CompositeDisposable mCompositeDisposable;

    protected PRESENTER mPresenter;

    private boolean destroyed = false;

    private TextView tvTitle;

    private RelativeLayout titleLayout;

    private ImageView imageBackAction;

    /**
     * 屏幕检测
     */
    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        if (layoutRes() < 0) {
        }
        setContentView(layoutRes());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int layoutRes();

    protected PRESENTER createPresenter() {
        return null;
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public boolean addDisposable(Disposable disposable) {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.add(disposable);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        destroyed = true;
    }

    public boolean isDestroyedCompatible() {
        return destroyed || super.isFinishing();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        titleLayout = (RelativeLayout)findViewById(R.id.head_activity_view);
        tvTitle = (TextView)findViewById(R.id.textViewTitle);
        imageBackAction = (ImageView)findViewById(R.id.imageBackAction);
    }

    public void addBackAction() {
        imageBackAction.setVisibility(View.VISIBLE);
        imageBackAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 设置标题栏的title
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /**
     * 获取宽度 单位px
     *
     * @return
     */
    public int getWidthPixels() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 获取高度 单位 px
     *
     * @return
     */
    public int getHeightPixels() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public float getDensity() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

}
