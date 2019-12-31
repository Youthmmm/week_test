package com.example.week_test1.model;

import com.example.week_test1.contract.IHomeContract;
import com.example.week_test1.entity.HomeEntity;
import com.example.week_test1.utils.OkhttpUtils;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getData(String url, final IHomeContract.IModel.IModelCallback iModelCallback) {

        OkhttpUtils.getInstance().doGet(url, new OkhttpUtils.OkhttpCallback() {
            @Override
            public void success(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result,HomeEntity.class);
                iModelCallback.success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {

                iModelCallback.failure(throwable);
            }
        });
    }
}
