package com.tmacbo.eqdushu.ui.main.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseFragment;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.presenter.main.MainPresenter;
import com.tmacbo.eqdushu.ui.book.QRScanActivity;
import com.tmacbo.eqdushu.ui.main.MainView;
import com.tmacbo.eqdushu.ui.main.adapter.CompanyBorrowInfoAdapter;
import com.tmacbo.eqdushu.ui.main.viewholder.BorrowInfoViewHolder;
import com.tmacbo.eqdushu.utils.permissionlib.PerUtils;
import com.tmacbo.eqdushu.utils.permissionlib.PerimissionsCallback;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionEnum;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.share.jack.cygwidget.recyclerview.PtrRecyclerViewUIComponent;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerAdapter;
import cn.share.jack.cygwidget.refersh.OnPullToRefreshListener;
import io.reactivex.disposables.Disposable;

/**
 * @Author tmacbo
 * @Since on 2017/8/10  11:35
 * @mail tang_bo@hotmail.com
 * @Description 管理借阅
 */

public class ManageFragment extends BaseFragment<MainPresenter>
    implements MainView<List<BorrowInfoData>>, View.OnClickListener {
    @BindView(R.id.ptrCompanyBorrowInfo)
    PtrRecyclerViewUIComponent ptrCompanyBorrowInfo;

    @BindView(R.id.textViewBackBook)
    TextView textViewBackBook;

    CompanyBorrowInfoAdapter mAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_manage;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initData() {
        textViewBackBook.setOnClickListener(this);

        mAdapter = new CompanyBorrowInfoAdapter(getContext(),
                                                new CygBaseRecyclerAdapter.OnItemClickListener<BorrowInfoViewHolder>() {
                                                    @Override
                                                    public void onItemClick(int position) {

                                                    }
                                                });
        ptrCompanyBorrowInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        //ptrRecyclerViewUIComponent.setRecyclerViewDivider(new RecyclerViewDivider(getActivity()));
        ptrCompanyBorrowInfo.setAdapter(mAdapter);

        ptrCompanyBorrowInfo.delayRefresh(100);
        ptrCompanyBorrowInfo.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                getBorrowData();
            }
        });
    }

    private void getBorrowData() {
        mPresenter.getBorrowData(this);
    }

    @Override
    public void onClick(View view) {
        if(view==textViewBackBook){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PermissionManager.with(getActivity())
                        .tag(10)
                        .permission(PermissionEnum.CAMERA,
                                PermissionEnum.WRITE_EXTERNAL_STORAGE)
                        .callback(new PerimissionsCallback() {
                            @Override
                            public void onGranted(ArrayList<PermissionEnum> grantedList) {
                                gotoBackSan();
                            }

                            @Override
                            public void onDenied(ArrayList<PermissionEnum> deniedList) {
                                PerUtils.PermissionDenied(getActivity(), deniedList);
                            }
                        })
                        .checkAsk();
            } else {
                if (PerUtils.cameraIsCanUse()) {
                    gotoBackSan();
                } else {
                    PerUtils.PermissionDenied(getActivity(), PermissionEnum.CAMERA);
                }
            }
        }
    }

    /**
     * 跳转扫码还书操作
     */
    private void gotoBackSan() {
        Intent intent = new Intent(getActivity(), QRScanActivity.class);
        intent.putExtra("from", "backBook");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onRequestSuccessData(List<BorrowInfoData> data) {
        mAdapter.setDataList(data);
        ptrCompanyBorrowInfo.refreshComplete();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        ptrCompanyBorrowInfo.refreshComplete();
    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        return false;
    }

    @Override
    public void getBookDataFailure(Throwable t) {

    }

    @Override
    public void getRewardGas(RewardInfoData data) {

    }

    @Override
    public void getTokenEorro() {

    }
}