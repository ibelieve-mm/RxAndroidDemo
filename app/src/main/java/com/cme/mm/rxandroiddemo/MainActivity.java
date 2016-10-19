package com.cme.mm.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private TextView tv_test;
    private String baiduIconUrl="https://www.baidu.com/img/bd_logo1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_test = (TextView) findViewById(R.id.tv_test);
        hello("Java", "iOS", "PHP");
    }

    private void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                Log.i("info", s);
            }
        });
    }
}
