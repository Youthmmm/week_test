package com.example.week_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.week_test1.base.BaseActivity;
import com.example.week_test1.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class TwoActivity extends BaseActivity {
    @BindView(R.id.keyword)
    EditText keyword;
    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_send)
    Button tvSend;
    @BindView(R.id.obj_send)
    Button objSend;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        CodeUtils.init(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        ivCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //通过imageview识别二维码图片的信息，控件引用，第二个参数：解析的回调
                CodeUtils.analyzeByImageView(ivCode, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(TwoActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(TwoActivity.this, "解析失败", Toast.LENGTH_SHORT).show();

                    }
                });
                return true;
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_two;
    }

    @OnClick(R.id.btn_code)
    public void onViewClicked() {


        if (keyword.getText().toString().length() == 0
        ) {
            Toast.makeText(this, "关键词不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = CodeUtils.createImage(keyword.getText().toString(), 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        ivCode.setImageBitmap(bitmap);


    }
    @OnClick({R.id.tv_send, R.id.obj_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:

                //发送字符串
                EventBus.getDefault().post("微信");


                break;
            case R.id.obj_send:

                //发送对象
                EventBus.getDefault().post("qq");
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getString(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEdittext(EditText editText) {

        Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();

    }
}