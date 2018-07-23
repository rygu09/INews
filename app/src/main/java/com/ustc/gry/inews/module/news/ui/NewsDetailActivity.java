package com.ustc.gry.inews.module.news.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.bean.news.NewsDetailBean;
import com.ustc.gry.inews.module.news.presenter.INewsDetailPresenter;
import com.ustc.gry.inews.module.news.presenter.INewsDetailPresenterImpl;
import com.ustc.gry.inews.module.news.view.INewsDetailView;
import com.ustc.gry.inews.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhou.widget.RichText;

public class NewsDetailActivity extends AppCompatActivity implements INewsDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvSrc)
    TextView tvSrc;
    @BindView(R.id.imvNews)
    ImageView imvPic;
    @BindView(R.id.tv_news_detail_body)
    RichText mBodyTv;
    @BindView(R.id.tvPTime)
    TextView tvPTime;

    private NewsBean mNews;
    private INewsDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        mNews = (NewsBean) getIntent().getSerializableExtra("news");
        initToolbar();
        mPresenter = new INewsDetailPresenterImpl(this, mNews.getDocid());
    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initNewsDetail(NewsDetailBean data) {
        tvTitle.setText(data.getTitle());
        tvSrc.setText(data.getSource());
        tvPTime.setText(data.getPtime());
        GlideUtils.loadIntoUseFitWidth(getApplicationContext(),data.getImg().get(0).getSrc(),
                R.drawable.place_holder_pic,imvPic,0);

        if (!TextUtils.isEmpty(data.getBody())) {
            mBodyTv.setRichText(data.getBody());
        }
    }
}
