package com.example.week_test.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.week_test.contract.IHomeContract;
import com.example.week_test.model.HomeModel;

public abstract class BaseActivity<H extends BasePresenter<HomeModel, IHomeContract.IView>> extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initview();
        initdata();
    }

    protected abstract void initdata();
    

    protected abstract void initview();


    protected abstract int layoutId();

}
