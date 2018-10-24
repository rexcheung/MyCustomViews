package com.zxbin.mycustomviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    boolean onPointChange() {
        ArrayList<Double> list = new ArrayList<>(this.points);
        setDatas(list);
        return true;
    }

    public void setDatas(@NonNull final List<Double> pointList) {
        if (pointList.size() == 0) {
            return;
        }


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                calPath(pointList);
                return null;
            }
        }.execute();

        this.invalidate();
    }

    private void calPath(@NonNull List<Double> pointList) {
        this.points.clear();
        this.points.addAll(pointList);
        mPath.reset();

        int startX = mCenterPoint.x;
        int x = startX;
        for (Double y : pointList) {
            double yAxle = y / 0.1 * UNIT_INTERVAL;
            float yPoint = ((float) yAxle) * -1 + mCenterPoint.y;

            // 移动到中心，否则会从0，0开始连线
            if (x == startX) {
                mPath.moveTo(startX, mCenterPoint.y);
            }
            mPath.lineTo(x, yPoint);
            x += 25;
        }
    }
}
