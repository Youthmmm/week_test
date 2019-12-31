package com.example.week_test1.base;

import java.lang.ref.WeakReference;

/**
 * p层基类
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {

    public M model;
    public WeakReference<V> weakReference;


    public BasePresenter(){
        model = initModel();//对model进行初始化
    }


    protected abstract M initModel();


    /**
     * 绑定
     * @param v
     */
    public void attach(V v){
        weakReference = new WeakReference<>(v);
    }


    /**
     * 解绑：解省内存（解决内存泄漏）
     */
    public void detach(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference = null;
        }
    }


    /**
     * 获取mvp的view对象
     * @return
     */
    public V getView(){

        return weakReference.get();
    }



}
