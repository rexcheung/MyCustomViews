package com.zxbin.mycustomviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxbin.commomlib.base.BaseActivity;
import com.zxbin.mycustomviews.activity.SinActivity;
import com.zxbin.mycustomviews.activity.TestActivity;
import com.zxbin.mycustomviews.utils.SinUtils;
import com.zxbin.mycustomviews.views.SinView;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_sin)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SinActivity.class);
                        startActivity(intent);
                    }
                });

        findViewById(R.id.btn_test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(TestActivity.class);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
