package com.example.week_test1.presenter;


import com.example.week_test1.base.BasePresenter;
import com.example.week_test1.contract.IHomeContract;
import com.example.week_test1.entity.HomeEntity;
import com.example.week_test1.model.HomeModel;

public class HomePresenter extends BasePresenter<HomeModel, IHomeContract.IView> implements IHomeContract.IPresenter {
    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    public void getData(String url) {

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
