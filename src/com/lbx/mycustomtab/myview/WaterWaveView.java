package com.lbx.mycustomtab.myview;

import java.util.ArrayList;
import java.util.List;

import com.lbx.mycustomtab.util.LogUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
 
/**
 * ˮ��Ч��
 * 
 * @author sky
 *
 */
public class WaterWaveView extends View {
    private final static int FPS = 1000 / 4;
    private float mMaxWaveAreaRadius;
    /**
     * ��������֮��ļ��
     */
    private float mWaveIntervalSize;
    /**
     *  ���ƶ��Ĳ���\
     */
    private float mStirStep;// ���ƶ��Ĳ���\
    /**
     * ��ʼ���ο��
     */
    private float mWaveStartWidth;// px
    /**
     * ��ֹ���ο��
     */
    private float mWaveEndWidth;// px
    private int mWaveColor;
    /** ��ɫ��������� */
    private Interpolator interpolator = new CycleInterpolator(0.5f);
    private float mViewCenterX;
    private float mViewCenterY;
    private final Paint mWavePaint = new Paint();
    {
        mWavePaint.setAntiAlias(true);
        mWavePaint.setStyle(Style.STROKE);
    }
    private final Paint mWaveCenterShapePaint = new Paint();
    {
        mWaveCenterShapePaint.setAntiAlias(true);
        mWaveCenterShapePaint.setStyle(Style.STROKE);
    }
    /**
     * 
     */
    private boolean mFillAllView = false;
    /**
     * ��������Բ�İ뾶�������Ƶ���ʼ�뾶
     */
    private float mFillWaveSourceShapeRadius;
    private final List<Wave> mWaves = new ArrayList<Wave>();
 
    public WaterWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
 
    public WaterWaveView(Context context) {
        super(context);
        init();
    }
 
    private void init() {
        setWaveInfo(60f, 2f, 2f, 15f, Color.BLUE);
    }
 
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.i("infos", "--------onLayout -----------");
        mViewCenterX = getWidth() / 2;
        mViewCenterY = getHeight() / 2;
        float waveAreaRadius = mMaxWaveAreaRadius;
        if (mFillAllView) {
            waveAreaRadius = (float) Math.sqrt(mViewCenterX * mViewCenterX
                    + mViewCenterY * mViewCenterY);
        } else {
            waveAreaRadius = Math.min(mViewCenterX, mViewCenterY);
        }
        if (mMaxWaveAreaRadius != waveAreaRadius) {
            mMaxWaveAreaRadius = waveAreaRadius;
            resetWave();
        }
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        stir();
        for (Wave w : mWaves) {
            mWavePaint.setColor(w.color);
            mWavePaint.setStrokeWidth(w.width);
            canvas.drawCircle(mViewCenterX, mViewCenterY, w.radius, mWavePaint);
        }
        if (mFillWaveSourceShapeRadius > 0f) {
            canvas.drawCircle(mViewCenterX, mViewCenterY,
                    mFillWaveSourceShapeRadius, mWaveCenterShapePaint);
        }
        postInvalidateDelayed(FPS);
    }
 
    /**
     * ��
     * 
     * @author sky
     */
    class Wave {
        public float radius;
        public float width;
        public int color;
 
        public Wave() {
            reset();
        }
 
        public void reset() {
            radius = mFillWaveSourceShapeRadius;//mFillWaveSourceShapeRadius
            width = mWaveStartWidth;
            color = mWaveColor;
        }
 
        @Override
        public String toString() {
            return "Wave [radius=" + radius + ", width=" + width + ", color="
                    + color + "]";
        }
 
    }
 
 
    /**
     * ����ӿ������
     */
    private void stir() {
        Wave nearestWave = mWaves.isEmpty() ? null : mWaves.get(0);
        if (nearestWave == null || nearestWave.radius - mFillWaveSourceShapeRadius >= mWaveIntervalSize) {
            Wave w = null;
            w = new Wave();
            mWaves.add(0, w);
        }
        float waveWidthIncrease = mWaveEndWidth - mWaveStartWidth;
        int size = mWaves.size();
        for (int i = 0; i < size; i++) {
            Wave w = mWaves.get(i);
            float rP = w.radius / mMaxWaveAreaRadius;
            if (rP > 1f) {
                rP = 1f;
            }
            w.width = mWaveStartWidth + rP * waveWidthIncrease;
            w.radius += mStirStep;
            float factor = interpolator.getInterpolation(rP);
            w.color = mWaveColor & 0x00FFFFFF | ((int) (255 * factor) << 24);
        }
        Wave farthestWave = mWaves.get(size - 1);
        if (farthestWave.radius > mMaxWaveAreaRadius + farthestWave.width / 2) {
            mWaves.remove(size - 1);
        }
    }
 
    /**
     * ���true��ѡ��view�����ĶԽ�����Ϊ��뾶
     * 
     * @param fillAllView
     */
    public void setFillAllView(boolean fillAllView) {
        mFillAllView = fillAllView;
        resetWave();
    }
 
    public void resetWave() {
        mWaves.clear();
        postInvalidate();
    }
 
    /**
     * ��䲨����Դ�����ĵ�
     * 
     * @param radius
     *            �뾶��С
     */
    public void setFillWaveSourceShapeRadius(float radius) {
        mFillWaveSourceShapeRadius = radius;
    }
 
    /**
     * ���ò�������
     * 
     * @param intervalSize
     *            ��������֮��ļ��
     * @param stireStep
     *            �����ƶ��ٶ�
     * @param startWidth
     *            ��ʼ���ο��
     * @param endWidth
     *            ��ֹ���ο��
     * @param color
     *            ������ɫ
     */
    public void setWaveInfo(float intervalSize, float stireStep,
            float startWidth, float endWidth, int color) {
        mWaveIntervalSize = intervalSize;
        mStirStep = stireStep;
        mWaveStartWidth = startWidth;
        mWaveEndWidth = endWidth;
        setWaveColor(color);
        resetWave();
    }
 
    /**
     * ���ò�����ɫ
     * 
     * @param color
     */
    public void setWaveColor(int color) {
        mWaveColor = color;
        mWaveCenterShapePaint.setColor(mWaveColor);
    }
    /**
     * �������ĵ�
     * @param centerP
     */
    public void setCenter(int x,int y){
    	mViewCenterX = x;
    	mViewCenterY = y;
    	resetWave();
    }
}
