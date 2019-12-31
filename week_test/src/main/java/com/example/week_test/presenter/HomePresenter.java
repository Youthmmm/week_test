package com.example.week_test.presenter;

import com.example.week_test.base.BasePresenter;
import com.example.week_test.contract.IHomeContract;
import com.example.week_test.entity.HomeEntity;
import com.example.week_test.model.HomeModel;

public class HomePresenter extends BasePresenter<HomeModel,IHomeContract.IView> {

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    public void getData(String url){
        model.getData(url, new IHomeContract.IModel.IModelCallback() {
            @Override
            public void success(HomeEntity homeEntity) {
                getView().success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
