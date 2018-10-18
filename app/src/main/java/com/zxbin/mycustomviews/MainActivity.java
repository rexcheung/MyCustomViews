package com.zxbin.mycustomviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxbin.mycustomviews.utils.SinUtils;
import com.zxbin.mycustomviews.views.SinView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Double> unitCircle = SinUtils.getUnitCircle();
        final SinView sv = findViewById(R.id.sv);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.setDatas(unitCircle);
            }
        });
        Log.i("test", "test");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
