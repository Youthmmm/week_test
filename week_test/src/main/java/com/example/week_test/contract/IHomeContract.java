package com.example.week_test.contract;

import com.example.week_test.base.IBaseModel;
import com.example.week_test.base.IBaseView;
import com.example.week_test.entity.HomeEntity;

/**
 * 契约类
 */
public interface IHomeContract {

    interface IModel extends IBaseModel {

        void getData(String url,IModelCallback iModelCallback);

        interface IModelCallback{
            void success(HomeEntity homeEntity);
            void failure(Throwable throwable);
        }

    }


    interface IView extends IBaseView {

        void success(HomeEntity homeEntity);
        void failure(Throwable throwable);

    }

    interface IPresenter{

        void getData(String url);

    }

}

