package com.rcgsoft.pruebajni;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class drawView extends View {
	public drawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public drawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	MainActivity maPadre=null;
	public void setMainAct(MainActivity ma){
		maPadre=ma;
	}
	long lAnterior=0;
	float tMedio=0;
	long nEventos=0;
	
	boolean bIniciado=false;
	
	public drawView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		long lActual=System.currentTimeMillis();
		int act=event.getActionMasked();
		float eventX = event.getRawX();
	    float eventY = event.getRawY();
		if (act==event.ACTION_DOWN){
			maPadre.log("ACTION_DOWN");
			lAnterior=lActual;
			//if (!bIniciado) {
				maPadre.JNIinitFB();
				bIniciado=true;
			//}
		}else if (act==event.ACTION_UP){
			maPadre.log("ACTION_UP:"+"T="+tMedio  +"  X:" + eventX+" E="+nEventos+"  Y:" + eventY);
			maPadre.JNIfinishFB();
			lAnterior=0;
			
/*			TextView tX=(TextView) findViewById(R.id.txtX);
			TextView tY=(TextView) findViewById(R.id.txtY);
			tX.setText("T="+tMedio  +"  X:" + eventX);
			tY.setText("E="+nEventos+"  Y:" + eventY);
			View vTest=(View )findViewById(R.id.view1);*/
		}else if (act==event.ACTION_MOVE){
			//maPadre.log("ACTION_MOVE");
			if (lAnterior>0) {
				long tActo=lActual-lAnterior;
				if (tMedio==0) {
					tMedio=tActo;
				} else {
					tMedio=(tActo+(tMedio*nEventos))/(nEventos+1);	
				}
				nEventos++;
			}
			lAnterior=lActual;
			maPadre.JNIpaintFB((int)eventX,(int)eventY);
		}

		return true;
	}

}
