package com.tmacbo.eqdushu.ui.main.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseFragment;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.presenter.PersonalInfoPresenter;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.PersonalInfoView;
import com.tmacbo.eqdushu.ui.settting.SettingActivity;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * @Author tmacbo
 * @Since on 2017/6/26  22:46
 * @mail tang_bo@hotmail.com
 * @Description 个人信息页面
 */

public class PersonalFragment extends BaseFragment<PersonalInfoPresenter>
    implements View.OnClickListener, PersonalInfoView<RewardInfoData> {

    @BindView(R.id.flMyBorrowSetting)
    FrameLayout flMyBorrowSetting;

    @BindView(R.id.flMyCompanyInfo)
    FrameLayout flMyCompanyInfo;

    @BindView(R.id.imageSetting)
    ImageView mImageViewSetting;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewAsset)
    TextView textViewAsset;
    private static int GOTO_SETTING = 1001;

    public PersonalInfoPresenter createPresenter() {
        return new PersonalInfoPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_personal;
    }

    private void getAccountInfoData() {
        mPresenter.getRewardInfo(this);
    }

    @Override
    protected void onViewReallyCreated(View view) {
        super.onViewReallyCreated(view);
    }

    @Override
    protected void initData() {
        getAccountInfoData();
        //mPresenter.getTest(this);
        if (UserDao.getInstance().getUserInfo().getUsername().isEmpty()) {

        }
        flMyCompanyInfo.setOnClickListener(this);
        flMyBorrowSetting.setOnClickListener(this);

        mImageViewSetting.setOnClickListener(this);
    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == mImageViewSetting) {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivityForResult(intent, GOTO_SETTING);
        } else if (view == flMyCompanyInfo) {

        } else if (view == flMyBorrowSetting) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOTO_SETTING && resultCode == RESULT_OK) {
            getActivity().finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestSuccessData(RewardInfoData data) {
        textViewAsset.setText(data.getAsset() + data.getType());
    }

    @Override
    public void onRequestFaliure(String rspInf) {

    }

}
