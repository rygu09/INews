package com.ustc.gry.inews.app;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.module.news.ui.NewsFragment;
import com.ustc.gry.inews.module.picture.ui.PictureFragment;
import com.ustc.gry.inews.module.setting.SettingFragment;
import com.ustc.gry.inews.module.video.ui.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.bottom_tab_news)
    RadioButton bottomTabNews;
    @BindView(R.id.bottom_tab_pic)
    RadioButton bottomTabPic;
    @BindView(R.id.bottom_tab_video)
    RadioButton bottomTabVideo;
    @BindView(R.id.bottom_tab_my)
    RadioButton bottomTabMy;
    @BindView(R.id.bottom_tab)
    ConstraintLayout bottomTab;

    private NewsFragment newsFragment;
    private PictureFragment pictureFragment;
    private VideoFragment videoFragment;
    private SettingFragment settingFragment;
    private BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);     //绑定视图
        setToolbar();
//        getSupportFragmentManager().beginTransaction()//让App进去是新闻Fragment界面
//                .replace(R.id.frame_content,new NewsFragment()).commit();
        Logger.addLogAdapter(new AndroidLogAdapter());//Logger要加这句话
        initData();
    }

    private void initData() {
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        if (!newsFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_content, newsFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(newsFragment).commit();
        }
        currentFragment = newsFragment;
    }

    @OnClick({R.id.bottom_tab_news, R.id.bottom_tab_pic, R.id.bottom_tab_video, R.id.bottom_tab_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom_tab_news:
                clickNews();
                break;
            case R.id.bottom_tab_pic:
                clickPic();
                break;
            case R.id.bottom_tab_video:
                clickVideo();
                break;
            case R.id.bottom_tab_my:
                clickSetting();
                break;
        }
    }


    private void clickNews() {
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), newsFragment);
    }

    private void clickPic() {
        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), pictureFragment);
    }

    private void clickVideo() {
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), videoFragment);
    }

    private void clickSetting() {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), settingFragment);
    }


    protected void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.frame_content, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = (BaseFragment) fragment;
    }



    void setToolbar() {
        setSupportActionBar(toolbar);
        //        ActionBar actionBar=getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


}


