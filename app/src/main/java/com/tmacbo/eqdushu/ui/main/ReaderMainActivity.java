package com.tmacbo.eqdushu.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.DoubleClickExitHelper;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.common.view.BaseViewPager;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.presenter.main.MainPresenter;
import com.tmacbo.eqdushu.ui.main.fragment.HomeFragment;
import com.tmacbo.eqdushu.ui.main.fragment.MyBorrowListFragment;
import com.tmacbo.eqdushu.ui.main.fragment.PersonalFragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author tmacbo
 * @Since on 2017/8/10  11:34
 * @mail tang_bo@hotmail.com
 * @Description 员工管理主页
 */

public class ReaderMainActivity extends BaseActivity<MainPresenter>
    implements MainView, View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.viewPagecontent)
    BaseViewPager mViewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private ArrayList<Fragment> fragmentContainter;

    DoubleClickExitHelper mDoubleClickExitHelper;
    private String todayTime;

    @Override
    protected int layoutRes() {
        return R.layout.activity_user;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    private void getRewardGas() {
        mPresenter.getRewardGas(this);
    }

    @Override
    protected void initView() {

        HomeFragment f1 = new HomeFragment();
        MyBorrowListFragment f3 = new MyBorrowListFragment();
        PersonalFragment f4 = new PersonalFragment();
        fragmentContainter = new ArrayList<Fragment>();
        fragmentContainter.add(f1);
        fragmentContainter.add(f3);
        fragmentContainter.add(f4);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setScanScroll(false);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        getRewardGas();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
        mOnNavigationItemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        mViewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        };

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRequestSuccessData(Object data) {

    }

    @Override
    public void onRequestFaliure(String rspInf) {

    }

    @Override
    public void getBookDataFailure(Throwable t) {

    }

    @Override
    public void getRewardGas(RewardInfoData data) {
        showNoProject(data.getIncrease(), data.getType());
    }

    @Override
    public void getTokenEorro() {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentContainter.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return fragmentContainter.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home 1";
                case 1:
                    return "DataList 2";
                case 2:
                    return "Personal 3";
            }
            return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExitHelper.onKeyDown(keyCode, mViewPager);
        }
        return true;
    }

    /**
     * 判断是否是当日第一次登陆
     */
    private boolean isTodayFirstLogin() {
        //取
        SharedPreferences preferences =
            getContext().getSharedPreferences("LastLoginTime", MODE_PRIVATE);
        String lastTime = preferences.getString("LoginTime", "2018-03-08");
        // Toast.makeText(MainActivity.this, "value="+date, Toast.LENGTH_SHORT).show();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        todayTime = df.format(new Date());// 获取当前的日期

        if (lastTime.equals(todayTime)) { //如果两个时间段相等
            return false;
        } else {
            saveExitTime(todayTime);
            return true;
        }
    }

    /**
     * 保存每次退出的时间
     */
    private void saveExitTime(String extiLoginTime) {
        SharedPreferences.Editor editor =
            getSharedPreferences("LastLoginTime", MODE_PRIVATE).edit();
        editor.putString("LoginTime", extiLoginTime);
        //这里用apply()而没有用commit()是因为apply()是异步处理提交，不需要返回结果，而我也没有后续操作
        //而commit()是同步的，效率相对较低
        //apply()提交的数据会覆盖之前的,这个需求正是我们需要的结果
        editor.apply();
    }

    public void showNoProject(double asset, String type) {
        AlertDialog.Builder builder =
            new AlertDialog.Builder(getContext()).setMessage("恭喜您获得" + asset + type)
                                                 .setPositiveButton("领取奖励",
                                                                    new DialogInterface.OnClickListener() {

                                                                        @Override
                                                                        public void onClick(
                                                                            DialogInterface dialog,
                                                                            int which) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    });
        builder.setCancelable(false);
        builder.show();
    }
}
