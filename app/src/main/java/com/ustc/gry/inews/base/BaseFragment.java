package com.ustc.gry.inews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

//public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
//
//    protected T mPresenter;
//
//    protected abstract void initView();
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mPresenter != null) {
//            mPresenter.onResume();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mPresenter != null) {
//            mPresenter.onDestory();
//        }
//    }
//}

public abstract class BaseFragment extends Fragment implements BaseView{


    protected View mFragmentRootView;

    protected boolean isViewCreated = false;

    private final String TAG = this.getClass().getSimpleName();

    //    protected OnFragmentInteractionListener fragmentInteractionListener;
    private Unbinder bind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        mFragmentRootView= inflater.inflate(setFragmentLayoutID(), container, false);
        bind = ButterKnife.bind(this, mFragmentRootView);
        initView();
        initData();
        return mFragmentRootView;
    }
    public abstract int setFragmentLayoutID();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        bind.unbind();
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");
    }

    @Override
    public void toast(String msg) {
        showSnackbar(msg);
    }
    protected void showSnackbar(String msg) {
        Snackbar.make(mFragmentRootView, msg, Snackbar.LENGTH_SHORT).show();
    }


}
