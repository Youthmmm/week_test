package com.example.week_test.model;

import com.example.week_test.contract.IHomeContract;
import com.example.week_test.entity.HomeEntity;
import com.example.week_test.utils.OkHttpUtils;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getData(String url, final IModelCallback iModelCallback) {
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void success(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result, HomeEntity.class);
                iModelCallback.success(homeEntity);
            }

            @Override
            public void error(Throwable throwable) {
                iModelCallback.failure(throwable);
            }
        });
    }
}
