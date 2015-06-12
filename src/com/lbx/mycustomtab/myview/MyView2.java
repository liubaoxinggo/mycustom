package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.view.View;
/**
 *  
 * @author liubaoxing
 *
 */
@SuppressLint("DrawAllocation") public class MyView2 extends View implements Runnable {

	public MyView2(Context context) {
		super(context);
		 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//���û�������ɫ
		canvas.drawColor(Color.GRAY);
		//ʵ����Paint
		Paint p  = new Paint();
		//�����Ƿ�ʹ�ÿ���ݹ��ܣ������Ľϴ���Դ������ͼ���ٶȻ������
		p.setAntiAlias(true);
		//���û�����ɫ
		p.setColor(Color.YELLOW);
		//���û�����ʽ
		p.setStyle(Paint.Style.STROKE);//[������ͼ�ζ��ǿ��ĵ�]
		//���ñʻ���ϸ
		p.setStrokeWidth(3);
		//��Բ
		canvas.drawCircle(140, 140, 130, p);
		//������
		canvas.drawRect(10, 290, 270, 550, p);
		canvas.drawRect(10, 570, 270, 800, p);
		//��������
		RectF re = new RectF(10, 820, 270, 1000);
//		canvas.drawRect(re, p);
		//���� re ��������Բ
		canvas.drawOval(re, p);
		//ʵ����·��
		Path path = new Path();
		//�ƶ���ָ����
		path.moveTo(10, 1150);
		//����
		path.lineTo(270, 1150);
		path.lineTo(140, 1020);
		//�ر�·�����������յ�����������
		path.close();
		//��·��
		canvas.drawPath(path, p);//��������������
		//==========================================================
		
		
		//���û�����ʽ
		p.setStyle(Paint.Style.FILL);//[������ͼ�ζ���ʵ�ĵ�]
		//���û�����ɫ
		p.setColor(Color.BLUE);
		
		//��Բ
		canvas.drawCircle(450, 140, 130, p);
		//������
		canvas.drawRect(310, 290, 570, 550, p);
		canvas.drawRect(310, 570, 570, 800, p);
		//��������
		RectF re1 = new RectF(310, 820, 570, 1000);
//		canvas.drawRect(re1, p);
		//���� re ��������Բ
		canvas.drawOval(re1, p);
		
		//ʵ����·��
		Path path2 = new Path();
		//�ƶ���ָ����
		path2.moveTo(310, 1150);
		//����
		path2.lineTo(570, 1150);
		path2.lineTo(440, 1020);
		//�ر�·�����������յ�����������
		path2.close();
		//��·��
		canvas.drawPath(path2, p);//��������������
		
		//������Ⱦ
		Shader mShader = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.RED,Color.DKGRAY,Color.GREEN}, null, Shader.TileMode.CLAMP);//Shader.TileMode.CLAMP��Ե����.
		//���Ρ����������䡿��Ⱦ [������Ⱦ��ԭ������뱻��Ⱦͼ�ε�Բ��һ�£���뾶�ı仯����ֹ���Ч������ε���Ч��]
		Shader mShader0 = new RadialGradient(730, 140, 20, Color.YELLOW, Color.RED, TileMode.MIRROR);
		//Ϊ��������������Ⱦ
		p.setColor(Color.BLACK);
		p.setShader(mShader0);
		
		//��Բ
		canvas.drawCircle(730, 140, 130, p);
		
		//������Ⱦ
		Shader mShader1 = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.BLUE,Color.YELLOW}, null, Shader.TileMode.MIRROR);//Shader.TileMode.MIRROR��ˮƽ����ʹ�ֱ�����澰��, ��������ͼ���û�з�϶.
		//Ϊ��������������Ⱦ
		p.setShader(mShader1);
		//������
		canvas.drawRect(600, 290, 860, 550, p);
		//������Ⱦ
		Shader mShader2 = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.RED,Color.GREEN}, new float[]{0.4f,0.4f}, Shader.TileMode.REPEAT);//Shader.TileMode.REPEAT��ˮƽ����ʹ�ֱ�����ظ��ڷ�,��������ͼ����з�϶��϶.
		//Ϊ��������������Ⱦ
		p.setShader(mShader2);
		canvas.drawRect(600, 570, 860, 800, p);	
		//������Ⱦ
		Shader mShader3 = new LinearGradient(0, 0, 20, 20, Color.GREEN, Color.YELLOW, TileMode.REPEAT) ;
		//Ϊ��������������Ⱦ
		p.setShader(mShader3);
		//��������
		RectF re2 = new RectF(600, 820, 860, 1000);
//		canvas.drawRect(re1, p);
		//���� re ��������Բ
		canvas.drawOval(re2, p);
		//������Ⱦ 
		Shader mShader4 = new RadialGradient(0, 0, 30, Color.BLUE, Color.RED, TileMode.REPEAT);
		p.setShader(mShader4);
		//ʵ����·��
		Path path3 = new Path();
		//�ƶ���ָ����
		path3.moveTo(600, 1150);
		//����
		path3.lineTo(860, 1150);
		path3.lineTo(730, 1020);
		//�ر�·�����������յ�����������
		path3.close();
		//��·��
		canvas.drawPath(path3, p);//��������������
		
		//ɨ�轥��   Χ��һ�����ĵ�ɨ�轥�䣬�������״�ɨ��
		Shader sweepGradient = new SweepGradient(140, 1300, new int[]{Color.BLUE,Color.RED,Color.GREEN}, null);
		p.setShader(sweepGradient);
		canvas.drawCircle(140, 1300, 130, p);
		
		Shader linearGradient = new LinearGradient(0, 0, 20, 20, Color.GREEN, Color.YELLOW, TileMode.REPEAT) ;
		Shader radialGradient = new RadialGradient(420, 1300, 20, Color.YELLOW, Color.RED, TileMode.MIRROR);
		Shader sweepGradient1 = new SweepGradient(420, 1300, new int[]{Color.BLUE,Color.RED,Color.GREEN}, null);
	    Shader composeShader = new ComposeShader(linearGradient, radialGradient, PorterDuff.Mode.DARKEN);
	    p.setShader(composeShader);
	    canvas.drawCircle(420, 1300, 130, p);
	    Shader bitmapShader = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), TileMode.MIRROR, TileMode.MIRROR);
	    p.setShader(bitmapShader);
	    canvas.drawRect(10, 1450, 600, 1880, p);
	    
	    
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	} 
	
}


















