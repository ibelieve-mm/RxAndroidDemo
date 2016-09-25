package com.cme.mm.rxandroiddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Observable;
import rx.functions.Action1;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_test)
    TextView tv_test;

    @AfterViews
    void onPageStart() {
        hello("iOS", "Android", "PHP");
    }

    /**
     * 打印字符串
     *
     * @param names
     */
    private void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                tv_test.setText(s);
                App.log(s);
            }
        });
    }
}
