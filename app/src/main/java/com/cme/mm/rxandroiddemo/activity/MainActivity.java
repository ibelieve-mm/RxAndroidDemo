package com.cme.mm.rxandroiddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cme.mm.rxandroiddemo.App;
import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.utils.RxUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
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

    @Click(R.id.btn_toRxReplaceAsyncTaskPage)
    void toRxReplaceAsyncTaskPage() {
        RxReplaceAsyncTaskActivity_.intent(this).start();
    }

    /**
     * 调用create1
     */
    @Click(R.id.btn_createFunction)
    void createFunction() {
        RxUtils.createObservable();
    }

    /**
     * 调用create2
     */
    @Click(R.id.btn_createFunction2)
    void createFunction2() {
        RxUtils.createPrint();
    }

    /**
     * 调用from()方法
     */
    @Click(R.id.btn_fromFunction)
    void fromFunction() {
        RxUtils.from();
    }

    /**
     * 调用interval()方法
     */
    @Click(R.id.btn_intervalFunction)
    void intervalFunction() {
        RxUtils.interval();
    }

    /**
     * 调用just()方法
     */
    @Click(R.id.btn_justFunction)
    void justFunction() {
        RxUtils.just();
    }

    /**
     * 调用range()方法
     */
    @Click(R.id.btn_rangeFunction)
    void rangeFunction() {
        RxUtils.range();
    }

    /**
     * 调用调用filter()方法
     */
    @Click(R.id.btn_filterFunction)
    void filterFunction() {
        RxUtils.filter();
    }
}
