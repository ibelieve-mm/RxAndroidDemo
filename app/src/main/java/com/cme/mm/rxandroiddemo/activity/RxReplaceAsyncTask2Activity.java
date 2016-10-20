package com.cme.mm.rxandroiddemo.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.bean.LoginBean;
import com.cme.mm.rxandroiddemo.utils.DownloadUtils;
import com.cme.mm.rxandroiddemo.utils.JsonUtils;
import com.cme.mm.rxandroiddemo.utils.LoginUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Descriptions：利用Rx替代AsyncTask,登录(利用Rx和OKHttp)
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_rx_replace_asynctask2)
public class RxReplaceAsyncTask2Activity extends AppCompatActivity {

    @ViewById(R.id.et_account)
    EditText et_account;
    @ViewById(R.id.et_pwd)
    EditText et_pwd;

    private ProgressDialog dialog;

    private final String LOGIN_PATH = "http://interface.wuliupai.cn/login";
    //        private final String LOGIN_PATH = "http://192.168.1.13:8080/completemobilewlp/login";
    private LoginUtils utils;

    @AfterViews
    void onPageStart() {
        dialog = new ProgressDialog(this);
        utils = new LoginUtils();
    }

    private String getImei() {
        String imei = ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        if (imei == null || "".equals(imei)) {
            imei = "";
        }
        return imei;
    }

    @Click(R.id.btn_commit)
    void commit() {
//        Map<String, String> params = new HashMap<>();
//        params.put("userName", et_account.getText().toString());
//        params.put("password", et_pwd.getText().toString());

        LoginBean loginBean = new LoginBean(getImei());
        String loginJson = JsonUtils.createJsonString(loginBean);
        String aes;
        try {
            aes = JsonUtils.aes(loginJson);
            /**
             * observeOn()表示Observable应该在哪个Scheduler(调度器)上执行任务
             *subscribeOn()表示Observable将在哪个Scheduler上发送通知
             */
            utils.login(LOGIN_PATH, aes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    dialog.dismiss();
                }

                @Override
                public void onError(Throwable e) {
                    App.log("结果异常~~" + e.getMessage());
                }

                @Override
                public void onNext(String s) {
                    dialog.show();
                    if (s != null) {
                        App.log(s);
                    }
                }
            });
        } catch (Exception e) {
            App.log("Json串加密出现异常~~" + e.toString());
        }
    }
}