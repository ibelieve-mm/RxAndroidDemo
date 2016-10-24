package com.cme.mm.rxandroiddemo.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.bean.LoginBean;
import com.cme.mm.rxandroiddemo.utils.JsonUtils;
import com.cme.mm.rxandroiddemo.utils.LoginUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

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

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("登录");
        toolbar.setSubtitle("登录测试");
        toolbar.setNavigationIcon(R.drawable.common_press_back);
        /**
         * 注意：
         * 1. setTitle()方法必须放在setSupportActionBar()方法之前；
         * 2. 所有的事件监听器必须放在setSupportActionBar()方法之后；
         */
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);


        dialog = new ProgressDialog(this);
        utils = new LoginUtils();
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                App.toast(msg);
            }
            return true;
        }
    };

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}