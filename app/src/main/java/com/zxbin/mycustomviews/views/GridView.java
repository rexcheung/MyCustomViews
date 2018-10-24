package com.zxbin.mycustomviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public abstract class GridView extends View {
    private Paint mPaint;
    protected Point mCenterPoint;
    protected final int UNIT_INTERVAL = 15;

    public GridView(Context context) {
        this(context, null);
    }

    public GridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(getContext().getResources().getColor(android.R.color.darker_gray));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (mCenterPoint == null) {
            mCenterPoint = new Point();
        }

        mCenterPoint.x = width / 2;
        mCenterPoint.y = height / 2;

        Log.i("center X: ", mCenterPoint.x + "");
        Log.i("center Y: ", mCenterPoint.y + "");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCenterPoint(canvas);
        drawCenterCross(canvas);
        drawXLine(canvas);

    }

    private void drawXLine(Canvas canvas) {
        mPaint.setStrokeWidth(1);

        // 画横线
        int startY = mCenterPoint.y % UNIT_INTERVAL;
        for (int i = startY; i < getHeight(); i += UNIT_INTERVAL) {
            canvas.drawLine(0, i, getWidth(), i, mPaint);
        }


        // 画竖线
        int startX = mCenterPoint.x % UNIT_INTERVAL;
        for (int i = startX; i < getWidth(); i += UNIT_INTERVAL) {
            canvas.drawLine(i, 0, i, getHeight(), mPaint);
        }
    }


    private void drawCenterCross(Canvas canvas) {
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, mCenterPoint.y, getWidth(), mCenterPoint.y, mPaint);
        canvas.drawLine(mCenterPoint.x, 0, mCenterPoint.x, getHeight(), mPaint);
    }

    private void drawCenterPoint(Canvas canvas) {
        if (mCenterPoint == null) {
            return;
        }

        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, 5, mPaint);
    }

    private int percent = 0;
    private Runnable moveCenterRunable = new Runnable() {

        @Override
        public void run() {
            int centerX = getWidth() / 2;
            if (percent == 0) {
                mCenterPoint.x = centerX;
            }  else {
                mCenterPoint.x = centerX + centerX * percent / 100;
                Log.i("X = ", mCenterPoint.x + "");
            }

            if (!onPointChange()) {
                invalidate();
            }

        }
    };

    /**
     * 移动X坐标
     * 接受正负值 -100 到 100
     * 负为左移
     * 正为右移
     */
    public void moveCenterX(int percent) {
        if (percent < -100 || percent > 100) {
            return;
        }

        removeCallbacks(moveCenterRunable);
        this.percent = percent;
        postDelayed(moveCenterRunable, 200);
    }

    public Point getCenterPoint() {
        return mCenterPoint;
    }

    public int getUNIT_INTERVAL() {
        return UNIT_INTERVAL;
    }


    abstract boolean onPointChange();
}
