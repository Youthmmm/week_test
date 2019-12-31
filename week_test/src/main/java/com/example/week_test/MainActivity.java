package com.example.week_test;


import com.example.week_test.base.BaseActivity;
import com.example.week_test.contract.IHomeContract;
import com.example.week_test.entity.HomeEntity;
import com.example.week_test.presenter.HomePresenter;

public class MainActivity extends BaseActivity<HomePresenter>implements IHomeContract.IView {

    @Override
    protected void initdata() {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(HomeEntity homeEntity) {

    }

    @Override
    public void failure(Throwable throwable) {

    }
}

