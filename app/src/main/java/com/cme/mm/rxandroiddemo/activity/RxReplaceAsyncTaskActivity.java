package com.cme.mm.rxandroiddemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.utils.DownloadUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Descriptions：利用Rx替代AsyncTask
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 * <p>
 * <p>
 * 在实际开发中Activity充当的角色太多，
 * 1. UI主线程负责绘制UI
 * 2. 开启子线程获取网络数据
 * 3. 赋值到控件中
 * 4. 逻辑判断等等
 */
@EActivity(R.layout.activity_rx_replace_asynctask)
public class RxReplaceAsyncTaskActivity extends AppCompatActivity {

    @ViewById(R.id.iv_showDownload)
    ImageView iv_showDownload;


    private final String IMG_PATH = "http://f.hiphotos.baidu.com/image/h%3D200/sign=269538a28344ebf87271633fe9f8d736/2e2eb9389b504fc29897b1a4e1dde71191ef6d42.jpg";

    private DownloadUtils utils;

    @AfterViews
    void onPageStart() {
        utils = new DownloadUtils();
    }

    @Click(R.id.btn_downloadImg)
    void downloadImg() {
        utils.downloadImage(IMG_PATH).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<byte[]>() {
            @Override
            public void onCompleted() {
                App.log("Download——>onCompleted");//此处一般为关闭对话框
            }

            @Override
            public void onError(Throwable e) {

                App.log("Download——>"+e.getMessage());
            }

            @Override
            public void onNext(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                iv_showDownload.setImageBitmap(bitmap);
            }
        });
    }
}