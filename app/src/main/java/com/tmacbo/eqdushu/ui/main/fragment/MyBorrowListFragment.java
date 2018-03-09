package com.tmacbo.eqdushu.ui.main.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.util.FRToast;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseFragment;
import com.tmacbo.eqdushu.common.widget.RecyclerViewPager;
import com.tmacbo.eqdushu.model.book.GetMyBorrowData;
import com.tmacbo.eqdushu.presenter.book.MyBorrowPresenter;
import com.tmacbo.eqdushu.ui.book.QRScanActivity;
import com.tmacbo.eqdushu.ui.main.BorrowView;
import com.tmacbo.eqdushu.ui.main.adapter.LayoutAdapter;
import com.tmacbo.eqdushu.ui.settting.SettingActivity;
import com.tmacbo.eqdushu.utils.permissionlib.PerUtils;
import com.tmacbo.eqdushu.utils.permissionlib.PerimissionsCallback;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionEnum;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionManager;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @Author tmacbo
 * @Since on 2017/6/26  22:46
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class MyBorrowListFragment extends BaseFragment<MyBorrowPresenter>
    implements BorrowView<List<GetMyBorrowData>>, View.OnClickListener {

    @BindView(R.id.textViewScan)
    TextView textViewScan;

    /**
     * 医生列表
     */
    @BindView(R.id.gallery)
    RecyclerViewPager gallery;

    @BindView(R.id.gallery_empty)
    LinearLayout galleryEmptyView;

    LayoutAdapter mAdapter;

    @Override
    protected MyBorrowPresenter createPresenter() {
        return new MyBorrowPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_my_borrow;
    }

    @Override
    protected void onViewReallyCreated(View rootView) {
        super.onViewReallyCreated(rootView);
        textViewScan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getBookData();
    }

    private void getBookData() {
        mPresenter.getMyBorrowData(this);
    }

    @Override
    public void onClick(View view) {
        if (view == textViewScan) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PermissionManager.with(getActivity())
                                 .tag(10)
                                 .permission(PermissionEnum.CAMERA,
                                             PermissionEnum.WRITE_EXTERNAL_STORAGE)
                                 .callback(new PerimissionsCallback() {
                                     @Override
                                     public void onGranted(ArrayList<PermissionEnum> grantedList) {
                                         gotoSan();
                                     }

                                     @Override
                                     public void onDenied(ArrayList<PermissionEnum> deniedList) {
                                         PerUtils.PermissionDenied(getActivity(), deniedList);
                                     }
                                 })
                                 .checkAsk();
            } else {
                if (PerUtils.cameraIsCanUse()) {
                    gotoSan();
                } else {
                    PerUtils.PermissionDenied(getActivity(), PermissionEnum.CAMERA);
                }
            }
        }
    }

    private void gotoSan() {
        Intent intent = new Intent(getActivity(), QRScanActivity.class);
        intent.putExtra("from", "borrow");
        startActivityForResult(intent, 0);
    }

    private void initGallery(List<GetMyBorrowData> data) {

        mAdapter = new LayoutAdapter(getActivity(), gallery, data);
        LinearLayoutManager layout =
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        gallery.setLayoutManager(layout);
        gallery.setAdapter(mAdapter);
        gallery.setHasFixedSize(true);
        gallery.setLongClickable(false);
        gallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = gallery.getChildCount();
                int width = gallery.getChildAt(0).getWidth();
                int padding = (gallery.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;

                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.15f);
                        v.setScaleX(1 - rate * 0.15f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f
                                / v.getWidth();
                        }
                        v.setScaleY(0.85f + rate * 0.15f);
                        v.setScaleX(0.85f + rate * 0.15f);
                    }
                }
            }
        });
        gallery.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                //changeTo(newPosition + 1);
                //lastPosition = newPosition + 1;
            }
        });

        gallery.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (gallery.getChildCount() < 3) {
                    if (gallery.getChildAt(1) != null) {
                        if (gallery.getCurrentPosition() == 0) {
                            View v1 = gallery.getChildAt(1);
                            v1.setScaleY(0.85f);
                            v1.setScaleX(0.85f);
                        } else {
                            View v1 = gallery.getChildAt(0);
                            v1.setScaleY(0.85f);
                            v1.setScaleX(0.85f);
                        }
                    }
                } else {
                    if (gallery.getChildAt(0) != null) {
                        View v0 = gallery.getChildAt(0);
                        v0.setScaleY(0.85f);
                        v0.setScaleX(0.85f);
                    }
                    if (gallery.getChildAt(2) != null) {
                        View v2 = gallery.getChildAt(2);
                        v2.setScaleY(0.85f);
                        v2.setScaleX(0.85f);
                    }
                }
            }
        });
    }

    @Override
    public void onRequestSuccessData(List<GetMyBorrowData> data) {
        if (data != null && data.size() > 0) {
            galleryEmptyView.setVisibility(View.GONE);
            gallery.setVisibility(View.VISIBLE);
            initGallery(data);
        } else {
            galleryEmptyView.setVisibility(View.VISIBLE);
            gallery.setVisibility(View.GONE);
        }
        //mAdapter.setDataList(data);
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    public void getBookDataFailure(Throwable t) {

    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            getBookData();
        }
    }
}
