package com.tmacbo.eqdushu.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import cn.share.jack.cyghttp.callback.BaseImpl;

/**
 * Created by jack on 2017/6/14
 */

public abstract class BaseFragment<PRESENTER extends BasePresenter> extends Fragment
    implements BaseImpl {

    private static final String TAG = "BaseFragment";
    protected PRESENTER mPresenter;

    protected abstract int layoutRes();

    protected PRESENTER createPresenter() {
        return null;
    }

    protected void onViewReallyCreated(View view) {
    }

    protected abstract void initData();

    private View rootView;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (null == rootView) {
            rootView = inflater.inflate(layoutRes(), null);
            ButterKnife.bind(this, rootView);
            onViewReallyCreated(rootView);
        } else {
            ViewGroup parent = (ViewGroup)rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        if (null == mPresenter) {
            mPresenter = createPresenter();
        }

        initData();

        return rootView;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public <VIEW extends View> VIEW findView(int id) {
        if (null != rootView) {
            View child = rootView.findViewById(id);
            try {
                return (VIEW)child;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "findView: " + String.valueOf(e.getMessage()));
                return null;
            }
        }
        return null;
    }
}