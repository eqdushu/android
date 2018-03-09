package com.tmacbo.eqdushu.ui.main.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.util.FRToast;
import cn.share.jack.cygwidget.recyclerview.PtrRecyclerViewUIComponent;
import cn.share.jack.cygwidget.recyclerview.adapter.CygBaseRecyclerAdapter;
import cn.share.jack.cygwidget.refersh.OnPullToRefreshListener;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseFragment;
import com.tmacbo.eqdushu.model.main.BookInfo;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.presenter.main.MainPresenter;
import com.tmacbo.eqdushu.ui.book.BookDetailActivity;
import com.tmacbo.eqdushu.ui.book.QRScanActivity;
import com.tmacbo.eqdushu.ui.login.LoginActivity;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.MainView;
import com.tmacbo.eqdushu.ui.main.adapter.MainAdapter;
import com.tmacbo.eqdushu.ui.main.viewholder.MainViewHolder;
import com.tmacbo.eqdushu.ui.msg.MessageActivity;
import com.tmacbo.eqdushu.utils.permissionlib.PerUtils;
import com.tmacbo.eqdushu.utils.permissionlib.PerimissionsCallback;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionEnum;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionManager;
import io.reactivex.disposables.Disposable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * @Author tmacbo
 * @Since on 2017/6/26  21:30
 * @mail tang_bo@hotmail.com
 * @Description 書庫主頁
 */

public class HomeFragment extends BaseFragment<MainPresenter>
    implements MainView<List<BookInfo>>, View.OnClickListener {

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.imageMsg)
    ImageView imageMsg;

    @BindView(R.id.am_ptr_framelayout)
    PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent;

    @BindView(R.id.multipleFloatAction)
    FloatingActionsMenu multipleFloatAction;

    @BindView(R.id.scanFloatBtn)
    FloatingActionButton scanFloatBtn;

    private MainAdapter mAdapter;
    private List<BookInfo> mBookInfos;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onViewReallyCreated(View rootView) {
        super.onViewReallyCreated(rootView);

        scanFloatBtn.setOnClickListener(this);
        imageMsg.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        if (UserDao.getInstance().getUserInfo() != null
            && UserDao.getInstance().getUserInfo().getCompany() != null) {
            String companyName = UserDao.getInstance().getUserInfo().getCompany();
            textViewTitle.setText(companyName + "书库");
        }

        mAdapter = new MainAdapter(getActivity(),
                                   new CygBaseRecyclerAdapter.OnItemClickListener<MainViewHolder>() {
                                       @Override
                                       public void onItemClick(int position) {
                                           String isbn = "";
                                           if (mBookInfos != null && mBookInfos.size() > 0) {
                                               isbn = mBookInfos.get(position).getIsbn();
                                           }
                                           Intent intent =
                                               new Intent(getActivity(), BookDetailActivity.class);
                                           intent.putExtra("isbn", isbn);
                                           startActivityForResult(intent, 0);
                                       }
                                   });
        ptrRecyclerViewUIComponent.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //ptrRecyclerViewUIComponent.setRecyclerViewDivider(new RecyclerViewDivider(getActivity()));
        ptrRecyclerViewUIComponent.setAdapter(mAdapter);

        ptrRecyclerViewUIComponent.delayRefresh(100);
        ptrRecyclerViewUIComponent.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                getBookData();
            }
        });
    }


    private void getBookData() {
        mPresenter.getBookData(this);
    }

    @Override
    public void onClick(View view) {
        if (view == scanFloatBtn) {
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
        } else if (view == imageMsg) {
            startActivity(new Intent(getActivity(), MessageActivity.class));
        }
    }

    private void gotoSan() {
        Intent intent = new Intent(getActivity(), QRScanActivity.class);
        intent.putExtra("from", "addBook");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onRequestSuccessData(List<BookInfo> data) {
        mAdapter.setDataList(data);
        mBookInfos = data;
        ptrRecyclerViewUIComponent.refreshComplete();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
        ptrRecyclerViewUIComponent.refreshComplete();
    }

    @Override
    public void getBookDataFailure(Throwable t) {

    }

    @Override
    public void getRewardGas(RewardInfoData data) {
    }

    @Override
    public void getTokenEorro() {
        UserDao.getInstance().deleteAll(UserDao.class);
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
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
