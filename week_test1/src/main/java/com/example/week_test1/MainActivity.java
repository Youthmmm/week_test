package com.example.week_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.week_test1.adapter.HomeAdapter;
import com.example.week_test1.base.BaseActivity;
import com.example.week_test1.contract.IHomeContract;
import com.example.week_test1.entity.HomeEntity;
import com.example.week_test1.presenter.HomePresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter>implements IHomeContract.IView {
    @BindView(R.id.btn_intent)
    Button btn_intent;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        
        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(HomeEntity homeEntity) {
        HomeAdapter homeAdapter = new HomeAdapter(this,homeEntity.getRanking());
        rv.setAdapter(homeAdapter);
    }

    @Override
    public void failure(Throwable throwable) {

    }
}
