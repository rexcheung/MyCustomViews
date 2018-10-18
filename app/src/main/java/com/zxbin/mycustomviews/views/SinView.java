package com.zxbin.mycustomviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SinView extends GridView {
    private List<Double> points;
    private Path mPath;
    private Paint mPaint;

    public SinView(Context context) {
        this(context, null);
    }

    public SinView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        points = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPath != null && !mPath.isEmpty()) {
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void setDatas(@NonNull List<Double> points) {
        if (points.size() == 0) {
            return;
        }

        this.points.clear();
        this.points.addAll(points);
        mPath.reset();
        if (mPath.isEmpty()) {
            Log.i("mPath", "is Empty");
        }

        int x = mCenterPoint.x;
        for (Double y : points) {
            double yAxle = y / 0.1 * UNIT_INTERVAL;
            float yPoint = ((float) yAxle) * -1 + mCenterPoint.y;

            // 移动到中心，否则会从0，0开始连线
            if (x == mCenterPoint.x) {
                mPath.moveTo(mCenterPoint.x, mCenterPoint.y);
            }
            mPath.lineTo(x, yPoint);
            x += 15;
        }

        this.invalidate();
    }
}
