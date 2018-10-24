package com.zxbin.mycustomviews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.zxbin.commomlib.base.BaseActivity;
import com.zxbin.mycustomviews.R;
import com.zxbin.mycustomviews.utils.SinUtils;
import com.zxbin.mycustomviews.views.SinView;

import java.util.List;

public class SinActivity extends BaseActivity {
    private SeekBar sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin);
        final List<Double> unitCircle = SinUtils.getUnitCircle();
        final SinView sv = findViewById(R.id.sinView);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.setDatas(unitCircle);
            }
        });

        sb = findViewById(R.id.xseekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percent = (progress - 50) * 2;
                sv.moveCenterX(percent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Log.i("test", "test");
    }
}
