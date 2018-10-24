package com.zxbin.mycustomviews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zxbin.commomlib.base.BaseActivity;
import com.zxbin.mycustomviews.R;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
