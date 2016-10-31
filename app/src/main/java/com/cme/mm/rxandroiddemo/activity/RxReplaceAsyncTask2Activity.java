package com.cme.mm.rxandroiddemo.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.bean.LoginBean;
import com.cme.mm.rxandroiddemo.bean.LoginBeanMine;
import com.cme.mm.rxandroiddemo.constants.CommConstants;
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

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.et_account)
    EditText et_account;
    @ViewById(R.id.et_pwd)
    EditText et_pwd;

    @ViewById(R.id.tv_requestResult)
    TextView tv_requestResult;


    private ProgressDialog dialog;


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

            if (!msg.equals("")) {
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
        loginEmulator1();
//        loginEmulator2();
    }

    /**
     * 直接登陆到服务器
     */
    private void loginEmulator2() {
        Map<String, String> params = new HashMap<>();
        String userName = et_account.getText().toString().trim();
        String password = et_pwd.getText().toString().trim();
        params.put("userName", userName);
        params.put("password", password);
        utils.login(CommConstants.DEMO_SERVER + "LoginAction", params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
    }

    /**
     * 将字符串加密后登陆
     */
    private void loginEmulator1() {
//        LoginBean loginBean = new LoginBean(getImei());

        LoginBeanMine loginBean = new LoginBeanMine(et_account.getText().toString().trim(), et_pwd.getText().toString().trim());

        String loginJson = JsonUtils.createJsonString(loginBean);
        String aes;
        try {
            aes = JsonUtils.aes(loginJson);

            App.log("加密后~~"+aes);
            /**
             * observeOn()表示Observable应该在哪个Scheduler(调度器)上执行任务
             * subscribeOn()表示Observable将在哪个Scheduler上发送通知
             */
            utils.login(CommConstants.DEMO_SERVER + "LoginAction", aes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                //            utils.login(CommConstants.LOGIN_PATH + "login", aes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
                        tv_requestResult.setText(s);
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