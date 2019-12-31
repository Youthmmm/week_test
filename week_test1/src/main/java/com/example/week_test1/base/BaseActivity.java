package com.example.week_test1.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    Unbinder unbinder;
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        //绑定当前对象
        unbinder = ButterKnife.bind(this);

        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach(this);//绑定当前view
        }

        initView();
        initData();
    }

    protected abstract P initPresenter();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int bindLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();//解绑
        }
        if (presenter!=null){
            presenter.detach();//解决内存泄漏方式
        }
    }
}
