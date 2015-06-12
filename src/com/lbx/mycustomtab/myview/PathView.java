package com.lbx.mycustomtab.myview;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
/**
 * �ο���http://blog.csdn.net/harvic880925/article/details/38926877
 *     http://kevinems.com/151.html
 *     //������ʹ�ý���
 *     http://blog.csdn.net/harvic880925/article/details/39080931
 * @author liubaoxing
 *
 */
public class PathView extends View {

	Paint paint;
	Paint textPaint;
	float phase = 0f;
	boolean isRun = false;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x001:
				init();
				phase++;
				invalidate();
				break;
			case 0x002:
				
				break;

			default:
				break;
			}
		}
		
	};
	public PathView(Context context) {
		super(context);
		init();
	}
	private void init(){
		paint = new Paint();
		//ָ���Ƿ�ʹ�ÿ���ݹ��ܣ����ʹ�ã���ʹ��ͼ�ٶȱ���  
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3f);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStyle(Style.STROKE);
		
		//��ʼ��д���ֵĻ���
	    textPaint = new Paint(paint);
		textPaint.setColor(Color.DKGRAY);
		textPaint.setTextSize(24);
		//ֻ�Ὣˮƽ�������죬�߶Ȳ����  
		textPaint.setTextScaleX(2f);
		textPaint.setStrokeWidth(1);
		textPaint.setStyle(Style.FILL_AND_STROKE);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(0xffaaaaaa);
		test1(canvas);
		test2(canvas);
		testWH(canvas);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(isRun){
			isRun = false;
		}else{
			isRun = true;
			testThread();
		}
		return super.onTouchEvent(event);
	}
	private void testThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isRun) {
					try {
						handler.sendEmptyMessage(0x001);
						Thread.sleep(100);
					} catch (InterruptedException e) {
						handler.sendEmptyMessage(0x002);
					}
				}
			}
		}).start();
	}
	private void testWH(Canvas canvas) {
		int canvasW = canvas.getWidth();
		int canvasH = canvas.getHeight();
		int w = this.getWidth();
		int h = this.getHeight();
		StringBuilder sb = new StringBuilder();
		sb.append("canvasW = ").append(canvasW).append(",canvasH = "+canvasH).append("\n");
		canvas.drawText(sb.toString(), 260, 720, textPaint);
		StringBuilder sb1 = new StringBuilder();
		sb1.append("this.W = ").append(w).append(",this.H = "+h);	
		canvas.drawText(sb1.toString(), 260, 750, textPaint);
		textPaint.setColor(0xff00f0f0);
		textPaint.setTextSize(44);
		canvas.drawText("�������", 260, 850, textPaint);
	}
	private void test2(Canvas canvas) {
		Path path0 = new Path();
		path0.moveTo(220, 100);
		//����һ�������һ�����α��������ߣ��������Ƶ�(x1, y1), (x2, y2)����(x3, y3) ����
		path0.cubicTo(250, 30, 320, 150, 400, 10);
		//����һ�������һ�����α��������ߣ��������Ƶ�(x1, y1)����(x2, y2) ����
		path0.quadTo(390,80, 500, 150); 
		canvas.drawPath(path0, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path0, 10f, 0f, textPaint);
		canvas.save();
		
		Path path1 = new Path();
		path1.moveTo(250, 150);
		Path path2 = new Path();
		path2.moveTo(250, 150);
		path2.lineTo(950, 150);
		for (int i = 0; i < 15; i++) {
			path1.lineTo(i * 20 + 250, (float)Math.random() * 60 + 150);
		}
		int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.BLACK,Color.MAGENTA,Color.CYAN};
		PathEffect[] effects  = new PathEffect[7];
		//��ʹ��·��Ч��
		effects[0] = null;
		paint.setPathEffect(effects[0]);
		paint.setColor(colors[0]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("û��ʹ��·��Ч��[0]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//CornerPathEffect ����ʹ��Բ�����������ĽǴӶ��Ի���ͼ�ε���״����ı߽ǽ���ƽ��
		effects[1] = new CornerPathEffect(10);
		paint.setPathEffect(effects[1]);
		paint.setColor(colors[1]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("CornerPathEffect[1]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		//DashPathEffect  ����ʹ��DashPathEffect������һ�����ߵ�����(�̺���/СԲ��)��������ʹ��ʵ�ߡ��㻹����ָ���������/ʵ�߶ε��ظ�ģʽ
		//����ʹpaint�����������ߵ�����,���ҿ�������ָ����ʵ�����з�ʽ.�����е�float����,������ż������,��>=2,ָ���˶��ٳ��ȵ�ʵ��֮���ٻ����ٳ��ȵĿհ�.
		//�籾������,���Ƴ���2��ʵ��,�ٻ��Ƴ���4�Ŀհ�,�ٻ��Ƴ���8��ʵ��,�ٻ��Ƴ���6�Ŀհ�,�����ظ�.phase��ƫ����,���Բ������.
		effects[2] = new DashPathEffect(new float[]{2,4,8,6}, phase);
		paint.setPathEffect(effects[2]);
		paint.setColor(colors[2]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("DashPathEffect[2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//DiscretePathEffect  ��DashPathEffect���ƣ��������������ԡ�����������ʱ����Ҫָ��ÿһ�εĳ��Ⱥ���ԭʼ·����ƫ��ȡ�
		effects[3] = new DiscretePathEffect(3.0f, 5.0f);
		paint.setPathEffect(effects[3]);
		paint.setColor(colors[3]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("DiscretePathEffect[3]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		Path path3 = new Path();
		path3.addRect(0, 0, 8, 8, Direction.CCW);
		// PathDashPathEffect  ����Ч�����Զ���һ���µ���״(·��)����������ԭʼ·����������ǡ�
		effects[4] = new PathDashPathEffect(path3, 12f, phase,PathDashPathEffect.Style.ROTATE);
		paint.setPathEffect(effects[4]);
		paint.setColor(colors[4]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("PathDashPathEffect[4]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		//˳�����һ��·�����������Ч��������ÿһ��Ч��������Ӧ�õ�ԭʼ·���У��������ֽ�����Խ��������
		effects[5] = new SumPathEffect(effects[1], effects[2]);
		paint.setPathEffect(effects[5]);
		paint.setColor(colors[5]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("SumPathEffect[5]=[1,2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//������Ч���������Ӧ�ã���ʹ�õ�һ��Ч����Ȼ��������Ч���Ļ�����Ӧ�õڶ���Ч����
        //������״��PathEffect�ĸı��Ӱ�쵽��״����������ܹ���֤Ӧ�õ���ͬ��״�����Ч��������Ƶ��µı߽��С�
		effects[6] = new ComposePathEffect(effects[1], effects[2]);
		paint.setPathEffect(effects[6]);
		paint.setColor(colors[6]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("ComposePathEffect[6]=[1,2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		canvas.restore();
	}
	
	private void test1(Canvas canvas) {
		Path path = new Path();
		//�������
		path.moveTo(10, 10);//
		//��һ��ֱ�ߵ��յ㣬Ҳ�ǵڶ��������
		path.lineTo(10, 100);
		//�ڶ���ֱ��
		path.lineTo(200, 100);
		//�ջ�
		path.close();
		canvas.drawPath(path, paint);
		//------- //����·��//----------//
		Path path1 = new Path();
		RectF rect1 = new RectF(10, 120, 210, 320);
		//˳ʱ�����ʱ�뷽����ҪӰ�����ֵ��Ű�����
		path1.addRect(rect1, Direction.CCW);//��ʱ�� ����
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("1234567890987654321", path1, 10f, 5f, textPaint);
		
		Path path2 = new Path();
		RectF rect2 = new RectF(10, 340, 210, 540);
		//˳ʱ�����ʱ�뷽����ҪӰ�����ֵ��Ű�����
		path2.addRect(rect2, Direction.CW);//˳ʱ�뷽��
		canvas.drawPath(path2, paint);
		canvas.drawTextOnPath("1234567890987654321", path2, 10f, 5f, textPaint);
		
		//------- //Բ�Ǿ���·��,�ĸ�Բ�ǿ��Էֱ����С//----------//
		Path path3 = new Path();
		RectF rect3 = new RectF(10, 560, 210, 760);
		//ֻ�ܹ���ͳһԲ�Ǵ�С,float rx��������Բ�ǵ���Բ�ĺ���뾶��float ry��������Բ�ǵ���Բ������뾶��
		path3.addRoundRect(rect3, 20, 20, Direction.CCW); 
		canvas.drawPath(path3, paint);
		canvas.drawTextOnPath("1234567890987654321", path3, 10f, 5f, textPaint);
		
		Path path4 = new Path();
		RectF rect4 = new RectF(10, 780, 210, 980);
		//float[] radii�����봫��8����ֵ�������飬�ֱ��Ӧÿ������ʹ�õ���Բ�ĺ���뾶������뾶
		//���x1,y1,x2,y2,x3,y3,x4,y4�������У�x1,y1��Ӧ��һ���ǵģ����Ͻǣ���������Բ�ǵ���Բ�ĺ���뾶������뾶��
		//�������ơ���
		float[] radii = {10f,10f,20f,20f,30f,40f,50f,60f}; 
		path4.addRoundRect(rect4, radii, Direction.CW);
		canvas.drawPath(path4, paint);
		canvas.drawTextOnPath("1234567890987654321", path4, 10f, 5f, textPaint);
		
		//------- //Բ��·��//----------//
		Path path5 = new Path();
		path5.addCircle(110, 1100, 100, Direction.CW);
		canvas.drawPath(path5, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path5, 0f, 5f, textPaint);
		
		Path path6 = new Path();
		path6.addCircle(110, 1320, 100, Direction.CCW);
		canvas.drawPath(path6, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path6, 0f, 5f, textPaint);
		
		//------- //��Բ��·��//----------//
		Path path7 = new Path();
		//��Բ���Եľ���
		RectF rect7 = new RectF(10, 1440, 210, 1540);
		path7.addOval(rect7, Direction.CW);
		canvas.drawPath(path7, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path7, 0f, 5f, textPaint);
		
		//------- //����·��//----------//
		Path path8 = new Path();
		//��������Ӧ�ľ��� 
		RectF rect8 = new RectF(10, 1560, 210, 1760);
		//startAngle ����ʼ�ĽǶȣ�X��������Ϊ0��,sweepAngel�������Ķ�����
		path8.addArc(rect8, 0, 190);
		canvas.drawRect(rect8, paint);
		canvas.drawPath(path8, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path8, 0f, 5f, textPaint);
	}
	
}


























